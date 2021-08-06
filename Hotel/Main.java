import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel(new Room[]{
                new Room(true, 2, true, true, 11),
                new Room(true, 3, true, true, 12),
                new Room(true, 2, false, false, 13),
                new Room(false, 1, false, false, 14),
                new Room(true, 4, true, true, 21),
                new Room(true, 3, false, false, 22),
                new Room(false, 3, false, false, 23),
                new Room(true, 4, true, false, 24),
                new Room(true, 4, true, true, 31),
                new Room(true, 1, false, false, 32),
                new Room(false, 1, false, false, 33),
                new Room(true, 4, true, false, 34),

        });

        MenuArg mainMenu = new MenuArg() {{
            add(new MenuArg("getFreeRooms", "показать все свободные комнаты") {{
                add("-wc");
                add("-wifi");
                add(new MenuArg("-sleepingPlace", "", "кол-во"));
            }});
            add(new MenuArg("reserve", "забронировать комнату", "номер_комнаты"));
            add(new MenuArg("getReservedRooms", "показать зарезервированные номера"));
            add(new MenuArg("exit", "выход из системы"));
        }};

        System.out.println(mainMenu.list());

        boolean exit = false;
        do {

            System.out.print("Введите команду:");

            Map<String, String> com = mainMenu.checkInput(scanner.nextLine().split(" "));

            String command = com.getOrDefault("command", "error");

            switch (command) {
                case "getFreeRooms" -> {
                    boolean wc = com.containsKey("-wc");
                    boolean wifi = com.containsKey("-wifi");
                    boolean conditioner = com.containsKey("-conditioner");
                    int sleepingPlace = Integer.parseInt(com.getOrDefault("-sleepingPlace", "0"));
                    hotel.getFreeRooms(wc, sleepingPlace, conditioner, wifi, com.size() - 1);
                }
                case "reserve" -> hotel.reserve(Integer.parseInt(com.get("value")));
                case "getReservedRooms" -> hotel.getReservedRooms();
                case "exit" -> exit = true;
                case "error" -> {
                    System.out.println("Ошибка в запросе!");
                    System.out.println(mainMenu.list());
                }
            }
        } while (!exit);
    }
}