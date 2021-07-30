/*
Задание: Сделайте функцию, которая параметрами принимает 2 числа. Если эти числа равны - пусть функция вернет true, а если не равны - false.
*/


public class Main {
    public static void main(String[] args) {
        System.out.println(compare(2, 2));
    }

    public static boolean compare(int a, int b) {
        return a == b;
    }
}
