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
    println(s"State before pressing digit ${d} : ${calc.state}")
    calculator.currentValue = calculator.currentValue * 10 + d
    println(s"State didn't change after pressing digit")
    this
  }
  def handleClear: State = ???

  override def handleMultiplyOperator: State = {
    val new_lhs = operation(lhs, calc.currentValue)
    calc.currentValue = 0.0
    new CalculatingState(calc, new_lhs, (x, y) => x * y)
  }

  override def handleDivideOperator: State = {
    val new_lhs = operation(lhs, calc.currentValue)
    calc.currentValue = 0.0
    new CalculatingState(calc, new_lhs, (x, y) => x / y)
  }

  override def handleAddOperator: State = {
    val new_lhs = operation(lhs, calc.currentValue)
    calc.currentValue = 0.0
    new CalculatingState(calc, new_lhs, (x, y) => x + y)
  }

  override def handleSubtractOperator: State = {
    val new_lhs = operation(lhs, calc.currentValue)
    calc.currentValue = 0.0
    new CalculatingState(calc, new_lhs, (x, y) => x - y)
  }
}
