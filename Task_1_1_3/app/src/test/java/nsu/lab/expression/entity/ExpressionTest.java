package nsu.lab.expression.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import nsu.lab.expression.parsing.Parser;

import org.junit.jupiter.api.Test;

public class ExpressionTest {

    @Test
    void evalTest() { // - /
        Expression e1 = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        assertEquals(7, e1.eval("x=2"));
        Expression e2 = Parser.pars("(2+2)");
        assertEquals(new Add(new Number(2), new Number(2)), e2);
        Expression e3 = Parser.pars("((x * x)*x)");
        Expression check3 =
                new Mul(new Mul(new Variable("x"), new Variable("x")), new Variable("x"));
        assertEquals(check3, e3);
        assertEquals(3 * 3 * 3, e3.eval("x=3"));
        assertEquals(2 * 2 * 2, e3.eval("y=1; x=2; dfsf=4"));
        Expression e4 = Parser.pars("(1/x)");
        Expression de4x = e4.derivative("x");
        Expression de4y = e4.derivative("y");
        Expression check4x =
                new Div(
                        new Sub(
                                new Mul(new Number(0), new Variable("x")),
                                new Mul(new Number(1), new Number(1))),
                        new Mul(new Variable("x"), new Variable("x")));
        Expression check4y =
                new Div(
                        new Sub(
                                new Mul(new Number(0), new Variable("x")),
                                new Mul(new Number(1), new Number(0))),
                        new Mul(new Variable("x"), new Variable("x")));

        assertEquals(check4x, de4x);
        assertEquals(check4y, de4y);

        Expression e5 = Parser.pars("(x-y)");
        assertEquals(1, e5.eval("y=4; x=5"));
        Expression check5 = new Sub(new Number(1), new Number(0));
        assertEquals(check5, e5.derivative("x"));

        Expression e6 = Parser.pars("(x*y)");
        Expression check6 =
                new Add(
                        new Mul(new Number(1), new Variable("y")),
                        new Mul(new Variable("x"), new Number(0)));
        assertEquals(check6, e6.derivative("x"));

        assertThrows(IllegalArgumentException.class, () -> Parser.pars("(2--2)"));

        Expression e7 = Parser.pars("(x/y)");
        assertEquals(1, e7.eval("y=4; x=5"));

        Expression e8 = Parser.pars("(x+y)");
        Expression check8 = Parser.pars("(1+0)");
        assertEquals(check8, e8.derivative("x"));

        String exp = "((((1+2)-3)*4)/5)";
        assertEquals(exp, Parser.pars(exp).toString());
    }
}
