package com.example.spacedrifters;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.io.IOException;

public class Settings extends AppCompatActivity {
    private boolean musicPlaying;
    private boolean soundMusicOn;
    private ImageButton bSelectMusic;
    private ImageButton bMuteMusic;
    private ImageButton bMuteSound;
    private ImageButton bUnmuteMusic;
    private ImageButton bUnmuteSound;
    private Uri[] choiceMusic = new Uri[6];

    /**
     * The onCreate function of the activity Settings: this activity is set to full screen, the
     * content view of this activity is set to its xml, the buttons are initialized, the preferences
     * for the muting of sounds and music are loaded and for each button an onClickListener is
     * implemented.
     * @param savedInstanceState - a Bundle of the saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_settings);

        ImageButton bback = findViewById(R.id.btn_Back);
        this.bSelectMusic = findViewById(R.id.btn_SelectMusic);
        this.bMuteMusic = findViewById(R.id.btn_MuteMusic);
        this.bMuteSound = findViewById(R.id.btn_MuteSound);
        this.bUnmuteMusic = findViewById(R.id.btn_UnMuteMusic);
        this.bUnmuteSound = findViewById(R.id.btn_UnMuteSound);

        loadMusic();
        loadSounds();
        saveMusic();
        saveSounds();
        setVisibilityButtonMusic();
        setVisibilityButtonSound();

        registerForContextMenu(bSelectMusic);

        bback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                if (soundMusicOn) {
                    MainActivity.buttonClick.start();
                }
            }
        });

        bSelectMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContextMenu(bSelectMusic);
                if (soundMusicOn) {
                    MainActivity.buttonClick.start();
                }
            }
        });

        bMuteMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.backgroundMusic.pause();
                musicPlaying = false;
                saveMusic();
                setVisibilityButtonMusic();
                if (soundMusicOn) {
                    MainActivity.buttonClick.start();
                }
            }
        });

        bMuteSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundMusicOn = false;
                saveSounds();
                setVisibilityButtonSound();
                if (soundMusicOn) {
                    MainActivity.buttonClick.start();
                }
            }
        });

        bUnmuteMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.backgroundMusic.start();
                musicPlaying = true;
                saveMusic();
                setVisibilityButtonMusic();
                if (soundMusicOn) {
                    MainActivity.buttonClick.start();
                }
            }
        });

        bUnmuteSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundMusicOn = true;
                saveSounds();
                setVisibilityButtonSound();
                if (soundMusicOn) {
                    MainActivity.buttonClick.start();
                }
            }
        });
    }

    /**
     * A getter for the status of the background music: playing or not playing
     * @return - a boolean with the status of the background music: true when the music is playing and false otherwise
     */
    public boolean getMusicPlaying() {
        return musicPlaying;
    }

    /**
     * A function which uses shared preferences to load whether the background music is muted or not
     */
    private void loadMusic() {
        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        musicPlaying = gameData.getBoolean("BooleanMusic", true);
    }

    /**
     * A function which uses shared preferences to load whether the sounds are muted or not
     */
    private void loadSounds() {
        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        soundMusicOn = gameData.getBoolean("BooleanSound", true);
    }

    /**
     * A function that checks if a certain item from the context menu is selected or not
     * @param item - the item that needs to be checked to see if it is selected or not
     * @return - a boolean with the status of the item: true if item is selected and false otherwise
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        choiceMusic[0] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.epicaction_bg);
        choiceMusic[1] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.arcade_bg);
        choiceMusic[2] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.classic_bg);
        choiceMusic[3] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.deathmetal_bg);
        choiceMusic[4] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.hiphop_bg);
        choiceMusic[5] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dubstep_bg);

        MainActivity.backgroundMusic.reset();

        switch (item.getItemId()) {
            case R.id.Epic_Action:
                try {
                    MainActivity.backgroundMusic.setDataSource(getApplicationContext(), choiceMusic[0]);
                    MainActivity.backgroundMusic.prepareAsync();
                    saveMusicChoice(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.Arcade:
                try {
                    MainActivity.backgroundMusic.setDataSource(getApplicationContext(), choiceMusic[1]);
                    MainActivity.backgroundMusic.prepareAsync();
                    saveMusicChoice(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.Classic:
                try {
                    MainActivity.backgroundMusic.setDataSource(getApplicationContext(), choiceMusic[2]);
                    MainActivity.backgroundMusic.prepareAsync();
                    saveMusicChoice(2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.Deathmetal:
                try {
                    MainActivity.backgroundMusic.setDataSource(getApplicationContext(), choiceMusic[3]);
                    MainActivity.backgroundMusic.prepareAsync();
                    saveMusicChoice(3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.Hiphop:
                try {
                    MainActivity.backgroundMusic.setDataSource(getApplicationContext(), choiceMusic[4]);
                    MainActivity.backgroundMusic.prepareAsync();
                    saveMusicChoice(4);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.Dubstep:
                try {
                    MainActivity.backgroundMusic.setDataSource(getApplicationContext(), choiceMusic[5]);
                    MainActivity.backgroundMusic.prepareAsync();
                    saveMusicChoice(5);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                return super.onContextItemSelected(item);
        }

        MainActivity.backgroundMusic.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        return true;
    }

    /**
     * The function that is called when the context menu for view v is being built
     * @param menu - the context menu that is being built
     * @param v - the view for which the context menu is being built
     * @param menuInfo - extra information about the item for which the context menu should be shown.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, android.view.View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.select_music_menu, menu);
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
                    android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
                            | android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    /**
     * A function which uses shared preferences to save whether the background music is muted or not
     */
    private void saveMusic() {
        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = gameData.edit();
        editor.putBoolean("BooleanMusic", musicPlaying);
        editor.apply();
    }

    /**
     * A function which uses shared preferences to save the choice of the user for the background music
     */
    private void saveMusicChoice(int i) {
        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = gameData.edit();
        editor.putString("musicChoice", choiceMusic[i].toString());
        editor.apply();
    }

    /**
     * A function which uses shared preferences to save whether the sounds are muted or not
     */
    private void saveSounds() {
        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = gameData.edit();
        editor.putBoolean("BooleanSound", soundMusicOn);
        editor.apply();
    }

    /**
     * A setter for the visibility of the button for muting/unmuting music: setting the visibility
     * of mute to visible and of unmute to invisible if the music is playing or when the music is
     * not playing, set the visibility of mute to invisible and of unmute to visible.
     */
    private void setVisibilityButtonMusic() {
        if (musicPlaying) {
            bMuteMusic.setVisibility(View.VISIBLE);
            bUnmuteMusic.setVisibility(View.INVISIBLE);
        } else {
            bMuteMusic.setVisibility(View.INVISIBLE);
            bUnmuteMusic.setVisibility(View.VISIBLE);
        }
    }

    /**
     * A setter for the visibility of the button for muting/unmuting music: setting the visibility
     * of mute to visible and of unmute to invisible if the sounds are set to on or when the sounds
     * are set to off, set the visibility of mute to invisible and of unmute to visible.
     */
    private void setVisibilityButtonSound() {
        if (soundMusicOn) {
            bMuteSound.setVisibility(View.VISIBLE);
            bUnmuteSound.setVisibility(View.INVISIBLE);
        } else {
            bMuteSound.setVisibility(View.INVISIBLE);
            bUnmuteSound.setVisibility(View.VISIBLE);
        }
    }
}