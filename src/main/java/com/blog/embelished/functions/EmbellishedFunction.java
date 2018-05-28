package com.blog.embelished.functions;

import io.vavr.Function1;
import io.vavr.Tuple2;

public interface EmbellishedFunction<T, LOG, R> extends Function1<T, Tuple2<R, LOG>> {

}
