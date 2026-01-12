import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ComparingTwoNumbersTest {
    @Test
    public void testCompareEqual() {
        ComparingTwoNumbers comparator = new ComparingTwoNumbers();
        assertEquals(comparator.compare(5, 5), \"Значения 5 и 5 равны\");
    }
    
    @Test
    public void testCompareLess() {
        ComparingTwoNumbers comparator = new ComparingTwoNumbers();
        assertEquals(comparator.compare(3, 7), \"Первое значение (3) меньше второго (7)\");
    }
    
    @Test
    public void testCompareGreater() {
        ComparingTwoNumbers comparator = new ComparingTwoNumbers();
        assertEquals(comparator.compare(10, 2), \"Первое значение (10) больше второго (2)\");
    }
}
