/*
* MIT License
* 
* Copyright (c) 2022 Leonardo de Lima Oliveira
* 
* https://github.com/l3onardo-oliv3ira
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/


package com.github.filehandler4j.imp;

import com.github.filehandler4j.IFileInfoEvent;
import com.github.filehandler4j.IFileSlice;
import com.github.utils4j.IResetableIterator;
import com.github.utils4j.imp.Args;

public abstract class AbstractFileRageHandler<T extends IFileInfoEvent, R extends IFileSlice> extends AbstractFileHandler<T> {

  private IResetableIterator<R> iterator;
  
  public AbstractFileRageHandler(IResetableIterator<R> iterator) {
    this.setIterator(iterator);
  }
  
  protected final R nextSlice() {
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
  
  protected void setIterator(IResetableIterator<R> iterator) {
    Args.requireNonNull(iterator, "iterator is null");
    this.iterator = iterator;
  }
}
