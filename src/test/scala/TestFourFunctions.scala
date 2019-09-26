
import calculator.controller.{AdditionAction, DecimalAction, DivisionAction, EqualAction, MultiplicationAction, NumberAction}
import calculator.model.Calculator
import org.scalatest.FunSuite

class TestFourFunctions extends FunSuite {
  val EPSILON: Double = 0.000001

  def equalDoubles(d1: Double, d2: Double): Boolean = {
    (d1 - d2).abs < EPSILON
  }


  test("Keypresses 5, *, 4, = should return 20.0") {
    val calculator: Calculator = new Calculator()

    new NumberAction(calculator, 5).handle(null)
    new MultiplicationAction(calculator).handle(null)
    new NumberAction(calculator, 4).handle(null)
    new EqualAction(calculator).handle(null)

    assert(
      equalDoubles(calculator.displayNumber(), 20.0),
      calculator.displayNumber()
    )
  }

  test("Keypresses 2, 5, /, 5, = should return 5.0") {
    val calculator: Calculator = new Calculator()

    new NumberAction(calculator, 2).handle(null)
    new NumberAction(calculator, 5).handle(null)

    new DivisionAction(calculator).handle(null)
    new NumberAction(calculator, 5).handle(null)
    new EqualAction(calculator).handle(null)

    assert(
      equalDoubles(calculator.displayNumber(), 5.0),
      calculator.displayNumber()
    )
  }

  test("Keypresses 1, ., ., 2, ., +, 4, ., 3, = should return 5.5") {
    val calculator: Calculator = new Calculator()

    new NumberAction(calculator, 1).handle(null)
    new DecimalAction(calculator).handle(null)
    new DecimalAction(calculator).handle(null)
    new NumberAction(calculator, 2).handle(null)

    println("pressing decimal...")
    new DecimalAction(calculator).handle(null)
    println("pressing add button...")
    new AdditionAction(calculator).handle(null)
    new NumberAction(calculator, 4).handle(null)
    new DecimalAction(calculator).handle(null)
    new NumberAction(calculator, 3).handle(null)

    new EqualAction(calculator).handle(null)

    assert(
      equalDoubles(calculator.displayNumber(), 5.5),
      calculator.displayNumber()
    )
  }

  test("Keypresses 1, ., ., 2, ., +, ., 3, = should return 1.5") {
    val calculator: Calculator = new Calculator()

    new NumberAction(calculator, 1).handle(null)
    new DecimalAction(calculator).handle(null)
    new DecimalAction(calculator).handle(null)
    new NumberAction(calculator, 2).handle(null)

    println("pressing decimal...")
    new DecimalAction(calculator).handle(null)
    println("pressing add button...")
    new AdditionAction(calculator).handle(null)
    new DecimalAction(calculator).handle(null)
    new NumberAction(calculator, 3).handle(null)

    new EqualAction(calculator).handle(null)

    assert(
      equalDoubles(calculator.displayNumber(), 1.5),
      calculator.displayNumber()
    )
  }

}
