import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ArithmeticOperationsTest {

    @Test
    public void testSum() {
        ArithmeticOperations ops = new ArithmeticOperations();
        assertEquals(ops.sum(3, 16), 19);
    }

    @Test
    public void testSubtraction() {
        ArithmeticOperations ops = new ArithmeticOperations();
        assertEquals(ops.subtraction(3, 15), -12);
    }

    @Test
    public void testMultiply() {
        ArithmeticOperations ops = new ArithmeticOperations();
        assertEquals(ops.multiply(3, 18), 54);
    }

    @Test
    public void testDivision() {
        ArithmeticOperations ops = new ArithmeticOperations();
        assertEquals(ops.division(6, 3), 2);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivisionByZero() {
        ArithmeticOperations ops = new ArithmeticOperations();
        ops.division(5, 0);
    }
}
