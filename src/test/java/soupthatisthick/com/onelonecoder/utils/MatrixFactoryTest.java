package soupthatisthick.com.onelonecoder.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soupthatisthick.com.utils.logger.Logger;

import static soupthatisthick.com.onelonecoder.ArcadeApplication.VIEW_HEIGHT;
import static soupthatisthick.com.onelonecoder.ArcadeApplication.VIEW_WIDTH;

public class MatrixFactoryTest {


    private Matrix observed;

    @BeforeEach
    public void onSetup() {
    }

    @AfterEach
    public void onTearDown() {
        Logger.info("observed: " + observed.toString());
    }

    @Test
    public void createSimpleProjection() {

        // Build the projection matrix
        final double fNear = 0.1D;
        final double fFar = 1000.0D;
        final double fEyeFovDeg = 90;  // Degrees
        final double fFovDeg = fEyeFovDeg/2D;
        final double fAspectRatio = VIEW_HEIGHT / VIEW_WIDTH;
//        final double fFovRad = 1.0d / ( Math.tan(fEyeFovDeg * 0.5d / 180D * 3.14159D) );
        final double fFovRad = 1D / Math.toRadians(fFovDeg);


        Logger.info("fNear        : " + fNear);
        Logger.info("fFar         : " + fFar);
        Logger.info("fAspectRatio : " + fAspectRatio);
        Logger.info("fEyeFovDeg   : " + fEyeFovDeg);
        Logger.info("fFovDeg      : " + fFovDeg);
        Logger.info("fFovRad      : " + fFovRad);

        observed = MatrixFactory.createSimpleProjection(
                fAspectRatio,
                fFovRad,
                fNear,
                fFar
        );
    }
}
