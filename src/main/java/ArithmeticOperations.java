public class ArithmeticOperations {
    public int sum(int x, int y) { return x + y; }
    public int subtraction(int x, int y) { return x - y; }
    public int multiply(int x, int y) { return x * y; }
    public int division(int x, int y) {
        if (y == 0) throw new ArithmeticException("Деление на ноль невозможно");
        return x / y;
    }
}
