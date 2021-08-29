package command;

import java.util.List;

/**
 * @author Hu yujing
 */
public abstract class BaseCommand {
  public String doExecute(List<String> args) {
    final boolean pass = checkArg(args);
    if (pass) {
      return execute(args);
    }
    return "Executor check args is fail!";
  }

  /**
   * @param args
   * @return
   */
  public abstract String execute(List<String> args);

  /**
   * @param args
   * @return
   */
  protected abstract boolean checkArg(List<String> args);
}
