package command.impl;

import annotation.Command;
import command.BaseCommand;
import entites.RedisDb;
import util.StringUtils;

import java.util.List;
import java.util.Map;

import static util.StringUtils.allNotEmpty;

/**
 * @author Hu yujing
 */
@Command("rpush")
public class RPushCommand extends BaseCommand {

  Map<Object, Object> dict;
  RedisDb redisDb;

  public RPushCommand(RedisDb redisDb) {
    this.redisDb = redisDb;
    this.dict = redisDb.getDict();
  }

  @Override
  public String execute(List<String> args) {
    final String key = args.get(0);
    final List<String> list = args.subList(1, args.size());
    dict.put(key, list);
    return "OK";
  }

  @Override
  protected boolean checkArg(List<String> args) {
    return args.size() >= 2 && !allNotEmpty(args);
  }
}
