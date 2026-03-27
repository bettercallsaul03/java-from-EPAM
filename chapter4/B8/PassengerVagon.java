public class PassengerVagon extends Vagon {
    public int passengers;
    public int baggageItems;

    public PassengerVagon(int number, int comfort, int passengers, int baggage) {
        super(number, comfort);
        this.passengers = passengers;
        this.baggageItems = baggage;
    }

    @Override
    public int getPassengers() {
        return passengers;
    }

    @Override
    public int getBaggage() {
        return baggageItems;
    }

    @Override
    public String toString() {
        return super.toString() + " | Пассажиров: " + passengers + " | Багаж: " + baggageItems;
    }
}
