package com.william.boom;

import android.graphics.Canvas;

/**
 * GameObject is an abstract class which is the foundation of all object in the game
 */
public abstract class GameObject {
    protected double positionX, positionY;
    protected double velocityX;
    protected double velocityY;

    public GameObject(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;

    }

    public abstract void draw(Canvas canvas);

    public abstract void update();
}
