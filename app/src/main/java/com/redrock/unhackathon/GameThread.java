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

    boolean die = false;
    private GameState gameState;

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

    }

    /**
     * Run method for the thread.
     */
    @Override
    public void run() {

        while(!die) {

            switch (gameState) {

                case RUNNING:

                    try {

                        long startTime = System.currentTimeMillis();

                        // Update the game graphics.
                        gameView.postInvalidate();

                        long timeDifference = System.currentTimeMillis() - startTime;
                        long sleepTime = (1000 / FRAME_RATE) - timeDifference;

                        // Sleep.
                        sleep(sleepTime > 0 ? sleepTime : 0);

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }
                    break;

                case PAUSED:

                    // Do nothing
                    break;

                case OVER:

                    die = true;
                    break;

            }

        }

    }

    public void setGameState(GameState setState) {

        gameState = setState;

    }
}
