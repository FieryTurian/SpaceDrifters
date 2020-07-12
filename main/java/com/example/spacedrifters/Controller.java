package com.example.spacedrifters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class Controller extends SurfaceView implements View.OnTouchListener{
    private View view;

    /**
     * The constructor of the class Controller
     * @param context - global information about the application environment (given through level)
     * @param view - an instance of our game view
     */
    @SuppressLint("ClickableViewAccessibility")
    public Controller(Context context, View view) {
        super(context);
        this.view = view;
        view.setOnTouchListener(this);
    }

    /**
     * A function which is responsible for the movement of the player ship when the player touches the screen
     * @param v - the view of the onTouch function
     * @param event - the motion event of the onTouch function
     * @return - always returns true after an action happened on the screen
     */
    @Override
    public boolean onTouch(android.view.View v, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                view.setPausedFalse();
                if (event.getX() > view.getPlayerShip().getRect().centerX()) {
                    view.getPlayerShip().setMovementState(view.getPlayerShip().getRIGHT());
                }
                else {
                    view.getPlayerShip().setMovementState(view.getPlayerShip().getLEFT());
                }
                break;
            case MotionEvent.ACTION_UP:
                view.getPlayerShip().setMovementState(view.getPlayerShip().getSTOPPED());
                break;
        }
        return true;
    }
}