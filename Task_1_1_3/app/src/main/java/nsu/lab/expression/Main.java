package nsu.lab.expression;

import nsu.lab.expression.entity.Expression;
import nsu.lab.expression.parsing.Parser;

public class Main {
    public static void main(String[] args) {
        String exp = "(((2+2)+2)/(2+2))";
        Expression e = Parser.pars(exp);
        e.print();
    }
}
