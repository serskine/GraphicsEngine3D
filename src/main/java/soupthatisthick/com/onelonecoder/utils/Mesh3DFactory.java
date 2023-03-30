package soupthatisthick.com.onelonecoder.utils;

public class Mesh3DFactory {

    public static Mesh3D createXAxis() {
        final Mesh3D axis = new Mesh3D();

        final Point3D p0 = new Point3D(0D, 0D, 0D);
        final Point3D p1 = new Point3D(1d, 0d, 0d);

        axis.triangles.add(new Triangle3D(p0, p1, p1));

        return axis;
    }

    public static Mesh3D createYAxis() {
        final Mesh3D axis = new Mesh3D();

        final Point3D p0 = new Point3D(0D, 0D, 0D);
        final Point3D p1 = new Point3D(0d, 1d, 0d);

        axis.triangles.add(new Triangle3D(p0, p1, p1));

        return axis;
    }

    public static Mesh3D createXYZAxis() {
        final Mesh3D axis = new Mesh3D();

        final Point3D p0 = new Point3D(0D, 0D, 0D);
        final Point3D p1 = new Point3D(1d, 1d, 1d);

        axis.triangles.add(new Triangle3D(p0, p1, p1));

        return axis;
    }

    public static Mesh3D createZAxis() {
        final Mesh3D axis = new Mesh3D();

        final Point3D p0 = new Point3D(0D, 0D, 0D);
        final Point3D p1 = new Point3D(0d, 0d, 1d);

        axis.triangles.add(new Triangle3D(p0, p1, p1));

        return axis;
    }
    public static Mesh3D createUnitCube() {

        final Mesh3D unitCube = new Mesh3D();

        final Point3D p000 = new Point3D(0D, 0D, 0D);
        final Point3D p001 = new Point3D(0D, 0D, 1D);
        final Point3D p010 = new Point3D(0D, 1D, 0D);
        final Point3D p011 = new Point3D(0D, 1D, 1D);
        final Point3D p100 = new Point3D(1D, 0D, 0D);
        final Point3D p101 = new Point3D(1D, 0D, 1D);
        final Point3D p110 = new Point3D(1D, 1D, 0D);
        final Point3D p111 = new Point3D(1D, 1D, 1D);

        final Triangle3D tSouthA = new Triangle3D(p000, p010, p110);
        final Triangle3D tSouthB = new Triangle3D(p000, p110, p100);

        final Triangle3D tEastA = new Triangle3D(p100, p110, p111);
        final Triangle3D tEastB = new Triangle3D(p100, p111, p101);

        final Triangle3D tNorthA = new Triangle3D(p101, p111, p011);
        final Triangle3D tNorthB = new Triangle3D(p101, p011, p001);

        final Triangle3D tWestA = new Triangle3D(p001, p011, p010);
        final Triangle3D tWestB = new Triangle3D(p001, p010, p000);

        final Triangle3D tTopA = new Triangle3D(p010, p011, p111);
        final Triangle3D tTopB = new Triangle3D(p010, p111, p110);

        final Triangle3D tBottomA = new Triangle3D(p101, p001, p000);
        final Triangle3D tBottomB = new Triangle3D(p101, p000, p100);

        unitCube.triangles.add(tSouthA);
        unitCube.triangles.add(tSouthB);

        unitCube.triangles.add(tEastA);
        unitCube.triangles.add(tEastB);

        unitCube.triangles.add(tNorthA);
        unitCube.triangles.add(tNorthB);

        unitCube.triangles.add(tWestA);
        unitCube.triangles.add(tWestB);

        unitCube.triangles.add(tTopA);
        unitCube.triangles.add(tTopB);

        unitCube.triangles.add(tBottomA);
        unitCube.triangles.add(tBottomB);

        return unitCube;
    }

    public static Mesh3D createOriginCubeCube() {

        final Mesh3D originCube = new Mesh3D();

        final Point3D p000 = new Point3D(-1, -1, -1);
        final Point3D p001 = new Point3D(-1, -1, 1);
        final Point3D p010 = new Point3D(-1, 1, -1);
        final Point3D p011 = new Point3D(-1, 1, 1);
        final Point3D p100 = new Point3D(1, -1, -1);
        final Point3D p101 = new Point3D(1, -1, 1);
        final Point3D p110 = new Point3D(1, 1, -1);
        final Point3D p111 = new Point3D(1, 1, 1);

        final Triangle3D tNorthA = new Triangle3D(p101, p111, p011);
        final Triangle3D tNorthB = new Triangle3D(p101, p011, p001);
        final Triangle3D tSouthA = new Triangle3D(p000, p010, p110);
        final Triangle3D tSouthB = new Triangle3D(p000, p110, p100);
        final Triangle3D tEastA = new Triangle3D(p100, p110, p111);
        final Triangle3D tEastB = new Triangle3D(p100, p111, p101);
        final Triangle3D tWestA = new Triangle3D(p001, p011, p010);
        final Triangle3D tWestB = new Triangle3D(p001, p010, p000);
        final Triangle3D tTopA = new Triangle3D(p010, p011, p111);
        final Triangle3D tTopB = new Triangle3D(p010, p111, p110);
        final Triangle3D tBottomA = new Triangle3D(p101, p001, p000);
        final Triangle3D tBottomB = new Triangle3D(p101, p000, p100);

        originCube.triangles.add(tNorthA);
        originCube.triangles.add(tNorthB);
        originCube.triangles.add(tSouthA);
        originCube.triangles.add(tSouthB);
        originCube.triangles.add(tEastA);
        originCube.triangles.add(tEastB);
        originCube.triangles.add(tWestA);
        originCube.triangles.add(tWestB);
        originCube.triangles.add(tTopA);
        originCube.triangles.add(tTopB);
        originCube.triangles.add(tBottomA);
        originCube.triangles.add(tBottomB);

        return originCube;
    }
}
