package command.impl;

import annotation.Command;
import command.BaseCommand;
import entites.RedisDb;

import java.util.List;
import java.util.Map;

/**
 * @author Hu yujing
 */
@Command("get")
public class GetCommand extends BaseCommand {

  RedisDb redisDb;

  public GetCommand(RedisDb redisDb) {
    this.redisDb = redisDb;
  }

  @Override
  public String execute(List<String> args) {
    final Object o = redisDb.get(args.get(0));
    if (o == null) {
      return "(nil)";
    }
    return o.toString();
  }

  @Override
  protected boolean checkArg(List<String> args) {
    if (args.size() != 1) {
      return false;
    }
    return args.get(0) != null && !"".equals(args.get(0));
  }
}
