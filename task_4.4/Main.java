/*
Создай классы Dog, Cat, Mouse.
Добавь по три поля в каждый класс, на твой выбор.
Создай объекты для героев мультика Том и Джерри.
Так много, как только вспомнишь.
Пример:
Mouse jerryMouse = new Mouse("Jerry", 12 , 5),
где 12 - высота в см,
5 - длина хвоста в см.
Требования:
•	Создай класс Dog.
•	Создай класс Cat.
•	В классе Dog должно быть три переменные.
•	В классе Cat должно быть три переменные.
•	Должен быть создан хотя бы один объект типа Mouse.
•	Должен быть создан хотя бы один объект типа Dog.
•	Должен быть создан хотя бы один объект типа Cat.
*/

public class Main {
    public static void main(String[] args) {
        Dog spikeDog = new Dog("Spike", "bulldog", true);
        Cat tomCat = new Cat("Tom", "Blue/White", true);
        Cat taraCat = new Cat("Tara", "White/Grey", false);
        Mouse jerryMouse = new Mouse("Jerry", 12, 5);
    }
}

class Dog {
    String name;
    String breed;
    boolean isAGoodBoy;

    public Dog(String name, String breed, boolean isAGoodBoy) {
        this.name = name;
        this.breed = breed;
        this.isAGoodBoy = isAGoodBoy;
    }
}

class Cat {
    String name;
    String coatColor;
    boolean isMale;

    public Cat(String name, String coatColor, boolean isMale) {
        this.name = name;
        this.coatColor = coatColor;
        this.isMale = isMale;
    }
}

class Mouse {
    String name;
    int height;
    int tail;

    public Mouse(String name, int height, int tail) {
        this.name = name;
        this.height = height;
        this.tail = tail;
    }
}
