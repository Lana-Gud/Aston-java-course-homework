import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        printThreeWords();            // Задание 1
        checkSumSign();               // Задание 2
        printColor();                 // Задание 3
        compareNumbers();             // Задание 4
        boolean result = checkSumRange(12, 20); // Задание 5
        System.out.println("Результат для checkSumRange: " + result);
        checkNumber(40);              // Задание 6
        System.out.println("Результат для isNegative" + isNegative(-5)); // Задание 7
        printString("Hello", 3);      // Задание 8
        System.out.println("2024 високосный? " + checYear(2024)); // Задание 9
        invertArray();                // Задание 10
        to100();                      // Задание 11
        processArray();               // Задание 12
        fillDiagonals();              // Задание 13
        int[] arrayResult = createArray(5, 8); // Задание 14
        System.out.println(Arrays.toString(arrayResult));
    }

    public static void printThreeWords() { //задание 1
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() { // Задание 2
        int a = 4;
        int b = 12;
        int sum = a + b;
        if (sum >= 0)
            System.out.println("Сумма положительная");
        else
            System.out.println("Сумма отрицательная");
    }

    public static void printColor() { // Задание 3
        int value = 99;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Жёлтый");
        } else {
            System.out.println("Зелёный");
        }
    }
    public static void compareNumbers(){ // Задание 4
        int a=18;
        int b=22;
        if (a >= b){
            System.out.println("a >= b");
        }else {
            System.out.println("a < b");
        }
    }
    public static boolean checkSumRange (int a, int b){ // Задание 5
        int sum = a + b;
        return sum >= 10 && sum<=20;
    }
    public static void  checkNumber( int x){ // Задание 6
        if (x>=0){
            System.out.println("положительное");
        }else{
            System.out.println("отрицательное");
        }
    }
    public static boolean isNegative(int number) { // Задание 7
        if (number < 0) {
            return true;
        } else {
            return false;
        }
    }
    public static void printString (String a, int b) { // Задание 8
        while (b > 0) {
            System.out.println(a);
            b--;
        }
    }
    public static boolean checYear(int yaer){
        return (yaer%4==0 && yaer%100 !=0 || yaer% 400 == 0);
    } // Задание 9
    public static void invertArray() { // Задание 10
        int[] a = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                a[i] = 1;
            } else {
                a[i] = 0;
            }
        }
        System.out.println("Массив после замены: " + Arrays.toString(a));
    }
    public static void to100 (){ // Задание 11
        int[] arr = new int[100];
             for (int i = 0; i < arr.length; i++) {
                 arr[i] = i + 1;}
        System.out.println(Arrays.toString(arr));
    }
    public static void processArray (){ // Задание 12
        int[] app = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        for (int i=0; i<app.length; i++){
            if (app[i]<6){
                app[i]=app[i]*2;
            }
        }
        System.out.println(Arrays.toString(app));
    }
    public static void fillDiagonals() { // Задание 13
        int size = 5;
        int[][] mat= new int[size][size];

        for (int i = 0; i < size; i++) {
            mat[i][i] = 1;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }
    public static int[]  createArray(int len ,int initialValue) { // Задание 14
        int[] a = new int[len];
        {
            for (int i = 0; i < a.length; i++) {
                a[i] = initialValue;
            }

        }
        return a;
    }
}
