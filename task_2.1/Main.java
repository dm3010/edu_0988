/*Вывести на экран все возможные комбинации слов "Мама", "Мыла", "Раму".
Подсказка: их 6 штук.
Каждую комбинацию вывести с новой строки. Слова не разделять.
Пример:
МылаРамуМама
РамуМамаМыла
...
Требования:
•	Программа должна выводить текст.
•	Выведенный текст должен содержать 6 строк.
•	Текст в каждой строке должен быть уникален.
•	Должны быть выведены все возможные комбинации. */

public class Main {

    public static void main(String[] args) {

        String[] a = {"Мама", "Мыла", "Раму"};

        permute(a, 0);
    }

    public static <T> void permute(T[] a, int n) {

        if (n >= a.length - 1) {
            print(a);
            return;
        }

        permute(a, n + 1);
        for (int i = n + 1; i < a.length; i++) {
            swap(a, i, n);
            permute(a, n + 1);
            swap(a, n, i);
        }
    }

    public static <T> void print(T[] a) {
        for (T x : a) System.out.print(x);
        System.out.println();
    }

    public static <T> void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
