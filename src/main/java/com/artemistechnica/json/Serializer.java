package com.artemistechnica.json;

import com.artemistechnica.models.Model;
import com.artemistechnica.models.UIList;
import com.artemistechnica.models.UIString;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Serializer {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.addMixIn(Model.class, UIString.class);
        mapper.addMixIn(Model.class, UIList.class);
    }

    public static String serialize(Model model) {
        try {
            return mapper.writeValueAsString(model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <A extends Model> String prettyPrint(A model) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <A extends Model> String prettyPrint(List<A> model) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
