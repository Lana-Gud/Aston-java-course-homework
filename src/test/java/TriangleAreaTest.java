import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleAreaTest {

    @Test
    public void testTriangleArea() {
        TriangleArea triangle = new TriangleArea();
        assertEquals(24.0, triangle.triangle(4.0, 12.0));
    }

    @Test
    public void testTriangleAreaFractional() {
        TriangleArea triangle = new TriangleArea();
        assertEquals(7.5, triangle.triangle(3.0, 5.0));
    }

    @Test
    public void testTriangleAreaZeroBase() {
        TriangleArea triangle = new TriangleArea();
        assertThrows(IllegalArgumentException.class,
                () -> triangle.triangle(0, 10));
    }
}
