package calculator.model.states

import calculator.model.Calculator
import calculator.model.states.Zero

class AcceptingInput(calc: Calculator) extends State(calc) {

  def handleEqual: State = ???

  def handleOperator: State = ???

  def handleDecimal: State = new AcceptingInputWithDecimalStarted(calc)

  def handleDigit(d: Int): State = {
    calculator.currentValue = calculator.currentValue * 10 + d
    this
  }

  def handleClear: State = {
    calc.currentValue = 0.0
    new Zero(calc)
  }

  override def handleMultiplyOperator: State = {
    val nextState =
      new OperationPerformed(calc, calc.currentValue, (x, y) => x * y)
    calc.currentValue = 0.0
    nextState
  }

  override def handleDivideOperator: State = {
    val nextState =
      new OperationPerformed(calc, calc.currentValue, (x, y) => x / y)
    calc.currentValue = 0.0
    nextState
  }

  override def handleAddOperator: State = {
    val nextState =
      new OperationPerformed(calc, calc.currentValue, (x, y) => x + y)
    calc.currentValue = 0.0
    nextState
  }

  override def handleSubtractOperator: State = {
    val nextState =
      new OperationPerformed(calc, calc.currentValue, (x, y) => x - y)
    calc.currentValue = 0.0
    nextState
  }
}
