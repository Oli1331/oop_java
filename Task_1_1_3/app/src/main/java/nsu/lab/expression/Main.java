package nsu.lab.expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import nsu.lab.expression.entity.Expression;
import nsu.lab.expression.parsing.Parser;

/**
 * Application entry point that evaluates left sample expression.
 */
public class Main {

    /**
     * Work with expression in console.
     *
     * @param args path to file
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            Path path = Path.of(args[0]);
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String content = reader.readLine();
                String var = reader.readLine();
                Expression exp = Parser.pars(content);
                System.out.println(exp.eval(var));

            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error parse exception");
            }

        } else {
            Scanner scan = new Scanner(System.in);
            String input;
            String var;
            Expression exp;

            while (true) {
                System.out.println(
                        "\nWrite expression on the first line, then the vars on the second line.");
                input = scan.nextLine();
                if (":q".equals(input)) {
                    break;
                }
                var = scan.nextLine();

                try {
                    exp = Parser.pars(input);
                    System.out.println(exp + "=" + exp.eval(var));
                } catch (RuntimeException e) {
                    System.out.println("wrong struct exception");
                }
            }
        }
    }
}
