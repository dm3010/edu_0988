/*
Задание: Сделайте функцию, которая параметрами принимает 2 числа. Если их сумма больше 10 - пусть функция вернет true, а если нет - false.
*/


public class Main {
    public static void main(String[] args) {
        System.out.println(sumAbove10(3,7));
    }

    public static boolean sumAbove10(int a, int b) {
        return a + b > 10;
    }
}
