package com.example.spacedrifters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Timer;
import java.util.TimerTask;

public class View extends SurfaceView implements Runnable {
    private boolean gameOverMessage = false;
    private boolean paused = true;
    private boolean sounds;
    private double elapsedSeconds;
    private int counter = 0;
    private int enemyUse;
    private int score = 0;
    private int screenWidth;
    private int screenLength;
    private int spaceShip;
    private long fps;
    private long startTime;
    private volatile boolean playing;
    private Bullet bullet;
    private Context context;
    private Enemy enemy;
    private GameOver endOfGame;
    private Meteorite[] meteorites = new Meteorite[4];
    private Paint paint;
    private PlayerShip playerShip;
    private Star[] stars = new Star[8];
    private SurfaceHolder holder;
    private Thread thread = null;

    /**
     * The constructor of the class View
     *
     * @param context       - global information about the application environment (given through level)
     * @param screenWidth   - an int of the width of the screen of the mobile phone (x-coordinates)
     * @param screenLength  - an int of the length of the screen of the mobile phone (y-coordinates)
     * @param playerShipUse - an int that indicates which player ship is in use in the game
     * @param enemyUse      - an int that indicates which enemy is in use in the game
     * @param sounds        - a boolean that indicates whether the sounds are muted or not
     */
    public View(Context context, int screenWidth, int screenLength, int playerShipUse, int enemyUse, boolean sounds) {
        super(context);
        this.context = context;
        holder = getHolder();
        paint = new Paint();
        this.screenWidth = screenWidth;
        this.screenLength = screenLength;
        this.spaceShip = playerShipUse;
        this.enemyUse = enemyUse;
        this.sounds = sounds;
        prepareLevel();
    }

