package ro.ase.cts.main;

import ro.ase.cts.animals.Lion;
import ro.ase.cts.animals.FoodType;
import ro.ase.cts.zoo.Zoo;
import ro.ase.cts.zoo.ZooKeeper;

public class Main {
    public static void main(String[] args){
        Zoo zoo = new Zoo("Zoo Baneasa", new ZooKeeper("Luisa"));

        zoo.addAnimal(new Lion("Leon", 5, FoodType.Meat, 200));
    }
}