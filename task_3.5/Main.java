/*
Задание: Дан массив с числами. Выведите последовательно его элементы используя рекурсию и не используя цикл.
*/


public class Main {
    public static void main(String[] args) {
        int num[] = {4, 2, 6, 2, 65};
        print(num, 0);
    }

    public static void print(int[] a, int i) {
        if (i < a.length) {
            System.out.println(a[i]);
            print(a, i + 1);
        }
    }
}
