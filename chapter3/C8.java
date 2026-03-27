class Complex {
    double real;
    double imag;

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }
}

class Point {
    Complex coordinate;

    public Point(Complex coordinate) {
        this.coordinate = coordinate;
    }

    public double distanceToOrigin() {
        return Math.sqrt(coordinate.real * coordinate.real + coordinate.imag * coordinate.imag);
    }

    public double distanceTo(Point other) {
        double dx = this.coordinate.real - other.coordinate.real;
        double dy = this.coordinate.imag - other.coordinate.imag;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public String toString() {
        return "(" + coordinate.real + " + " + coordinate.imag + "i)";
    }
}

public class Main {
    public static void main(String[] args) {
        Complex c1 = new Complex(3, 4);
        Point p1 = new Point(c1);

        Complex c2 = new Complex(6, 8);
        Point p2 = new Point(c2);

        System.out.println("Точка 1: " + p1.toString());
        System.out.println("Расстояние до нуля: " + p1.distanceToOrigin());

        System.out.println("Точка 2: " + p2.toString());
        System.out.println("Расстояние между точками: " + p1.distanceTo(p2));
    }
}
