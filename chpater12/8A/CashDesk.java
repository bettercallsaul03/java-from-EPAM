public class CashDesk {

    private int number;
    private double cash;

    public CashDesk(int number, double cash) {
        this.number = number;
        this.cash = cash;
    }

    public synchronized void addCash(double amount) {
        cash += amount;
    }

    public synchronized boolean takeCash(double amount) {

        if (cash >= amount) {
            cash -= amount;
            return true;
        }

        return false;
    }

    public double getCash() {
        return cash;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Касса №" + number + " | Наличные: " + cash;
    }
}
