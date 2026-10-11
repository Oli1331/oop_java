package nsu.lab.expression.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import nsu.lab.expression.parsing.Parser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests parsing, evaluation and differentiation of expressions.
 */
public class ExpressionTest {

    @Test
    void parseTest() {
        assertEquals(new Variable("x"), Parser.pars("(((x)))"));
        assertEquals(new Add(new Variable("x"), new Variable("y")),
                Parser.pars("((x)+(y))"));
        assertEquals(new Add(new Variable("a"), new Mul(new Variable("b"),
                        new Variable("c"))),
                Parser.pars("((a)+((b)*(c)))"));

        assertEquals(new Add(new Variable("alpha"), new Variable("beta")),
                Parser.pars("(alpha+beta)"));
        assertEquals(new Mul(new Variable("veryLongVariableName"),
                        new Variable("short")),
                Parser.pars("(veryLongVariableName * short)"));

        assertEquals(new Add(new Variable("x"), new Variable("y")),
                Parser.pars("( x + y )"));
        assertEquals(new Add(new Variable("x"), new Variable("y")),
                Parser.pars("( x+ y )"));
        assertEquals(new Add(new Variable("x"), new Variable("y")),
                Parser.pars("( x +y )"));

        assertEquals(new Number(-5), Parser.pars("(-5)"));
        assertEquals(new Add(new Variable("x"), new Number(-3)),
                Parser.pars("(x + (-3))"));

        assertEquals(new Add(
                new Mul(new Variable("a"), new Variable("b")),
                new Div(
                        new Sub(new Variable("c"), new Variable("d")),
                        new Add(new Number(1), new Number(2))
                )
        ), Parser.pars("((a*b)+((c-d)/(1+2)))"));


    }

    @ParameterizedTest(name = "Test eval{index}: experssion {0} with args {1} must be {2}")
    @CsvSource({
        "(x+y), 'x=1;y=2', 3",
        "(x-y), 'x=1;y=2', -1",
        "(x*y), 'x=1;y=2', 2",
        "(y/x), 'x=1;y=2', 2",
        "(17*3), '', 51"
    })
    void evalTest(String exp, String var, int res) {
        Expression expression = Parser.pars(exp);
        assertEquals(res, expression.eval(var));
    }

    @ParameterizedTest(name = "Test derivative{index}: experssion {0} with args {1} must be {2}")
    @CsvSource({
        "(x+y), 'x', (1+0)",
        "(x-y), 'y', (0-1)",
        "(x*y), 'x', ((1*y)+(x*0))",
        "(x/y), 'x', (((1*y)-(x*0))/(y*y))"
    })
    void derivativeTest(String exp, String var, String dexp) {
        Expression expression = Parser.pars(exp);
        Expression dexpression = Parser.pars(dexp);
        assertEquals(dexpression, expression.derivative(var));
    }


    /**
     * Checks parsing, evaluation and differentiation.
     */
    @Test
    void allTest() {
        Expression e1 = new Add(new Number(3),
                new Mul(new Number(2), new Variable("x")));
        assertEquals(7, e1.eval("x=2"));
        Expression e2 = Parser.pars("(2+2)");
        assertEquals(new Add(new Number(2), new Number(2)), e2);
        Expression e3 = Parser.pars("((x * x)*x)");
        Expression check3 = new Mul(new Mul(new Variable("x"), new Variable("x")),
                new Variable("x"));
        assertEquals(check3, e3);
        assertEquals(3 * 3 * 3, e3.eval("x=3"));
        assertEquals(2 * 2 * 2, e3.eval("y=1; x=2; dfsf=4"));
        Expression e4 = Parser.pars("(1/x)");
        Expression de4x = e4.derivative("x");
        Expression de4y = e4.derivative("y");
        Expression check4x = new Div(new Sub(new Mul(new Number(0), new Variable("x")),
                new Mul(new Number(1), new Number(1))), new Mul(new Variable("x"),
                new Variable("x")));
        Expression check4y = new Div(new Sub(new Mul(new Number(0), new Variable("x")),
                new Mul(new Number(1), new Number(0))), new Mul(new Variable("x"),
                new Variable("x")));

        assertEquals(check4x, de4x);
        assertEquals(check4y, de4y);

        Expression e5 = Parser.pars("(x-y)");
        assertEquals(1, e5.eval("y=4; x=5"));
        Expression check5 = new Sub(new Number(1), new Number(0));
        assertEquals(check5, e5.derivative("x"));

        Expression e6 = Parser.pars("(x*y)");
        Expression check6 = new Add(new Mul(new Number(1), new Variable("y")),
                new Mul(new Variable("x"), new Number(0)));
        assertEquals(check6, e6.derivative("x"));

        // assertThrows(IllegalArgumentException.class, () -> Parser.pars("(2--2)"));

        Expression e7 = Parser.pars("(x/y)");
        assertEquals(1, e7.eval("y=4; x=5"));

        Expression e8 = Parser.pars("(x+y)");
        Expression check8 = Parser.pars("(1+0)");
        assertEquals(check8, e8.derivative("x"));

        String exp = "((((1+2)-3)*4)/x)";
        assertEquals(exp, Parser.pars(exp).toString());

        Integer cratch = 228;

        Expression e9 = Parser.pars("(x/y)");
        assertThrows(IllegalArgumentException.class, () -> e9.eval("y=y; x=5"));
        assertThrows(IllegalArgumentException.class, () -> e9.eval("t=1; x=5"));
        assertNotEquals(null, e9);
        assertEquals(e9, e9);
        assertNotEquals(e9, cratch);


        Expression e10 = Parser.pars("(x*4)");
        assertNotEquals(null, e10);
        assertEquals(e10, e10);
        assertNotEquals(e10, cratch);


        Expression e11 = Parser.pars("(x-4)");
        assertNotEquals(null, e11);
        assertEquals(e11, e11);
        assertNotEquals(e11, cratch);


        Expression e12 = Parser.pars("(x+4)");
        assertNotEquals(null, e12);
        assertEquals(e12, e12);
        assertNotEquals(e12, cratch);

        Expression e13 = Parser.pars("110");
        assertNotEquals(null, e13);
        assertEquals(e13, e13);
        assertNotEquals(e13, cratch);

        Expression e14 = Parser.pars("(1/0)");
        assertThrows(ArithmeticException.class, () -> e14.eval(""));
        Expression e15 = Parser.pars("(((x)))");
        assertEquals(new Variable("x"), e15);
    }


}
