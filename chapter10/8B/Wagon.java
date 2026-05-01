import java.io.Serializable;

public abstract class Wagon implements Serializable {
    private static final long serialVersionUID = 1L;

    private static String railwayName = "Российские железные дороги";

    private int number;
    private ComfortLevel comfortLevel;
    private int passengerCount;
    private int baggageCount;

    private transient String serviceNote;

    public Wagon(int number, ComfortLevel comfortLevel, int passengerCount, int baggageCount) {
        this.number = number;
        this.comfortLevel = comfortLevel;
        this.passengerCount = passengerCount;
        this.baggageCount = baggageCount;
    }

    public int getNumber() {
        return number;
    }

    public ComfortLevel getComfortLevel() {
        return comfortLevel;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public int getBaggageCount() {
        return baggageCount;
    }

    public void setBaggageCount(int baggageCount) {
        this.baggageCount = baggageCount;
    }

    public String getServiceNote() {
        return serviceNote;
    }

    public void setServiceNote(String serviceNote) {
        this.serviceNote = serviceNote;
    }

    public static String getRailwayName() {
        return railwayName;
    }

    public static void setRailwayName(String railwayName) {
        Wagon.railwayName = railwayName;
    }

    @Override
    public String toString() {
        return "Вагон №" + number +
                ", тип: " + comfortLevel.getTitle() +
                ", пассажиров: " + passengerCount +
                ", багажа: " + baggageCount +
                ", примечание: " + serviceNote;
    }
}
