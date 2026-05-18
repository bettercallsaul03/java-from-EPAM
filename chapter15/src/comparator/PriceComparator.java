package comparator;

import model.Airplane;
import java.util.Comparator;

public class PriceComparator implements Comparator<Airplane> {
    public int compare(Airplane a1, Airplane a2) {
        return Integer.compare(a1.getPrice(), a2.getPrice());
    }
}
