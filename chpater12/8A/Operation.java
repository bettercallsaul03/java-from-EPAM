public class Operation {

    private OperationType type;
    private double amount;
    private Account target;

    public Operation(OperationType type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public Operation(OperationType type, double amount, Account target) {
        this.type = type;
        this.amount = amount;
        this.target = target;
    }

    public OperationType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Account getTarget() {
        return target;
    }
}
