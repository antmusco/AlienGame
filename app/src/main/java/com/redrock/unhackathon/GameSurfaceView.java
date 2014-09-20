package com.redrock.unhackathon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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

    // A different comment here.
    private Bitmap bmp;
    private CollisionMan sprite;
    private ArrayList<CollisionMan> sprites;

    public GameSurfaceView(Context context) {

        super(context);
        setWillNotDraw(false);
        getHolder().addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        float scale = (float) background.getHeight() / (float) getHeight();
        int newWidth = Math.round(background.getWidth() / scale);
        int newHeight = Math.round(background.getHeight() / scale);
        bmp = Bitmap.createScaledBitmap(background, newWidth, newHeight, true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(bmp, 0, 0, null);
        sprite.onDraw(canvas);

    }

}
