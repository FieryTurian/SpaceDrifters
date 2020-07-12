package com.example.spacedrifters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameOver {
    private float x;
    private float y;
    private Bitmap gameOverBitmap;

    /**
     * The constructor of the class GameOver
     * @param context - global information about the application environment (given through level)
     * @param screenWidth - an int of the width of the screen of the mobile phone (x-coordinates)
     * @param screenLength - an int of the length of the screen of the mobile phone (y-coordinates)
     */
    public GameOver(Context context, int screenWidth, int screenLength) {
        float width = screenWidth;
        float length = screenLength / 3;
        this.x = screenWidth/2 - width / 2 + 15;
        this.y = screenLength/2 - length / 2 - 100;

        gameOverBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.gameover);
        gameOverBitmap = Bitmap.createScaledBitmap(gameOverBitmap, (int) (width), (int) (length), false);
    }

    /**
     * A getter for the bitmap which will show in the game when the player is game over
     * @return - a Bitmap with a picture of game over
     */
    public Bitmap getGameOverBitmap(){
        return gameOverBitmap;
    }

    /**
     * A getter for the x-coordinate of the positioning of the game over picture
     * @return - a float of the x-coordinate of the starting position of the picture
     */
    public float getX(){
        return x;
    }

    /**
     * A getter for the y-coordinate of the positioning of the game over picture
     * @return - a float of the y-coordinate of the starting position of the picture
     */
    public float getY(){
        return y;
    }
}