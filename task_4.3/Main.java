/*
Реализовать метод boolean fight(Cat anotherCat):
реализовать механизм драки котов в зависимости от их веса, возраста и силы.
Нужно сравнить каждый критерий по отдельности, и победителем будет тот, у которого больше "победивших" критериев.
Метод должен определять, выиграли ли мы (this) бой или нет, т.е. возвращать true, если выиграли и false - если нет.
Если ничья и никто не выиграл, возвращаем либо true либо false, по вашему усмотрению
Требования:
•	Класс Cat должен содержать конструктор без параметров.
•	Класс Cat должен содержать всего три поля: age, weight и strength. Поля должны быть публичные.
•	В классе Cat должен быть метод fight.
•	В методе fight реализовать механизм драки котов в зависимости от их веса, возраста и силы согласно условию.
•	Метод должен возвращать одно и тоже значение, если мы деремся с одним и тем же котом.
•	Если некий кот1 выигрывает у кота кот2, то кот2 должен проигрывать коту кот1.
*/

public class Main {
    public static void main(String[] args) {

        Cat kitty = new Cat() {{
            age = 1;
            weight = 2;
            strength = 6;
        }};

        Cat oldCat = new Cat() {{
            age = 9;
            weight = 3;
            strength = 5;
        }};

        boolean kittyWin = kitty.fight(oldCat);
        boolean oldCatWin = oldCat.fight(kitty);
        System.out.println("Котенок победил старого кота: " + kittyWin);
        System.out.println("Старый кот победил котенка: " + oldCatWin);
    }
}

class Cat {
    public int age;
    public int weight;
    public int strength;

    public Cat() {
    }

    public boolean fight(Cat anotherCat) {
        int balance = 0;
        balance += this.age > anotherCat.age ? 1 : -1; // fight 1: age
        balance += this.weight > anotherCat.weight ? 1 : -1; // fight 2: weight
        balance += this.strength > anotherCat.strength ? 1 : -1; // fight 3: strength
        return balance > 0;
    }
}
