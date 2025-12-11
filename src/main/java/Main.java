import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        boolean result = checkSumRange(12, 20);
        System.out.println("Результат для checkSumRange: " + result);
        checkNumber(40);
        System.out.println("Результат для isNegative" + isNegative(-5));
        printString("Hello", 3);
        System.out.println("2024 високосный? " + checYear(2024));
        invertArray();
        to100();
        processArray();
        fillDiagonals();
        int[] arrayResult = createArray(5, 8); // измени имя
        System.out.println(Arrays.toString(arrayResult));
    }

    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() {
        int a = 4;
        int b = 12;
        int sum = a + b;
        if (sum >= 0)
            System.out.println("Сумма положительная");
        else
            System.out.println("Сумма отрицательная");
    }

    public static void printColor() {
        int value = 99;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Жёлтый");
        } else {
            System.out.println("Зелёный");
        }
    }
    public static void compareNumbers(){
        int a=18;
        int b=22;
        if (a >= b){
            System.out.println("a >= b");
        }else {
            System.out.println("a < b");
        }
    }
    public static boolean checkSumRange (int a, int b){
        int sum = a + b;
        return sum >= 10 && sum<=20;
    }
    public static void  checkNumber( int x){
        if (x>=0){
            System.out.println("положительное");
        }else{
            System.out.println("отрицательное");
        }
    }
    public static boolean isNegative(int number) {
        if (number < 0) {
            return true;
        } else {
            return false;
        }
    }
    public static void printString (String a, int b) {
        while (b > 0) {
            System.out.println(a);
            b--;
        }
    }
    public static boolean checYear(int yaer){
        return (yaer%4==0 && yaer%100 !=0 || yaer% 400 == 0);
    }
    public static void invertArray() {
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
    public static void to100 (){
        int[] arr = new int[100];
             for (int i = 0; i < arr.length; i++) {
                 arr[i] = i + 1;}
        System.out.println(Arrays.toString(arr));
    }
    public static void processArray (){
        int[] app = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        for (int i=0; i<app.length; i++){
            if (app[i]<6){
                app[i]=app[i]*2;
            }
        }
        System.out.println(Arrays.toString(app));
    }
    public static void fillDiagonals() {
        int size = 5;
        int[][] mat= new int[size][size];

        for (int i = 0; i < size; i++) {
            mat[i][i] = 1;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }
    public static int[]  createArray(int len ,int initialValue) {
        int[] a = new int[len];
        {
            for (int i = 0; i < a.length; i++) {
                a[i] = initialValue;
            }

        }
        return a;
    }
}
