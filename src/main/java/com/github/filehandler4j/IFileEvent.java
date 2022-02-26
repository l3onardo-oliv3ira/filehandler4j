package com.github.filehandler4j;

import java.io.File;
import java.util.Optional;

public interface IFileEvent {
  String getMessage();

  Optional<File> getOutput();
}
