import java.util.List;

public class Client {

    private String name;
    private Account account;
    private List<Operation> operations;

    public Client(String name, Account account, List<Operation> operations) {
        this.name = name;
        this.account = account;
        this.operations = operations;
    }

    public String getName() {
        return name;
    }

    public Account getAccount() {
        return account;
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
