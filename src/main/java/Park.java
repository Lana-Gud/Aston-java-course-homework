import java.time.LocalDate;

public class Park {


    public class Attraction {
        String name;
        String workingHours;
        int price;

        public Attraction(String name, String workingHours, int price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void displayInfo() {
            System.out.println("Название атракциона: " + name);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Цена: " + price);
        }
    }

    public static void main(String[] args) {
        // Создаём парк
        Park park = new Park();

        // Создаём аттракцион ВНУТРИ парка
        Attraction attraction1 = park.new Attraction("Колесо обозрения", "10:00-22:00", 300);
        attraction1.displayInfo();
    }
}
