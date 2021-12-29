package com.william.boom.Graphics;

import com.william.boom.GameObject.GameObject;
import com.william.boom.GameObject.Player;

/**
 * GameDisplay is a class to make Player center with Screen
 */
public class GameDisplay {
    private double gameToDisplayCoordinateOffsetX;
    private double gameToDisplayCoordinateOffsetY;
    private double displayCenterX;
    private double displayCenterY;
    private double gameCenterX;
    private double gameCenterY;
    private GameObject centerObject;

    public GameDisplay(int widthPixels, int heightPixels, GameObject centerObject) {
        this.centerObject = centerObject;

        displayCenterX = widthPixels / 2.0;
        displayCenterY = heightPixels / 2.0;
    }

    public void update() {
        gameCenterX = centerObject.getPositionX();
        gameCenterY = centerObject.getPositionY();

        gameToDisplayCoordinateOffsetX = displayCenterX - gameCenterX;
        gameToDisplayCoordinateOffsetY = displayCenterY - gameCenterY;
    }

    public double gameToDisplayX(double x) {
        return x + gameToDisplayCoordinateOffsetX;
    }
    public double gameToDisplayY(double y) {
        return y + gameToDisplayCoordinateOffsetY;
    }
}
