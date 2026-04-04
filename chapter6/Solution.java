public class Solution extends Medicine {
    private double volume;

    public Solution(String name, ActiveSubstance substance, double volume) {
        super(name, substance);
        this.volume = volume;
    }

    public void use(double amount) {
        if (amount <= volume) {
            volume -= amount;
            System.out.println("Использовано раствора: " + amount);
        } else {
            System.out.println("Недостаточно раствора");
        }
    }
}
