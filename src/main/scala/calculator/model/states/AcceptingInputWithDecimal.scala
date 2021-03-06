package calculator.model.states

import calculator.model.Calculator

class AcceptingInputWithDecimal(calc: Calculator) extends State(calc) {
  override def handleEqual = ???

  override def handleDecimal = this

  override def handleDigit(digit: Int) = {
    var currentStr = calc.currentValue.toString
    val dotIndex = currentStr.toString.indexOf('.')
    calc.currentValue += digit * 1 / Math.pow(
      10,
      (currentStr.length - dotIndex)
    )
    this
  }

  override def handleClear = {
    calc.currentValue = 0.0
    new Zero(calc)
  }

  override def handleMultiplyOperator =
    new OperationPerformed(calc, calc.currentValue, (x, y) => x * y)

  override def handleDivideOperator =
    new OperationPerformed(calc, calc.currentValue, (x, y) => x / y)

  override def handleAddOperator = {
    val next = new OperationPerformed(calc, calc.currentValue, (x, y) => x + y)
    calc.currentValue = 0.0
    next
  }

  override def handleSubtractOperator: State =
    new OperationPerformed(calc, calc.currentValue, (x, y) => x - y)
}
