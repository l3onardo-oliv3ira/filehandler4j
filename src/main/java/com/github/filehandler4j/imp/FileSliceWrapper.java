package com.github.filehandler4j.imp;

import com.github.filehandler4j.IFileSlice;
import com.github.utils4j.imp.Args;

public class FileSliceWrapper implements IFileSlice {

  protected IFileSlice slice;
  
  protected FileSliceWrapper(IFileSlice slice) {
    setSlice(slice);
  }

  @Override
  public long start() {
    return slice.start();
  }

  @Override
  public long end() {
    return slice.start();
  }
  
  @SuppressWarnings("unchecked")
  protected final <T extends IFileSlice> T getSlice() {
    return (T)slice;
  }
  
  protected final void setSlice(IFileSlice slice) {
    this.slice = Args.requireNonNull(slice, "slice is null");
  }
}
