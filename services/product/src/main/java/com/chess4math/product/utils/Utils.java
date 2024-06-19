package com.chess4math.product.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


public final class Utils {

    private Utils() {
    }

    public static <T>URI withLocation(T resourceId) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{resourceId}").buildAndExpand(resourceId).toUri();
    }
}
