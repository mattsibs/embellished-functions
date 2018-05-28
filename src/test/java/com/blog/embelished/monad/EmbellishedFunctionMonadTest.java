package com.blog.embelished.monad;


import com.blog.embelished.functions.ModuloFunction;
import com.blog.embelished.functions.MultiplyFunction;
import com.blog.embelished.functions.ToStringFunction;
import io.vavr.Tuple;
import org.assertj.core.data.Offset;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmbellishedFunctionMonadTest {
    private final ModuloFunction modulo2 = new ModuloFunction(2);
    private final MultiplyFunction multiplyFunction = new MultiplyFunction(2);
    private final ToStringFunction<Double> toStringFunction = new ToStringFunction<>();
    @Test
    public void flatMap_returnsEmbellishedMessage() throws Exception {
        LoggingMonad<Double> loggingMonad = new LoggingMonad<>(1.4);

        String result = loggingMonad.flatMap(multiplyFunction)
                .flatMap(modulo2)
                .flatMap(multiplyFunction)
                .flatMap(multiplyFunction)
                .flatMap(modulo2)
                .embellishment();

        assertThat(result).isEqualTo("\n" +
                "Multiply 2.0 applied to 1.4 with result 2.8\n" +
                "Modulo 2 applied to 2.8 with result 0.7999999999999998\n" +
                "Multiply 2.0 applied to 0.7999999999999998 with result 1.5999999999999996\n" +
                "Multiply 2.0 applied to 1.5999999999999996 with result 3.1999999999999993\n" +
                "Modulo 2 applied to 3.1999999999999993 with result 1.1999999999999993");

    }

    @Test
    public void flatMap_returnsResult() throws Exception {
        LoggingMonad<Double> loggingMonad = new LoggingMonad<>(1.4);

        Double result = loggingMonad.flatMap(multiplyFunction)
                .flatMap(modulo2)
                .result();

        assertThat(result).isCloseTo(0.8, Offset.offset(0.1));

    }

    @Test
    public void flatMap_MappingToDifferentType_returnsResult() throws Exception {
        LoggingMonad<Double> loggingMonad = new LoggingMonad<>(1.4);

        String result = loggingMonad.flatMap(multiplyFunction)
                .flatMap(modulo2)
                .flatMap(toStringFunction)
                .result();

        assertThat(Double.valueOf(result)).isCloseTo(0.8, Offset.offset(0.1));

    }

    private static class LoggingMonad<T> extends EmbellishedFunctionMonad<T, String> {

        private LoggingMonad(final T initial) {
            super(Tuple.of(initial, ""), (log1, log2) -> log1 + "\n" + log2);
        }
    }
}