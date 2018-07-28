package com.common.http.okhttp.utils;

public class Exceptions {

    public static void illegalArgument(String msg, Object... params) {
        throw new IllegalArgumentException(String.format(msg, params));
    }

}
