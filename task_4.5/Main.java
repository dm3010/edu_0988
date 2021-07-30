/*
Создать абстрактный класс Animal с характеристиками животного.
Создать класс Horse который наследуется от Animal, в классе Horse реализовать метод public void run(){ System.out.println("Игого, я поскакал(а)"); }
Создать класс Pegasus который наследуется от Horse, в классе Pegasus реализовать метод public void fly(){ System.out.println("Игого, я полетел(а)"); }
Создать объект лошади и вызвать метод run();
Создать объект пегаса и вызвать метод fly();
*/

public class Main {
    public static void main(String[] args) {
        Horse horse = new Horse();
        horse.name = "Лошадка";
        horse.run();

        Pegasus pegasus = new Pegasus();
        pegasus.name = "Пегас";
        pegasus.fly();
    }
}

class Pegasus extends Horse {
    public void fly() {
        System.out.println("Игого, я полетел(а)");
    }
}

class Horse extends Animal {
    public void run() {
        System.out.println("Игого, я поскакал(а)");
    }
}

abstract class Animal {
    public String name;
}
