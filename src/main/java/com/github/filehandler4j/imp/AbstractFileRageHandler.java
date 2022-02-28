package com.github.filehandler4j.imp;

import com.github.filehandler4j.IFileInfoEvent;
import com.github.filehandler4j.IFileRange;
import com.github.filehandler4j.IIterator;
import com.github.utils4j.imp.Args;

public abstract class AbstractFileRageHandler<T extends IFileInfoEvent, R extends IFileRange> extends AbstractFileHandler<T> {

  private IIterator<R> iterator;
  
  public AbstractFileRageHandler(IIterator<R> iterator) {
    this.setIterator(iterator);
  }
  
  protected final R next() {
    R next = null;
    while(iterator.hasNext() && (next = iterator.next()) == null)
      ;
    return next;
  }
  
  @Override
  public void reset() {
    iterator.reset();
    super.reset();
  }
  
  protected void setIterator(IIterator<R> iterator) {
    Args.requireNonNull(iterator, "iterator is null");
    this.iterator = iterator;
  }
}
