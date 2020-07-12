package com.example.spacedrifters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class Shop extends AppCompatActivity {
    private boolean sounds;
    private int coins;
    private int enUse;
    private int spUse;
    private ArrayList<String> purchased = new ArrayList<>();
    private Map<Integer, View> spaceShips = new HashMap<>();
    private Map<Integer, View> enemies = new HashMap<>();
    private ShopInfo shopInfo;
    private TextView coinValue;
    private TextView[] hundredText = new TextView[3];
    private TextView[] twoHundredText = new TextView[10];
    private TextView fiveHundredText;
    private View[] hundred = new View[3];
    private View[] twoHundred = new View[10];
    private View fiveHundred;

    /**
     * The onCreate function of the activity Shop: this activity is set to full screen, the
     * content view of this activity is set to its xml, the coins, purchased items, space ships
     * in use and enemy in use are loaded and saved, the hashmaps, text arrays and view arrays
     * are initialized, the texts of the items in the shop are set accordingly and click listeners
     * are made to handle clicks on the items in the shop.
     * @param savedInstanceState - a Bundle of the saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_shop);
        shopInfo = new ShopInfo(this);

        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        sounds = gameData.getBoolean("BooleanSound", true);

        loadCoins();
        loadPurchased();
        loadSpaceShipInUse();
        loadEnemyInUse();
        saveCoins();
        savePurchased();
        saveSpaceshipInUse();
        saveEnemyInUse();

        coinValue = findViewById(R.id.txt_CoinValue);
        coinValue.setText(String.format(Locale.getDefault(), "%d", coins));

        hundred[0] = findViewById(R.id.img_Ship1);
        hundredText[0] = findViewById(R.id.txt_Ship1);
        hundred[1] = findViewById(R.id.img_Enemy1);
        hundredText[1] = findViewById(R.id.txt_Enemy1);
        hundred[2] = findViewById(R.id.img_Enemy2);
        hundredText[2] = findViewById(R.id.txt_Enemy2);
        twoHundred[0] = findViewById(R.id.img_Ship2);
        twoHundredText[0] = findViewById(R.id.txt_Ship2);
        twoHundred[1] = findViewById(R.id.img_Ship3);
        twoHundredText[1] = findViewById(R.id.txt_Ship3);
        twoHundred[2] = findViewById(R.id.img_Ship4);
        twoHundredText[2] = findViewById(R.id.txt_Ship4);
        twoHundred[3] = findViewById(R.id.img_Ship5);
        twoHundredText[3] = findViewById(R.id.txt_Ship5);
        twoHundred[4] = findViewById(R.id.img_Enemy3);
        twoHundredText[4] = findViewById(R.id.txt_Enemy3);
        twoHundred[5] = findViewById(R.id.img_Enemy4);
        twoHundredText[5] = findViewById(R.id.txt_Enemy4);
        twoHundred[6] = findViewById(R.id.img_Enemy5);
        twoHundredText[6] = findViewById(R.id.txt_Enemy5);
        twoHundred[7] = findViewById(R.id.img_Enemy6);
        twoHundredText[7] = findViewById(R.id.txt_Enemy6);
        twoHundred[8] = findViewById(R.id.img_Enemy7);
        twoHundredText[8] = findViewById(R.id.txt_Enemy7);
        twoHundred[9] = findViewById(R.id.img_Enemy8);
        twoHundredText[9] = findViewById(R.id.txt_Enemy8);
        fiveHundred = findViewById(R.id.img_Ship6);
        fiveHundredText = findViewById(R.id.txt_Ship6);

        ships();
        enemies();

        setHundredText();
        setTwoHundredText();
        setFiveHundredText();

        clickOnHundred();
        clickOnTwoHundred();
        clickOnFiveHundred();
    }

    /**
     * A function which is responsible for showing a notification that the player has too less
     * coins for buying an item
     * @param v - the used view of the function
     */
    public void buyItemClickFail(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You have insufficient funds to buy this item!");
        builder.setTitle("Not enough Coins");
        builder.setCancelable(false);
        builder.setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }
        );
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * A function which is responsible for showing a notification that the player has bought an
     * item successfully
     * @param v - the used view of the function
     */
    public void buyItemClickSuccess(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You successfully bought a new item!");
        builder.setTitle("Yay!");
        builder.setCancelable(false);
        builder.setNeutralButton("Great!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }
        );
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * A function which is responsible for handling clicks on objects that cost 100 coins:
     * showing that the player cannot buy this item, that the player has bought this item
     * or that the player is using this item in the game
     */
    public void clickOnHundred() {
        for (int i = 0; i < hundred.length; i++) {
            final int finalI = i;
            hundred[finalI].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (coins < 100 && !purchased.contains("Hundred " + finalI)) {
                        buyItemClickFail(v);
                    } else if (purchased.contains("Hundred " + finalI)) {
                        if (hundredText[finalI].getText().equals("Tap to use")) {
                            shopInfo.usingHundred(v, finalI);
                            for (int key : spaceShips.keySet()) {
                                if (spaceShips.get(key).equals(hundred[finalI])) {
                                    spUse = key;
                                    saveSpaceshipInUse();
                                }
                            }
                            for (int key : enemies.keySet()) {
                                if (enemies.get(key).equals(hundred[finalI])) {
                                    enUse = key;
                                    saveEnemyInUse();
                                }
                            }
                        }
                    } else {
                        buyItemClickSuccess(v);
                        coins = coins - 100;
                        saveCoins();
                        coinValue.setText(String.format(Locale.getDefault(), "%d", coins));
                        purchased.add(purchased.size(), "Hundred " + finalI);
                        savePurchased();
                        hundredText[finalI].setText("Tap to use");
                    }
                }
            });
        }
    }

    /**
     * A function which is responsible for handling clicks on objects that cost 200 coins:
     * showing that the player cannot buy this item, that the player has bought this item
     * or that the player is using this item in the game
     */
    public void clickOnTwoHundred() {
        for (int i = 0; i < twoHundred.length; i++) {
            final int finalI = i;
            twoHundred[finalI].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (coins < 200 && !purchased.contains("TwoHundred " + finalI)) {
                        buyItemClickFail(v);
                    } else if (purchased.contains("TwoHundred " + finalI)) {
                        if (twoHundredText[finalI].getText().equals("Tap to use")) {
                            shopInfo.usingTwoHundred(v, finalI);
                            for (int key : spaceShips.keySet()) {
                                if (spaceShips.get(key).equals(twoHundred[finalI])) {
                                    spUse = key;
                                    saveSpaceshipInUse();
                                }
                            }
                            for (int key : enemies.keySet()) {
                                if (enemies.get(key).equals(twoHundred[finalI])) {
                                    enUse = key;
                                    saveEnemyInUse();
                                }
                            }
                        }
                    } else {
                        buyItemClickSuccess(v);
                        coins = coins - 200;
                        saveCoins();
                        coinValue.setText(Integer.toString(coins));
                        purchased.add(purchased.size(), "TwoHundred " + finalI);
                        savePurchased();
                        twoHundredText[finalI].setText("Tap to use");
                    }
                }
            });
        }
    }

    /**
     * A function which is responsible for handling clicks on the object that costs 500 coins:
     * showing that the player cannot buy this item, that the player has bought this item
     * or that the player is using this item in the game
     */
    public void clickOnFiveHundred() {
        fiveHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coins < 500 && !purchased.contains("FiveHundred")) {
                    buyItemClickFail(v);
                } else if (purchased.contains("FiveHundred")) {
                    if (fiveHundredText.getText().equals("Tap to use")) {
                        shopInfo.usingFiveHundred(v);
                        for (int key : spaceShips.keySet()) {
                            if (spaceShips.get(key).equals(fiveHundred)) {
                                spUse = key;
                                saveSpaceshipInUse();
                            }
                        }
                        for (int key : enemies.keySet()) {
                            if (enemies.get(key).equals(fiveHundred)) {
                                enUse = key;
                                saveEnemyInUse();
                            }
                        }
                    }
                } else {
                    buyItemClickSuccess(v);
                    coins = coins - 500;
                    saveCoins();
                    coinValue.setText(Integer.toString(coins));
                    purchased.add(purchased.size(), "FiveHundred");
                    savePurchased();
                    fiveHundredText.setText("Tap to use");
                }
            }
        });
    }

    /**
     * The initialization of the hashmaps containing integers and enemies
     */
    private void enemies() {
        enemies.put(1, hundred[1]);
        enemies.put(2, hundred[2]);
        enemies.put(3, twoHundred[4]);
        enemies.put(4, twoHundred[5]);
        enemies.put(5, twoHundred[6]);
        enemies.put(6, twoHundred[7]);
        enemies.put(7, twoHundred[8]);
        enemies.put(8, twoHundred[9]);
    }

    /**
     * A function which is responsible for closing this activity when the back button is pushed
     * @param v - the used view of the function
     */
    public void goBackButton(View v) {
        if (sounds) {
            MainActivity.buttonClick.start();
        }
        finish();
    }

    /**
     * A function which uses shared preferences to load the amount of coins collected
     */
    private void loadCoins() {
        int score = getIntent().getIntExtra("SCORE", 0);
        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int currentAmount = gameData.getInt("coinsAmount", 0);
        coins = score + currentAmount;
    }

    /**
     * A function which uses shared preferences to load which enemy the player is using
     */
    private void loadEnemyInUse() {
        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        enUse = gameData.getInt("EnemyInUse", 0);
    }

    /**
     * A function which uses shared preferences to load the items which the player already bought
     */
    private void loadPurchased() {
        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        Set<String> set = gameData.getStringSet("purchasedItems", null);
        if(set != null) {
            purchased.addAll(set);
        }
    }

    /**
     * A function which uses shared preferences to load which spaceship the player is using
     */
    private void loadSpaceShipInUse() {
        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        spUse = gameData.getInt("SpaceshipInUse", 0);
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
     * A function which uses shared preferences to save the amount of coins collected
     */
    private void saveCoins() {
        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = gameData.edit();
        editor.putInt("coinsAmount", coins);
        editor.apply();
    }

    /**
     * A function which uses shared preferences to save which enemy the player is using
     */
    private void saveEnemyInUse() {
        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = gameData.edit();
        editor.putInt("EnemyInUse", enUse);
        editor.apply();
    }

    /**
     * A function which uses shared preferences to save the items which the player already bought
     */
    private void savePurchased() {
        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = gameData.edit();
        Set<String> set = new HashSet<>(purchased);
        editor.putStringSet("purchasedItems", set);
        editor.apply();
    }

    /**
     * A function which uses shared preferences to save which spaceship the player is using
     */
    private void saveSpaceshipInUse() {
        SharedPreferences gameData = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = gameData.edit();
        editor.putInt("SpaceshipInUse", spUse);
        editor.apply();
    }

    /**
     * A setter for the texts of items that cost 100 coins: if the item is bought the text will be
     * 'tap to use', otherwise the text will stay the same
     */
    private void setHundredText() {
        for (int i = 0; i < hundred.length; i++) {
            if (purchased.contains("Hundred " + i)) {
                hundredText[i].setText("Tap to use");
            }
        }
    }

    /**
     * A setter for the texts of items that cost 200 coins: if the item is bought the text will be
     * 'tap to use', otherwise the text will stay the same
     */
    private void setTwoHundredText() {
        for (int i = 0; i < twoHundred.length; i++) {
            if (purchased.contains("TwoHundred " + i)) {
                twoHundredText[i].setText("Tap to use");
            }
        }
    }

    /**
     * A setter for the text of the item that costs 500 coins: if the item is bought the text will be
     * 'tap to use', otherwise the text will stay the same
     */
    private void setFiveHundredText() {
        if (purchased.contains("FiveHundred")) {
            fiveHundredText.setText("Tap to use");
        }
    }

    /**
     * The initialization of the hashmaps containing integers and player ships
     */
    private void ships() {
        spaceShips.put(1, hundred[0]);
        spaceShips.put(2, twoHundred[0]);
        spaceShips.put(3, twoHundred[1]);
        spaceShips.put(4, twoHundred[2]);
        spaceShips.put(5, twoHundred[3]);
        spaceShips.put(6, fiveHundred);
    }
}