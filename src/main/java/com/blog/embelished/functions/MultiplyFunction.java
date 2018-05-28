package com.blog.embelished.functions;

import io.vavr.Tuple;
import io.vavr.Tuple2;

public class MultiplyFunction implements EmbellishedFunction<Double, String, Double> {

    private final double multiplier;

    public MultiplyFunction(final double multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public Tuple2<Double, String> apply(final Double input) {
        double result = input * multiplier;
        return Tuple.of(
                result, String.format("Multiply %s applied to %s with result %s", multiplier, input, result));
    }
}
