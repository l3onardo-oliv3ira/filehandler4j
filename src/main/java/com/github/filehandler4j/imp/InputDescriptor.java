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
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.filehandler4j.IInputDescriptor;
import com.github.filehandler4j.IInputFile;
import com.github.utils4j.imp.Args;
import com.github.utils4j.imp.Strings;

public abstract class InputDescriptor implements IInputDescriptor {
  
  protected List<IInputFile> inputs;
  protected Path outputPath;
  protected String namePrefix;
  protected String nameSuffix;
  protected String extension;
  
  protected InputDescriptor() {
  }

  @Override
  public Iterable<IInputFile> getInputFiles() {
    return inputs;
  }

  @Override
  public File resolveOutput(String fileName) {
    return outputPath.resolve(namePrefix + fileName + nameSuffix + extension).toFile();
  }

  public static abstract class Builder {
    protected List<IInputFile> inputs = new ArrayList<>(2);
    protected String nameSuffix = Strings.empty();
    protected String namePrefix = Strings.empty();
    protected String extension;
  
    protected Path output = Paths.get(System.getProperty("java.io.tmpdir"));
    
    public Builder(String extension) {
      this.extension = Strings.trim(extension);
    }
    
    public Builder add(File input) {
      return add(new FileWrapper(input));
    }
    
    public Builder nameSuffix(String suffix) {
      Args.requireNonNull(suffix, "suffix is empty");
      nameSuffix = Strings.trim(suffix);
      return this;
    }
    
    public Builder namePrefix(String prefix) {
      Args.requireNonNull(prefix, "prefix is empty");
      namePrefix = Strings.trim(prefix);
      return this;
    }
    
    public Builder output(Path folder) {
      Args.requireNonNull(folder, "folder is null");
      this.output = folder;
      return this;
    }
    
    public Builder add(IInputFile file) {
      Args.requireTrue(file.exists(), "input does not exists");
      inputs.add(file);
      return this;
    }
    
    @SuppressWarnings("unchecked")
    public <T extends IInputDescriptor> T build() throws IOException {
      InputDescriptor desc = createDescriptor();
      desc.inputs = Collections.unmodifiableList(inputs);
      desc.namePrefix = namePrefix;
      desc.nameSuffix = nameSuffix;
      desc.outputPath = output;
      desc.extension = extension;
      File outputFile = output.toFile();
      if (!outputFile.exists()) {
        if (!outputFile.mkdirs()) {
          throw new IOException("Unabled to create output dir " + outputFile.getCanonicalPath());
        }
      }
      return (T)desc;
    }

    protected abstract InputDescriptor createDescriptor();
  }
}
