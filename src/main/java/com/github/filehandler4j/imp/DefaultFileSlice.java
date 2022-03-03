package com.github.filehandler4j.imp;

import com.github.filehandler4j.IFileSlice;
import com.github.utils4j.imp.Args;

public class DefaultFileSlice implements IFileSlice {

  private final long start;
  private final long end;
  
  public DefaultFileSlice() {
    this(1, Integer.MAX_VALUE);
  }

  public DefaultFileSlice(long start, long end) {
    Args.requireTrue(start >= 0 && start <= end, "invalid arguments");
    this.start = start;
    this.end = end;
  }
  
  @Override
  public final long start() {
    return start;
  }

  @Override
  public final long end() {
    return end;
  }
}
