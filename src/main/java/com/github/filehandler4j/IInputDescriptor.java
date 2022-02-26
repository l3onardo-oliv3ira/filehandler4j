package com.github.filehandler4j;

import java.io.File;

public interface IInputDescriptor extends IOutputResolver {
  Iterable<File> getInputPdfs();
}
