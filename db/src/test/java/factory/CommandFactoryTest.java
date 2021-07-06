package factory;

import command.BaseCommand;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CommandFactoryTest {
  @Test
  public void c1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    final Map<String, Class<BaseCommand>> map = CommandFactory.findClassByDirectory("command.impl", BaseCommand.class.getResource("").getPath() + "/impl");
    assertEquals(2, map.size());
    final Class<BaseCommand> setCommand = map.get("set");

    final Constructor<?> constructor = setCommand.getConstructor(Map.class);
    final Object o = constructor.newInstance(new HashMap<>());
    System.out.println(o);
  }

}