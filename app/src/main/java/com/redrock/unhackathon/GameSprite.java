package com.redrock.unhackathon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

/**
 * Sprite which can be drawn on a view. Includes an x and y position, as well as a bitmap depicting
 * the image to display the sprite on the screen.
 *
 * @author
 *          Anthony G. Musco
 *
 * <dt><b>Date Created:</b></dd>
 *          9/20/2014
 */
public abstract class GameSprite {

/*
====================================================================================================
=================================       Constants/Variables     ====================================
====================================================================================================
 */

    /**
     * Vector containing two double values, indicating the x-magnitude and y-magnitude of the
     * position of the sprite.
     */
    private double[] position;

    /**
     * Vector containing two double values, indicating the x-magnitude and y-magnitude of the
     * velocity of the sprite.
     */
    private double[] velocity;

    /**
     * Bitmap for sprite rendering.
     */
    private Bitmap spriteBitmap;

    /**
     * View in which the sprite is located.
     */
    private View view;

    private int count = 0;

/*
====================================================================================================
====================================       Constructors     ========================================
====================================================================================================
 */

    /**
     * Takes a view object and a bitmap image and initializes a new sprite with these values.
     *
     * @param initView
     *          View object in which this sprite is located.
     *
     * @param initBmp
     *          Bitmap image representation of the sprite.
     */
    public GameSprite(View initView, Bitmap initBmp) {

        view = initView;
        spriteBitmap = initBmp;
        position = new double[2];
        velocity = new double[2];

    }

/*
====================================================================================================
===================================       Instance Methods     =====================================
====================================================================================================
 */

    /**
     * Draws the bitmap on the canvas as the sprite's current x and y position.
     *
     * @param canvas
     *          Canvas on which to draw the sprite (using the sprite's bitmap variable).
     */
    public void onDraw(Canvas canvas) {

        moveSprite();
        canvas.drawBitmap(spriteBitmap, (int) position[0], (int) position[1], null);

    }

    /**
     * Update the sprites position in the view based on its current speed. If the sprite reaches a
     * boundary, the speed is reversed, and the sprite "bounces" off of the boundary.
     */
    public abstract void moveSprite();

    /**
     * Method which applies gravitational acceleration to the sprite as the accelerometer reads
     * in new values.
     *
     * @param values
     *          Array of float values indicating the x [0], y [1], and z [2] vector values of
     *          gravitational acceleration with respect to the accelerometer's coordinate system.
     */
    public void applyGravity(float[] values) {

        double xMagnitude = values[1] * 2;
        double yMagnitude = values[0] * 2;

        double time = 10.0 / GameThread.FRAME_RATE;

        velocity[0] = velocity[0] + (xMagnitude * time);
        velocity[1] = velocity[1] + (yMagnitude * time);

        //System.out.println(xMagnitude + " " + yMagnitude);
        //System.out.println(velocity[0] + " " + velocity[1]);

    }

/*
====================================================================================================
====================================       Getters/Setters     =====================================
====================================================================================================
 */

    /**
     * Public getter method for the width of the sprite in pixels.
     *
     * @return
     *          The value of the width of the sprite in pixels.
     */
    public int getWidth() {

        return spriteBitmap.getWidth();

    }

    /**
     * Public getter method for the height of the sprite in pixels.
     *
     * @return
     *          The value of the height of the sprite in pixels.
     */
    public int getHeight() {

        return spriteBitmap.getHeight();

    }

    /**
     * Public getter method for the horizontal position of the sprite.
     *
     * @return
     *          The value of the horizontal position of the sprite.
     */
    public double getX() {

        return position[0];

    }

    /**
     * Public setter method for the sprite's x coordinate.
     *
     * @param newX
     *          New x position on the View.
     */
    public void setX(double newX) {

        position[0] = newX;

    }

    /**
     * Public getter method for the vertical position of the sprite.
     *
     * @return
     *          The value of the vertical position of the sprite.
     */
    public double getY() {

        return position[1];

    }

    /**
     * Public setter method for the sprite's y coordinate.
     *
     * @param newY
     *          New x position on the View.
     */
    public void setY(double newY) {

        position[1] = newY;

    }

    /**
     * Public getter method for the magnitude of the velocity for the sprite. Returns the square
     * root of the squares of the horizontal and vertical velocities.
     *
     * @return
     *          The total speed of the sprite: the square root of the squares of the horizontal and
     *          vertical velocities.
     */
    public double getSpeed() {

        return Math.sqrt(velocity[0] * velocity[0] + velocity[1] * velocity[1]);

    }

    /**
     * Convenience setter method for the horizontal and vertical speeds of the sprite.
     *
     * @param xSpeed
     *          The new horizontal speed of the sprite.
     *
     * @param ySpeed
     *          The new vertical speed of the sprite.
     */
    public void setSpeed(double xSpeed, double ySpeed) {

        velocity[0] = xSpeed;
        velocity[1] = ySpeed;

    }

    /**
     * Public getter method for the horizontal velocity of the sprite.
     *
     * @return
     *          The value of the horizontal velocity of the sprite.
     */
    public double getxSpeed() {

        return velocity[0];

    }

    /**
     * Public getter for the horizontal velocity of the sprite.
     *
     * @param xSpeed
     *          Value to set the horizontal speed of the sprite to.
     */
    public void setxSpeed(double xSpeed) {


        velocity[0] = xSpeed;


    }

    /**
     * Public getter method for the vertical velocity of the sprite.
     *
     * @return
     *          The value of the vertical velocity of the sprite.
     */
    public double getySpeed() {

        return velocity[1];

    }

    /**
     * Public getter for the vertical velocity of the sprite.
     *
     * @param ySpeed
     *          Value to set the vertical speed of the sprite to.
     */
    public void setySpeed(double ySpeed) {

        velocity[1] = ySpeed;

    }

    /**
     * Public getter for the sprite image.
     *
     * @return
     *          The sprite image.
     */
    public Bitmap getBitmap() {

        return spriteBitmap;

    }

    /**
     * Public setter for the sprite image.
     *
     * @param bitmap
     *          Value to set the sprite image to.
     */
    public void setBitmap(Bitmap bitmap) {

        spriteBitmap = bitmap;

    }

    /**
     * Public getter for the view of this sprite.
     *
     * @return
     *          The sprite view.
     */
    public View getView() {

        return view;

    }
}
