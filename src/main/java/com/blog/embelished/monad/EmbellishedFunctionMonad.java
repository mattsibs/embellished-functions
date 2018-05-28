package com.blog.embelished.monad;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Tuple;
import io.vavr.Tuple2;

public class EmbellishedFunctionMonad<INPUT, EMBELLISHMENT> {

    private final Tuple2<INPUT, EMBELLISHMENT> innerTuple;
    private final Function2<EMBELLISHMENT, EMBELLISHMENT, EMBELLISHMENT> embellishmentCombiner;

    public EmbellishedFunctionMonad(final Tuple2<INPUT, EMBELLISHMENT> innerTuple,
                                    final Function2<EMBELLISHMENT, EMBELLISHMENT, EMBELLISHMENT> embellishmentCombiner) {
        this.innerTuple = innerTuple;
        this.embellishmentCombiner = embellishmentCombiner;
    }

    public <NEW_RETURN> EmbellishedFunctionMonad<NEW_RETURN, EMBELLISHMENT> flatMap(
            final Function1<INPUT, Tuple2<NEW_RETURN, EMBELLISHMENT>> mapper) {

        Tuple2<NEW_RETURN, EMBELLISHMENT> newReturn = mapper.apply(innerTuple._1());

        return new EmbellishedFunctionMonad<>(
                Tuple.of(newReturn._1(), embellishmentCombiner.apply(innerTuple._2(), newReturn._2())),
                embellishmentCombiner);
    }

    public INPUT result() {
        return this.innerTuple._1();
    }

    public EMBELLISHMENT embellishment() {
        return this.innerTuple._2();
    }


}
