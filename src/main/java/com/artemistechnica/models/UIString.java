package com.artemistechnica.models;

import com.artemistechnica.Main;
import com.artemistechnica.elements.Element;

public interface UIString<A extends Element.CTX> extends UIComponent<A> {
    String getValue();

    static <A extends Element.CTX, B extends UIComponent<A>> UIString<A> make(A ctx, String v) {
        return new UIString<>() {

            private final A context     = ctx;
            private final String value  = v;

            @Override
            public String getType() {
                return "UIString";
            }

            @Override
            public String getValue() {
                return value;
            }

            @Override
            public A getContext() {
                return context;
            }
        };
    }
}
