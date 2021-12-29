package com.william.boom.GameObject;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.william.boom.Graphics.Player.AnimatorPlayer;
import com.william.boom.Graphics.GameDisplay;
import com.william.boom.GameLoop;
import com.william.boom.GamePanel.Joystick;
import com.william.boom.R;
import com.william.boom.Common.Utils;

/**
 * Player is the main character of the game, which the user can control with a touch joystick.
 * The player class iss an extension of a Circle, which is an extension of a GameObject
 */
public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    public static int HEALTH = 100;

    private final Joystick joystick;
    private AnimatorPlayer animatorPlayer;
    private PlayerState playerState;

    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius, AnimatorPlayer animatorPlayer) {
        super(context, ContextCompat.getColor(context, R.color.red), positionX, positionY, radius);
        this.joystick = joystick;
        this.animatorPlayer = animatorPlayer;
        this.playerState = new PlayerState(this);

    }

    public void update() {
        //Update velocity based on actuator of joystick
        velocityX = joystick.getActuatorX() * MAX_SPEED;
        velocityY = joystick.getActuatorY() * MAX_SPEED;

        //Update position
        positionX += velocityX;
        positionY += velocityY;

        //Update boom direction
        if (velocityX != 0 || velocityY != 0) {
            //Normalize velocity to get direction
            double distance = Utils.getDistanceBP(0, 0, velocityX, velocityY);
            directionX = velocityX / distance;
            directionY = velocityY / distance;
        }

        //Update State
        playerState.update();
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void draw(Canvas canvas, String obj, GameDisplay gameDisplay) {
        animatorPlayer.draw(canvas, gameDisplay, this);

    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public int getHealth() {
        return HEALTH;
    }

    public void setHealth(int healthPoint) {
        if (HEALTH >= 0)
            this.HEALTH = healthPoint;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }
}
