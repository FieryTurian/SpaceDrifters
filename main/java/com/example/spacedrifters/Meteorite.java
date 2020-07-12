package com.example.spacedrifters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import java.util.Random;

public class Meteorite {
    private float length;
    private float[] meteoriteSpeed = new float[4];
    private float width;
    private float x;
    private float y;
    private Bitmap[] meteoriteBitmap = new Bitmap[4];
    private Random random = new Random();
    private RectF rectangle;

    /**
     * The constructor of the class Meteorite
     * @param context - global information about the application environment (given through level)
     * @param screenWidth - an int of the width of the screen of the mobile phone (x-coordinates)
     * @param screenLength - an int of the length of the screen of the mobile phone (y-coordinates)
     */
    public Meteorite(Context context, int screenWidth, int screenLength) {
        this.length = (float) screenLength / 20;
        this.rectangle = new RectF();
        this.width = (float) screenWidth / 10;
        this.x = random.nextInt((screenWidth - (int) width) + 1);
        this.y = 0 - length;

        meteoriteBitmap[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.meteorite1);
        meteoriteBitmap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.meteorite2);
        meteoriteBitmap[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.meteorite3);
        meteoriteBitmap[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.meteorite4);

        for(int i = 0; i < meteoriteBitmap.length; i++) {
            meteoriteBitmap[i] = Bitmap.createScaledBitmap(meteoriteBitmap[i], (int) (width), (int) (length), false);
        }

        meteoriteSpeed[0] = 350;
        meteoriteSpeed[1] = 250;
        meteoriteSpeed[2] = 450;
        meteoriteSpeed[3] = 400;
    }

    /**
     * A getter for the length of the meteorite
     * @return - a float with the length of the meteorite
     */
    public float getLength() {
        return length;
    }

    /**
     * A getter for the bitmap which will be used as meteorite in the game
     * @param i - an int which determines which bitmap gets chosen out of the array of bitmaps
     * @return - a Bitmap with a picture of a specific meteorite
     */
    public Bitmap getMeteoriteBitmap(int i){
        return meteoriteBitmap[i];
    }

    /**
     * A getter for the rectangle of the meteorite object
     * @return - a RectF with the rectangle of the meteorite object
     */
    public RectF getRectangle(){
        return rectangle;
    }

    /**
     * A getter for the x-coordinate of the positioning of the meteorite
     * @return - a float of the x-coordinate of the meteorite where it should start
     */
    public float getX(){
        return x;
    }

    /**
     * A getter for the y-coordinate of the positioning of the meteorite
     * @return - a float of the y-coordinate of the meteorite where it should start
     */
    public float getY(){
        return y;
    }

    /**
     * A function which is responsible for restarting a meteorite at the top of the screen
     * @param screenWidth - an int of the width of the screen of the mobile phone (x-coordinates)
     */
    public void restart(int screenWidth){
        y = 0 - length;
        x = random.nextInt((screenWidth - (int) width) + 1);
    }

    /**
     * A function which is responsible for the movement of the meteorites during the game
     * @param fps - a long with the frames per second on which our game is running
     * @param i - an int which determines which bitmap gets chosen out of the array of bitmaps
     */
    public void update(long fps, int i){
        y = y + meteoriteSpeed[i] / fps;
        rectangle.top = y;
        rectangle.bottom = y + length;
        rectangle.left = x;
        rectangle.right = x + width;
        meteoriteSpeed[i] = meteoriteSpeed[i] * 1.0001f;
    }
}