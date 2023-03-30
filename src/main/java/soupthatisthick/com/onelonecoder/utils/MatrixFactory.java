package soupthatisthick.com.onelonecoder.utils;

import soupthatisthick.com.utils.logger.Logger;

public class MatrixFactory {
    public static final Matrix createSimpleProjection(
        final double fAspectRatio,  // (width / height)
        final double fFovRad,
        final double fNear,
        final double fFar
    ) {
        Logger.info(String.format("\nfAspectRatio := %f\nfFovRad := %f\nfNear := %f\nfFar := %f", fAspectRatio, fFovRad, fNear, fFar));
        final Matrix matProj = new Matrix(4, 4);

        matProj.cell[0][0] = fAspectRatio * fFovRad;
        matProj.cell[0][1] = 0d;
        matProj.cell[0][2] = 0d;
        matProj.cell[0][3] = 0d;

        matProj.cell[1][0] = 0d;
        matProj.cell[1][1] = fFovRad;
        matProj.cell[1][2] = 0d;
        matProj.cell[1][3] = 0d;

        matProj.cell[2][0] = 0d;
        matProj.cell[2][1] = 0d;
        matProj.cell[2][2] = fFar / (fFar - fNear);
        matProj.cell[2][3] = 1d;

        matProj.cell[3][0] = 0d;
        matProj.cell[3][1] = 0d;
        matProj.cell[3][2] = (-fFar * fNear) / (fFar - fNear);
        matProj.cell[3][3] = 0d;

        return matProj;
    }

    public static final Matrix createIdentity(final int numDimensions) {
        final Matrix matIdentity = new Matrix(numDimensions, numDimensions);

        matIdentity.setAll(0D);
        for(int i=0; i<numDimensions; i++) {
            matIdentity.cell[i][i] = 1D;
        }
        return matIdentity;
    }

    public static final Matrix createEmpty(final int numRows, final int numCols) {
        final Matrix m = new Matrix(numRows, numCols);
        m.setAll(0D);
        return m;
    }

    public static final Matrix createRotationX(final double theta) {
        final Matrix m = new Matrix(4, 4);

        m.cell[0][0] = 1;
        m.cell[0][1] = 0;
        m.cell[0][2] = 0;
        m.cell[0][3] = 0;

        m.cell[1][0] = 0;
        m.cell[1][1] = Math.cos(theta);
        m.cell[1][2] = Math.sin(theta);
        m.cell[1][3] = 0;

        m.cell[2][0] = 0;
        m.cell[2][1] = -Math.sin(theta);
        m.cell[2][2] = Math.cos(theta);
        m.cell[2][3] = 0;

        m.cell[3][0] = 0;
        m.cell[3][1] = 0;
        m.cell[3][2] = 0;
        m.cell[3][3] = 1;

        return m;
    }

    public static final Matrix createRotationY(final double theta) {
        final Matrix m = new Matrix(4, 4);

        m.cell[0][0] = Math.cos(theta);
        m.cell[0][1] = 0;
        m.cell[0][2] = Math.sin(theta);
        m.cell[0][3] = 0;

        m.cell[1][0] = 0;
        m.cell[1][1] = 1;
        m.cell[1][2] = 0;
        m.cell[1][3] = 0;

        m.cell[2][0] = -Math.sin(theta);
        m.cell[2][1] = 0;
        m.cell[2][2] = Math.cos(theta);
        m.cell[2][3] = 0;

        m.cell[3][0] = 0;
        m.cell[3][1] = 0;
        m.cell[3][2] = 0;
        m.cell[3][3] = 1;

        return m;
    }

    public static final Matrix createRotationZ(final double theta) {
        final Matrix m = new Matrix(4, 4);

        m.cell[0][0] = Math.cos(theta);
        m.cell[0][1] = -Math.sin(theta);
        m.cell[0][2] = 0;
        m.cell[0][3] = 0;

        m.cell[1][0] = Math.sin(theta);
        m.cell[1][1] = Math.cos(theta);
        m.cell[1][2] = 0;
        m.cell[1][3] = 0;

        m.cell[2][0] = 0;
        m.cell[2][1] = 0;
        m.cell[2][2] = 1;
        m.cell[2][3] = 0;

        m.cell[3][0] = 0;
        m.cell[3][1] = 0;
        m.cell[3][2] = 0;
        m.cell[3][3] = 1;

        return m;
    }


    public static Matrix createRotationXYZ(double rX, double rY, double rZ) {
        final Matrix rotationX = createRotationX(rX);
        final Matrix rotationY = createRotationY(rY);
        final Matrix rotationZ = createRotationZ(rZ);
        final Matrix result = rotationX.multiply(rotationY).multiply(rotationZ);
        return result;
    }

    public static Matrix createTranslationMatrix(double tx, double ty, double tz) {
        final Matrix m = new Matrix(4, 4);

        m.cell[0][0] = 1;
        m.cell[0][1] = 0;
        m.cell[0][2] = 0;
        m.cell[0][3] = tx;

        m.cell[1][0] = 0;
        m.cell[1][1] = 1;
        m.cell[1][2] = 0;
        m.cell[1][3] = ty;

        m.cell[2][0] = 0;
        m.cell[2][1] = 0;
        m.cell[2][2] = 1;
        m.cell[2][3] = tz;

        m.cell[3][0] = 0;
        m.cell[3][1] = 0;
        m.cell[3][2] = 0;
        m.cell[3][3] = 1;

        return m;
    }
}
