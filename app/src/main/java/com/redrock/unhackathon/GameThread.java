package com.redrock.unhackathon;

import android.graphics.Canvas;

/**
 * Thread which runs the primary game loop.
 *
 * @author
 *          Anthony G. Musco
 * <dt><b>Date Created:</b></dd>
 *          09/20/2014
 */
public class GameThread extends Thread {

    /**
     * View in which the thread is running.
     */
    GameSurfaceView gameView;

    /**
     * Flag indicating whether the thread is running or not.
     */
    boolean running = true;

    /**
     * Time the thread began in milliseconds.
     */
    long startTime = 0;

    /**
     * Frame rate of the thread indicated in frames per second.
     */
    public static final int FRAME_RATE = 40;

    /**
     * Constructor for the game thread. Sets the view in which the thread is running and initializes
     * the time the thread began.
     *
     * @param initGameView
     *          Game view in which the thread is running.
     */
    public GameThread(GameSurfaceView initGameView) {

        gameView = initGameView;
        startTime = System.currentTimeMillis();

    }

    /**
     * Run method for the thread.
     */
    @Override
    public void run() {

        while (running) {

            try {

                // Update the game graphics.
                gameView.postInvalidate();

                // Sleep.
                sleep(1000/FRAME_RATE);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }
    }

    public void setRunning(boolean newRunning) {

        running = newRunning;

    }
}
