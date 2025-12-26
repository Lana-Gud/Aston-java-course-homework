import java.util.*;

public class PhoneBook {
    private Map<String, List<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    // Метод добавления записи
    public void add(String surname, String phoneNumber) {
        // Если фамилии нет в справочнике, создаем новый список
        phoneBook.putIfAbsent(surname, new ArrayList<>());

        // Добавляем номер в список для данной фамилии
        phoneBook.get(surname).add(phoneNumber);

        System.out.println("Добавлена запись: " + surname + " - " + phoneNumber);
    }

    // Метод поиска номеров по фамилии
    public List<String> get(String surname) {
        // Возвращаем список номеров или пустой список, если фамилии нет
        return phoneBook.getOrDefault(surname, Collections.emptyList());
    }

    // Метод для печати всего справочника
    public void printAll() {
        System.out.println("\nТелефонный справочник:");
        for (Map.Entry<String, List<String>> entry : phoneBook.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
