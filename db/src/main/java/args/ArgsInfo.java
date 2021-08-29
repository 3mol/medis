package args;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArgsInfo {
  private String commandName;
  private List<String> args;

  public ArgsInfo(String shellCommandLine) {

    final String[] args = shellCommandLine.split(" ");
    final List<String> list = Arrays.stream(args).collect(Collectors.toList());
    this.commandName = list.get(0);
    this.args = list.subList(1, list.size());
  }

  public String getCommandName() {
    return commandName;
  }

  public List<String> getArgs() {
    return args;
  }
}
