import java.lang.annotation.*;

/**
 * @author Hu yujing
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
  String[] value();
}
