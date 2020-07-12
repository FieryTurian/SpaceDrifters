package com.example.spacedrifters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

public class PlayerShip {
    private final int STOPPED = 0;
    private final int LEFT = 1;
    private final int RIGHT = 2;
    private float length;
    private float shipSpeed;
    private float width;
    private float x;
    private float y;
    private int screenWidth;
    private int shipMoving = STOPPED;
    private Bitmap[] playerShipBitmap = new Bitmap[7];
    private RectF rectangle;

    /**
     * The constructor of the class PlayerShip
     * @param context - global information about the application environment (given through level)
     * @param screenWidth - an int of the width of the screen of the mobile phone (x-coordinates)
     * @param screenLength - an int of the length of the screen of the mobile phone (y-coordinates)
     */
    public PlayerShip(Context context, int screenWidth, int screenLength){
        this.length = (float) screenLength / 17;
        this.rectangle = new RectF();
        this.screenWidth = screenWidth;
        this.shipSpeed = 750;
        this.width = (float) screenWidth / 10;
        this.x = (float) screenWidth / 2;
        this.y = screenLength;

        playerShipBitmap[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceship_default);
        playerShipBitmap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceship2);
        playerShipBitmap[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceship3);
        playerShipBitmap[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceship4);
        playerShipBitmap[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceship5);
        playerShipBitmap[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceship6);
        playerShipBitmap[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceship7);

        for(int i = 0; i < playerShipBitmap.length; i++) {
            playerShipBitmap[i] = Bitmap.createScaledBitmap(playerShipBitmap[i], (int) (width), (int) (length), false);
        }
    }

    /**
     * A getter for the movement of the player ship
     * @return - an int used for the direction left
     */
    public final int getLEFT() {
        return LEFT;
    }

    /**
     * A getter for the length of the player ship
     * @return - a float with the length of the player ship
     */
    public float getLength() {
        return length;
    }

    /**
     * A getter for the bitmap which will be used as player ship in the game
     * @param i - an int which determines which bitmap gets chosen out of the array of bitmaps
     * @return - a Bitmap with a picture of a specific player ship
     */
    public Bitmap getPlayerShipBitmap(int i){
        return playerShipBitmap[i];
    }

    /**
     * A getter for the rectangle of the player ship object
     * @return - a RectF with the rectangle of the player ship object
     */
    public RectF getRect(){
        return rectangle;
    }

    /**
     * A getter for the movement of the player ship
     * @return - an int used for the direction right
     */
    public final int getRIGHT() {
        return RIGHT;
    }

    /**
     * A getter for the movement of the player ship
     * @return - an int used for stopping the player ship
     */
    public final int getSTOPPED() {
        return STOPPED;
    }

    /**
     * A getter for the width of the player ship
     * @return - a float with the width of the player ship
     */
    public float getWidth(){
        return width;
    }

    /**
     * A getter for the x-coordinate of the positioning of the player ship
     * @return - a float of the x-coordinate of the player ship where it should start
     */
    public float getX(){
        return x;
    }

    /**
     * A setter for the movement of the player ship
     * @param state - an int with one of the three possible movements: stopped (0), left (1) or right (2)
     */
    public void setMovementState(int state){
        shipMoving = state;
    }

    /**
     * A function which is responsible for the movement of the player ship during the game
     * @param fps - a long with the frames per second on which our game is running
     */
    public void update(long fps){
        if(shipMoving == LEFT && x - width /2 > 0){
            x = x - shipSpeed / fps;
        }
        if(shipMoving == RIGHT && x + width /2 < screenWidth){
            x = x + shipSpeed / fps;
        }
        rectangle.top = y - length;
        rectangle.bottom = y;
        rectangle.left = x - width / 2;
        rectangle.right = x + width / 2;
    }
}