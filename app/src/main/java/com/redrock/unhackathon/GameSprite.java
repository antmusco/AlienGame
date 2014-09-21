package com.redrock.unhackathon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
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
public class GameSprite {

/*
====================================================================================================
=================================       Constants/Variables     ====================================
====================================================================================================
 */

    /**
     * Maximum total speed of the sprite.
     */
    private static final int MAX_VELOCITY = 10;

    /**
     * Sprite's current x position.
     */
    private int x;

    /**
     * Sprite's current y position.
     */
    private int y;

    /**
     * Horizontal velocity of the sprite.
     */
    private int xSpeed;

    /**
     * Vertical velocity of the sprite.
     */
    private int ySpeed;

    /**
     * Bitmap for sprite rendering.
     */
    private Bitmap spriteBitmap;

    /**
     * View in which the sprite is located.
     */
    private View view;

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
        x = 100;
        y = 50;
        xSpeed = 30;
        ySpeed = 30;

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
        canvas.drawBitmap(spriteBitmap, x, y, null);

    }

    /**
     * Update the sprites position in the view based on its current speed. If the sprite reaches a
     * boundary, the speed is reversed, and the sprite "bounces" off of the boundary.
     */
    private void moveSprite() {

        // The sprite has collided with the view boundary (sides).
        if ((x <= 0 - xSpeed) || (x >= view.getWidth() - spriteBitmap.getWidth() - xSpeed)) {

            xSpeed = -xSpeed;

        }

        // The sprite has collided with the view boundary (top/bottom).
        if ((y <= 0 - ySpeed) || (y >= view.getHeight() - spriteBitmap.getHeight() - ySpeed)) {

            ySpeed = -ySpeed;

        }

        // Update the position of the sprite.
        x = x + xSpeed;
        y = y + ySpeed;

    }

    private boolean underMaxSpeed(int checkX, int checkY) {

        return Math.sqrt(checkX * checkX + checkY * checkY) <= MAX_VELOCITY;

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
    public int getX() {

        return x;

    }

    /**
     * Public setter method for the sprite's x coordinate. Only updates the x variable if the newX
     * parameter is within the valid View range (greater than 0, less than View.getWidth().
     *
     * @param newX
     *          New x position on the View.
     */
    public void setX(int newX) {

        if (newX > 0 && newX < view.getWidth()){

            x = newX;

        }

    }

    /**
     * Public getter method for the vertical position of the sprite.
     *
     * @return
     *          The value of the vertical position of the sprite.
     */
    public int getY() {

        return y;

    }

    /**
     * Public setter method for the sprite's y coordinate. Only updates the y variable if the newY
     * parameter is within the valid View range (greater than 0, less than View.getHeight().
     *
     * @param newY
     *          New x position on the View.
     */
    public void setY(int newY) {

        if (newY > 0 && newY < view.getHeight()){

            y = newY;

        }

    }

    /**
     * Public getter method for the magnitude of the velocity for the sprite. Returns the square
     * root of the squares of the horizontal and vertical velocities.
     *
     * @return
     *          The total speed of the sprite: the square root of the squares of the horizontal and
     *          vertical velocities.
     */
    public int getSpeed() {

        return (int) Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed);

    }

    /**
     * Convenience setter method for the horizontal and vertical speeds of the sprite.
     *
     * @param newxSpeed
     *          The new horizontal speed of the sprite.
     *
     * @param newySpeed
     *          The new vertical speed of the sprite.
     */
    public void setSpeed(int newxSpeed, int newySpeed) {

        setxSpeed(newxSpeed);
        setySpeed(newySpeed);

    }

    /**
     * Public getter method for the horizontal velocity of the sprite.
     *
     * @return
     *          The value of the horizontal velocity of the sprite.
     */
    public int getxSpeed() {

        return xSpeed;

    }

    /**
     * Public getter for the horizontal velocity of the sprite. Value will only be updated if the
     * new value will keep the sprite under the maximum speed.
     *
     * @param newxSpeed
     *          Value to set the horizontal speed of the sprite to.
     */
    public void setxSpeed(int newxSpeed) {

        xSpeed = newxSpeed;

    }

    /**
     * Public getter method for the vertical velocity of the sprite.
     *
     * @return
     *          The value of the vertical velocity of the sprite.
     */
    public int getySpeed() {

        return ySpeed;

    }

    /**
     * Public getter for the vertical velocity of the sprite. Value will only be updated if the
     * new value will keep the sprite under the maximum speed.
     *
     * @param newySpeed
     *          Value to set the vertical speed of the sprite to.
     */
    public void setySpeed(int newySpeed) {

        if (underMaxSpeed(x, newySpeed)) {

            ySpeed = newySpeed;

        }

    }

}
