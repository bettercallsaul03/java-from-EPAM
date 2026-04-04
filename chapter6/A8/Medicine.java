public class Medicine {
    protected String name;
    protected ActiveSubstance substance;

    public Medicine(String name, ActiveSubstance substance) {
        this.name = name;
        this.substance = substance;
    }

    public void addSubstance(ActiveSubstance substance) {
        this.substance = substance;
    }

    public void changeStatus(String status) {
        if (substance != null) {
            substance.setStatus(status);
        }
    }

    public void showInfo() {
        System.out.println("Препарат: " + name);
        if (substance != null) {
            System.out.println("Вещество: " + substance.getName());
            System.out.println("Статус: " + substance.getStatus());
        }
    }

    public double getDosage(double weight) {
        if (substance != null) {
            return substance.calculateDosage(weight);
        }
        return 0;
    }
}
