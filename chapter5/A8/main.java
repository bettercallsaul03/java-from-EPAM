/* 
Задание
Создать класс City с внутренним классом, с помощью объектов которого
можно хранить информацию о проспектах, улицах, площадях.
*/

public class Main {
    public static void main(String[] args) {
        City city = new City("Москва");

        city.addStreet("Тверская", "Улица");
        city.addStreet("Ленинский", "Проспект");
        city.addStreet("Красная", "Площадь");

        city.showInfo();
    }
}
