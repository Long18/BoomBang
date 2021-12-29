package com.william.boom.Graphics.Enemy;

import android.graphics.Canvas;

import com.william.boom.GameObject.Enemy;
import com.william.boom.Graphics.GameDisplay;

public class AnimatorEnemy {
    private SpriteEnemy[] enemySpriteEnemyArray;

    private static final int MAX_UPDATE_FRAME = 5;
    private int updatesBeforeNextMoveFrame;
    private int indexNotMovingFrame = 0;
    private int indexMovingFrame = 1;

    public AnimatorEnemy(SpriteEnemy[] enemySpriteEnemyArray) {
        this.enemySpriteEnemyArray = enemySpriteEnemyArray;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, Enemy enemy) {
        switch (enemy.getEnemyState().getState()) {
            case NOT_MOVING:
                drawFrame(canvas, gameDisplay, enemy, enemySpriteEnemyArray[indexNotMovingFrame]);
                break;
            case STARED_MOVING:
                updatesBeforeNextMoveFrame = MAX_UPDATE_FRAME;
                drawFrame(canvas, gameDisplay, enemy, enemySpriteEnemyArray[indexMovingFrame]);
                break;
            case IS_MOVING:
                updatesBeforeNextMoveFrame--;
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATE_FRAME;
                    toggleIndexMovingFrame();
                }
                drawFrame(canvas, gameDisplay, enemy, enemySpriteEnemyArray[indexMovingFrame]);
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
        }else {
            indexMovingFrame = 1;
        }
    }

    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Enemy enemy, SpriteEnemy spriteEnemy) {
        spriteEnemy.draw(
                canvas,
                (int) gameDisplay.gameToDisplayX(enemy.getPositionX() - spriteEnemy.getWidth() / 2),
                (int) gameDisplay.gameToDisplayY(enemy.getPositionY()) - spriteEnemy.getHeight() / 2);
    }
}
