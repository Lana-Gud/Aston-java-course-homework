public interface GeometricFigure {// Задаие 2
    double getPerimeter();
    double getArea();
    String getFillColor();
    String getBorderColor();

    default void printInfo() {
        System.out.println("Периметр: " + String.format("%.2f", getPerimeter()));
        System.out.println("Площадь: " + String.format("%.2f", getArea()));
        System.out.println("Цвет заливки: " + getFillColor());
        System.out.println("Цвет границ: " + getBorderColor());
    }
}
