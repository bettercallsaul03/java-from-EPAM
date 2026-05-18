import java.util.*;

class Point {
    double x, y;
    Point(double x, double y) { this.x = x; this.y = y; }
}

class Quad {
    Point[] p = new Point[4];
    double[] sides = new double[4];
    double d1, d2; 
    String type;

    public Quad(Point p1, Point p2, Point p3, Point p4) {
        p[0] = p1; p[1] = p2; p[2] = p3; p[3] = p4;
        calculateProperties();
    }

    private void calculateProperties() {
        for (int i = 0; i < 4; i++) {
            Point start = p[i];
            Point end = p[(i + 1) % 4];
            sides[i] = Math.sqrt(Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2));
        }
      
        d1 = Math.sqrt(Math.pow(p[2].x - p[0].x, 2) + Math.pow(p[2].y - p[0].y, 2));
        d2 = Math.sqrt(Math.pow(p[3].x - p[1].x, 2) + Math.pow(p[3].y - p[1].y, 2));

        defineType();
    }

    private void defineType() {
        boolean sidesEqual = (Math.abs(sides[0] - sides[1]) < 0.001 && Math.abs(sides[1] - sides[2]) < 0.001 && Math.abs(sides[2] - sides[3]) < 0.001);
        boolean diagsEqual = (Math.abs(d1 - d2) < 0.001);
        boolean oppositeSidesEqual = (Math.abs(sides[0] - sides[2]) < 0.001 && Math.abs(sides[1] - sides[3]) < 0.001);

        if (sidesEqual && diagsEqual) type = "Квадрат";
        else if (sidesEqual) type = "Ромб";
        else if (oppositeSidesEqual && diagsEqual) type = "Прямоугольник";
        else type = "Произвольный";
    }

    public double getPerimeter() {
        return sides[0] + sides[1] + sides[2] + sides[3];
    }

    public double getArea() {
       
        double area = 0.5 * Math.abs(p[0].x * p[1].y + p[1].x * p[2].y + p[2].x * p[3].y + p[3].x * p[0].y -
                                    (p[1].x * p[0].y + p[2].x * p[1].y + p[3].x * p[2].y + p[0].x * p[3].y));
        return area;
    }

    public String toString() {
        return type + " [Периметр: " + getPerimeter() + ", Площадь: " + getArea() + "]";
    }
}

public class QuadManager {
    public static void main(String[] args) {
        List<Quad> quads = new ArrayList<>();
        quads.add(new Quad(new Point(0, 0), new Point(0, 2), new Point(2, 2), new Point(2, 0))); 
        quads.add(new Quad(new Point(0, 0), new Point(0, 3), new Point(2, 3), new Point(2, 0))); 
        quads.add(new Quad(new Point(0, 0), new Point(1, 2), new Point(3, 2), new Point(2, 0))); 
        quads.add(new Quad(new Point(0, 0), new Point(1, 4), new Point(5, 3), new Point(4, 0))); 

        Map<String, List<Quad>> groups = new HashMap<>();
        for (Quad q : quads) {
            groups.computeIfAbsent(q.type, k -> new ArrayList<>()).add(q);
        }

        for (String type : groups.keySet()) {
            List<Quad> currentGroup = groups.get(type);
            System.out.println("\n--- Группа: " + type + " (Количество: " + currentGroup.size() + ") ---");

            Quad maxArea = Collections.max(currentGroup, Comparator.comparingDouble(Quad::getArea));
            Quad minArea = Collections.min(currentGroup, Comparator.comparingDouble(Quad::getArea));

            System.out.println("Наибольший по площади: " + maxArea);
            System.out.println("Наименьший по площади: " + minArea);
        }
    }
}
