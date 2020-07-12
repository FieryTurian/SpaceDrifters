package com.example.spacedrifters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import java.util.Random;

public class Enemy {
    private boolean isVisible;
    private float enemySpeed;
    private float length;
    private float width;
    private float x;
    private float y;
    private Bitmap[] enemyBitmap = new Bitmap[9];
    private Random random = new Random();
    private RectF rectangle;

    /**
     * The constructor of the class Enemy
     * @param context - global information about the application environment (given through level)
     * @param screenWidth - an int of the width of the screen of the mobile phone (x-coordinates)
     * @param screenLength - an int of the length of the screen of the mobile phone (y-coordinates)
     */
    public Enemy(Context context, int screenWidth, int screenLength) {
        this.enemySpeed = 450;
        this.isVisible = true;
        this.length = (float) screenLength / 20;
        this.rectangle = new RectF();
        this.width = (float) screenWidth / 10;
        this.x = random.nextInt((screenWidth - (int) width) + 1);
        this.y = 0 - length;

        enemyBitmap[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_default);
        enemyBitmap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy1);
        enemyBitmap[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy3);
        enemyBitmap[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy6);
        enemyBitmap[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy7);
        enemyBitmap[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy4);
        enemyBitmap[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy8);
        enemyBitmap[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy5);
        enemyBitmap[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy9);

        for(int i = 0; i < enemyBitmap.length; i++) {
            enemyBitmap[i] = Bitmap.createScaledBitmap(enemyBitmap[i], (int) (width), (int) (length), false);
        }
    }

    /**
     * A getter for the bitmap which will be used as enemy in the game
     * @param i - an int which determines which bitmap gets chosen out of the array of bitmaps
     * @return - a Bitmap with a picture of a specific enemy
     */
    public Bitmap getEnemyBitmap(int i){
        return enemyBitmap[i];
    }

    /**
     * A getter for the length of the enemy
     * @return - a float with the length of the enemy
     */
    public float getLength() {
        return length;
    }

    /**
     * A getter for the rectangle of the bullet object
     * @return - a RectF with the rectangle of the enemy object
     */
    public RectF getRectangle(){
        return rectangle;
    }

    /**
     * A getter for the visibility of the enemy
     * @return - a boolean value which is true when the enemy is visible and false otherwise
     */
    public boolean getVisibility(){
        return isVisible;
    }

    /**
     * A getter for the x-coordinate of the positioning of the enemy
     * @return - a float of the x-coordinate of the enemy where it should start
     */
    public float getX(){
        return x;
    }

    /**
     * A getter for the y-coordinate of the positioning of the enemy
     * @return - a float of the y-coordinate of the enemy where it should start
     */
    public float getY(){
        return y;
    }

    /**
     * A setter for the visibility of the enemy: setting the visibility to invisible (false)
     */
    public void setInvisible(){
        isVisible = false;
    }

    /**
     * A function which is responsible for restarting an enemy at the top of the screen
     * @param screenWidth - an int of the width of the screen of the mobile phone (x-coordinates)
     */
    public void spawnAgain(int screenWidth) {
        isVisible = true;
        y = 0 - length;
        x = random.nextInt((screenWidth - (int) width) + 1);
    }

    /**
     * A function which is responsible for the movement of the enemy during the game
     * @param fps - a long with the frames per second on which our game is running
     */
    public void update(long fps){
        y = y + enemySpeed / fps;
        rectangle.top = y;
        rectangle.bottom = y + length;
        rectangle.left = x;
        rectangle.right = x + width;
        enemySpeed = enemySpeed * 1.0001f;
    }
}