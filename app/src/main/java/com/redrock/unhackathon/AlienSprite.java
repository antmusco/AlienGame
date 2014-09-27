package com.redrock.unhackathon;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;

/**
 * GameSprite object representing the main character of the game - an alien spaceship. This ship
 * is capable of shooting lasers at missiles.
 *
 * @author
 *          Anthony G. Musco
 *
 * <dt><b>Date Created:</b></dd>
 *          9/20/2014
 */
public class AlienSprite extends GameSprite implements View.OnTouchListener {

    /**
     * Drag factor for when the sprite bounces off the edge of the screen.
     */
    public static final double DRAG_FACTOR = 0.70;

    /**
     * Takes a view object and a bitmap image and initializes a new sprite with these values.
     *  @param initView View object in which this sprite is located.
     * @param initBmp
     * @param x
     * @param y
     */
    public AlienSprite(View initView, Bitmap initBmp, int x, int y) {

        super(initView, initBmp);
        setX(x); setY(y);

    }

    /**
     * Adding documentation.
     * @param view
     * @param x
     * @param y
     * @return
     */
    public LaserSprite shootLaser(View view, double x, double y) {

        double delX = x - getX();
        double delY = y - getY();

        double scale = LaserSprite.VELOCITY / Math.hypot(delX, delY);

        return new LaserSprite(view, this, delX * scale, delY * scale );

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        LaserSprite laser = shootLaser(view, motionEvent.getX(), motionEvent.getY());
        ((GameSurfaceView) view).addSprite(laser);
        return false;
    }

    @Override
    public void moveSprite() {

        double x = getX();
        double y = getY();
        double xSpeed = getxSpeed();
        double ySpeed = getySpeed();

        // The sprite has collided with the view boundary (sides).
        if ((x + getxSpeed() <= 0) || (x + getWidth() + getxSpeed() >= getView().getWidth())) {

            xSpeed = -(xSpeed * DRAG_FACTOR);

        }

        // The sprite has collided with the view boundary (top/bottom).
        if ((y + getySpeed() <= 0) || (y + getHeight() + getySpeed() >= getView().getHeight())) {

            ySpeed = -(ySpeed * DRAG_FACTOR);

        }

        // Update the position of the sprite.
        setX(x + xSpeed);
        setY(y + ySpeed);
        setxSpeed(xSpeed);
        setySpeed(ySpeed);

    }
}
