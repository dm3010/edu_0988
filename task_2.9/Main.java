/*
Задание: Дан массив с числами. Проверьте, что в этом массиве есть число 5. Если есть - выведите 'да', а если нет - выведите 'нет'.
*/

public class Main {
    public static void main(String[] args) {

        int[] a = {2, 3, 4, 5};

        boolean result = false;
        for (int j : a) {
            if (j == 5) {
                result = true;
                break;
            }
        }
        System.out.println(result ? "да" : "нет");
    }
}
