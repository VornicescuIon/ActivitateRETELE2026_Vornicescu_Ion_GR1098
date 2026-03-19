package ro.ase.cts.animals;

public class Animal {
    protected String name;
    protected int age;
    protected FoodType food;

    public Animal(String name, int age, FoodType foodType){
        this.name=name;
        this.age=age;
        this.food=foodType;
    }

    public void eat(String name) {

    }
}
