package com.blog.embelished;

import com.blog.embelished.functions.ModuloFunction;
import com.blog.embelished.functions.MultiplyFunction;
import com.blog.embelished.functions.ToStringFunction;
import com.blog.embelished.monad.SafeEmbellishedFunctionMonad;
import io.vavr.Function2;
import io.vavr.Tuple;
import io.vavr.control.Option;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final ModuloFunction modulo2 = new ModuloFunction(2);
    private static final MultiplyFunction multiplyFunction = new MultiplyFunction(2);
    private static final ToStringFunction<Double> toStringFunction = new ToStringFunction<>();


    public static void main(final String... args) {
    }

}
