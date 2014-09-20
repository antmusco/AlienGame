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
public class CollisionMan {

    /**
     * Sprite's current x position.
     */
    private int x;

    /**
     * Sprite's current y position.
     */
    private int y;

    /**
     * Bitmap for sprite rendering.
     */
    private Bitmap bmp;

    /**
     * View in which the sprite is located.
     */
    private View view;

    /**
     * Takes a view object and a bitmap image and initializes a new sprite with these values.
     *
     * @param initView
     *          View object in which this sprite is located.
     *
     * @param initBmp
     *          Bitmap image representation of the sprite.
     */
    public CollisionMan (View initView, Bitmap initBmp) {

        view = initView;
        bmp = initBmp;


    }

    /**
     * Draws the bitmap on the canvas as the sprite's current x and y position.
     *
     * @param canvas
     *          Canvas on which to draw the sprite (using the sprite's bitmap variable).
     */
    public void onDraw(Canvas canvas) {

        canvas.drawBitmap(bmp, x, y, null);

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

}
