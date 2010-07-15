package de.chkal.jsf.maven;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.maven.shared.runtime.DefaultMavenRuntime;
import org.apache.maven.shared.runtime.MavenProjectProperties;
import org.apache.maven.shared.runtime.MavenRuntime;
import org.apache.maven.shared.runtime.MavenRuntimeException;

public class JSFMavenProject {

  private final Logger logger = Logger.getLogger(this.getClass().getName());
  
  private final Map versions = new HashMap();
  
  public JSFMavenProject() {
    
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    
    MavenRuntime mavenRuntime = new DefaultMavenRuntime();

    try {

      Iterator iterator = mavenRuntime.getProjectsProperties(classLoader).iterator();
      while(iterator.hasNext()) {
        MavenProjectProperties mavenProjectProperties = (MavenProjectProperties) iterator.next();
        String key = mavenProjectProperties.getGroupId()+":"+mavenProjectProperties.getArtifactId();
        String version = mavenProjectProperties.getVersion();
        versions.put(key, version);
      }
      
      
    } catch (MavenRuntimeException e) {
      logger.warning("Failed to get maven project properties: "+e.getMessage());
    }
  }

  public Map getVersions() {
    return versions;
  }
  
}
