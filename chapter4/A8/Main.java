public class Main {
    public static void main(String[] args) {
        Point center = new Point(5, 5);
        Square mySquare = new Square(center, 10, "Зеленый");

        System.out.println("Создан: " + mySquare);

        mySquare.scale(1.5); 
        System.out.println("После растяжения: " + mySquare);

        mySquare.rotate(90); 
        System.out.println("После поворота на 90 градусов: " + mySquare);

        mySquare.color = "Золотой"; 
        System.out.println("Итоговый вариант: " + mySquare);
    }
}
