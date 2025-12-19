public class Bowl {// задание 1
    private int food;
    public Bowl() {
        this.food = 0;
    }
    public void addFood(int amount){
        this.food += amount;
    }
    public  boolean takeFood(int requestedAmount){
        if (requestedAmount <= this.food){
           this.food = this.food - requestedAmount;
           return true;
        } else {
            return false;
        }
    }
}
