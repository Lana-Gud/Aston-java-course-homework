import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialCalculatorTest {
    @Test
    public void testFactorialFive() {
        FactorialCalculator calculator = new FactorialCalculator();
        assertEquals(calculator.factorial(5), 120);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialNegative() {
        FactorialCalculator calculator = new FactorialCalculator();
        calculator.factorial(-1);
    }
}
