package com.william.boom.Graphics.Player;

import android.graphics.Canvas;

import com.william.boom.GameObject.Player;
import com.william.boom.Graphics.GameDisplay;

/**
 * Animator to make motion of sprite
 */
public class AnimatorPlayer {
    private SpritePlayer[][] playerSpritePlayerArray;

    private static final int MAX_UPDATE_FRAME = 5;
    private int updatesBeforeNextMoveFrame;
    private int indexNotMovingFrame = 0;
    private int indexMovingFrame = 1;
    private int indexMovingDownFrame = 0;
    private int indexMovingRightFrame = 2;
    private int indexMovingLeftFrame = 1;
    private int indexMovingUpFrame = 3;

    public AnimatorPlayer(SpritePlayer[][] playerSpritePlayerArray) {
        this.playerSpritePlayerArray = playerSpritePlayerArray;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player) {
        switch (player.getPlayerState().getState()) {
            case NOT_MOVING:
                drawFrame(canvas, gameDisplay, player, playerSpritePlayerArray[indexNotMovingFrame][indexNotMovingFrame]);
                break;
            case STARED_MOVING:
                updatesBeforeNextMoveFrame = MAX_UPDATE_FRAME;
                drawFrame(canvas, gameDisplay, player, playerSpritePlayerArray[indexMovingDownFrame][indexMovingFrame]);
                break;
            case MOVING_RIGHT:
                updatesBeforeNextMoveFrame--;
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATE_FRAME;
                    toggleIndexMovingFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpritePlayerArray[indexMovingRightFrame][indexMovingFrame]);
                break;
            case MOVING_LEFT:
                updatesBeforeNextMoveFrame--;
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATE_FRAME;
                    toggleIndexMovingFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpritePlayerArray[indexMovingLeftFrame][indexMovingFrame]);
                break;
            case MOVING_UP:
                updatesBeforeNextMoveFrame--;
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATE_FRAME;
                    toggleIndexMovingFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpritePlayerArray[indexMovingUpFrame][indexMovingFrame]);
                break;
            case IS_MOVING:
                updatesBeforeNextMoveFrame--;
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATE_FRAME;
                    toggleIndexMovingFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpritePlayerArray[indexMovingFrame][indexMovingFrame]);
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
/*

    private void toggleIndexMovingLeftFrame() {
        if (indexMovingFrame == 5) {
            indexMovingFrame = 6;
        } else if (indexMovingFrame == 6) {
            indexMovingFrame = 7;
        } else if (indexMovingFrame == 7) {
            indexMovingFrame = 8;
        } else {
            indexMovingFrame = 5;
        }
    }

    private void toggleIndexMovingRightFrame() {
        if (indexMovingFrame == 9) {
            indexMovingFrame = 10;
        } else if (indexMovingFrame == 10) {
            indexMovingFrame = 11;
        } else if (indexMovingFrame == 11) {
            indexMovingFrame = 12;
        } else {
            indexMovingFrame = 9;
        }
    }

    private void toggleIndexMovingUpFrame() {
        if (indexMovingFrame == 10) {
            indexMovingFrame = 11;
        } else if (indexMovingFrame == 11) {
            indexMovingFrame = 12;
        } else if (indexMovingFrame == 12) {
            indexMovingFrame = 13;
        } else {
            indexMovingFrame = 10;
        }
    }

*/


    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Player player, SpritePlayer spritePlayer) {
        spritePlayer.draw(
                canvas,
                (int) gameDisplay.gameToDisplayX(player.getPositionX() - spritePlayer.getWidth() ),
                (int) gameDisplay.gameToDisplayY(player.getPositionY()) - spritePlayer.getHeight() /2);
    }
}
