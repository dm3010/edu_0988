/*
 * 2) Номера (кол-во мест(1-3), сан.узел(есть/нет), питаение(есть/нет), wifi(есть/нет), свободен/занят)
 */
public class Room {
    private final byte quantity;
    private final boolean wc;
    private final boolean eat;
    private final boolean wifi;
    private boolean isFree;
    private final byte roomNumber;

    public Room(byte quantity, boolean wc, boolean eat, boolean wifi, byte roomNumber) {
        this.quantity = quantity;
        this.wc = wc;
        this.eat = eat;
        this.wifi = wifi;
        this.isFree = true;
        this.roomNumber = roomNumber;
    }


    public byte getRoomNumber() {
        return roomNumber;
    }

    public byte getQuantity() {
        return quantity;
    }

    public boolean isWc() {
        return wc;
    }

    public boolean isEat() {
        return eat;
    }

    public boolean isWifi() {
        return wifi;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    @Override
    public String toString() {
        return "Комната{" +
               "номер=" + roomNumber +
               ", вместительность=" + quantity + " мест" +
               ", wc=" + wc +
               ", eat=" + eat +
               ", wifi=" + wifi +
               ", isFree=" + isFree +
               '}';
    }

}
