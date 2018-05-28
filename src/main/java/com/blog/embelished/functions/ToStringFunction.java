package com.blog.embelished.functions;

import io.vavr.Tuple;
import io.vavr.Tuple2;

public class ToStringFunction<T> implements EmbellishedFunction<T, String, String> {
    @Override
    public Tuple2<String, String> apply(final T input) {
        return Tuple.of(input.toString(), "Converted " + input + " to a string");
    }
}
