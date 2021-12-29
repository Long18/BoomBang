package com.william.boom.GameObject;

import android.graphics.Canvas;

import com.william.boom.Graphics.GameDisplay;

/**
 * GameObject is an abstract class which is the foundation of all object in the game
 */
public abstract class GameObject {
    protected double positionX, positionY;
    protected double velocityX = 0;
    protected double velocityY = 0;
    protected double directionX = 1;
    protected double directionY = 0;

    public GameObject(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;

    }

    public abstract void draw(Canvas canvas, String obj, GameDisplay gameDisplay);
    public abstract void update();

    public double getPositionX() {
        return positionX;
    }
    public double getPositionY() {
        return positionY;
    }

    public static double getDistanceBetweenObject(GameObject obj1, GameObject obj2) {
        return Math.sqrt(
                Math.pow(obj2.getPositionX() - obj1.getPositionX(), 2) +
                        Math.pow(obj2.getPositionY() - obj1.getPositionY(), 2)
        );
    }

    protected double getDirectionX() {
        return directionX;
    }
    protected double getDirectionY() {
        return directionY;
    }
}
