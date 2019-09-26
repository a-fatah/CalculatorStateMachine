package calculator.model.states

import calculator.model.Calculator

class AcceptingInputWithDecimalStarted(calc: Calculator) extends State(calc) {
  override def handleEqual: State = ???

  override def handleOperator: State = ???

  override def handleDecimal: State = this

  override def handleDigit(d: Int): State = {
    println(s"State before pressing digit: ${d}: ${calc.state}")
    var currentStr = calc.currentValue.toString

    val dotIndex = currentStr.toString.indexOf('.')
    calc.currentValue += d * Math.pow(10, -(currentStr.length - dotIndex - 1))
    val nextState = new AcceptingInputWithDecimal(calc)
    println(s"state after pressing digit ${d}: ${nextState}")
    nextState
  }

  override def handleClear: State = ???

  override def handleMultiplyOperator: State = ???

  override def handleDivideOperator: State = ???

  override def handleAddOperator: State = ???

  override def handleSubtractOperator: State = ???
}
