public class Ointment extends Medicine {
    private String area;

    public Ointment(String name, ActiveSubstance substance, String area) {
        super(name, substance);
        this.area = area;
    }

    public void apply() {
        System.out.println("Нанесение мази на " + area);
    }
}
