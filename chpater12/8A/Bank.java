import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<CashDesk> cashDesks = new ArrayList<>();
    private double storage = 10000;

    public void addCashDesk(CashDesk cashDesk) {
        cashDesks.add(cashDesk);
    }

    public List<CashDesk> getCashDesks() {
        return cashDesks;
    }

    public synchronized void moveToStorage(CashDesk cashDesk, double amount) {

        if (cashDesk.takeCash(amount)) {

            storage += amount;

            System.out.println("В хранилище отправлено: " + amount);
        }
    }

    public synchronized void refillCashDesk(CashDesk cashDesk, double amount) {

        if (storage >= amount) {

            storage -= amount;
            cashDesk.addCash(amount);

            System.out.println("Касса пополнена на: " + amount);
        }
    }

    public double getStorage() {
        return storage;
    }
}
