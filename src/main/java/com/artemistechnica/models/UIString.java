package com.artemistechnica.models;

import com.artemistechnica.Main;
import com.artemistechnica.elements.Element;

public interface UIString extends UIComponent {
    String getValue();

    static <A extends Main.App.AppContext> UIString make(A ctx, String v) {
        return new UIString() {

            @Override
            public Element.CTX getContext() {
                return context;
            }

            private final A context = ctx;
            private final String value = v;

            @Override
            public String getType() {
                return "UIString";
            }
//
//            @Override
//            public <A extends Element.CTX> A getContext() {
//                return context;
//            }

            @Override
            public String getValue() {
                return value;
            }
        };
    }
}
