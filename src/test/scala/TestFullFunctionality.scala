import calculator.controller.{AdditionAction, DecimalAction, EqualAction, MultiplicationAction, NumberAction}
import calculator.model.Calculator
import org.scalatest.FunSuite

class TestFullFunctionality extends FunSuite{

  val EPSILON: Double = 0.000001

  def equalDoubles(d1: Double, d2: Double): Boolean = {
    (d1 - d2).abs < EPSILON
  }

  test("Keypresses 1, 2, +, 3, *, 2 = should return 30") {
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

}
