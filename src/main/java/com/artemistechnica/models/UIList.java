package com.artemistechnica.models;

import com.artemistechnica.Main;
import com.artemistechnica.elements.Element;

import java.util.List;

public interface UIList extends UIComponent {

    String getOrientation();

    List<? extends UIComponent> getValue();

//    List<UIComponent> getValue();
     static <A extends UIComponent, B extends Element.CTX> UIList make(B ctx, String direction, List<A> v) {
        return new UIList() {
            private final B context = ctx;

            private final String orientation = direction;
            private final List<A> value = v;

            @Override
            public String getType() {
                return "UIList";
            }

            @Override
            public B getContext() {
                return context;
            }

            @Override
            public String getOrientation() {
                return orientation;
            }

            @Override
            public List<A> getValue() {
                return value;
            }
        };
    }
}
