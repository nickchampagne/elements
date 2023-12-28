package com.artemistechnica.elements;

import com.artemistechnica.Main;
import com.artemistechnica.models.Model;
import com.artemistechnica.models.UIComponent;
import com.artemistechnica.models.UIList;
import com.artemistechnica.models.UIString;

import java.util.List;
import java.util.function.Function;

public interface Element {

    static <A extends CTX, B extends Model> Function<A, B> element(Function<A, B> ctx) {
        return (c) -> ctx.apply(Context.inc(c));
    }

    static <B extends Model> Function<Main.App.AppContext, B> screen(Function<Main.App.AppContext, B> ctx) {
        return element(ctx);
    }

    static <A extends CTX> Function<A, UIList> list(String direction, List<Function<A, ? extends UIComponent>> listFns) {
        return (ctx) -> UIList.make(ctx, direction, listFns.stream().map(fn -> element(fn).apply(ctx)).toList());
    }

    static Function<Main.App.AppContext, UIString> string(String str) {
        return (ctx) -> UIString.make(ctx, str);
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
