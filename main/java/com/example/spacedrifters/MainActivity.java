package com.example.spacedrifters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private boolean sounds;
    public static MediaPlayer backgroundMusic;
    public static MediaPlayer shooter;
    public static MediaPlayer explosion;
    public static MediaPlayer gameOver;
    public static MediaPlayer buttonClick;
    private Settings settings = new Settings();

    /**
     * The onCreate function of the activity MainActivity: this activity is set to full screen, the
     * content view of this activity is set to its xml, the preferences of music are loaded and set,
     * the sounds for the game are initialized and four onClickListeners are implemented: one for
     * going to the game, one for going to the shop, one for going to the settings and one for
     * closing the application Space Drifters.
     * @param savedInstanceState - a Bundle of the saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        boolean musicPlaying = gameData.getBoolean("BooleanMusic", true);
        String musicPathString = gameData.getString("musicChoice", "android.resource://" + getPackageName()
                + "/" + R.raw.arcade_bg);
        Uri musicPath = Uri.parse(musicPathString);

        backgroundMusic = MediaPlayer.create(this, musicPath);
        if(settings.getMusicPlaying() || musicPlaying) {
            backgroundMusic.setLooping(true);
            backgroundMusic.setVolume(200, 200);
            backgroundMusic.start();
        }
        buttonClick = MediaPlayer.create(this, R.raw.buttonclick);
        explosion = MediaPlayer.create(this, R.raw.explosion);
        gameOver = MediaPlayer.create(this, R.raw.gameover);
        shooter = MediaPlayer.create(this, R.raw.shoot);

        ImageButton bStart = findViewById(R.id.btn_Start);
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSounds();
                if(sounds) {
                    buttonClick.start();
                }
                Intent level = new Intent(MainActivity.this, Level.class);
                startActivity(level);
            }
        });

        ImageButton bShop = findViewById(R.id.btn_Shop);
        bShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSounds();
                if (sounds) {
                    buttonClick.start();
                }
                Intent shop = new Intent(MainActivity.this, Shop.class);
                startActivity(shop);
            }
        });

        ImageButton bSettings = findViewById(R.id.btn_Settings);
        bSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSounds();
                if (sounds) {
                    buttonClick.start();
                }
                Intent settings = new Intent(MainActivity.this, Settings.class);
                startActivity(settings);
            }
        });

        ImageButton bQuit = findViewById(R.id.btn_Quit);
        bQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSounds();
                if (sounds) {
                    buttonClick.start();
                }
                finish();
                System.exit(0);
            }
        });
    }

    /**
     * A function which is responsible for hiding the notification bar and making the activity run in full screen
     * @param hasFocus - a boolean which indicates whether the window of this activity has focus (true) or not (false)
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    /**
     * A function which uses shared preferences to load the choice of sounds made by the player
     */
    private void loadSounds() {
        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        sounds = gameData.getBoolean("BooleanSound", true);
    }
}