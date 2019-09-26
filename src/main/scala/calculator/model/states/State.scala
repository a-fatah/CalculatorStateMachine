package calculator.model.states

import calculator.model.Calculator

abstract class State(calc: Calculator) {
  var calculator: Calculator = calc

  def handleEqual: State
  def handleOperator: State
  def handleDecimal: State
  def handleDigit(d: Int): State
  def handleClear: State
  def handleMultiplyOperator: State
  def handleDivideOperator: State
  def handleAddOperator: State
  def handleSubtractOperator: State
}
