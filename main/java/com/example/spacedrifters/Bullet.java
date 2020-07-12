package com.example.spacedrifters;

import android.graphics.RectF;

public class Bullet {
    private boolean active;
    private boolean sounds;
    private float x;
    private float y;
    private int length;
    private RectF rectangle;
    private View view;

    /**
     * The constructor of the class Bullet
     * @param screenLength - an int of the length of the screen of the mobile phone (y-coordinates)
     * @param view - an instance of our game view
     * @param sounds - a boolean that controls the bullet sound
     */
    public Bullet(int screenLength, View view, boolean sounds) {
        this.active = false;
        this.length = screenLength / 30;
        this.rectangle = new RectF();
        this.sounds = sounds;
        this.view = view;
    }

    /**
     * A getter for the impact point of (the top of) a bullet
     * @return - a float with the top of the bullet: the bottom plus the length of the bullet
     */
    public float getImpactPointY(){
        return y + length;
    }

    /**
     * A getter for the rectangle of the bullet object
     * @return - a RectF with the rectangle of the bullet object
     */
    public RectF getRectangle(){
        return rectangle;
    }

    /**
     * A getter for the status of the bullet
     * @return - a boolean value which is true when the bullet is active and false otherwise
     */
    public boolean getStatus(){
        return active;
    }

    /**
     * A setter for the status of the bullet: setting the status to inactive (false)
     */
    public void setInactive(){
        active = false;
    }

    /**
     * A function which handles the shooting of bullets that are fired by the spaceship
     * @param x - a float of the x-coordinate of the bullet where it should be fired from
     * @param y - a float of the y-coordinate of the bullet where it should be fired from
     */
    public void shoot(float x, float y) {
        if (!view.getPaused() && !active) {
            if(!view.getGameOverMessage()){
                if (sounds) {
                    MainActivity.shooter.start();
                }
            }
            this.x = x;
            this.y = y;
            active = true;
        }
    }

    /**
     * A function which is responsible for the movement of the bullet, once the player shoots
     * @param fps - a long with the frames per second on which our game is running
     */
    public void update(long fps){
        float bulletSpeed = 2000;
        y = y - bulletSpeed / fps;
        rectangle.left = x;
        int width = 5;
        rectangle.right = x + width;
        rectangle.top = y;
        rectangle.bottom = y + length;
    }
}