package command.impl;

import annotation.Command.Command;
import command.BaseCommand;
import entites.RedisDb;

import java.util.List;
import java.util.Map;

/**
 * @author Hu yujing
 */
@Command("exit")
public class ExitCommand extends BaseCommand {

  Map<Object, Object> dict;
  RedisDb redisDb;

  public ExitCommand(RedisDb redisDb) {
    this.redisDb = redisDb;
    this.dict = redisDb.getDict();
  }

  @Override
  public String execute(List<String> args) {
    System.out.println("bye bye.");
    persist();
    System.exit(0);
    return "";
  }

  private void persist() {
    // todo
  }

  @Override
  protected boolean checkArg(List<String> args) {
    return args.size() == 0;
  }
}
