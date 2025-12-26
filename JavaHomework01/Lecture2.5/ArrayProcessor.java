import java.util.Random;

public class ArrayProcessor {

    static class MyArraySizeException extends Exception {
        public MyArraySizeException(String message) {
            super(message);
        }
    }

    static class MyArrayDataException extends Exception {
        private final int row;
        private final int col;

        public MyArrayDataException(int row, int col, String value) {
            super(String.format("Неверные данные в ячейке [%d][%d]: '%s'", row, col, value));
            this.row = row;
            this.col = col;
        }

        public int getRow() { return row; }
        public int getCol() { return col; }
    }

    public static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException("Массив должен иметь 4 строки. Передано: " + array.length);
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException(
                        String.format("Строка %d должна иметь 4 столбца. Передано: %d", i, array[i].length)
                );
            }
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, array[i][j]);
                }
            }
        }

        return sum;
    }

    public static void demonstrateArrayIndexException() {
        System.out.println(" Демонстрация ArrayIndexOutOfBoundsException ===");

        int[] arr = {1, 2, 3, 4, 5};

        try {
            System.out.println("Попытка доступа к arr[10]: " + arr[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано ArrayIndexOutOfBoundsException: " + e.getMessage());
            System.out.println("Длина массива: " + arr.length +
                    ", максимальный индекс: " + (arr.length - 1));
        }

        try {
            System.out.println("\nВторая демонстрация:");
            for (int i = 0; i <= arr.length; i++) { // Ошибка: i <= arr.length
                System.out.println("arr[" + i + "] = " + arr[i]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано при обходе массива: индекс " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        // ТЕСТ 1: Корректный массив
        System.out.println("\n Тест 1: Корректный массив ");
        String[][] correctArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int result = processArray(correctArray);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // ТЕСТ 2: Массив неправильного размера
        System.out.println("\n Тест 2: Массив неправильного размера");
        String[][] wrongSizeArray = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };

        try {
            int result = processArray(wrongSizeArray);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException e) {
            System.out.println("MyArraySizeException: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("MyArrayDataException: " + e.getMessage());
        }

        // ТЕСТ 3: Массив с нечисловыми данными
        System.out.println("\n Тест 3: Массив с нечисловыми данными ");
        String[][] wrongDataArray = {
                {"1", "2", "3", "4"},
                {"5", "six", "7", "8"},  // Ошибка здесь
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int result = processArray(wrongDataArray);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException e) {
            System.out.println("MyArraySizeException: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("MyArrayDataException: " + e.getMessage());
            System.out.println("Координаты ошибки: строка " + e.getRow() + ", столбец " + e.getCol());
        }

        // ТЕСТ 4: Смешанный тест
        System.out.println("\n Тест 4: Смешанный тест ");
        String[][] mixedArray = {
                {"10", "20", "30", "40"},
                {"50", "abc", "70", "80"},  // Ошибка
                {"90", "100"}
                // Не хватает 4-й строки и столбцов
        };

        try {
            int result = processArray(mixedArray);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException e) {
            System.out.println("MyArraySizeException: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("MyArrayDataException: " + e.getMessage());
        }

        // 5. Демонстрация ArrayIndexOutOfBoundsException
        demonstrateArrayIndexException();


        System.out.println("\n Тест 5: Случайные данные ");
        Random random = new Random();
        String[][] randomArray = new String[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (random.nextDouble() > 0.2) {
                    randomArray[i][j] = String.valueOf(random.nextInt(100));
                } else {
                    randomArray[i][j] = "invalid";
                }
            }
        }

        System.out.println("Сгенерированный массив:");
        for (String[] row : randomArray) {
            for (String cell : row) {
                System.out.printf("%-8s", cell);
            }
            System.out.println();
        }

        // Обрабатываем
        try {
            int result = processArray(randomArray);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}