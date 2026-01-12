public class TriangleArea {
    public double triangle(double a, double b) {
        if (a <= 0 || b <= 0) throw new IllegalArgumentException("Основание и высота должны быть > 0");
        return (a * b) / 2.0;
    }
}

