package calculator.model.states

import calculator.model.Calculator

class Zero(calc: Calculator) extends State(calc) {
  def handleEqual: State = ???
  def handleOperator: State = ???
  def handleDecimal: State = new AcceptingInputWithDecimalStarted(calc)
  def handleDigit(d: Int): State = {
    calculator.currentValue = d.toDouble
    println(s"State before pressing digit: ${d}: ${calc.state}")
    val nextState = new AcceptingInput(calc)
    println(s"state after pressing digit ${d}: ${nextState}")
    nextState
  }
  def handleClear: State = ???

  override def handleMultiplyOperator: State = ???

  override def handleDivideOperator: State = ???

  override def handleAddOperator: State = ???

  override def handleSubtractOperator: State = ???
}
