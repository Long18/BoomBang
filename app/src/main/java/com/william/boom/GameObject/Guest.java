package com.william.boom.GameObject;

import android.content.Context;

import com.william.boom.Common.Utils;

public class Guest extends Circle{

    public static int HEALTH = 1;

    public Guest(Context context, int color, double positionX, double positionY, double radius) {
        super(context, color, positionX, positionY, radius);
    }

    @Override
    public void update() {

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
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
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
}
