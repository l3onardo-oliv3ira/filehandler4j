package com.github.filehandler4j;

import java.nio.file.Path;

public interface IInputFile {
  String getAbsolutePath();

  String getName();
  
  Path toPath();
  
  long length();

  boolean exists();
}
