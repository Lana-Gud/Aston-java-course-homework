public class FactorialCalculator {
    public int factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Факториал отрицательного числа не существует");
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }
}
