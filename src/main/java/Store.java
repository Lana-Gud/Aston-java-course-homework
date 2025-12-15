import java.time.LocalDate;

public class Store {

    public static void main(String[] args) {
        Product[] productsArray = new Product[5];
        productsArray[0] = new Product("Samsung S25 Ultra", LocalDate.of(2025, 2, 1),
                "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Product("MacBook Pro", LocalDate.of(2024, 10, 15),
                "Apple", "USA", 2999, false);
        productsArray[2] = new Product("AirPods Pro", LocalDate.of(2024, 12, 1),
                "Apple", "China", 249, true);
        productsArray[3] = new Product("iPad Air", LocalDate.of(2024, 8 , 20),
                "Apple", "China", 799, false);
        productsArray[4] = new Product("Galaxy Watch", LocalDate.of(2024 , 11 , 10),
                "Samsung", "Korea", 399, true);

        for (Product p : productsArray) {
            p.info();
            System.out.println(); // пустая строка между товарами

        }
    }
}



