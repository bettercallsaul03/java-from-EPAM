package model;

public class Chars {

    private String type;
    private int crewSeats;
    private int passengers;
    private int loadCapacity;
    private boolean radar;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCrewSeats() {
        return crewSeats;
    }

    public void setCrewSeats(int crewSeats) {
        this.crewSeats = crewSeats;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public boolean isRadar() {
        return radar;
    }

    public void setRadar(boolean radar) {
        this.radar = radar;
    }
}
