package calculator.model.states

import calculator.model.Calculator

class AcceptingInputWithDecimalStarted(calc: Calculator) extends State(calc) {
  override def handleEqual: State = ???

  override def handleOperator: State = ???

  override def handleDecimal: State = this

  override def handleDigit(d: Int): State = {
    var currentStr = calc.currentValue.toString
    val dotIndex = currentStr.toString.indexOf('.')
    calc.currentValue += d * 1 / Math.pow(
      10,
      (currentStr.length - dotIndex - 1)
    )
    new AcceptingInputWithDecimal(calc)
  }

  override def handleClear = ???

  override def handleMultiplyOperator = ???

  override def handleDivideOperator = ???

  override def handleAddOperator = ???

  override def handleSubtractOperator = ???
}
