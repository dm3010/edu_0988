/*
 *  ***Гостиница***
 *  1) Гостиница
 *  2) Номера (кол-во мест(1-3), сан.узел(есть/нет), питаение(есть/нет), wifi(есть/нет), свободен/занят)
 *
 *   *Задание для лабораторной работы №4*
 *   Освободить комнату
 *   Вывести свойства комнаты
 *   Показать комнаты с WiFi
 *   Показать комнаты с WC
 *   Показать комнаты с Eat
 *   Показать комнаты по кол-ву спальных мест
 *   Отобразить список команд
 * */

import java.util.Scanner;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);

    private String title;
    private Object instance;
    private MenuItem[] items;
    private boolean exit = false;

    public void add(String title, Object instance, MenuItem[] items) {
        this.title = title;
        this.items = items;
        this.instance = instance;
    }

    public void run() {
        exit = false;
        System.out.println(title);
        while (!exit) {
            System.out.print(": ");
            String command = scanner.nextLine();
            for (MenuItem item : items)
                if (item.command.equals(command)) {
                    System.out.println(item.title);
                    item.run(instance);
                }
        }
    }

    public void exit() {
        exit = true;
    }

    public void printHelp() {
        for (MenuItem item : items) {
            System.out.println(item.command + " " + item.title);
        }
    }

}
