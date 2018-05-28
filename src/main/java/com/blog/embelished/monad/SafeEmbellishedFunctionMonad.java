package com.blog.embelished.monad;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Option;

public class SafeEmbellishedFunctionMonad<INPUT, EMBELLISHMENT> {

    private final Exception error;
    private final Tuple2<INPUT, EMBELLISHMENT> innerTuple;
    private final Function2<EMBELLISHMENT, EMBELLISHMENT, EMBELLISHMENT> embellishmentCombiner;

    private SafeEmbellishedFunctionMonad(final Tuple2<INPUT, EMBELLISHMENT> innerTuple,
                                         final Exception error,
                                         final Function2<EMBELLISHMENT, EMBELLISHMENT, EMBELLISHMENT> embellishmentCombiner) {
        this.error = error;
        this.innerTuple = innerTuple;
        this.embellishmentCombiner = embellishmentCombiner;
    }

    public SafeEmbellishedFunctionMonad(final Tuple2<INPUT, EMBELLISHMENT> initial,
                                        final Function2<EMBELLISHMENT, EMBELLISHMENT, EMBELLISHMENT> embellishmentCombiner) {
        this.innerTuple = initial;
        this.error = null;
        this.embellishmentCombiner = embellishmentCombiner;
    }

    public <NEW_RETURN> SafeEmbellishedFunctionMonad<NEW_RETURN, EMBELLISHMENT> flatMap(
            final Function1<INPUT, Tuple2<NEW_RETURN, EMBELLISHMENT>> mapper) {

        if (error != null) {
            return handleError(error);
        }

        try {

            Tuple2<NEW_RETURN, EMBELLISHMENT> newReturn = mapper.apply(innerTuple._1());
            return new SafeEmbellishedFunctionMonad<>(
                    Tuple.of(newReturn._1(), embellishmentCombiner.apply(innerTuple._2(), newReturn._2())),
                    embellishmentCombiner);

        } catch (Exception e) {
            return handleError(e);
        }
    }

    private <NEW_RETURN> SafeEmbellishedFunctionMonad<NEW_RETURN, EMBELLISHMENT> handleError(final Exception exception) {
        return new SafeEmbellishedFunctionMonad<>(
                Tuple.of(null, innerTuple._2()), exception, embellishmentCombiner);
    }

    public Option<INPUT> result() {
        return Option.when(error == null, innerTuple._1());
    }

    public EMBELLISHMENT embellishment() {
        return innerTuple._2();
    }

    public Option<Exception> failure() {
        return Option.when(error != null, error);
    }

}
