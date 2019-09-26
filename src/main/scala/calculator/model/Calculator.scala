package calculator.model

import calculator.model.states.{State, Zero}

class Calculator {
  var currentValue: Double = 0.0

  var currentOperator: Double => Double = _

  var state: State = new Zero(this)

  // Accessed by View. You should edit this method as you build functionality
  def displayNumber(): Double = {
    currentValue
  }

  def equalPressed() = {
    println(s"State before pressing equal: $state")
    val nextState = state.handleEqual
    println(s"State after pressing equal: $nextState")
    nextState
  }

  def operatorPressed() = {
    state.handleOperator
  }

  def decimalPressed(): State = {
    println(s"State before pressing decimal: $state")

    val nextState = state.handleDecimal

    println(s"State after pressing decimal: $nextState")
    nextState
  }

  def digitPressed(digit: Int) = {
    println(s"State before pressing digit $digit: $state")
    val nextState = state.handleDigit(digit)
    println(s"State after pressing digit $digit: $nextState")
    nextState
  }

  def clear() = {
    state.handleClear
  }

}
