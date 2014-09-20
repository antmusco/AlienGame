package com.redrock.unhackathon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * SurfaceView object which contains a collection of objects native to the CollisionMan game.
 * Includes a sprite (so far).
 *
 * @author
 *          Anthony G. Musco
 * <dt><b>Date Created:</b></dd>
 *          9/20/2014
 */
public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

/*
====================================================================================================
================================       Constants/Variables     =====================================
====================================================================================================
 */

    /**
     * Background image for the view.
     */
    private Bitmap background;

    /**
     * Primary sprite to be rendered on in the view.
     */
    private CollisionMan sprite;

/*
====================================================================================================
====================================       Constructors     ========================================
====================================================================================================
 */

    public GameSurfaceView(Context context, AttributeSet attrs, Bitmap bmp) {

        super(context, attrs);
        construct();

    }

    public GameSurfaceView(Context context, AttributeSet attrs, int defStyle, Bitmap bmp) {

        super(context, attrs, defStyle);
        construct();

    }


    public GameSurfaceView(Context context, AttributeSet attrs) {

        super(context, attrs);
        construct();

    }

    public GameSurfaceView(Context context) {

        super(context);
        construct();

    }

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
        Bitmap spriteBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sprite_01);
        sprite = new CollisionMan(this, spriteBitmap);
        sprite.setX(getWidth()/2);
        sprite.setY(getHeight()/2);

    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {

        Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.test);
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

        canvas.drawBitmap(background, 0, 0, null);
        sprite.onDraw(canvas);

    }

}
