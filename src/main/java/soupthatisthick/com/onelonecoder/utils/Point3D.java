package soupthatisthick.com.onelonecoder.utils;

public class Point3D {
    public double x, y, z;

    public Point3D() {
        this(0d, 0d, 0d);
    }
    public Point3D(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return String.format("{%5f, %5f, %5f}", x, y, z);
    }

}
