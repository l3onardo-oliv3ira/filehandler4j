package com.github.filehandler4j.imp;

import java.io.File;
import java.nio.file.Path;

import com.github.filehandler4j.IInputFile;
import com.github.utils4j.imp.Args;

public class FileWrapper implements IInputFile {

  private final File file;

  public FileWrapper(File file) {
    this.file = Args.requireNonNull(file, "file is null");
  }
  
  @Override
  public String getAbsolutePath() {
    return file.getAbsolutePath();
  }

  @Override
  public long length() {
    return file.length();
  }

  @Override
  public boolean exists() {
    return file.exists();
  }

  @Override
  public String getName() {
    return file.getName();
  }

  @Override
  public Path toPath() {
    return file.toPath();
  }
}
