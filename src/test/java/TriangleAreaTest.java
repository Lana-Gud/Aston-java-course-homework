import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TriangleAreaTest {
    @Test
    public void testTriangleArea() {
        TriangleArea triangle = new TriangleArea();
        assertEquals(triangle.triangle(4, 12), 24.0);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTriangleAreaZero() {
        TriangleArea triangle = new TriangleArea();
        triangle.triangle(0, 5);
    }
}
