package com.example.sweet.util;

import java.util.Optional;

public class FunctionUtil {

    private static <T> T findOrThrow(Optional<T> optional, String message) {
        return optional.orElseThrow(() -> new IllegalArgumentException(message));
    }

}
