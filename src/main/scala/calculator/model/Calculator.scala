package calculator.model

import calculator.model.states.{State, Zero}

class Calculator {
  var currentValue: Double = 0.0
  var state: State = new Zero(this)

  def displayNumber() = currentValue

  def equalPressed() = state.handleEqual

  def operatorPressed() = state.handleOperator

  def decimalPressed() = state.handleDecimal

  def digitPressed(digit: Int) = state handleDigit digit

  def clearPressed() = state.handleClear

}
