package calculator.model.states

import calculator.model.Calculator

class AcceptingInputWithDecimal(calc: Calculator) extends State(calc) {
  override def handleEqual: State = ???

  override def handleOperator: State = ???

  override def handleDecimal: State = {
    println(s"state didn't change after pressing decimal when in ${this} state")
    this
  }

  override def handleDigit(d: Int): State = {

    var currentStr = calc.currentValue.toString
    println(s"state before pressing digit ${d}: ${calc.state}")
    val dotIndex = currentStr.toString.indexOf('.')
    calc.currentValue += d * Math.pow(10, -(currentStr.length - dotIndex))
    val nextState = this
    println(s"state after pressing digit ${d}: ${nextState}")
    nextState
  }

  override def handleClear: State = ???

  override def handleMultiplyOperator: State =
    new CalculatingState(calc, calc.currentValue, (x, y) => x * y)

  override def handleDivideOperator: State =
    new CalculatingState(calc, calc.currentValue, (x, y) => x / y)

  override def handleAddOperator: State = {
    println(s"state before applying addition was ${calc.state}")
    val next = new CalculatingState(calc, calc.currentValue, (x, y) => x + y)
    println(s"state after addition: ${next}")
    calc.currentValue = 0.0
    next

  }

  override def handleSubtractOperator: State =
    new CalculatingState(calc, calc.currentValue, (x, y) => x - y)
}
