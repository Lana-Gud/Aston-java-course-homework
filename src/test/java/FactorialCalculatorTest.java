import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactorialCalculatorTest {

    @Test
    public void testFactorial() {
        FactorialCalculator calc = new FactorialCalculator();
        assertEquals(120, calc.factorial(5));
    }

    @Test
    public void testFactorialOfOne() {
        FactorialCalculator calc = new FactorialCalculator();
        assertEquals(1, calc.factorial(1));
    }

    @Test
    public void testFactorialNegative() {
        FactorialCalculator calc = new FactorialCalculator();
        assertThrows(IllegalArgumentException.class,
                () -> calc.factorial(-5));
    }
}
