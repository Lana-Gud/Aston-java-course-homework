public class Cat extends Animal {// задание 1
    static int catCount = 0;
    private boolean isFull ;
    private int appetite;
    public Cat (String name , int appetite){
        super(name);
        this.appetite = appetite;
        this.isFull = false;
        catCount++;
    }

    public boolean isFull() {
        return isFull;
    }
    public void eat(Bowl bowl) {
        if (bowl.takeFood(appetite)) {
            isFull = true;
            System.out.println(name + " поел и теперь сыт");
        } else {
            System.out.println(name + " не смог поесть, в миске мало еды");
        }
    }

    @Override
    public void run(int distance) {
        if (distance <= 200) {
            super.run(distance);
        } else {
            System.out.println(this.name + " не может бежать так далеко.");
        }
    }
    @Override
    public void swim(int distance){
        System.out.println("кот не умеет плавать");
    }
}
