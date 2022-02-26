package com.github.filehandler4j;

import java.io.File;

public interface IFileOutputEvent extends IFileInfoEvent {
  File getOutput();
}
