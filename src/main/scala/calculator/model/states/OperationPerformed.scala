package calculator.model.states

import calculator.model.Calculator

class OperationPerformed(
    calc: Calculator,
    lhs: Double,
    operation: (Double, Double) => Double
) extends State(calc) {

  def handleEqual: State = {
    this
  }
  def handleOperator: State = ???

  def handleDecimal: State = {
    new DecimalExpressionCompleted(calc, lhs, operation)
  }

  def handleDigit(d: Int): State = {
    calculator.currentValue = calculator.currentValue * 10 + d
    new ExpressionCompleted(calc, lhs, operation)
  }

  def handleClear: State = {
    calc.currentValue = 0.0
    new Zero(calc)
  }

  override def handleMultiplyOperator: State = this

  override def handleDivideOperator: State = this

  override def handleAddOperator: State = this

  override def handleSubtractOperator: State = this
}
