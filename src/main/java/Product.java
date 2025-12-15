import java.time.LocalDate;
public class Product {
    String name;
    LocalDate productionDate;
    String manufacturer;
    String countryOrigin;
    int price;
    boolean bookingStatus;

    public Product(String name, LocalDate productionDate,
    String manufacturer, String countryOrigin, int price, boolean bookingStatus){
        this.name=name;
        this.productionDate= productionDate;
        this.price=price;
        this.bookingStatus=bookingStatus;
        this.manufacturer=manufacturer;
        this.countryOrigin=countryOrigin;

    }
    public void info() {
        System.out.println("Название: " + this.name);
        System.out.println("Дата производства: " + this.productionDate);
        System.out.println("Производитель: " + this.manufacturer);
        System.out.println("Страна происхождения: " + this.countryOrigin);
        System.out.println("Цена: " + this.price + " руб.");
        System.out.println("Бронирование: " + (this.bookingStatus ? "забронировано" : "свободно"));
    }
    public static void main(String[] args) {
        Product product1 = new Product("Чипсы картофельные", LocalDate.of(2025, 5, 15), "ООО Веселая картошечка", "Россия", 270, true);
        product1.info();
    }
}
