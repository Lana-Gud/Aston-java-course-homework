import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComparingTwoNumbersTest {

    @Test
    public void testCompareEqual() {
        ComparingTwoNumbers comparator = new ComparingTwoNumbers();
        String result = comparator.compare(5, 5);
        assertTrue(result.contains("равны"));
    }

    @Test
    public void testCompareFirstLess() {
        ComparingTwoNumbers comparator = new ComparingTwoNumbers();
        String result = comparator.compare(3, 7);
        assertTrue(result.contains("меньше"));
    }

    @Test
    public void testCompareFirstGreater() {
        ComparingTwoNumbers comparator = new ComparingTwoNumbers();
        String result = comparator.compare(9, 4);
        assertTrue(result.contains("больше"));
    }
}