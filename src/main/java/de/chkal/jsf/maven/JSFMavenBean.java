package de.chkal.jsf.maven;

import java.util.Map;

public class JSFMavenBean {
  
  private final Map<String,String> version = new LazyVersionMap( new ArtifactVersionResolver() );
  
  public Map<String,String> getVersion() {
    return version;
  }
  
}
