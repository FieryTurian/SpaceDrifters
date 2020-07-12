package com.example.spacedrifters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import java.util.Random;

public class Star {
    private boolean one;
    private boolean two;
    private boolean three;
    private float length;
    private float starSpeed;
    private float width;
    private float x;
    private float y;
    private int counter = 0;
    private Bitmap[] starBitmap = new Bitmap[4];
    private Random random = new Random();
    private RectF rectangle;

    /**
     * The constructor of the class Enemy
     *
     * @param context      - global information about the application environment (given through level)
     * @param screenWidth  - an int of the width of the screen of the mobile phone (x-coordinates)
     * @param screenLength - an int of the length of the screen of the mobile phone (y-coordinates)
     */
    public Star(Context context, int screenWidth, int screenLength) {
        this.length = (float) screenLength / 40;
        this.one = true;
        this.rectangle = new RectF();
        this.three = false;
        this.two = false;
        this.width = (float) screenWidth / 20;
        this.x = random.nextInt((screenWidth - (int) width) + 1);
        this.y = screenLength + 3 * length;

        starBitmap[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.star1);
        starBitmap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.star2);
        starBitmap[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.star3);
        starBitmap[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.star4);

        for (int i = 0; i < starBitmap.length; i++) {
            starBitmap[i] = Bitmap.createScaledBitmap(starBitmap[i], (int) (width), (int) (length), false);
        }

        starSpeed = 500;
    }

    /**
     * A function which resets boolean values one, two and three to either true or false
     */
    public void changeBitmap() {
        if (one) {
            one = false;
            two = true;
            three = false;
        } else if (two) {
            one = false;
            two = false;
            three = true;
        } else if (three) {
            one = false;
            two = false;
            three = false;
        } else {
            one = true;
            two = false;
            three = false;
        }
    }

    /**
     * A getter for the length of the star
     *
     * @return - a float with the length of the star
     */
    public float getLength() {
        return length;
    }

    /**
     * A getter for the bitmap which will be used as star in the game
     *
     * @return - a Bitmap with a picture of one state of the first twinkling star
     */
    public Bitmap getStarBitmap() {
        if (one) {
            return starBitmap[0];
        } else if (two) {
            return starBitmap[1];
        } else if (three) {
            return starBitmap[2];
        } else {
            return starBitmap[3];
        }
    }

    /**
     * A getter for the x-coordinate of the positioning of the star
     *
     * @return - a float of the x-coordinate of the star where it should start
     */
    public float getX() {
        return x;
    }

    /**
     * A getter for the y-coordinate of the positioning of the star
     *
     * @return - a float of the y-coordinate of the star where it should start
     */
    public float getY() {
        return y;
    }

    /**
     * A function which is responsible for restarting a star at the top of the screen
     *
     * @param screenWidth - an int of the width of the screen of the mobile phone (x-coordinates)
     */
    public void restart(int screenWidth) {
        y = 0 - length;
        x = random.nextInt((screenWidth - (int) width) + 1);
    }

    /**
     * A function which is responsible for the movement of the stars during the game
     *
     * @param fps - a long with the frames per second on which our game is running
     */
    public void update(long fps) {
        y = y + starSpeed / fps;
        rectangle.top = y;
        rectangle.bottom = y + length;
        rectangle.left = x;
        rectangle.right = x + width;
        starSpeed = starSpeed * 1.0001f;
        counter++;
        if (counter % 10 == 0) {
            changeBitmap();
        }
    }
}