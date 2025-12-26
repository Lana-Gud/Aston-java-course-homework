public class Dog extends Animal {// задание 1
    static int dogCount = 0;
    public Dog (String name){
        super(name);
        dogCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= 500) {
            super.run(distance);
        } else {
            System.out.println(this.name + " не может бежать так далеко.");
        }
    }
    @Override
    public void swim(int distance){
        if (distance<=10){
            super.swim(distance);
        }
        else {
            System.out.println( this.name + " не может плыть так долго.");
        }
    }
}
