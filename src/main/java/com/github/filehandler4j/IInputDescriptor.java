package com.github.filehandler4j;

public interface IInputDescriptor extends IOutputResolver {
  Iterable<IInputFile> getInputPdfs();
}
