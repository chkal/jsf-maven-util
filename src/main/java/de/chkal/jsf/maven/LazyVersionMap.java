package de.chkal.jsf.maven;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class LazyVersionMap implements Map<String, String> {
  
  private final static Logger log = Logger.getLogger(LazyVersionMap.class.getName());

  private final ArtifactVersionResolver resolver;

  private final Map<String,String> cache = new HashMap<String, String>();
  
  public LazyVersionMap(ArtifactVersionResolver resolver) {
    this.resolver = resolver;
  }
  
  public String get(Object key) {

    // check for null argument
    if(key == null) {
      return null;
    }
    
    // the key is always a string
    String groupAndArtifact = key.toString();
    log.fine("Processing artifact: "+groupAndArtifact);
    
    // check for an existing result
    if(cache.containsKey(groupAndArtifact)) {
      String cachedVersion = cache.get(groupAndArtifact);
      log.fine("Found cached result: "+cachedVersion);
      return cachedVersion;
    }

    // resolve and cache result
    String version = resolver.resolveVersion(groupAndArtifact);
    log.fine("Version resolved to: "+version);
    cache.put(groupAndArtifact, version);
    return version;
  
  }

  public void clear() {
    throw new UnsupportedOperationException();
  }

  public boolean containsKey(Object key) {
    return get(key) != null;
  }

  public boolean containsValue(Object value) {
    return cache.containsValue(value);
  }

  public Set<java.util.Map.Entry<String, String>> entrySet() {
    return cache.entrySet();
  }

  public boolean isEmpty() {
    return cache.isEmpty();
  }

  public Set<String> keySet() {
    return cache.keySet();
  }

  public String put(String key, String value) {
    throw new UnsupportedOperationException();
  }

  public void putAll(Map<? extends String, ? extends String> m) {
    throw new UnsupportedOperationException();
  }

  public String remove(Object key) {
    throw new UnsupportedOperationException();
  }

  public int size() {
    return cache.size();
  }

  public Collection<String> values() {
    return cache.values();
  }
  
}
