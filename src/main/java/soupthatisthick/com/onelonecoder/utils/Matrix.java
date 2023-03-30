package soupthatisthick.com.onelonecoder.utils;

import com.almasb.fxgl.core.math.Vec3;

public class Matrix {
    public final double[][] cell;
    public final int numRows, numCols;
    Matrix(final int numRows, final int numCols) {
        this.cell = new double[numRows][numCols];
        this.numRows = numRows;
        this.numCols = numCols;
    }

    public void setAll(double value) {
        for(int row=0; row<numRows; row++) {
            for(int col=0; col<numCols; col++) {
                cell[row][col] = value;
            }
        }
    }

    public static void multiplyMatrixVector(final Point3D i, final Point3D o, final Matrix m) {
        o.x = i.x * m.cell[0][0] + i.y * m.cell[1][0] + i.z * m.cell[2][0] + m.cell[3][0];
        o.y = i.x * m.cell[0][1] + i.y * m.cell[1][1] + i.z * m.cell[2][1] + m.cell[3][1];
        o.z = i.x * m.cell[0][2] + i.y * m.cell[1][2] + i.z * m.cell[2][2] + m.cell[3][2];

        double w = i.x * m.cell[0][3] + i.y * m.cell[1][3] + i.z * m.cell[2][3] + m.cell[3][3];

        if (w != 0.0f)
        {
            o.x /= w;
            o.y /= w;
            o.z /= w;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("{\n");
        for(int row=0; row<numRows; row++) {
            sb.append("  {");
            for(int col=0; col<numCols; col++) {
                sb.append(String.format("%3.2f", cell[row][col]));
                if (col<numCols-1) {
                    sb.append(", ");
                }
            }
            sb.append("}");
            if (row < numRows-1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    public Matrix multiply(final Matrix m) {
        return multiply(this, m);
    }

    /**
     * Static helper method to perform multiplication of two matricies
     * @param m1
     * @param m2
     * @return
     */
    public static Matrix multiply(Matrix m1, Matrix m2) {
        int m1rows = m1.numRows;
        int m1cols = m1.numCols;
        int m2rows = m2.numRows;
        int m2cols = m2.numCols;

        if (m1cols != m2rows) {
            throw new IllegalArgumentException("Matrix dimensions do not match");
        }

        final Matrix result = new Matrix(m1rows, m2cols);

        for (int i = 0; i < m1rows; i++) {
            for (int j = 0; j < m2cols; j++) {
                for (int k = 0; k < m1cols; k++) {
                    result.cell[i][j] += m1.cell[i][k] * m2.cell[k][j];
                }
            }
        }

        return result;
    }


}
