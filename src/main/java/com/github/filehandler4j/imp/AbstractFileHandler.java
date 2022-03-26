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

import java.io.File;

import com.github.filehandler4j.IFileHandler;
import com.github.filehandler4j.IFileInfoEvent;
import com.github.filehandler4j.IInputDescriptor;
import com.github.filehandler4j.IInputFile;
import com.github.filehandler4j.IOutputResolver;

import io.reactivex.Emitter;
import io.reactivex.Observable;

public abstract class AbstractFileHandler<T extends IFileInfoEvent> implements IFileHandler<T> {

  private IOutputResolver resolver;
  
  public AbstractFileHandler() {
  }  
  
  protected void checkInterrupted() throws InterruptedException {
    if (Thread.currentThread().isInterrupted()) {
      throw new InterruptedException();
    }
  }
  
  @Override
  public final Observable<T> apply(IInputDescriptor desc) {
    this.resolver = desc;
    return Observable.create((emitter) -> {
      try {
        beforeHandle(emitter);
        for(IInputFile file: desc.getInputPdfs()) {
          checkInterrupted();
          handle(file, emitter);
        };        
        afterHandle(emitter);
        emitter.onComplete();
      }catch(Throwable e) {
        handleError(e);
        emitter.onError(e);
      }finally {
        reset();
      }
    });
  }
  
  @Override
  public void reset() {
    resolver = null;
  }
  
  protected final File resolveOutput(String fileName) {
    return resolver.resolveOutput(fileName);
  }

  protected void beforeHandle(Emitter<T> emitter) throws Exception { }
  
  protected void afterHandle(Emitter<T> emitter) throws Exception { }

  protected void handleError(Throwable e) { }
  
  protected abstract void handle(IInputFile file, Emitter<T> emitter) throws Exception;    
}
