package entites;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hu yujing
 */
public class RedisDb {
  public RedisDb() {
    this.dict = new HashMap<>();
  }

  /**
   * 数据库的键空间
   */
  private final Map<Object, Object> dict;

  public Object put(Object key, Object value) {
    return dict.put(key, value);
  }

  public Object get(Object key) {
    return dict.get(key);
  }

  public Object remove(Object key) {
    return dict.remove(key);
  }
}
