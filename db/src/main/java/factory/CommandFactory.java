package factory;

import annotation.Command.Command;
import command.BaseCommand;
import entites.RedisDb;
import exception.CommandNotFoundException;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author Hu yujing
 */
public class CommandFactory {

  private Map<String, BaseCommand> commandsMap;
  private RedisDb redisDb;
  private Map<String, Class<BaseCommand>> commandClassesMap;

  public CommandFactory(RedisDb redisDb) {
    this.redisDb = redisDb;
    init();
  }

  public void init() {
    this.commandsMap = new HashMap<>(8);
    loadClasses();
  }

  private void loadClasses() {
    commandClassesMap = CommandFactory.findClassByDirectory("command.impl", BaseCommand.class.getResource("").getPath() + "/impl");
  }

  public BaseCommand get(String commandName) throws CommandNotFoundException {
    final String lowerCommandName = commandName.toLowerCase(Locale.ROOT);
    final BaseCommand baseCommand = commandsMap.get(lowerCommandName);
    if (baseCommand == null) {
      final BaseCommand newCommand = tryNewCommand(lowerCommandName);
      if (newCommand == null) {
        throw new CommandNotFoundException("not found command:" + commandName);
      }
      commandsMap.put(lowerCommandName, newCommand);
      return newCommand;
    }
    return baseCommand;
  }

  private BaseCommand tryNewCommand(String commandName) {
    final Class<BaseCommand> baseCommandClass = commandClassesMap.get(commandName);
    if (baseCommandClass != null) {
      try {
        final Constructor<BaseCommand> commandConstructor = baseCommandClass.getConstructor(RedisDb.class);
        return commandConstructor.newInstance(redisDb);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  /**
   * 以文件的形式来获取包下的所有Class
   *
   * @param packageName 包名
   * @param packagePath 查找路径
   */
  public static Map<String, Class<BaseCommand>> findClassByDirectory(String packageName, String packagePath) {
    // 获取此包的目录 建立一个File
    File dir = new File(packagePath);
    if (!dir.exists() || !dir.isDirectory()) {
      return new HashMap<>(0);
    }

    File[] dirs = dir.listFiles();
    Map<String, Class<BaseCommand>> classesMap = new HashMap<>(8);
    // 循环所有文件
    assert dirs != null;
    for (File file : dirs) {
      // 如果是目录 则继续扫描
      if (file.isDirectory()) {
        classesMap.putAll(findClassByDirectory(packageName + "." + file.getName(),
          file.getAbsolutePath()));
      } else if (file.getName().endsWith(".class")) {
        // 如果是java类文件，去掉后面的.class 只留下类名
        String className = file.getName().substring(0, file.getName().length() - 6);
        try {
          @SuppressWarnings("unchecked") final Class<BaseCommand> aClass = (Class<BaseCommand>) Class.forName(packageName + '.' + className);
          final Command annotation = aClass.getAnnotation(Command.class);
          if (annotation == null) {
            throw new RuntimeException("please set @Command in Command class!");
          }
          final String[] value = annotation.value();
          final String commandKey = value[0];
          classesMap.put(commandKey, aClass);
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
      }
    }
    return classesMap;
  }

}
