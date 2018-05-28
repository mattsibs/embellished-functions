package com.blog.embelished.functions;

import io.vavr.Tuple2;

public class ThrowingFunction<T, R> implements EmbellishedFunction<T, String, R> {

    @Override
    public Tuple2<R, String> apply(final T input) {
        throw new RuntimeException("OOPS");
    }
}
