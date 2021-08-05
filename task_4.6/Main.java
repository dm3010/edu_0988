/*
Задача вывести на экран такое сообщение (для объекта gosha):
Меня зовут "имя объекта"
Имя моей мамы: "имя мамы"
Имя моего папы: "имя папы"
У меня две бабушки, бабушка "имя бабушки" и "имя бабушки"
У меня два деда, деда "имя деда" и "имя деда"
*/

public class Main {
    public static void main(String[] args) {
        Person valera = new Person("Валера", "Иванов", 69, null, null);
        Person olga = new Person("Ольга", "Иванова", 67, null, null);
        Person oleg = new Person("Олег", "Петров", 65, null, null);
        Person jula = new Person("Юля", "Петрова", 67, null, null);
        Person alex = new Person("Алексей", "Иванов", 37, olga, valera);
        Person eva = new Person("Ева", "Иванова", 37, jula, oleg);
        Person gosha = new Person("Гоша", "Иванов", 10, eva, alex);

        System.out.println(gosha.genealogic(3,"="));
        System.out.println();
        System.out.println(eva.genealogic(3,"="));


//        System.out.println(sd(5));
        //        System.out.println("Мама Алексея: " + alex.getMother().getName());
//        System.out.println("Бабушка Гоши: " + gosha.getMother().getMother().getName());
//        System.out.println();
//        System.out.println("Меня зовут " + gosha.getName());
//        System.out.println("Имя моей мамы " + gosha.getMother().getName());
//        System.out.println("Имя моего папы " + gosha.getFather().getName());
//        System.out.printf("У меня две бабушки, бабушка %s и %s%n",
//                gosha.getFather().getMother().getName(),
//                gosha.getMother().getMother().getName());
//        System.out.printf("У меня два деда, деда %s и %s%n",
//                gosha.getFather().getFather().getName(),
//                gosha.getMother().getFather().getName());
    }

    static String sd(int d) {
        String f = "";
        if(d>0)
            f += sd(d-1);
        return f+"+";
    }


}

class Person {
    private String name;
    private String lastname;
    private int age;
    private Person mother;
    private Person father;

    public Person(String name, String lastname, int age, Person mother, Person father) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.mother = mother;
        this.father = father;
    }

    public String genealogic(int v, String s) {
        String info = "";
        info += s + name + " " + lastname + ", " + age ;

        if(v>1) {
            if(father!=null) {
                info += "\n" + father.genealogic(v-1, s+s.charAt(0));
            }
            if(mother!=null) {
                info += "\n" + mother.genealogic(v-1, s+s.charAt(0));
            }
            if(father == null && mother == null)
                info += ": сирота";
        }
        return info;
    }

    public String getLastname() { return lastname; }

    public String getName() {
        return name;
    }

    public Person getMother() { return mother; }

    public Person getFather() {
        return father;
    }

}
