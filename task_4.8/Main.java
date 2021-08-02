import java.util.List;
import java.util.Scanner;

public class Main extends Hotel {

    static Scanner scanner = new Scanner(System.in);

    Menu mainMenu = new Menu();
    Menu subMenu = new Menu();
    Room room = null;

    public Main(Room[] rooms) {
        super(rooms);
    }

    public static void main(String[] args) {

        Main main = new Main(roomsInit());
        main.menuInit();
        main.mainMenu.run();

    }

    private void chooseRoom() {
        while (true) {
            System.out.print("Введите номер комнаты или 0 для возврата: ");
            String command = scanner.nextLine();
            if (command.equals("0")) break;
            List<Room> list = this.getRooms("n=" + Byte.parseByte(command));
            if (list.isEmpty()) {
                System.out.println("Комнаты с таким номером не найдено");
                continue;
            }
            room = list.get(0);
            System.out.println("Выбрана комната №" + room.getRoomNumber());
            subMenu.run();
            break;
        }
    }

    public void menuInit() {

        mainMenu.add("Главное меню (? - подсказка)", this, new MenuItem[]{
                new MenuItem(Hotel::printRooms, "WiFi", "1", "Показать комнаты с WiFi"),
                new MenuItem(Hotel::printRooms, "WC", "2", "Показать комнаты с WC"),
                new MenuItem(Hotel::printRooms, "Eat", "3", "Показать комнаты с Eat"),
                new MenuItem(x -> x.printRooms("q=" + x.getIntInput()), "4", "Показать комнаты по количеству спальных мест"),
                new MenuItem(Hotel::getFreeRooms, "5", "Показать свободные комнаты"),
                new MenuItem(Main::chooseRoom, "6", "6: Выбор комнаты"),
                new MenuItem(x -> x.mainMenu.exit(), "0", "0: Выход из системы"),
                new MenuItem(x -> x.mainMenu.printHelp(), "?", "Список команд")
        });

        subMenu.add("Меню комнаты (? - подсказка)", this, new MenuItem[]{
                new MenuItem(x -> System.out.println(x.room), "1", "Показать свойства комнаты"),
                new MenuItem(x -> x.reserveRoom(room.getRoomNumber()), "2", "Забронировать комнату"),
                new MenuItem(x -> x.releaseRoom(room.getRoomNumber()), "3", "Освободить комнату"),
                new MenuItem(x -> x.subMenu.exit(), "0", "Выход в главное меню"),
                new MenuItem(x -> x.subMenu.printHelp(), "?", "Список команд")
        });
    }

    public static Room[] roomsInit() {
        return new Room[]{
                new Room((byte) 1, false, false, true, (byte) 11),
                new Room((byte) 2, true, true, false, (byte) 12),
                new Room((byte) 1, false, true, true, (byte) 13),
                new Room((byte) 3, true, false, false, (byte) 21),
                new Room((byte) 2, false, false, false, (byte) 22),
                new Room((byte) 1, true, true, true, (byte) 23),
                new Room((byte) 3, false, true, false, (byte) 31),
                new Room((byte) 3, true, true, false, (byte) 32),
                new Room((byte) 1, false, false, true, (byte) 33),
        };
    }

    private int getIntInput() {
        System.out.print("Введите количество: ");
        return Integer.parseInt(scanner.nextLine());
    }

}


