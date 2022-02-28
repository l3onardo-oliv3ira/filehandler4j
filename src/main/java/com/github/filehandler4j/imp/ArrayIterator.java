package com.github.filehandler4j.imp;

import com.github.filehandler4j.IIterator;
import com.github.utils4j.imp.Args;

public class ArrayIterator<T> implements IIterator<T> {
  private int current = 0;
  private T[] array;
  
  public ArrayIterator(T[] array) {
    this.array = Args.requireNonNull(array, "array is null");
  }
  
  @Override
  public final boolean hasNext() {
    return current < array.length;
  }

  @Override
  public final T next() {
    if (current == array.length)
      return null;
    return array[current++];
  }

  @Override
  public final void reset() {
    current = 0;
  }
}
