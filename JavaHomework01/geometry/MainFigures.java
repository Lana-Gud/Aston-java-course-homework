public class MainFigures {// задание 2
    public static void main(String[] args) {
        System.out.println(" Расчет характеристик геометрических фигур ");

        GeometricFigure circle = new Circle(5.0, "Красный", "Черный");
        GeometricFigure rectangle = new Rectangle(4.0, 6.0, "Синий", "Белый");
        GeometricFigure triangle = new Triangle(3.0, 4.0, 5.0, "Зеленый", "Желтый");

        System.out.println("Круг (радиус = 5.0):");
        circle.printInfo();

        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println("Прямоугольник (длина = 4.0, ширина = 6.0):");
        rectangle.printInfo();

        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println("Треугольник (стороны = 3.0, 4.0, 5.0):");
        triangle.printInfo();

        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println("Работа с массивом фигур");
        GeometricFigure[] figures = {
                new Circle(3.0, "Розовый", "Серый"),
                new Rectangle(2.0, 5.0, "Оранжевый", "Коричневый"),
                new Triangle(6.0, 8.0, 10.0, "Фиолетовый", "Золотой")
        };

        for (int i = 0; i < figures.length; i++) {
            System.out.println(" Фигура " + (i + 1) + ":");
            figures[i].printInfo();
        }
    }
}