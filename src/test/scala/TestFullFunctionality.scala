import calculator.controller.{
  AdditionAction,
  DecimalAction,
  EqualAction,
  MultiplicationAction,
  DivisionAction,
  SubtractionAction,
  NumberAction,
  ClearAction
}
import calculator.model.Calculator
import calculator.model.states.Zero
import org.scalatest.FunSuite

class TestFullFunctionality extends FunSuite {

  val EPSILON: Double = 0.000001

  def equalDoubles(d1: Double, d2: Double): Boolean = {
    (d1 - d2).abs < EPSILON
  }

  test("1, 2, +, 3, *, 2 = should return 30") {
    val calculator: Calculator = new Calculator()

    new NumberAction(calculator, 1).handle(null)
    new NumberAction(calculator, 2).handle(null)

    new AdditionAction(calculator).handle(null)
    new NumberAction(calculator, 3).handle(null)

    new MultiplicationAction(calculator).handle(null)
    new NumberAction(calculator, 2).handle(null)

    new EqualAction(calculator).handle(null)

    assert(
      equalDoubles(calculator.displayNumber(), 30),
      calculator.displayNumber()
    )
  }

  test("1, 2, +, 3, /, 5 = should return 3") {
    val calculator: Calculator = new Calculator()

    new NumberAction(calculator, 1).handle(null)
    new NumberAction(calculator, 2).handle(null)

    new AdditionAction(calculator).handle(null)
    new NumberAction(calculator, 3).handle(null)

    new DivisionAction(calculator).handle(null)
    new NumberAction(calculator, 5).handle(null)

    new EqualAction(calculator).handle(null)

    assert(
      equalDoubles(calculator.displayNumber(), 3),
      calculator.displayNumber()
    )
  }

  test("1, 2, +, 3, +, 5 = should return 20") {
    val calculator: Calculator = new Calculator()

    new NumberAction(calculator, 1).handle(null)
    new NumberAction(calculator, 2).handle(null)

    new AdditionAction(calculator).handle(null)
    new NumberAction(calculator, 3).handle(null)

    new AdditionAction(calculator).handle(null)
    new NumberAction(calculator, 5).handle(null)

    new EqualAction(calculator).handle(null)

    assert(
      equalDoubles(calculator.displayNumber(), 20),
      calculator.displayNumber()
    )
  }

  test("1, 2, +, 3, -, 5 = should return 10") {
    val calculator: Calculator = new Calculator()

    new NumberAction(calculator, 1).handle(null)
    new NumberAction(calculator, 2).handle(null)

    new AdditionAction(calculator).handle(null)
    new NumberAction(calculator, 3).handle(null)

    new SubtractionAction(calculator).handle(null)
    new NumberAction(calculator, 5).handle(null)

    new EqualAction(calculator).handle(null)

    assert(
      equalDoubles(calculator.displayNumber(), 10),
      calculator.displayNumber()
    )
  }

  test("., 3, 5 should display 0.35") {
    val calculator = new Calculator()

    new DecimalAction(calculator).handle(null)
    new NumberAction(calculator, 3).handle(null)
    new NumberAction(calculator, 5).handle(null)

    assert(
      equalDoubles(calculator.displayNumber(), 0.35),
      calculator.displayNumber()
    )
  }

  test("Clear returns the calculator to initial state with 0.0 being displayed") {
    var calculator = new Calculator()

    new NumberAction(calculator, 3).handle(null)
    new ClearAction(calculator).handle(null)

    assert(
      equalDoubles(calculator.displayNumber(), 0.0),
      calculator.displayNumber()
    )
  }

  test("2, +, 3, Clear returns to initial state") {
    var calculator = new Calculator()

    new NumberAction(calculator, 2).handle(null)
    new AdditionAction(calculator).handle(null)
    new NumberAction(calculator, 3).handle(null)
    new ClearAction(calculator).handle(null)

    assert(
      equalDoubles(calculator.displayNumber(), 0.0),
      calculator.displayNumber()
    )

    assert(calculator.state.isInstanceOf[Zero], calculator.state)
  }

  test("previous operation is replaced with new one") {
    val calculator = new Calculator()

    new NumberAction(calculator, 3).handle(null)
    new AdditionAction(calculator).handle(null)
    new SubtractionAction(calculator).handle(null)
    new NumberAction(calculator, 1).handle(null)
    new EqualAction(calculator).handle(null)

    assert(
      equalDoubles(calculator.displayNumber(), 2.0),
      calculator.displayNumber()
    )
  }

  test("no order of operations, 3, *, 5, +, 1, 0, ., 5, = should display 25.5") {
    val calculator = new Calculator

    new NumberAction(calculator, 3).handle(null)
    new MultiplicationAction(calculator).handle(null)
    new NumberAction(calculator, 5).handle(null)
    new AdditionAction(calculator).handle(null)
    new NumberAction(calculator, 1).handle(null)
    new NumberAction(calculator, 0).handle(null)
    new DecimalAction(calculator).handle(null)
    new NumberAction(calculator, 5).handle(null)

    new EqualAction(calculator).handle(null)

    assert(
      equalDoubles(calculator.displayNumber(), 25.5),
      calculator.displayNumber()
    )
  }

  test(
    "Nothing happens if = immediately pressed after an operation without inputting RHS"
  ) {}

}
