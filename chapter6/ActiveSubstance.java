public class ActiveSubstance {
    private String name;
    private double concentration;
    private String status;

    public ActiveSubstance(String name, double concentration, String status) {
        this.name = name;
        this.concentration = concentration;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public double getConcentration() {
        return concentration;
    }

    public void setConcentration(double concentration) {
        this.concentration = concentration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void research() {
        System.out.println("Проведение исследования вещества " + name);
    }

    public double calculateDosage(double weight) {
        return weight * concentration;
    }
}
