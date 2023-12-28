package com.artemistechnica.models;

import com.artemistechnica.Main;
import com.artemistechnica.elements.Element;

import java.util.List;

public interface UIComponent<A extends Element.CTX> extends Model {

    A getContext();
}
