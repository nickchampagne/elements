package com.artemistechnica.elements;

import com.artemistechnica.Main;
import com.artemistechnica.models.Model;
import com.artemistechnica.models.UIComponent;
import com.artemistechnica.models.UIList;
import com.artemistechnica.models.UIString;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public interface Element {

    static <A extends CTX, B extends Model> Function<A, B> element(Function<A, B> ctx) {
        return (c) -> ctx.apply(Context.inc(c));
    }

    static <A extends CTX, B extends Model> Function<A, B> screen(Function<A, B> ctx) {
        return element(ctx);
    }

    static <A extends CTX> Function<A, UIList<A>> list(String direction, Function<A, ? extends UIComponent<A>>... listFns) {
        return (ctx) -> UIList.make(ctx, direction, Arrays.stream(listFns).map(fn -> element(fn).apply(ctx)).toList());
    }

    static <A extends CTX>Function<A, UIString<A>> string(String str) {
        return (ctx) -> UIString.make(ctx, str);
    }

    default <A extends CTX, B extends Model> List<Function<A, B>> screenListFns(List<B> elements) {
        return elements.stream().map(element -> (Function<A, B>) screen(ctx -> element)).toList();
    }

    default <B extends Model> Function<Main.App.AppContext, List<B>> screenList(List<B> elements) {
        return (ctx) -> elements;
    }

    default Function<Context, Component> component(Function<Context, Component> ctx) {
        return context -> {
            return Component.make(String.format("component {\n\t%s\n}", ctx.apply(context).value));
        };
    }

    default Function<Context, Component> header(Function<Context, Component> ctx) {
        return context -> {
            return Component.make(String.format("header {\n\t\t%s\n\t}", ctx.apply(context).value));
        };
    }

    default Function<Context, Component> block(Function<Context, Component> ctx) {
        return context -> {
            return Component.make(String.format("block {\n\t\t\t%s\n\t\t}", ctx.apply(context).value));
        };
    }

    default Function<Context, Component> span(List<Component> components, boolean horizontal) {
        return context -> {
            String direction = horizontal ? "\t" : "\n";
            String padding = horizontal ? "\t"  : "\t\t\t";
            String str = String.format("\t\t\t%s", String.join(direction + padding, components.stream().map(v -> v.value).toList()));
            Component cmp = components.stream().reduce(Component.pure(), (acc, c) -> Component.make(str));
            String tab = "\t".repeat(context.getDepth());
            return Component.make(String.format("\"span\": {\n%s%s\n\t\t}", tab, cmp.value));
        };
    }

    default Function<Context, Component> spanFn(List<Function<Context, Component>> componentFns) {
        return context -> {
            List<Component> components = componentFns.stream().map(fn -> fn.apply(context)).toList();
            String str = String.format("\t\t\t%s", String.join(",", components.stream().map(v -> v.value).toList()));
            Component cmp = components.stream().reduce(Component.pure(), (acc, c) -> Component.make(str));
            String tab = "\t".repeat(context.getDepth());
            return Component.make(String.format("\"span_"+context.getDepth() + "\": {\n%s%s\n\t\t}", tab, cmp.value));
        };
    }

    default <A extends CTX> Function<A, UIString<A>> UIString(A ctx, String value) {
        return context -> UIString.make(ctx, String.format("uiString {\n\t\t\t%s\n\t\t}", value));
    }

    interface CTX {
        int getDepth();
        <A extends CTX> A pure(int depth);
    }

    abstract class Context implements Element.CTX {
        protected int depth;
        public int getDepth() {
            return this.depth;
        }
        public void setDepth(int depth) {
            this.depth = depth;
        }
        public static <A extends CTX> A inc(A c) {
            return c.pure(c.getDepth() + 1);
        }
    }

    class Component implements Model {
        public final String value;

        public Component(String value) { this.value = value; }
        static Component pure() { return new Component(""); }
        public static Component make(String v) { return new Component(v); }

        @Override
        public String toString() { return value; }

        @Override
        public String getType() {
            return getClass().getSimpleName();
        }
    }
}
