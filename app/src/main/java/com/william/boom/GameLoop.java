package com.william.boom;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameLoop extends Thread {

    public static final double MAX_UPS = 60.0;
    private static final double UPS_PERIOD = 1E+3 / MAX_UPS;

    private Game game;
    private SurfaceHolder surfaceHolder;

    private boolean isRunning = false;
    private double averageUPS, averageFPS;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public void startLoop() {
        Log.d("GameLoop: ","startLoop");
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        Log.d("GameLoop: ","run");
        super.run();

        //Decalre time and cycle count variables
        int updateCount = 0;
        int frameCount = 0;

        long startTime, elapsedTime, sleepTime;

        //Game Loop
        Canvas canvas = null;
        startTime = System.currentTimeMillis();
        while (isRunning) {

            //Try to update and render game loop
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    game.update();
                    updateCount++;

                    game.draw(canvas);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            //Pause game loop to not exceed target UPS
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //Skip frames to keep up with UPS
            while (sleepTime < 0 && updateCount < MAX_UPS - 1) {
                game.update();
                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
            }

            //Calculate average UPS and FPS
            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000) {
                averageUPS = updateCount / (1E-3 * elapsedTime);
                averageFPS = updateCount / (1E-3 * elapsedTime);
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }

    public void stopLoop() {
        Log.d("GameLoop: ","stopLoop");
        isRunning = false;
        //Wait for thread to join
        try{
            join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
