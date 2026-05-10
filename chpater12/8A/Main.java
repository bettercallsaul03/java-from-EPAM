import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Bank bank = new Bank();

        CashDesk cashDesk1 = new CashDesk(1, 5000);
        CashDesk cashDesk2 = new CashDesk(2, 1500);

        bank.addCashDesk(cashDesk1);
        bank.addCashDesk(cashDesk2);

        Observer observer = new Observer(bank);
        observer.start();

        Account acc1 = new Account(1, "Иван", 10000);
        Account acc2 = new Account(2, "Ольга", 8000);

        List<Operation> operations1 = new ArrayList<>();

        operations1.add(new Operation(OperationType.СНЯТИЕ, 1000));
        operations1.add(new Operation(OperationType.ПОПОЛНЕНИЕ, 2000));
        operations1.add(new Operation(OperationType.ПЕРЕВОД, 1500, acc2));

        Client client1 = new Client("Клиент 1", acc1, operations1);

        List<Operation> operations2 = new ArrayList<>();

        operations2.add(new Operation(OperationType.ОПЛАТА, 500));
        operations2.add(new Operation(OperationType.ОБМЕН, 300));

        Client client2 = new Client("Клиент 2", acc2, operations2);

        Cashier cashier1 = new Cashier(client1, cashDesk1);
        Cashier cashier2 = new Cashier(client2, cashDesk2);

        cashier1.start();
        cashier2.start();

        cashier1.join();
        cashier2.join();

        Thread.sleep(5000);

        observer.interrupt();

        System.out.println("\nДеньги в хранилище: " + bank.getStorage());
    }
}
