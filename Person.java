package Model.Animal;

import java.util.ArrayList;

public class Person {
    private int number;
    private ArrayList<Animals> AnimalsArrayList;
    public Person(int number){
        this.AnimalsArrayList.set(0,new Rat(number));
        this.AnimalsArrayList.set(1,new Cat(number));
        this.AnimalsArrayList.set(2,new Dog(number));
        this.AnimalsArrayList.set(3,new Wolf(number));
        this.AnimalsArrayList.set(4,new Tiger(number));
        this.AnimalsArrayList.set(5,new Leopard(number));
        this.AnimalsArrayList.set(6,new Lion(number));
        this.AnimalsArrayList.set(7,new Elephant(number));
    }
    public ArrayList<Animals> getAnimalsArrayList() {
        return AnimalsArrayList;
    }

    public void setAnimalsArrayList(ArrayList<Animals> animalsArrayList) {
        AnimalsArrayList = animalsArrayList;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
