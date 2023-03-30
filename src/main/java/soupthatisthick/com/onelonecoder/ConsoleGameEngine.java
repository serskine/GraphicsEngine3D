package soupthatisthick.com.onelonecoder;

import javafx.concurrent.Task;
import soupthatisthick.com.utils.logger.Logger;

import java.util.Date;

public interface ConsoleGameEngine extends Runnable {
    boolean onUserCreate();
    boolean onUserUpdate(final double elapsedTime);

    boolean isDone();

    default void run() {

        final long targetFps = 30;
        final long interval = 1000 / targetFps;

        final Task<Void> gameTask = new Task<Void>() {

            @Override
            protected Void call() {
                final Date startTime = new Date();
                final long startTimeMs = startTime.getTime();

                Date lastUpdate = startTime;
                Date currentUpdate;
                long numUpdates = 0;

                while(!isDone()) {
                    currentUpdate = new Date();
                    final long currentUpdateMs = currentUpdate.getTime();
                    final long lastUptadeMs = lastUpdate.getTime();

                    final long elapsedTime = currentUpdateMs - lastUptadeMs;
                    onUserUpdate(elapsedTime);
                    numUpdates++;
                    lastUpdate = currentUpdate;

                    final long runTime = currentUpdateMs - startTimeMs;
                    final double fps = (double) numUpdates * 1000 / (runTime);

                    Logger.info(String.format("FPS: %3.2f, numUpdates: %d, totalElapsedTime %d", fps, numUpdates, runTime));

                    final long delay = interval - elapsedTime % interval;
                    pause(delay);

                }

                final Date endTime = new Date();
                final long endTimeMs = endTime.getTime();
                final long runTime = endTimeMs - startTimeMs;
                final double fps = (double) numUpdates * 1000 / (runTime);

                Logger.info(String.format("FPS: %3.2f, numUpdates: %d, totalElapsedTime %d", fps, numUpdates, runTime));
                return null;
            }
        };
        new Thread(gameTask).start();
    }

    default void pause(final long delayMs) {
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            // Do nothing
        }
    }


}
