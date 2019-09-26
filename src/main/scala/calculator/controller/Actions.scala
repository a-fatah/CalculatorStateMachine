package calculator.controller

import calculator.model.Calculator
import javafx.event.{ActionEvent, EventHandler}

/**
  * EventHandlers f0r each of the button on the calculator. NumberAction takes
  * an Int representing the which number button was pressed. Implement each of
  * these handle methods to call the appropriate method in your Calculator API.
  * You may assume that each of these classes has a reference to the same
  * Calculator object
  *
  * For testing use only these classes to ensure your tests will run with the
  * submissions on the server
  */
class EqualAction(calculator: Calculator) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    // Example: If you write a method in calculator named equalPressed() you would
    //          implement this method to call equalPressed() on the input calculator
    calculator.state = calculator.equalPressed
  }
}

class ClearAction(calculator: Calculator) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    calculator.clear
  }
}

class DecimalAction(calculator: Calculator) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    val nextState = calculator.decimalPressed()
    calculator.state = nextState
  }
}

class NumberAction(calculator: Calculator, number: Int)
    extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    val nextState = calculator.state.handleDigit(number)
    calculator.state = nextState
  }
}

class AdditionAction(calculator: Calculator) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    val nextState = calculator.state.handleAddOperator
    calculator.state = nextState
  }
}

class SubtractionAction(calculator: Calculator)
    extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    val nextState = calculator.state.handleSubtractOperator
    calculator.state = nextState
  }
}

class MultiplicationAction(calculator: Calculator)
    extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    val nextState = calculator.state.handleMultiplyOperator
    calculator.state = nextState
  }
}

class DivisionAction(calculator: Calculator) extends EventHandler[ActionEvent] {
  override def handle(event: ActionEvent): Unit = {
    val nextState = calculator.state.handleDivideOperator
    calculator.state = nextState
  }
}
