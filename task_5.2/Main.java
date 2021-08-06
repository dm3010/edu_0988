/*
Задача: Программа определяет, какая семья (фамилию) живёт в доме с указанным номером.
Новая задача: Программа должна работать не с номерами домов, а с городами:
Пример ввода:
Москва
Ивановы
Киев
Петровы
Лондон
Абрамовичи
Лондон
Роттенберги

Лондон
Пример вывода:
[Абрамовичи, Роттенберги] */


import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> cities = new HashMap<>();

        System.out.println("Заполнение данных: ");
        while (true) {
            String city = scanner.nextLine();
            if (city.isEmpty()) { break; } // Выход из цикла ввода
            String family = scanner.nextLine();
            cities.computeIfAbsent(city, x->new ArrayList<>()).add(family);
        }

        System.out.println("Получение данных: ");
        String city = scanner.nextLine();
        if (cities.containsKey(city))
            System.out.println(cities.get(city));
        else
            System.out.println("Неверно указан город");

    }
}
