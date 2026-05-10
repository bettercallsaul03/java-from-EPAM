public class Observer extends Thread {

    private Bank bank;

    public Observer(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {

        while (true) {

            for (CashDesk cashDesk : bank.getCashDesks()) {

                if (cashDesk.getCash() > 7000) {

                    bank.moveToStorage(cashDesk, 2000);
                }

                if (cashDesk.getCash() < 2000) {

                    bank.refillCashDesk(cashDesk, 3000);
                }
            }

            try {

                Thread.sleep(2000);

            } catch (InterruptedException e) {

                break;
            }
        }
    }
}
