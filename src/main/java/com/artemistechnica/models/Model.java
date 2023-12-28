package com.artemistechnica.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes({
        @JsonSubTypes.Type(value = UIString.class, name = "UIString"),
        @JsonSubTypes.Type(value = UIList.class, name = "UIList")
})
public interface Model {

    String getType();

}
