package com.redrock.unhackathon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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
public class GameSurfaceView extends SurfaceView {

    private Bitmap bmp;
    private CollisionMan sprite;
    private ArrayList<CollisionMan> sprites;

    public GameSurfaceView(Context context) {

        super(context);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.BLACK);
        sprite.onDraw(canvas);

    }

}
