package com.github.filehandler4j.imp.event;

import com.github.filehandler4j.IFileInfoEvent;

public class FileInfoEvent implements IFileInfoEvent {

  private final String message;

  public FileInfoEvent(String message) {
    this.message = message;
  }
  
  @Override
  public final String getMessage() {
    return message;
  }
  
  @Override
  public String toString() {
    return message;
  }
}
