package com.redrock.unhackathon;

import android.graphics.Bitmap;
import android.view.View;

/**
 * GameSprite representing a rocket aimed at the alien spaceship. The rocket can be shot down by
 * the ship.
 *
 * @author
 *          Anthony G. Musco
 *
 * <dt><b>Date Created:</b></dd>
 *          9/20/2014
 */
public class RocketSprite extends GameSprite {

    /**
     * Takes a view object and a bitmap image and initializes a new sprite with these values.
     *
     * @param initView View object in which this sprite is located.
     * @param initBmp
     */
    public RocketSprite(View initView, Bitmap initBmp) {

        super(initView, initBmp);

    }

    @Override
    public void moveSprite() {

    }

}
