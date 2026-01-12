import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticOperationsTest {

    @Test
    public void testSum() {
        ArithmeticOperations ops = new ArithmeticOperations();
        assertEquals(19, ops.sum(3, 16));
    }

    @Test
    public void testSubtraction() {
        ArithmeticOperations ops = new ArithmeticOperations();
        assertEquals(-12, ops.subtraction(3, 15));
    }

    @Test
    public void testMultiply() {
        ArithmeticOperations ops = new ArithmeticOperations();
        assertEquals(54, ops.multiply(3, 18));
    }

    @Test
    public void testDivision() {
        ArithmeticOperations ops = new ArithmeticOperations();
        assertEquals(2, ops.division(6, 3));
    }

    @Test
    public void testDivisionByZero() {
        ArithmeticOperations ops = new ArithmeticOperations();
        assertThrows(ArithmeticException.class,
                () -> ops.division(5, 0));
    }
}
