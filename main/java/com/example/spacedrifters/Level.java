package com.example.spacedrifters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class Level extends Activity {
    Controller controller;
    View view;

    /**
     * The onCreate function of the activity Level: this activity is set to full screen, the
     * space ship and enemy which (can be) chosen in the shop are loaded, just as the preferences
     * of the sounds. Also the view and controller get initialized and the content view of this
     * activity is set to the class View.
     * @param savedInstanceState - a Bundle of the saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int spaceShip = gameData.getInt("SpaceshipInUse", 0);
        int enemy = gameData.getInt("EnemyInUse", 0);
        boolean soundMusicOn = gameData.getBoolean("BooleanSound", true);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        view = new View(this, size.x, size.y, spaceShip, enemy, soundMusicOn);
        controller = new Controller(this, view);
        setContentView(view);
    }

    /**
     * A function which is responsible for pausing the game
     */
    @Override
    protected void onPause() {
        super.onPause();
        view.pause();
    }

    /**
     * A function which is responsible for resuming the game
     */
    @Override
    protected void onResume() {
        super.onResume();
        view.resume();
    }

    /**
     * A function which is responsible for hiding the notification bar when playing the game
     * @param hasFocus - a boolean which indicates whether the window of this activity has focus (true) or not (false)
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
                            | android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}