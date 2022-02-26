package com.github.filehandler4j.imp.event;

import java.io.File;

import com.github.filehandler4j.IFileOutputEvent;
import com.github.utils4j.imp.Args;

public class FileOutputEvent implements IFileOutputEvent {

  private final String message;
  private final File output;

  public FileOutputEvent(String message, File output) {
    this.message = message;
    this.output = Args.requireNonNull(output, "output is null");
  }
  
  @Override
  public final String getMessage() {
    return message;
  }
  
  @Override
  public final File getOutput() {
    return output;
  }
  
  @Override
  public String toString() {
    return getMessage() + " file: " + output.toString();
  }
}
