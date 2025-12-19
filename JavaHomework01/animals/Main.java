public class Main {// задание 1
    public static void main(String[] args) {
        Bowl bowl = new Bowl();
        bowl.addFood(50);

        Cat[] cats = {
                new Cat("Барсик", 20),
                new Cat("Мурзик", 30),
                new Cat("Васька", 40)
        };

        for (Cat cat : cats) {
            cat.eat(bowl);
        }

        System.out.println("Состояние котов:");
        for (Cat cat : cats) {
            System.out.println(cat.name + " сыт? " + cat.isFull());
        }
        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Шарик");

        System.out.println(" Проверяем бег и плавание");


        Cat testCat = new Cat("ТестовыйКот", 10);
        testCat.run(150);
        testCat.swim(10);


        Dog testDog = new Dog("ТестоваяСобака");
        testDog.run(400);
        testDog.run(600);
        testDog.swim(5);
        testDog.swim(15);


        System.out.println(" Итоговые счетчики ");
        System.out.println("Всего животных: " + Animal.animalCount);
        System.out.println("Всего котов: " + Cat.catCount);
        System.out.println("Всего собак: " + Dog.dogCount);

        System.out.println("Добавление еды");
        bowl.addFood(100);
        System.out.println("Добавили 100 еды в миску");

        Cat cat4 = new Cat("Васька2", 40);
        cat4.eat(bowl);
        System.out.println(cat4.name + " сыт? " + cat4.isFull());
    }

}
