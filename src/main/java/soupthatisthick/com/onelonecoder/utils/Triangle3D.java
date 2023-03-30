package soupthatisthick.com.onelonecoder.utils;

import soupthatisthick.com.utils.desc.Describe;

public class Triangle3D {
    public Point3D p[];

    public Triangle3D() {
        this(new Point3D(), new Point3D(), new Point3D());
    }
    public Triangle3D(final Point3D p0, final Point3D p1, final Point3D p2) {
        this.p = new Point3D[] {p0, p1, p2};
    }

    public double[] getXs() {
        return new double[] {
            p[0].x, p[1].x, p[2].x
        };
    }

    public double[] getYs() {
        return new double[] {
                p[0].y, p[1].y, p[2].y
        };
    }

    public double[] getZs() {
        return new double[] {
                p[0].z, p[1].z, p[2].z
        };
    }

    public void setCopy(final Triangle3D t) {
        p[0].x = t.p[0].x;
        p[0].y = t.p[0].y;
        p[0].z = t.p[0].z;

        p[1].x = t.p[1].x;
        p[1].y = t.p[1].y;
        p[1].z = t.p[1].z;

        p[2].x = t.p[2].x;
        p[2].y = t.p[2].y;
        p[2].z = t.p[2].z;
    }

    @Override
    public String toString() {
        return Describe.array(p);
    }



}
