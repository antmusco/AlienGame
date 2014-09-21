package com.redrock.unhackathon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;

/**
 * Created by Anthony on 9/21/2014.
 */
public class LaserSprite extends GameSprite{

    public static final int VELOCITY = 100;
    public static final double DEGREE_PER_RAD = 57.2958;
    private static final int MAX_COUNT = 15;


    private double angle;
    private int count;

    /**
     * Takes a view object and a bitmap image and initializes a new sprite with these values.
     *
     * @param initView View object in which this sprite is located.
     * @param initBmp
     */
    public LaserSprite(View initView, Bitmap initBmp) {

        super(initView, initBmp);

    }

    public LaserSprite(View initView, double posX, double posY, double velX, double velY) {

        super(initView, BitmapFactory.decodeResource(initView.getResources(), R.drawable.laser_01));
        setX(posX); setY(posY);
        setSpeed(velX, velY);
        angle = Math.atan(velY/velX) * DEGREE_PER_RAD;
        count = 0;

    }

    private void createMatrix(double angle, double velX, double velY) {

        Matrix matrix = new Matrix();
        matrix.postRotate((float) angle);
        matrix.postTranslate((float)velX, (float)velY);

    }

    @Override
    public void moveSprite() {

        setX(getX() + getxSpeed());
        setY(getY() + getySpeed());
        count++;

        if(count >= MAX_COUNT) {

            ((GameSurfaceView) getView()).removeSprite(this);

        }

    }

    @Override
    public void onDraw(Canvas canvas) {

        moveSprite();
        canvas.drawBitmap(rotateBitmap(getBitmap()),(float)getX(),(float)getY(),null);

    }

    /**
     * I'm documenting this.
     * I'm a different user and I'm documenting too.
     * @param bitmap
     * @return
     */
    private Bitmap rotateBitmap(Bitmap bitmap) {

        Bitmap source = getBitmap();


        Matrix matrix = new Matrix();
        matrix.postTranslate(source.getHeight()/2, source.getWidth()/2);
        matrix.postRotate((float)angle);
        matrix.postTranslate((float)getxSpeed(), (float)getySpeed());
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

    }
}
