public class Square {
    public Point[] points = new Point[4];
    public Segment[] sides = new Segment[4];
    public String color;

    public Square(Point center, double sideLength, String color) {
        this.color = color;
        double half = sideLength / 2.0;
        points[0] = new Point(center.x - half, center.y - half);
        points[1] = new Point(center.x - half, center.y + half);
        points[2] = new Point(center.x + half, center.y + half);
        points[3] = new Point(center.x + half, center.y - half);
        updateSides();
    }

    public void updateSides() {
        for (int i = 0; i < 4; i++) {
            sides[i] = new Segment(points[i], points[(i + 1) % 4]);
        }
    }

    public Point getCenter() {
        double centerX = (points[0].x + points[2].x) / 2.0;
        double centerY = (points[0].y + points[2].y) / 2.0;
        return new Point(centerX, centerY);
    }

    public double getSideLength() {
        double dx = points[0].x - points[1].x;
        double dy = points[0].y - points[1].y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void scale(double factor) {
        Point center = getCenter();
        for (Point p : points) {
            p.x = center.x + (p.x - center.x) * factor;
            p.y = center.y + (p.y - center.y) * factor;
        }
        updateSides();
    }

    public void rotate(double degrees) {
        double radians = Math.toRadians(degrees);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        Point center = getCenter();

        for (Point p : points) {
            double dx = p.x - center.x;
            double dy = p.y - center.y;
            p.x = center.x + dx * cos - dy * sin;
            p.y = center.y + dx * sin + dy * cos;
        }
        updateSides();
    }

    @Override
    public String toString() {
        return "Квадрат [Цвет: " + color + ", Сторона: " + String.format("%.2f", getSideLength()) + "]";
    }
}
