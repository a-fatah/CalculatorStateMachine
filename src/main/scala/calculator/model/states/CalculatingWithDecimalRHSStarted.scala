package calculator.model.states

import calculator.model.Calculator

class CalculatingWithDecimalRHSStarted(
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
  def handleDecimal: State = this
  def handleDigit(d: Int): State = {
    println(s"State before pressing ${d} was ${calc.state}")
    var currentStr = calc.currentValue.toString

    val dotIndex = currentStr.toString.indexOf('.')
    calc.currentValue += d * Math.pow(10, -(currentStr.length - dotIndex - 1))
    val next = new CalculatingWithDecimalRHS(calc, lhs, operation)
    println(s"State after pressing digit is ${next}")
    next
  }
  def handleClear: State = ???

  override def handleMultiplyOperator: State = ???

  override def handleDivideOperator: State = ???

  override def handleAddOperator: State = ???

  override def handleSubtractOperator: State = ???
}