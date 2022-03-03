package com.github.filehandler4j.imp;

import com.github.filehandler4j.IFileInfoEvent;
import com.github.filehandler4j.IFileSlice;
import com.github.utils4j.ResetableIterator;
import com.github.utils4j.imp.Args;

public abstract class AbstractFileRageHandler<T extends IFileInfoEvent, R extends IFileSlice> extends AbstractFileHandler<T> {

  private ResetableIterator<R> iterator;
  
  public AbstractFileRageHandler(ResetableIterator<R> iterator) {
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
  
  protected void setIterator(ResetableIterator<R> iterator) {
    Args.requireNonNull(iterator, "iterator is null");
    this.iterator = iterator;
  }
}
