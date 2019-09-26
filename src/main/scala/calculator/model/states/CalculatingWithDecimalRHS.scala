package calculator.model.states

import calculator.model.Calculator

class CalculatingWithDecimalRHS(
    calc: Calculator,
    lhs: Double,
    operation: (Double, Double) => Double
) extends State(calc) {

  def handleEqual: State = {
    val rhs = calc.currentValue
    val result = operation(lhs, rhs)
    calc.currentValue = result
    this
  }
  def handleOperator: State = ???
  def handleDecimal: State = this
  def handleDigit(d: Int): State = {
    var currentStr = calc.currentValue.toString

    val dotIndex = currentStr.toString.indexOf('.')
    calc.currentValue += d * Math.pow(10, -(currentStr.length - dotIndex))
    this
  }
  def handleClear: State = {
    calc.currentValue = 0.0
    new Zero(calc)
  }

  override def handleMultiplyOperator: State = ???

  override def handleDivideOperator: State = ???

  override def handleAddOperator: State = ???

  override def handleSubtractOperator: State = ???
}
