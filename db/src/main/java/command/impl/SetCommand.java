package command.impl;

import annotation.Command;
import command.BaseCommand;
import entites.RedisDb;

import java.util.List;
import java.util.Map;

/**
 * @author Hu yujing
 */
@Command("set")
public class SetCommand extends BaseCommand {

  Map<Object, Object> dict;
  RedisDb redisDb;

  public SetCommand(RedisDb redisDb) {
    this.redisDb = redisDb;
    this.dict = redisDb.getDict();
  }

  @Override
  public String execute(List<String> args) {
    final String key = args.get(0);
    final String val = args.get(1);
    dict.put(key, val);
    return "OK";
  }

  @Override
  protected boolean checkArg(List<String> args) {
    if (args.size() != 2) {
      return false;
    }
    return args.get(0) != null && !"".equals(args.get(0));
  }
}
