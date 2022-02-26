package com.github.filehandler4j.imp;

import java.io.File;
import java.util.Optional;

import com.github.filehandler4j.IFileEvent;

public class FileEvent implements IFileEvent {

  private final String message;
  private final Optional<File> output;

  public FileEvent(String message) {
    this(message, null);
  }

  public FileEvent(String message, File output) {
    this.message = message;
    this.output = Optional.ofNullable(output);
  }
  
  @Override
  public final String getMessage() {
    return message;
  }
  
  @Override
  public final Optional<File> getOutput() {
    return output;
  }
}
