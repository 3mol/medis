package util;

import java.util.List;

/**
 * @author Hu yujing
 */
public class StringUtils {
  public static boolean isEmpty(String s) {
    return s == null || s.isEmpty();
  }

  public static boolean isEmpty(List<String> list) {
    if (list.isEmpty()) {
      return false;
    }
    return list.stream().anyMatch(StringUtils::isEmpty);
  }
}
