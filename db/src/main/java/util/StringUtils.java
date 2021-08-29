package util;

import java.util.List;

/**
 * @author Hu yujing
 */
public class StringUtils {


  public static boolean allNotEmpty(List<String> list) {
    return !anyEmpty(list);
  }


  public static boolean anyEmpty(List<String> list) {
    if (list.isEmpty()) {
      return false;
    }
    return list.stream().anyMatch(StringUtils::isEmpty);
  }

  private static boolean isEmpty(String s) {
    return s == null || s.isEmpty();
  }
}
