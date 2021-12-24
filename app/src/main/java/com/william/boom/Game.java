package com.william.boom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.william.boom.Object.Boom;
import com.william.boom.Object.Circle;
import com.william.boom.Object.Enemy;
import com.william.boom.Object.Player;

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
    private GameLoop gameLoop;

    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<Boom> boomList = new ArrayList<Boom>();
    private int joystickPointerId = 0;
    private int numberOfBoomToDrop = 0;

    public Game(Context context) {
        super(context);

        //Get surface holder and add call back
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        //Initialize player
        joystick = new Joystick(275, 750, 70, 40);
        player = new Player(getContext(), joystick, 500, 500, 30);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //Handle touch event actions
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
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
                return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
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
        drawUPS(canvas);
        drawFPS(canvas);

        joystick.draw(canvas);
        player.draw(canvas, "player");
        for (Enemy enemy : enemyList) {
            enemy.draw(canvas, "enemy");
        }
        for (Boom boom : boomList) {
            boom.draw(canvas, "boom");
        }
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.red);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("UPS: " + averageUPS, 50, 50, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.red);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("FPS: " + averageFPS, 50, 100, paint);
    }

    public void update() {
        joystick.update();
        player.update();

        //Spawn enemy if is the first time to spawn new enemies
        if (Enemy.readyToSpawn()) {
            enemyList.add(new Enemy(getContext(), player));
        }

        //Update state of each enemy
        while (numberOfBoomToDrop>0){
            boomList.add(new Boom(getContext(),player));
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
                //Remove enemy if it colides with the player
                IEnemy.remove();
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

    }
}
