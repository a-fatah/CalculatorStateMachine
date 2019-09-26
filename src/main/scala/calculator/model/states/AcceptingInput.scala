package calculator.model.states

import calculator.model.Calculator

class AcceptingInput(calc: Calculator) extends State(calc) {
  def handleEqual: State = ???
  def handleOperator: State = ???
  def handleDecimal: State = new AcceptingInputWithDecimalStarted(calc)
  def handleDigit(d: Int): State = {
    println(s"State before pressing digit ${d} : ${calc.state}")
    calculator.currentValue = calculator.currentValue * 10 + d
    println(s"State didn't change after pressing digit")
    this
  }
  def handleClear: State = ???

  override def handleMultiplyOperator: State = {
    val nextState =
      new CalculatingState(calc, calc.currentValue, (x, y) => x * y)
    calc.currentValue = 0.0
    nextState
  }

  override def handleDivideOperator: State = {
    val nextState =
      new CalculatingState(calc, calc.currentValue, (x, y) => x / y)
    calc.currentValue = 0.0
    nextState
  }

  override def handleAddOperator: State = {
    val nextState =
      new CalculatingState(calc, calc.currentValue, (x, y) => x + y)
    calc.currentValue = 0.0
    nextState
  }

  override def handleSubtractOperator: State = {
    val nextState =
      new CalculatingState(calc, calc.currentValue, (x, y) => x - y)
    calc.currentValue = 0.0
    nextState
  }
}
