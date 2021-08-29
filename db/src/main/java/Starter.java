import args.ArgsInfo;
import command.BaseCommand;
import entites.RedisDb;
import exception.CommandNotFoundException;
import factory.CommandFactory;

import java.util.List;

/**
 * @author Hu yujing
 */
public class Starter {
  private RedisDb redisDb;
  private CommandFactory commandsFactory;

  public Starter() {
    init();
  }

  private void init() {
    initDb();
    initCommandFactory();
  }

  private void initDb() {
    redisDb = new RedisDb();
  }

  private void initCommandFactory() {
    this.commandsFactory = new CommandFactory(redisDb);
  }

  public String tryExecute(String inputLine) throws CommandNotFoundException {
    final ArgsInfo argsInfo = getCommandInfo(inputLine);
    final String commandName = argsInfo.getCommandName();
    final List<String> args = argsInfo.getArgs();
    final BaseCommand baseCommand = commandsFactory.get(commandName);
    return baseCommand.doExecute(args);
  }

  private ArgsInfo getCommandInfo(String inputLine) {
    return new ArgsInfo(inputLine);
  }
}
