/*
Задание: Даны переменные a и b. Проверьте, что a делится без остатка на b. Если это так - выведите 'Делится' и результат деления, иначе выведите 'Делится с остатком' и остаток от деления.
*/

public class Main {
    public static void main(String[] args) {
        int a = 10;
        int b = 7;

        int r = a % b;

        System.out.println(r == 0 ? "Делится" : "Делится с остатком: " + r);
    }
}
