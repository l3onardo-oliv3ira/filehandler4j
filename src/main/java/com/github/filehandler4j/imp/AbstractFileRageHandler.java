package com.github.filehandler4j.imp;

import com.github.filehandler4j.IFileInfoEvent;
import com.github.filehandler4j.IFileRange;
import com.github.utils4j.imp.Args;

public abstract class AbstractFileRageHandler<T extends IFileInfoEvent> extends AbstractFileHandler<T> {

  private int iterator = 0;
  private final FileRange[] ranges;
  
  public AbstractFileRageHandler(FileRange ...ranges) {
    this.ranges = Args.requireNonEmpty(ranges, "ranges is empty");
  }
  
  @SuppressWarnings("unchecked")
  protected final <R extends IFileRange> R nextRange() {
    if (iterator == ranges.length)
      return null;
    FileRange next = ranges[iterator++];
    while(next == null && iterator < ranges.length)
      next = ranges[iterator++];
    return (R)next;
  }
  
  @Override
  public void reset() {
    iterator = 0;
    super.reset();
  }
  
}
