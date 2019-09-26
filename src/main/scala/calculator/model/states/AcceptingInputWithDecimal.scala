package calculator.model.states

import calculator.model.Calculator

class AcceptingInputWithDecimal(calc: Calculator) extends State(calc) {
  override def handleEqual = ???

  override def handleOperator = ???

  override def handleDecimal = this

  override def handleDigit(digit: Int) = {
    var currentStr = calc.currentValue.toString
    val dotIndex = currentStr.toString.indexOf('.')
    calc.currentValue += digit * Math.pow(10, -(currentStr.length - dotIndex))
    this
  }

  override def handleClear = ???

  override def handleMultiplyOperator =
    new CalculatingState(calc, calc.currentValue, (x, y) => x * y)

  override def handleDivideOperator =
    new CalculatingState(calc, calc.currentValue, (x, y) => x / y)

  override def handleAddOperator = {
    val next = new CalculatingState(calc, calc.currentValue, (x, y) => x + y)
    calc.currentValue = 0.0
    next
  }

  override def handleSubtractOperator: State =
    new CalculatingState(calc, calc.currentValue, (x, y) => x - y)
}
