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

  public Map<Object, Object> getDict() {
    return dict;
  }

  /**
   * 数据库的键空间
   */
  private Map<Object, Object> dict;
}
