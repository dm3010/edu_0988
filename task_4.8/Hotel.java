import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hotel {
    Room[] rooms;

    public Hotel(Room[] rooms) {
        this.rooms = rooms;
    }

    public void getFreeRooms() {
        String freeRoomsList = "";
        for (Room room : rooms) {
            if (room.isFree()) freeRoomsList += room.getRoomNumber() + ", ";
        }
        System.out.println("Номера свободных комнат: " + freeRoomsList);
    }

    public void reserveRoom(byte roomNumber) {
        String info = "Такой комнаты не существует";
        for (int i = 0; i < rooms.length; i++) {
            Room room = rooms[i];
            if (room.getRoomNumber() == roomNumber && room.isFree()) {
                room.setFree(false);
                info = ("Комната номер " + roomNumber + " успешно забронированна");
                break;
            } else if (room.getRoomNumber() == roomNumber && !room.isFree()) {
                info = "Комната " + roomNumber + " занята";
                break;
            }
        }
        System.out.println(info);
    }

    public void releaseRoom(byte roomNumber) {
        String info = "Такой комнаты не существует";
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && !room.isFree()) {
                room.setFree(true);
                info = ("Комната номер " + roomNumber + " успешно освобождена");
                break;
            } else if (room.getRoomNumber() == roomNumber && room.isFree()) {
                info = "Комната " + roomNumber + " не занята";
                break;
            }
        }
        System.out.println(info);
    }

    public List<Room> getRooms(String filter) {

        List<Predicate<Room>> filters = new ArrayList();

        filter = filter.toLowerCase();
        if (filter.contains("wifi")) filters.add(Room::isWifi);
        if (filter.contains("wc")) filters.add(Room::isWc);
        if (filter.contains("eat")) filters.add(Room::isEat);
        if (filter.contains("free")) filters.add(Room::isFree);
        if (filter.matches(".*q=[0-9]+.*")) {
            int q = Integer.parseInt(filter.replaceAll(".*q=([0-9]+).*", "$1"));
            filters.add(room -> room.getQuantity() == q);
        }
        if (filter.matches(".*n=[0-9]+.*")) {
            int n = Integer.parseInt(filter.replaceAll(".*n=([0-9]+).*", "$1"));
            filters.add(room -> room.getRoomNumber() == n);
        }

        Stream<Room> stream = Arrays.stream(rooms);
        for (Predicate<Room> el : filters) stream = stream.filter(el);
        return stream.collect(Collectors.toList());
    }

    public void printRooms(String criteria) {
        Collection<Room> rooms = getRooms(criteria);
        String info = "";
        if (rooms.isEmpty()) {
            System.out.println("Нет подходящих комнат");
            return;
        }
        for (Room room : rooms) info += room.getRoomNumber() + " ";
        System.out.println("Список подходящих комнат: " + info);
    }

}
