public class Program {
    public static void main(String[] args) {


        // 1. Факториал
        int a = 5;
        FactorialCalculator factCalc = new FactorialCalculator();
        System.out.println("1. Факториал числа " + a + " = " + factCalc.factorial(a));

        // 2. Площадь треугольника
        TriangleArea triangle = new TriangleArea();
        System.out.println("2. Площадь треугольника (основание=4, высота=12) = " + triangle.triangle(4.0, 12.0));

        // 3. Арифметические операции
        ArithmeticOperations arithmetic = new ArithmeticOperations();
        System.out.println("3. Арифметические операции:");
        System.out.println("   Сумма 3 + 16 = " + arithmetic.sum(3, 16));
        System.out.println("   Разница 3 - 15 = " + arithmetic.subtraction(3, 15));
        System.out.println("   Произведение 3 * 18 = " + arithmetic.multiply(3, 18));
        System.out.println("   Частное 3 / 6 = " + arithmetic.division(3, 6));

        // 4. Сравнение чисел
        ComparingTwoNumbers comparator = new ComparingTwoNumbers();
        System.out.println("4. Сравнение чисел:");
        System.out.println("   compare(5, 5) -> " + comparator.compare(5, 5));
        System.out.println("   compare(3, 7) -> " + comparator.compare(3, 7));
        System.out.println("   compare(9, 4) -> " + comparator.compare(9, 4));


    }
}