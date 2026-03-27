import java.util.*;

public class Train {
    public List<Vagon> vagons = new ArrayList<>();

    public void addVagon(Vagon v) {
        vagons.add(v);
    }

    public int calculateTotalPassengers() {
        int total = 0;
        for (Vagon v : vagons) {
            total += v.getPassengers();
        }
        return total;
    }

    public int calculateTotalBaggage() {
        int total = 0;
        for (Vagon v : vagons) {
            total += v.getBaggage();
        }
        return total;
    }

    public void sortVagons() {
        vagons.sort(Comparator.comparingInt(v -> v.comfortLevel));
    }

    public List<Vagon> findVagonsByCapacity(int min, int max) {
        List<Vagon> found = new ArrayList<>();
        for (Vagon v : vagons) {
            if (v.getPassengers() >= min && v.getPassengers() <= max) {
                found.add(v);
            }
        }
        return found;
    }
}
