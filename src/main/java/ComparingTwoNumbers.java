public class ComparingTwoNumbers {
    public String compare(int x, int y) {
        if (x == y) return "Значения " + x + " и " + y + " равны";
        else if (x < y) return "Первое значение (" + x + ") меньше второго (" + y + ")";
        else return "Первое значение (" + x + ") больше второго (" + y + ")";
    }
}
