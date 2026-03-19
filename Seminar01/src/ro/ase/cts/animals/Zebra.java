package ro.ase.cts.animals;

public class Zebra extends Animal{
    private int stripeCnt;

    public Zebra(String name, int age, FoodType foodType, int stripeCnt) {
        super(name, age, foodType);
        this.stripeCnt = stripeCnt;
    }

    @Override
    public void eat(String zooKeeperName){
        System.out.println(("Zookeeper "+zooKeeperName+" feeds Zebra "+name+" with "+super.food));
    }
}
