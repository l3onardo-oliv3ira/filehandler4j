package com.github.filehandler4j;

import java.util.function.Function;

import io.reactivex.Observable;

public interface IFileHandler<T extends IFileInfoEvent> extends Function<IInputDescriptor, Observable<T>> {

  void reset();
}
