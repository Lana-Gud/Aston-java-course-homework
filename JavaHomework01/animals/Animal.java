public class Animal {// задание 1
    protected String name;
    static int animalCount = 0;
    public Animal(String name){
        this.name= name;
        animalCount++;
    }
    public  void run(int distance){
        System.out.println(this.name + " пробежал: "+ distance);
    }
    public  void swim(int distance){
        System.out.println(this.name + " проплыл: "+ distance);
    }

}

