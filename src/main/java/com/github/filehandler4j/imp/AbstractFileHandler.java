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
