import java.util.Arrays;

public class Triangle {

    Point point1, point2, point3;

    public boolean isSimilarTo(Triangle other) {
        double[] b = other.getLengthArray();
        double[] c = this.getLengthArray();
        for (int i = 0; i < 3; i++) {
            if (b[i] == 0) return false;
        }
        for (int i = 0; i < 3; i++) {
            if (c[i] == 0) return false;
        }
        Arrays.sort(b);
        Arrays.sort(c);
        return b[0] / c[0] == b[1] / c[1] && b[0] / c[0] == b[2] / c[2];
    }


    public boolean isTriangleExist() {
        double[] b = this.getLengthArray();
        return b[1] + b[2] > b[0] && b[2] + b[0] > b[1] && b[1] + b[0] > b[2];
    }


    public double[] getLengthArray() {
        double[] a = new double[3];
        a[0] = point1.distanceTo(point2);
        a[1] = point2.distanceTo(point3);
        a[2] = point3.distanceTo(point1);
        return a;
    }

    public Triangle(Point point1, Point point2, Point point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    public static class Point {

        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double distanceTo(Point other) {
            return  Math.pow(Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2), 0.5);
        }

    }

}
