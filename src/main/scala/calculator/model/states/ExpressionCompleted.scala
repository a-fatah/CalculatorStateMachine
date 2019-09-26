package calculator.model.states

import calculator.model.Calculator

class ExpressionCompleted(
    calc: Calculator,
    lhs: Double,
    operation: (Double, Double) => Double
) extends State(calc) {
  override def handleEqual: State = {
    val rhs = calc.currentValue
    val result = operation(lhs, rhs)
    calc.currentValue = result
    this
  }

  override def handleDecimal: State =
    new DecimalExpressionCompleted(calc, lhs, operation)

  override def handleDigit(d: Int): State = {
    calculator.currentValue = calculator.currentValue * 10 + d
    this
  }

  override def handleClear: State = {
    calc.currentValue = 0.0
    new Zero(calc)
  }

  override def handleMultiplyOperator: State = {
    val newLHS = operation(lhs, calc.currentValue)
    calc.currentValue = 0.0
    new OperationPerformed(calc, newLHS, (x, y) => x * y)
  }

  override def handleDivideOperator: State = {
    val newLHS = operation(lhs, calc.currentValue)
    calc.currentValue = 0.0
    new OperationPerformed(calc, newLHS, (x, y) => x / y)
  }

  override def handleAddOperator: State = {
    val newLHS = operation(lhs, calc.currentValue)
    calc.currentValue = 0.0
    new OperationPerformed(calc, newLHS, (x, y) => x + y)
  }

  override def handleSubtractOperator: State = {
    val newLHS = operation(lhs, calc.currentValue)
    calc.currentValue = 0.0
    new OperationPerformed(calc, newLHS, (x, y) => x - y)
  }
}
