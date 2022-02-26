package com.github.filehandler4j;

import java.io.File;

public interface IOutputResolver {
  File resolveOutput(String fileName);
}
