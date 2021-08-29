import exception.CommandNotFoundException;

import java.util.Locale;
import java.util.Scanner;

/**
 * @author Hu yujing
 */
public class Main {
  private Scanner sc;
  private Starter starter;

  public static void main(String[] args) {
    System.out.println("Starting...");
    final Main main = new Main();
    main.starter = new Starter();
    System.out.println("Starter initialized...");
    main.setupShell();
    main.eventLoop();
  }

  private void eventLoop() {
    for (; ; ) {
      System.out.print("medis> ");
      final String inputLine = sc.nextLine();
      final String result;
      try {
        result = starter.tryExecute(inputLine);
        System.out.println(result);
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
      }
    }
  }

  private void setupShell() {
    this.sc = new Scanner(System.in);
  }
}
