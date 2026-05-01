import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Train implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private List<Wagon> wagons;

    public Train(String name) {
        this.name = name;
        this.wagons = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Wagon> getWagons() {
        return wagons;
    }

    public void addWagon(Wagon wagon) {
        wagons.add(wagon);
    }

    public void removeWagonByNumber(int number) {
        wagons.removeIf(wagon -> wagon.getNumber() == number);
    }

    public int getTotalPassengers() {
        int sum = 0;
        for (Wagon wagon : wagons) {
            sum += wagon.getPassengerCount();
        }
        return sum;
    }

    public int getTotalBaggage() {
        int sum = 0;
        for (Wagon wagon : wagons) {
            sum += wagon.getBaggageCount();
        }
        return sum;
    }

    public void sortWagonsByComfort() {
        wagons.sort(Comparator.comparingInt(w -> w.getComfortLevel().getRank()));
    }

    public List<Wagon> findWagonsByPassengerRange(int min, int max) {
        List<Wagon> result = new ArrayList<>();
        for (Wagon wagon : wagons) {
            int passengers = wagon.getPassengerCount();
            if (passengers >= min && passengers <= max) {
                result.add(wagon);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Поезд: ").append(name).append("\n");
        for (Wagon wagon : wagons) {
            sb.append(wagon).append("\n");
        }
        return sb.toString();
    }
}
