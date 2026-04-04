/* 
Задание
Создать класс City с внутренним классом, с помощью объектов которого
можно хранить информацию о проспектах, улицах, площадях.
*/

import java.util.ArrayList;

public class City {
    private String name;
    private ArrayList<Street> streets;

    public City(String name) {
        this.name = name;
        this.streets = new ArrayList<>();
    }

    public void addStreet(String name, String type) {
        streets.add(new Street(name, type));
    }

    public void showInfo() {
        System.out.println("Город: " + name);
        for (Street s : streets) {
            System.out.println(s.getType() + ": " + s.getName());
        }
    }

    class Street {
        private String name;
        private String type;

        public Street(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }
    }
}
