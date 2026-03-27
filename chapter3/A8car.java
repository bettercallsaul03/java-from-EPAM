import java.util.Scanner;

class Car {
    int id;
    String brand;
    String model;
    int year;
    String color;
    double price;
    String regNumber;

    
    public Car(int id, String brand, String model, int year, String color, double price, String regNumber) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.price = price;
        this.regNumber = regNumber;
    }

    
    public String toString() {
        return "ID: " + id + " | " + brand + " " + model + " (" + year + "), Цвет: " + color + 
               ", Цена: " + price + ", Госномер: " + regNumber;
    }
}

public class CarTask {
    public static void main(String[] args) {
        
        Car[] cars = {
            new Car(1, "BMW", "X5", 2018, "Черный", 4500000, "А001АА"),
            new Car(2, "Lada", "Vesta", 2021, "Белый", 1200000, "В002ВВ"),
            new Car(3, "BMW", "M3", 2015, "Синий", 3200000, "Е003ЕЕ"),
            new Car(4, "Toyota", "Camry", 2010, "Серый", 1500000, "К004КК"),
            new Car(5, "Toyota", "Corolla", 2010, "Красный", 900000, "М005ММ")
        };

        Scanner sc = new Scanner(System.in);
        int currentYear = 2026; 

      
        System.out.print("Введите марку для поиска: ");
        String searchBrand = sc.next();
        System.out.println("- Автомобили марки " + searchBrand + " -");
        for (Car c : cars) {
            if (c.brand.equalsIgnoreCase(searchBrand)) {
                System.out.println(c);
            }
        }

    
        System.out.print("\nВведите модель: ");
        String searchModel = sc.next();
        System.out.print("Введите количество лет (n): ");
        int n = sc.nextInt();
        System.out.println("- " + searchModel + " старше " + n + " лет -");
        for (Car c : cars) {
            if (c.model.equalsIgnoreCase(searchModel) && (currentYear - c.year > n)) {
                System.out.println(c);
            }
        }

    
        System.out.print("\nВведите год выпуска: ");
        int searchYear = sc.nextInt();
        System.out.print("Введите минимальную цену: ");
        double minPrice = sc.nextDouble();
        System.out.println("- Авто " + searchYear + " года дороже " + minPrice + " -");
        for (Car c : cars) {
            if (c.year == searchYear && c.price > minPrice) {
                System.out.println(c);
            }
        }
    }
}
