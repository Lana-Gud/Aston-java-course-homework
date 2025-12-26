import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ЗАДАНИЕ 1: Студенты ===");

        // Создаем список студентов
        List<Student> students = new ArrayList<>();

        // Добавляем студентов с разными оценками
        students.add(new Student("Иванов Иван", "ГР-101", 1,
                Arrays.asList(5, 4, 3, 5)));
        students.add(new Student("Петрова Анна", "ГР-101", 1,
                Arrays.asList(2, 3, 2, 3)));
        students.add(new Student("Сидоров Алексей", "ГР-102", 2,
                Arrays.asList(4, 4, 4, 5)));
        students.add(new Student("Козлова Мария", "ГР-102", 2,
                Arrays.asList(5, 5, 5, 5)));
        students.add(new Student("Васильев Дмитрий", "ГР-103", 1,
                Arrays.asList(2, 2, 2, 2)));

        // Выводим всех студентов
        System.out.println("\nВсе студенты:");
        for (Student student : students) {
            System.out.println(student);
        }

        // Удаляем неуспевающих студентов
        System.out.println("\n--- Удаление неуспевающих студентов ---");
        Student.removeFailedStudents(students);

        // Переводим успешных на следующий курс
        System.out.println("\n--- Перевод на следующий курс ---");
        Student.promoteStudents(students);

        // Выводим студентов по курсам
        Student.printStudents(students, 1);
        Student.printStudents(students, 2);
        Student.printStudents(students, 3);

        System.out.println("\n\n=== ЗАДАНИЕ 2: Телефонный справочник ===");

        // Создаем телефонный справочник
        PhoneBook phoneBook = new PhoneBook();

        // Добавляем записи
        phoneBook.add("Иванов", "+7-999-111-11-11");
        phoneBook.add("Иванов", "+7-999-111-22-22"); // Еще один номер для Иванова
        phoneBook.add("Петров", "+7-999-222-33-44");
        phoneBook.add("Сидоров", "+7-999-333-55-66");
        phoneBook.add("Петров", "+7-999-444-77-88"); // Еще один номер для Петрова
        phoneBook.add("Иванов", "+7-999-555-99-00"); // Третий номер для Иванова

        // Выводим весь справочник
        phoneBook.printAll();

        // Поиск номеров по фамилии
        System.out.println("\n--- Поиск по фамилии ---");

        String[] searchSurnames = {"Иванов", "Петров", "Сидоров", "Неизвестный"};

        for (String surname : searchSurnames) {
            List<String> phones = phoneBook.get(surname);
            if (!phones.isEmpty()) {
                System.out.println(surname + ": " + phones);
            } else {
                System.out.println(surname + ": Нет в справочнике");
            }
        }

        // Тестирование однофамильцев
        System.out.println("\n--- Тест: Однофамильцы ---");
        phoneBook.add("Иванов", "+7-999-666-77-88"); // Добавляем для однофамильца
        System.out.println("Все номера Ивановых: " + phoneBook.get("Иванов"));
    }
}
