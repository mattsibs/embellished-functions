package com.blog.embelished.functions;

import io.vavr.Tuple;
import io.vavr.Tuple2;

public class ModuloFunction implements EmbellishedFunction<Double, String, Double> {

    private final int modulo;

    public ModuloFunction(final int modulo) {
        this.modulo = modulo;
    }

    public Tuple2<Double, String> apply(final Double input) {
        double result = input % modulo;
        return Tuple.of(
                result, String.format("Modulo %s applied to %s with result %s", modulo, input, result));
    }
}
