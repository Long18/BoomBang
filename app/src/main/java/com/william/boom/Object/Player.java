package com.william.boom.Object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.william.boom.GameLoop;
import com.william.boom.Joystick;
import com.william.boom.R;
import com.william.boom.Utils;

/**
 * Player is the main character of the game, which the user can control with a touch joystick.
 * The player class iss an extension of a Circle, which is an extension of a GameObject
 */
public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    private final Joystick joystick;

    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.red), positionX, positionY, radius);
        this.joystick = joystick;

    }

    public void update() {
        //Update velocity based on actuator of joystick
        velocityX = joystick.getActuatorX() * MAX_SPEED;
        velocityY = joystick.getActuatorY() * MAX_SPEED;

        //Update position
        positionX += velocityX;
        positionY += velocityY;

        //Update boom direction
        if (velocityX != 0 || velocityY != 0){
            //Normalize velocity to get direction
            double distance = Utils.getDistanceBP(0,0,velocityX,velocityY);
            directionX = velocityX/distance;
            directionY = velocityY/distance;
        }
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
