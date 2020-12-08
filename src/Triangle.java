public class Triangle {
    int x1, y1, x2, y2, y3, x3;
    boolean isTriangleGrouped = false;

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.y3 = y3;
        this.x3 = x3;
    }

    public boolean isTrianglePoint() {
        if (x1 == x2 && x1 == x3 && y1 == y2 && y2 == y3) return true;
        return false;
    }

    public boolean isTriangleExist() {
        double[] b = this.lengthArray();
        if (b[1] + b[2] > b[0] && b[2] + b[0] > b[1] && b[1] + b[0] > b[2]) return true;
        return false;
    }

    public double length(int x1, int x2, int y1, int y2) {
        return Math.pow(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2), 0.5);
    }

    public double[] lengthArray() {
        double[] a = new double[3];
        a[0] = length(x1, x2, y1, y2);
        a[1] = length(x1, x3, y1, y3);
        a[2] = length(x3, x2, y3, y2);
        return a;
    }

    public Triangle(Point point1, Point point2, Point point3) {
        this.x1 = point1.x;
        this.y1 = point1.y;
        this.x2 = point2.x;
        this.y2 = point2.y;
        this.x3 = point3.x;
        this.y3 = point3.y;
    }

    public class Point {

        private int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
