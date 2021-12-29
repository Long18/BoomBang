package com.william.boom.Graphics;

import android.graphics.Canvas;

import com.william.boom.GameObject.Player;
import com.william.boom.GameObject.PlayerState;

/**
 * Animator to make motion of sprite
 */
public class Animator {
    private Sprite[] playerSpriteArray;

    private static final int MAX_UPDATE_FRAME = 5;
    private int updatesBeforeNextMoveFrame;
    private int indexNotMovingFrame = 0;
    private int indexMovingFrame = 1;

    public Animator(Sprite[] playerSpriteArray) {
        this.playerSpriteArray = playerSpriteArray;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player) {
        switch (player.getPlayerState().getState()) {
            case NOT_MOVING:
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[indexNotMovingFrame]);
                break;
            case STARED_MOVING:
                updatesBeforeNextMoveFrame = MAX_UPDATE_FRAME;
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[indexMovingFrame]);
                break;
            case IS_MOVING:
                updatesBeforeNextMoveFrame--;
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATE_FRAME;
                    toggleIndexMovingFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[indexMovingFrame]);
                break;
            default:
                break;
        }

    }

    private void toggleIndexMovingFrame() {
        if (indexMovingFrame == 1) {
            indexMovingFrame = 2;
        } else if (indexMovingFrame == 2) {
            indexMovingFrame = 3;
        } else if (indexMovingFrame == 3) {
            indexMovingFrame = 4;
        } else {
            indexMovingFrame = 1;
        }
    }

    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Player player, Sprite sprite) {
        sprite.draw(
                canvas,
                (int) gameDisplay.gameToDisplayX(player.getPositionX() - sprite.getWidth() / 2),
                (int) gameDisplay.gameToDisplayY(player.getPositionY()) - sprite.getHeight() / 2);
    }
}
