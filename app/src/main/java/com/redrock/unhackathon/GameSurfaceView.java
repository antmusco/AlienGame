package com.redrock.unhackathon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * SurfaceView object which contains a collection of objects native to the GameSprite game.
 * Includes a alien (so far).
 *
 * @author
 *          Anthony G. Musco
 * <dt><b>Date Created:</b></dd>
 *          9/20/2014
 */
public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback,
  SensorEventListener {

/*
====================================================================================================
================================       Constants/Variables     =====================================
====================================================================================================
 */

    /**
     * Maximum number of sprites to display on the screen (including the alien ship).
     */
    private static final int MAX_SPRITES = 5;

    GameThread gameLoopThread;

    SurfaceHolder holder;

    /**
     * Background image for the view.
     */
    private Bitmap background;

    /**
     * Primary alien to be rendered on in the view.
     */
    private AlienSprite alien;

    /**
     * Collection of sprites added to the frame.
     */
    private GameSprite[] sprites;

    /**
     * Running total of sprites on the game screen.
     */
    private int spritesCount;

    private int count = 0;
/*
====================================================================================================
====================================       Constructors     ========================================
====================================================================================================
 */

/*    public GameSurfaceView(Context context, AttributeSet attrs, Bitmap bmp) {

        super(context, attrs);
        construct();

    }

    public GameSurfaceView(Context context, AttributeSet attrs, int defStyle, Bitmap bmp) {

        super(context, attrs, defStyle);
        construct();

    }*/

    public GameSurfaceView(Context context, AttributeSet attrs) {

        super(context, attrs);
        construct();

    }

/*    public GameSurfaceView(Context context) {

        super(context);
        construct();

    }*/

/*
====================================================================================================
==================================       Instance Methods     ======================================
====================================================================================================
 */

    /**
     * Private method to be called on construction. Sets the callback for this View to this
     * GameSurfaceView (this class implements callback).
     */
    public void construct() {

        // Allow the view to be drawn.
        setWillNotDraw(false);

        // Allow this view to be its own callback.
        getHolder().addCallback(this);

        // Decode the alien image into a bitmap object.
        Bitmap spriteBitmap = BitmapFactory.decodeResource(getResources(),
          R.drawable.sprite_01);

        // Create a new alien for this game object.
        alien = new AlienSprite(this, spriteBitmap, 100, 50);
        sprites = new GameSprite[MAX_SPRITES - 1];
        spritesCount = 0;

        // Create the primary GameThread for this view.
        gameLoopThread = new GameThread(this);

        setOnTouchListener(alien);

        // Initialize and set the SurfaceHolder.
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback(){

            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

                // Start and run the primary game loop
                gameLoopThread.setRunning(true);
                gameLoopThread.start();


            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

                // Stop the game loop.
                gameLoopThread.setRunning(false);

            }
        });

    }


    @Override
    public void surfaceCreated(SurfaceHolder arg0) {

        // Set background once the surface has been created.
        Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.game_background_01);
        float scale = (float) background.getHeight() / (float) getHeight();
        int newWidth = Math.round(background.getWidth() / scale);
        int newHeight = Math.round(background.getHeight() / scale);
        this.background = Bitmap.createScaledBitmap(background, newWidth, newHeight, true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    protected void onDraw(Canvas canvas) {

        // Draw the canvas.
        canvas.drawBitmap(background, 0, 0, null);

        // Draw the alien.
        alien.onDraw(canvas);

        // Draw all of the sprites.
        for(int i = 0; i < spritesCount; i++) {

            sprites[i].onDraw(canvas);

        }

    }

    /**
     * Sensor changed event.
     * @param sensorEvent
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor.getType() == Sensor.TYPE_GRAVITY) {

            alien.applyGravity(sensorEvent.values);

        }

    }


    /**
     * Sensor accuracy changed event.
     * @param sensor
     * @param i
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public GameThread getGameThread() {

        return gameLoopThread;

    }

    public void addSprite(GameSprite sprite) {

        if (spritesCount < MAX_SPRITES - 1) {

            sprites[spritesCount++] = sprite;

        }
    }

    public void removeSprite(GameSprite sprite) {

        for (int i = 0; i < spritesCount; i++) {

            if(sprites[i] == sprite) {

                for(int j = i; j < (spritesCount - 1); j++) {

                    sprites[j] = sprites[j+1];

                }
                spritesCount--;
                return;

            }

        }

    }
}
