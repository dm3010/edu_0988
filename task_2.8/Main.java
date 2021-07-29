/*
Задание: Дан массив с элементами [2, 3, 4, 5]. С помощью цикла for найдите произведение элементов этого массива.
*/

public class Main {
    public static void main(String[] args) {

        int[] a = {2, 3, 4, 5};

        int result = a[0];
        for (int i = 1; i < a.length; i++) {
            result *= a[i];
        }
        System.out.println(result);
    }
}
