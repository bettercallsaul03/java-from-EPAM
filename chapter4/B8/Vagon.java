public abstract class Vagon {
    public int number;
    public int comfortLevel;

    public Vagon(int number, int comfortLevel) {
        this.number = number;
        this.comfortLevel = comfortLevel;
    }

    public abstract int getPassengers();
    public abstract int getBaggage();

    @Override
    public String toString() {
        return "Вагон #" + number + " [Уровень комфорта: " + comfortLevel + "]";
    }
}
