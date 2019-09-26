package calculator.model.states

import calculator.model.Calculator

class OperationPerformed(
    calc: Calculator,
    lhs: Double,
    operation: (Double, Double) => Double
) extends State(calc) {

  def handleEqual: State = {
    this
  }

  def handleDecimal: State = {
    new DecimalExpressionCompleted(calc, lhs, operation)
  }

  def handleDigit(d: Int): State = {
    calculator.currentValue = calculator.currentValue * 10 + d
    new ExpressionCompleted(calc, lhs, operation)
  }

  def handleClear: State = {
    calc.currentValue = 0.0
    new Zero(calc)
  }

  override def handleMultiplyOperator: State =
    new OperationPerformed(calc, lhs, (x, y) => x * y)

  override def handleDivideOperator: State =
    new OperationPerformed(calc, lhs, (x, y) => x / y)

  override def handleAddOperator: State =
    new OperationPerformed(calc, lhs, (x, y) => x + y)

  override def handleSubtractOperator: State =
    new OperationPerformed(calc, lhs, (x, y) => x - y)
}
