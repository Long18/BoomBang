package com.william.boom;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.william.boom.GameObject.Boom;
import com.william.boom.GameObject.Circle;
import com.william.boom.GameObject.Enemy;
import com.william.boom.GameObject.Player;
import com.william.boom.GamePanel.GameOver;
import com.william.boom.GamePanel.Joystick;
import com.william.boom.GamePanel.Menu;
import com.william.boom.GamePanel.Performance;
import com.william.boom.GamePanel.ServerRequest;
import com.william.boom.Graphics.Enemy.AnimatorEnemy;
import com.william.boom.Graphics.Player.AnimatorPlayer;
import com.william.boom.Graphics.GameDisplay;
import com.william.boom.Graphics.Enemy.SpriteSheetEnemy;
import com.william.boom.Graphics.Player.SpriteSheetPlayer;
import com.william.boom.Graphics.SpriteSheet;
import com.william.boom.Map.Tilemap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Game manages all object in the game
 * Updating all states
 * Render all object to the screen
 */
public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private final Player player;
    private final Joystick joystick;
    private final Menu menu;
    private final Tilemap tilemap;
    private ServerRequest serverRequest;
    //private final Guest guest;

    private GameLoop gameLoop;
    private GameOver gameOver;
    private Activity activity;

    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<Boom> boomList = new ArrayList<Boom>();

    private int joystickPointerId = 0;
    private int menuPointerId = 0;
    private int numberOfBoomToDrop = 0;
    private Performance performance;
    private GameDisplay gameDisplay;
    private boolean turnOn = false;


    public Game(Context context) {
        super(context);

        //Get surface holder and add call back
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        //Initialize game panels
        gameOver = new GameOver(context);
        performance = new Performance(context, gameLoop);
        joystick = new Joystick(275, 750, 70, 40);
        menu = new Menu(context,gameLoop,10,10);
        activity = new Activity();

        //Initialize player
        SpriteSheetPlayer spriteSheetPlayer = new SpriteSheetPlayer(context);
        AnimatorPlayer animatorPlayer = new AnimatorPlayer(spriteSheetPlayer.getPlayerSpriteArray());
        player = new Player(context, joystick, 1200, 900, 30, animatorPlayer);

        //guest = new Guest(context,R.color.colorPrimary,serverRequest.getPositionX(),serverRequest.getPositionY(),30);

        //Initialize gameDisplay and center it around player
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);

        //Initialize Tilemap
        SpriteSheet spriteSheet = new SpriteSheet(context);
        tilemap = new Tilemap(spriteSheet);


        //Initialize server
        serverRequest = new ServerRequest(player, context);

        setFocusable(true);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //Handle touch event actions
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:

                if (menu.getIsPressed()){
                    pause();
                }else if (menu.isPressed((double) event.getX(), (double) event.getY())){
                    menuPointerId = event.getPointerId(event.getActionIndex());
                    menu.setIsPressed(true);
                }

                if (joystick.getIsPressed()) {
                    //When joystick was pressed before this event -> drop boom
                    numberOfBoomToDrop++;
                } else if (joystick.isPressed((double) event.getX(), (double) event.getY())) {
                    //Joystick is pressed in this event -> setIsPressed(true) and store ID
                    joystickPointerId = event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                } else {
                    //Joystick was not previously, and is not pressed in this event -> drop boom
                    numberOfBoomToDrop++;
                    serverRequest.send();

                }
                return true;
            case MotionEvent.ACTION_MOVE:
                //Joystick was pressed previously and is now moved
                if (joystick.getIsPressed()) {
                    joystick.setActuator((double) event.getX(), (double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (joystickPointerId == event.getPointerId(event.getActionIndex())) {
                    //Joystick was let go of -> setIsPressed(false) and resetActuator
                    joystick.setIsPressed(false);
                    joystick.resetActuator();
                }
                if (menuPointerId == event.getPointerId(event.getActionIndex())) {
                    menu.setIsPressed(false);
                }
                return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            gameLoop = new GameLoop(this, surfaceHolder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //draw tilemap
        tilemap.draw(canvas, gameDisplay);

        //draw menu
        menu.draw(canvas);

        //draw game panels
        if (turnOn) {
            performance.draw(canvas);
        }
        joystick.draw(canvas);


        //draw Object
        player.draw(canvas, "player", gameDisplay);
        //guest.draw(canvas,"player");

        for (Enemy enemy : enemyList) {
            enemy.draw(canvas, "enemy", gameDisplay);
        }
        for (Boom boom : boomList) {
            boom.draw(canvas, "boom", gameDisplay);
        }

        //Draw Game Over
        if (player.getHealth() <= 0) {
            //gameOver.draw(canvas);
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    menu.gameOverDialog();
                }
            });
        }
    }


    public void update() {
        //Stop updating the game if the player is death
        if (player.getHealth() <= 0) {
            return;
        }
        //Stop updating the game if menu is on
        if (menu.getState()){
            return;
        }

        //Update game state
        joystick.update();
        player.update();
        menu.update();

        //Update server
        serverRequest.update();

        //Spawn enemy if is the first time to spawn new enemies
        if (Enemy.readyToSpawn()) {
            SpriteSheetEnemy spriteSheetEnemy = new SpriteSheetEnemy(getContext());
            AnimatorEnemy animatorEnemy = new AnimatorEnemy(spriteSheetEnemy.getEnemySpriteArray());
            enemyList.add(new Enemy(getContext(), player, animatorEnemy));
        }

        //Update state of each enemy
        while (numberOfBoomToDrop > 0) {
            boomList.add(new Boom(getContext(), player));
            numberOfBoomToDrop--;
        }
        for (Enemy enemy : enemyList) {
            enemy.update();
        }

        //Update state of each boom
        for (Boom boom : boomList) {
            boom.update();
        }

        //Iterate through enemyList and check for collision
        //between each enemy, the player and boom
        Iterator<Enemy> IEnemy = enemyList.iterator();
        while (IEnemy.hasNext()) {
            Circle enemy = IEnemy.next();
            if (Circle.isColliding(enemy, player)) {
                //Remove enemy if it collides with the player
                IEnemy.remove();
                player.setHealth(player.getHealth() - 1);
                continue;
            }
            Iterator<Boom> IBoom = boomList.iterator();
            while (IBoom.hasNext()) {
                Circle boom = IBoom.next();
                //Remove boom if it collides with an enemy
                if (Circle.isColliding(boom, enemy)) {
                    IBoom.remove();
                    IEnemy.remove();
                    break;
                }
            }
        }
        gameDisplay.update();

    }

    public void pause() {
        menu.showDialog();
        gameLoop.stopLoop();
    }
}
