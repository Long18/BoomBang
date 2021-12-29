package com.william.boom.GameObject;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.william.boom.GameLoop;
import com.william.boom.Graphics.Enemy.AnimatorEnemy;
import com.william.boom.Graphics.GameDisplay;
import com.william.boom.R;

/**
 * Enemy is a character which always moves in the direction of the player.
 * The Enemy class is an extension of a Circle, which is an extension a GameObject.
 */
public class Enemy extends Circle {
    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND * 0.6;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    private static final double SPAWNS_PER_MINUTE = 20;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE / 60.0;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS / SPAWNS_PER_SECOND;
    /**
     * @param updateUNS = update Until Next Spawn.
     */
    private static double updatesUNS = UPDATES_PER_SPAWN;

    private final Player player;
    private AnimatorEnemy animatorEnemy;
    private EnemyState enemyState;

    public Enemy(Context context, Player player, AnimatorEnemy animatorEnemy) {
        super(
                context,
                ContextCompat.getColor(context, R.color.enemy),
                Math.random() * 1000,
                Math.random() * 1000,
                30
        );
        this.player = player;
        this.animatorEnemy = animatorEnemy;
        this.enemyState = new EnemyState(this);
    }

    /**
     * readyToSpawn checks if a new enemy should spawn, according to the decided number of spawns
     * per minute ( Check SPAWNS_PER_MINUTE at top )
     *
     * @return
     */
    public static boolean readyToSpawn() {
        if (updatesUNS <= 0) {
            updatesUNS += UPDATES_PER_SPAWN;
            return true;
        } else {
            updatesUNS--;
            return false;
        }
    }

    @Override
    public void update() {

        //Update velocity of the enemy so that the velocity is
        //in the direction of the player
        //====================================================
        //Calculate vector from enemy to player ( x, y )
        double distanceToPlayerX = player.getPositionX() - positionX;
        double distanceToPlayerY = player.getPositionY() - positionY;

        //Calculate (absolute) distance between enemy (this) and player
        double distanceToPlayer = GameObject.getDistanceBetweenObject(this, player);

        //Calculate direction from enemy to player
        double directionX = distanceToPlayerX / distanceToPlayer;
        double directionY = distanceToPlayerY / distanceToPlayer;

        //Set velocity in the direction to the player
        if (distanceToPlayer > 0) {
            //Avoid division by zero
            velocityX = directionX * MAX_SPEED;
            velocityY = directionY * MAX_SPEED;
        } else {
            velocityX = 0;
            velocityY = 0;
        }

        //Update the position of the enemy
        positionX += velocityX;
        positionY += velocityY;

        //Update State
        enemyState.update();
    }

    public void draw(Canvas canvas, String obj, GameDisplay gameDisplay) {
        animatorEnemy.draw(canvas, gameDisplay, this);

    }

    public EnemyState getEnemyState() {
        return enemyState;
    }
}
