
import calculator.controller.{DecimalAction, NumberAction}
import calculator.model.Calculator
import org.scalatest.FunSuite

class TestEnterNumbers extends FunSuite {

  val EPSILON: Double = 0.000001

  def equalDoubles(d1: Double, d2: Double): Boolean = {
    (d1 - d2).abs < EPSILON
  }

  // Example test case
  test("Numbers pressed in sequence 1,2,5 display 125.0") {
    val calculator: Calculator = new Calculator()

    new NumberAction(calculator, 1).handle(null)
    new NumberAction(calculator, 2).handle(null)
    new NumberAction(calculator, 5).handle(null)

    assert(
      equalDoubles(calculator.displayNumber(), 125.0),
      calculator.displayNumber()
    )
  }

  test("Numbers pressed in sequence 4,1,7 display 417.0") {
    val calculator: Calculator = new Calculator()

    new NumberAction(calculator, 4).handle(null)
    new NumberAction(calculator, 1).handle(null)
    new NumberAction(calculator, 7).handle(null)

    assert(
      equalDoubles(calculator.displayNumber(), 417.0),
      calculator.displayNumber()
    )
  }

  test("Numbers pressed in sequence 0,0,0,0,2,.,3 display 2.3") {
    val calculator: Calculator = new Calculator()

    new NumberAction(calculator, 0).handle(null)
    new NumberAction(calculator, 0).handle(null)
    new NumberAction(calculator, 0).handle(null)
    new NumberAction(calculator, 0).handle(null)
    new NumberAction(calculator, 2).handle(null)
    new DecimalAction(calculator).handle(null)
    new NumberAction(calculator, 3).handle(null)





    assert(
      equalDoubles(calculator.displayNumber(), 2.3),
      calculator.displayNumber()
    )
  }

  test("Numbers pressed in sequence 2, ., ., 3, ., 5 display 2.35") {
    val calculator: Calculator = new Calculator()

    new NumberAction(calculator, 2).handle(null)
    new DecimalAction(calculator).handle(null)
    new DecimalAction(calculator).handle(null)
    new NumberAction(calculator, 3).handle(null)
    new DecimalAction(calculator).handle(null)
    new NumberAction(calculator, 5).handle(null)


    assert(
      equalDoubles(calculator.displayNumber(), 2.35),
      calculator.displayNumber()
    )
  }

  test("Numbers pressed in sequence ., 3 display 0.3") {
    val calculator: Calculator = new Calculator()

    new DecimalAction(calculator).handle(null)
    new NumberAction(calculator, 3).handle(null)

    assert(
      equalDoubles(calculator.displayNumber(), 0.3),
      calculator.displayNumber()
    )
  }
}
