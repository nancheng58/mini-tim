package net.Json;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;

import java.io.File;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Parser {
    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return Primitives.wrap(classOfT).cast(new Gson().fromJson(json, classOfT));
    }

    public static <T> T fromJson(String json, Type typeOf) {
        return new Gson().fromJson(json, typeOf);
    }

    public static String toJson(Object src) {
        return new Gson().toJson(src);
    }
}

