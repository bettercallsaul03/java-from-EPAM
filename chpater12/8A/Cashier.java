public class Cashier extends Thread {

    private Client client;
    private CashDesk cashDesk;

    public Cashier(Client client, CashDesk cashDesk) {
        this.client = client;
        this.cashDesk = cashDesk;
    }

    @Override
    public void run() {

        System.out.println("\nКассир обслуживает: " + client.getName());

        for (Operation operation : client.getOperations()) {

            switch (operation.getType()) {

                case ПОПОЛНЕНИЕ:

                    client.getAccount().deposit(operation.getAmount());
                    cashDesk.addCash(operation.getAmount());

                    System.out.println("Пополнение: " + operation.getAmount());

                    break;

                case СНЯТИЕ:

                    if (client.getAccount().withdraw(operation.getAmount())
                            && cashDesk.takeCash(operation.getAmount())) {

                        System.out.println("Снятие: " + operation.getAmount());

                    } else {

                        System.out.println("Недостаточно средств");
                    }

                    break;

                case ПЕРЕВОД:

                    if (client.getAccount().withdraw(operation.getAmount())) {

                        operation.getTarget().deposit(operation.getAmount());

                        System.out.println("Перевод: " + operation.getAmount());
                    }

                    break;

                case ОПЛАТА:

                    if (client.getAccount().withdraw(operation.getAmount())) {

                        System.out.println("Оплата: " + operation.getAmount());
                    }

                    break;

                case ОБМЕН:

                    if (client.getAccount().withdraw(operation.getAmount())) {

                        System.out.println("Обмен валюты: " + operation.getAmount());
                    }

                    break;
            }
        }

        System.out.println(client.getAccount());
    }
}
