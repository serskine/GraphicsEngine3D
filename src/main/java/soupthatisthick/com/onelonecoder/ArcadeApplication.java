package soupthatisthick.com.onelonecoder;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import soupthatisthick.com.onelonecoder.utils.*;
import soupthatisthick.com.utils.desc.Describe;
import soupthatisthick.com.utils.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static soupthatisthick.com.onelonecoder.utils.Matrix.multiplyMatrixVector;

public class ArcadeApplication extends Application implements ConsoleGameEngine {

    public static final double VIEW_WIDTH = 640;
    public static final double VIEW_HEIGHT = 480;

    private GraphicsContext getGraphicsContext() {
        if (stage != null) {
            canvas.setWidth(stage.getWidth());
            canvas.setHeight(stage.getHeight());
        }
        return canvas.getGraphicsContext2D();
    }
    private Stage stage;
    private Canvas canvas;

    private List<Mesh3D> allMesh = new ArrayList<>();

    private Matrix matProj;
    double fTheta = 0D;

    private boolean isRunning = false;

    @Override
    public final void start(final Stage stage) throws IOException {
        this.stage = stage;
        stage.setTitle("Arcade Application");

        final Group root = new Group();

        this.canvas = new Canvas(VIEW_WIDTH, VIEW_HEIGHT);

        onUserCreate();

        root.getChildren().add(canvas);

        final Scene scene = new Scene(root);
        stage.setResizable(true);

        stage.setScene(scene);
        stage.show();


        isRunning = true;

        run();  // This will get the updates running
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    public double getViewWidth() {
        return Math.max(1D, canvas.getWidth());
    }

    public double getViewHeight() {
        return Math.max(1D, canvas.getHeight());
    }

    public static void main(final String[] args) {
        Logger.info(String.format("%s started with arguments %s.", ArcadeApplication.class.getSimpleName(), Describe.array(args)));
        launch();
    }



    @Override
    public boolean onUserCreate() {
        final Mesh3D cubeMesh = Mesh3DFactory.createUnitCube();
        allMesh.add(cubeMesh);

//        allMesh.add(Mesh3DFactory.createXAxis());
//        allMesh.add(Mesh3DFactory.createYAxis());
//        allMesh.add(Mesh3DFactory.createZAxis());
//        allMesh.add(Mesh3DFactory.createXYZAxis());


        // Build the projection matrix
        final double viewWidth = getViewWidth();
        final double viewHeight = getViewHeight();
        final double fNear = 0.1D;
        final double fFar = 1000.0D;
        final double fEyeFovDeg = 90;  // Degrees
        final double fFovDeg = fEyeFovDeg/2D;
        final double fAspectRatio = (getViewWidth() <= 0d) ? 1d : viewHeight / viewWidth;
        final double fFovRad = 1D / Math.toRadians(fFovDeg);


        this.matProj = MatrixFactory.createSimpleProjection(
            fAspectRatio,
            fFovRad,
            fNear,
            fFar
        );

        Logger.info("matProj => " + matProj.toString());

        return true;
    }

    public boolean onUserUpdate(final double elapsedTime) {

        final double w = getGraphicsContext().getCanvas().getWidth();
        final double h = getGraphicsContext().getCanvas().getHeight();

        final GraphicsContext gc = getGraphicsContext();
        if (gc==null) {
            Logger.warning("Failed torender because the graphics context is null");
            return false;
        }

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, w, h);

        fTheta += 1D * elapsedTime / 1000D;

        final double rX = fTheta;
        final double rY = fTheta / 2D;
        final double rZ = fTheta / 2D;


        final Matrix rotationXZ = MatrixFactory.createRotationXYZ(rX, rY, rY);

        for(Mesh3D mesh : allMesh) {

            for(final Triangle3D tri : mesh.triangles) {

                final Triangle3D triProjected, triTranslated, triRotatedZ, triRotatedZX;

                triProjected = new Triangle3D();
                triTranslated = new Triangle3D();
                triRotatedZ = new Triangle3D();
                triRotatedZX = new Triangle3D();

//                // Rotate in Z-Axis
//                multiplyMatrixVector(tri.p[0], triRotatedZ.p[0], matRotZ);
//                multiplyMatrixVector(tri.p[1], triRotatedZ.p[1], matRotZ);
//                multiplyMatrixVector(tri.p[2], triRotatedZ.p[2], matRotZ);
//
//                // Rotate in X-Axis
//                multiplyMatrixVector(triRotatedZ.p[0], triRotatedZX.p[0], matRotX);
//                multiplyMatrixVector(triRotatedZ.p[1], triRotatedZX.p[1], matRotX);
//                multiplyMatrixVector(triRotatedZ.p[2], triRotatedZX.p[2], matRotX);

                // Rotate in Z-Axis
                multiplyMatrixVector(tri.p[0], triRotatedZX.p[0], rotationXZ);
                multiplyMatrixVector(tri.p[1], triRotatedZX.p[1], rotationXZ);
                multiplyMatrixVector(tri.p[2], triRotatedZX.p[2], rotationXZ);



                // Offset into the screen
                triTranslated.setCopy(triRotatedZX);

                triTranslated.p[0].z += 3D;
                triTranslated.p[1].z += 3D;
                triTranslated.p[2].z += 3D;

                // Project triangles from 3D --> 2D
                multiplyMatrixVector(triTranslated.p[0], triProjected.p[0], matProj);
                multiplyMatrixVector(triTranslated.p[1], triProjected.p[1], matProj);
                multiplyMatrixVector(triTranslated.p[2], triProjected.p[2], matProj);

                // Scale into view
                final double scalar = 1D;
                triProjected.p[0].x += scalar; triProjected.p[0].y += scalar;
                triProjected.p[1].x += scalar; triProjected.p[1].y += scalar;
                triProjected.p[2].x += scalar; triProjected.p[2].y += scalar;

                final double screenWidth = getViewWidth();
                final double screenHeight = getViewHeight();

//                Logger.info(String.format("screenSize: %3.2f x %3.2f", screenWidth, screenHeight));

                final double half = 0.5D;

                triProjected.p[0].x *= half * screenWidth;
                triProjected.p[0].y *= half * screenHeight;
                triProjected.p[1].x *= half * screenWidth;
                triProjected.p[1].y *= half * screenHeight;
                triProjected.p[2].x *= half * screenWidth;
                triProjected.p[2].y *= half * screenHeight;

                drawTriangle(gc, triProjected);

//                Logger.info(
//                          "\n------------------------------------------------"
//                        + "\n tri              : " + tri.toString()
//                        + "\n => triRotatedZ   : " + triRotatedZ.toString()
//                        + "\n => triRotatedZX  : " + triRotatedZX.toString()
//                        + "\n => triTranslated : " + triTranslated.toString()
//                        + "\n => triProjected  : " + triProjected.toString()
//                        + "\n------------------------------------------------"
//                );
            }
        }

        return true;
    }

    @Override
    public boolean isDone() {
        return !isRunning;   // The thread is never done.
    }

    void drawTriangle(final GraphicsContext gc, final Triangle3D triangle) {

        final double[] xs = triangle.getXs();
        final double[] ys = triangle.getYs();

        final Paint linePaint = Color.YELLOW;
        gc.setStroke(linePaint);
        gc.setLineWidth(1);
        gc.strokePolygon(xs, ys, 3);

//        gc.strokeLine(triangle.p[0].x, triangle.p[0].y, triangle.p[1].x, triangle.p[1].y);
//        gc.strokeLine(triangle.p[1].x, triangle.p[1].y, triangle.p[2].x, triangle.p[2].y);
//        gc.strokeLine(triangle.p[2].x, triangle.p[2].y, triangle.p[0].x, triangle.p[0].y);

    }






}