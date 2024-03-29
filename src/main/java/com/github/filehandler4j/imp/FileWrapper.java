/*
* MIT License
* 
* Copyright (c) 2022 Leonardo de Lima Oliveira
* 
* https://github.com/l3onardo-oliv3ira
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/


package com.github.filehandler4j.imp;

import java.io.File;
import java.nio.file.Path;

import com.github.filehandler4j.IInputFile;
import com.github.utils4j.imp.Args;
import com.github.utils4j.imp.Directory;

public class FileWrapper implements IInputFile {

  private final File file;

  public FileWrapper(File file) {
    this.file = Args.requireNonNull(file, "file is null");
  }
  
  @Override
  public String getAbsolutePath() {
    return Directory.stringPath(file);
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

  @Override
  public final String getShortName() {
    String name = file.getName();
    int dot = name.lastIndexOf('.');
    return name.substring(0, dot > 0 ? dot : name.length());
  }
}
