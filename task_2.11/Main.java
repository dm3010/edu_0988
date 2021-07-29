/*
Задание: Дан массив с числами. Узнайте сколько элементов с начала массива надо сложить, чтобы в сумме получилось больше 10-ти.
*/

public class Main {
    public static void main(String[] args) {

        int[] a = {2, 3, 4, 5};

        int count = 0;
        int sum = 0;
        while (sum <= 10 && count < a.length) {
            sum += a[count++];
        }
        System.out.println(sum > 10 ? count : "Нет достаточного кол-ва элементов");
    }
}
