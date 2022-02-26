package com.github.filehandler4j.imp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.filehandler4j.IInputDescriptor;
import com.github.utils4j.imp.Args;
import com.github.utils4j.imp.Strings;

public class InputDescriptor implements IInputDescriptor {
  
  private List<File> inputs;
  private Path outputPath;
  private String namePrefix;
  private String nameSuffix;
  
  private InputDescriptor() {}

  @Override
  public Iterable<File> getInputPdfs() {
    return inputs;
  }

  @Override
  public File resolveOutput(String fileName) {
    return outputPath.resolve(namePrefix + fileName + nameSuffix + ".pdf").toFile();
  }

  public static class Builder {
    private List<File> inputs = new ArrayList<>(2);
    private String nameSuffix = Strings.empty();
    private String namePrefix = Strings.empty();
  
    private Path output = Paths.get(System.getProperty("java.io.tmpdir"));
    
    public Builder add(File input) {
      Args.requireExists(input, "input does not exists");
      inputs.add(input);
      return this;
    }
    
    public Builder nameSuffix(String suffix) {
      Args.requireText(suffix, "suffix is empty");
      nameSuffix = Strings.trim(suffix);
      return this;
    }
    
    public Builder namePrefix(String prefix) {
      Args.requireText(prefix, "prefix is empty");
      namePrefix = Strings.trim(prefix);
      return this;
    }
    
    public Builder output(Path output) {
      Args.requireNonNull(output, "output is null");
      this.output = output;
      return this;
    }
    
    public IInputDescriptor build() throws IOException {
      InputDescriptor desc = new InputDescriptor();
      desc.inputs = Collections.unmodifiableList(inputs);
      desc.namePrefix = namePrefix;
      desc.nameSuffix = nameSuffix;
      desc.outputPath = output;
      File outputFile = output.toFile();
      if (!outputFile.exists()) {
        if (!outputFile.mkdirs()) {
          throw new IOException("Unabled to create output dir " + outputFile.getAbsolutePath());
        }
      }
      return desc;
    }
  }
}
