package calculator.model.states

import calculator.model.Calculator

class Zero(calc: Calculator) extends State(calc) {
  def handleEqual: State = ???

  def handleOperator: State = ???

  def handleDecimal: State = new DecimalLHS(calc)

  def handleDigit(d: Int): State = {
    calculator.currentValue = d.toDouble
    new NumberLHS(calc)
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
