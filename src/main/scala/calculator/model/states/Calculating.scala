package calculator.model.states

import calculator.model.Calculator

class CalculatingState(
    calc: Calculator,
    lhs: Double,
    operation: (Double, Double) => Double
) extends State(calc) {

  def handleEqual: State = {
    val result = operation(lhs, calc.currentValue)
    calc.currentValue = result
    this
  }
  def handleOperator: State = ???

  def handleDecimal: State = {
    new CalculatingWithDecimalRHSStarted(calc, lhs, operation)
  }

  def handleDigit(d: Int): State = {
    calculator.currentValue = calculator.currentValue * 10 + d
    this
  }
  def handleClear: State = ???

  override def handleMultiplyOperator: State = {
    val newLHS = operation(lhs, calc.currentValue)
    calc.currentValue = 0.0
    new CalculatingState(calc, newLHS, (x, y) => x * y)
  }

  override def handleDivideOperator: State = {
    val newLHS = operation(lhs, calc.currentValue)
    calc.currentValue = 0.0
    new CalculatingState(calc, newLHS, (x, y) => x / y)
  }

  override def handleAddOperator: State = {
    val newLHS = operation(lhs, calc.currentValue)
    calc.currentValue = 0.0
    new CalculatingState(calc, newLHS, (x, y) => x + y)
  }

  override def handleSubtractOperator: State = {
    val newLHS = operation(lhs, calc.currentValue)
    calc.currentValue = 0.0
    new CalculatingState(calc, newLHS, (x, y) => x - y)
  }
}
