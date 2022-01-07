package com.william.boom.Graphics;

import android.graphics.Rect;

import com.william.boom.GameObject.GameObject;
import com.william.boom.GameObject.Player;

/**
 * GameDisplay is a class to make Player center with Screen
 */
public class GameDisplay {

    public final Rect DISPLAY_RECT;

    private GameObject centerObject;

    private double gameToDisplayCoordinateOffsetX;
    private double gameToDisplayCoordinateOffsetY;
    private double displayCenterX;
    private double displayCenterY;
    private double gameCenterX;
    private double gameCenterY;

    private final int widthPixels;
    private final int heightPixels;

    public GameDisplay(int widthPixels, int heightPixels, GameObject centerObject) {
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;

        DISPLAY_RECT = new Rect(0, 0, widthPixels, heightPixels);

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

    public Rect getGameRect() {
        return new Rect(
                (int) gameCenterX - widthPixels / 2,
                (int) gameCenterY - heightPixels / 2,
                (int) gameCenterX + widthPixels / 2,
                (int) gameCenterY + heightPixels / 2
        );
    }
}