    /**
     * A function which is responsible for drawing the game with all the elements on the screen
     * when the game gets started. This function is also responsible for drawing the game over
     */
    public void draw() {
        if (holder.getSurface().isValid()) {
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.argb(255, 0, 0, 0));
            if (!gameOverMessage) {
                String scoreText = "Score: " + score;
                paint.setColor(Color.argb(255, 255, 255, 0));
                paint.setTextSize(50);
                canvas.drawText(scoreText, 10, 50, paint);

                for (Star star : stars) {
                    canvas.drawBitmap(star.getStarBitmap(), star.getX(), star.getY(), paint);
                }

                if (bullet.getStatus()) {
                    canvas.drawRect(bullet.getRectangle(), paint);
                }

                canvas.drawBitmap(playerShip.getPlayerShipBitmap(spaceShip), playerShip.getX() - playerShip.getWidth() / 2,
                        screenLength - playerShip.getLength(), paint);
                canvas.drawBitmap(enemy.getEnemyBitmap(enemyUse), enemy.getX(), enemy.getY(), paint);

                for (int i = 0; i < meteorites.length; i++) {
                    canvas.drawBitmap(meteorites[i].getMeteoriteBitmap(i), meteorites[i].getX(), meteorites[i].getY(), paint);
                }
            } else {
                canvas.drawBitmap(endOfGame.getGameOverBitmap(), endOfGame.getX(), endOfGame.getY(), paint);
                String endScore = "You obtained a score of " + score;
                String playTime = "You played " + elapsedSeconds + " seconds.";
                paint.setColor(Color.argb(255, 255, 255, 0));
                paint.setTextSize(50);
                paint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(endScore, (float) screenWidth / 2, (float) screenLength / 4 * 3, paint);
                canvas.drawText(playTime, (float) screenWidth / 2, (float) screenLength / 4 * 3 + 100, paint);
            }
            holder.unlockCanvasAndPost(canvas);
        }
    }

    /**
     * A function which is responsible for handling the game over. It redirects the game through
     * the shop after two seconds and saves the score as coins
     */
    public void endGame() {
        long endTime = SystemClock.elapsedRealtime();
        long elapsedMilliSeconds = endTime - startTime;
        elapsedSeconds = elapsedMilliSeconds / 1000.0;
        gameOverMessage = true;
        playing = false;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(context, Shop.class);
                intent.putExtra("SCORE", score);
                context.startActivity(intent);
            }
        }, 2000);
    }

    /**
     * A getter for the status of the game: game over or not
     *
     * @return - a boolean with the status of the game: true when the player is game over and false otherwise
     */
    public boolean getGameOverMessage() {
        return gameOverMessage;
    }

    /**
     * A getter for the status of the game: paused or not
     *
     * @return - a boolean with the status of the game: true when the game is paused and false otherwise
     */
    public boolean getPaused() {
        return paused;
    }

    /**
     * A getter for the used player ship (needed for the controller)
     *
     * @return - a PlayerShip which is used in the game
     */
    public PlayerShip getPlayerShip() {
        return playerShip;
    }

    /**
     * A function which is responsible for pausing the game (through threads)
     */
    public void pause() {
        playing = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }
    }

    /**
     * A function which is responsible for preparing all objects needed in this class to start a game
     */
    private void prepareLevel() {
        bullet = new Bullet(screenLength, this, sounds);
        endOfGame = new GameOver(context, screenWidth, screenLength);
        enemy = new Enemy(context, screenWidth, screenLength);
        playerShip = new PlayerShip(context, screenWidth, screenLength);

        for (int i = 0; i < meteorites.length; i++) {
            meteorites[i] = new Meteorite(context, screenWidth, screenLength);
        }

        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(context, screenWidth, screenLength);
        }
    }

    /**
     * A function which is responsible for resuming the game (though threads)
     */
    public void resume() {
        playing = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * A function that is needed with the Runnable interface: this function describes what should
     * happen when the game is running and thus when the player is playing Space Drifters
     */
    @Override
    public void run() {
        while (playing) {
            long startFrameTime = System.currentTimeMillis();
            if (!paused) {
                update();
            }
            draw();
            long timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame;
            }
            bullet.shoot(playerShip.getX(), screenLength - playerShip.getLength() / 2);
        }
    }

    /**
     * A setter for pausing the game: setting the game to play and starting playtime
     */
    public void setPausedFalse() {
        if (paused) {
            startTime = SystemClock.elapsedRealtime();
            paused = false;
        }
    }

    /**
     * A function which is responsible for the movement of the entire game
     */
    public void update() {
        boolean bumped = false;
        boolean lost = false;
        counter++;

        if (bullet.getStatus()) {
            bullet.update(fps);
        }

        if (bullet.getImpactPointY() < 0) {
            bullet.setInactive();
        }

        if (bullet.getStatus()) {
            if (enemy.getVisibility()) {
                if (RectF.intersects(bullet.getRectangle(), enemy.getRectangle())) {
                    if (sounds) {
                        MainActivity.explosion.start();
                    }
                    enemy.setInvisible();
                    enemy.spawnAgain(screenWidth);
                    bullet.setInactive();
                    score += 5;
                }
            }
        }

        if (enemy.getVisibility()) {
            enemy.update(fps);
            if (enemy.getY() > screenLength + enemy.getLength()) {
                bumped = true;
            }
            if (RectF.intersects(playerShip.getRect(), enemy.getRectangle())) {
                enemy.setInvisible();
                lost = true;
            }
        }

        if (bumped) {
            enemy.spawnAgain(screenWidth);
        }

        for (int i = 0; i < meteorites.length; i++) {
            meteorites[i].update(fps, i);
            if (meteorites[i].getY() > screenLength + meteorites[i].getLength()) {
                meteorites[i].restart(screenWidth);
            }
            if (bullet.getStatus() && RectF.intersects(bullet.getRectangle(), meteorites[i].getRectangle())) {
                bullet.setInactive();
            }
            if (RectF.intersects(meteorites[i].getRectangle(), playerShip.getRect())) {
                lost = true;
            }
        }

        if (lost) {
            if (sounds) {
                MainActivity.gameOver.start();
            }
            endGame();
        }

        for (Star star : stars) {
            star.update(fps);
            if (star.getY() > screenLength + star.getLength()) {
                if (counter % 25 == 0) {
                    star.restart(screenWidth);
                    break;
                }
            }
        }

        playerShip.update(fps);
    }
}