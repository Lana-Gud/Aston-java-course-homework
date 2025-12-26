import java.util.*;

public class Student {
    private String name;
    private String group;
    private int course;
    private List<Integer> grades;

    public Student(String name, String group, int course, List<Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public int getCourse() {
        return course;
    }

    public double getAverageGrade() {
        if (grades.isEmpty()) return 0;
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public void nextCourse() {
        this.course++;
    }

    @Override
    public String toString() {
        return String.format("Студент: %s, Группа: %s, Курс: %d, Средний балл: %.2f",
                name, group, course, getAverageGrade());
    }

    // Метод удаления студентов со средним баллом < 3
    public static void removeFailedStudents(List<Student> students) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getAverageGrade() < 3.0) {
                System.out.println("Отчислен: " + student.getName());
                iterator.remove();
            }
        }
    }

    // Метод перевода на следующий курс
    public static void promoteStudents(List<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3.0) {
                student.nextCourse();
                System.out.println("Переведен на следующий курс: " + student.getName());
            }
        }
    }

    // Метод печати студентов по курсу
    public static void printStudents(List<Student> students, int course) {
        System.out.println("\nСтуденты " + course + " курса:");
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println("- " + student.getName());
            }
        }
    }
}
