package nsu.lab.expression.entity;

import java.util.Objects;

/** Expression node for addition. */
public class Add extends Expression {

  /** Left operand. */
  protected Expression left;

  /** Right operand. */
  protected Expression right;

  /**
   * Creates an addition node.
   *
   * @param left left operand
   * @param right right operand
   */
  public Add(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public int eval(String var) {
    return left.eval(var) + right.eval(var);
  }

  @Override
  public String toString() {
    return "(" + left.toString() + "+" + right.toString() + ")";
  }

  @Override
  public Expression derivative(String var) {
    return new Add(left.derivative(var), right.derivative(var));
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;

    if (obj == null || obj.getClass() != getClass()) return false;

    Add element = (Add) obj;
    return element.left.equals(left) && element.right.equals(right);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getClass(), left, right);
  }
}
