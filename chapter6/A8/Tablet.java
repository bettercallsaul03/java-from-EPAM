public class Tablet extends Medicine {
    private int count;

    public Tablet(String name, ActiveSubstance substance, int count) {
        super(name, substance);
        this.count = count;
    }

    public void take(int amount) {
        if (amount <= count) {
            count -= amount;
            System.out.println("Принято таблеток: " + amount);
        } else {
            System.out.println("Недостаточно таблеток");
        }
    }
}
