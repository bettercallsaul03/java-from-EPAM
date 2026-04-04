/*
Лекарственный препарат. Возможности: добавить действующее вещест-
во; рассчитать дозировку; провести исследование вещества; изменить ста-
тус вещества (запрещенное, по рецепту, разрешенное); получить и изме-
нить информации о действующем веществе. Добавить дополнительные
возможности для Лекарства, Мази, Таблетки и Раствора.
*/

public class Main {
    public static void main(String[] args) {
        ActiveSubstance substance = new ActiveSubstance("Парацетамол", 0.5, "Разрешенное");

        Tablet tablet = new Tablet("Таблетки", substance, 10);
        tablet.showInfo();
        System.out.println("Дозировка: " + tablet.getDosage(70));
        tablet.take(2);

        Ointment ointment = new Ointment("Мазь", substance, "кожа");
        ointment.apply();

        Solution solution = new Solution("Раствор", substance, 100);
        solution.use(20);
    }
}
