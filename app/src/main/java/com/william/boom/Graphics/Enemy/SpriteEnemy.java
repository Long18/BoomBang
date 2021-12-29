package com.william.boom.Graphics.Enemy;

import android.graphics.Canvas;
import android.graphics.Rect;

public class SpriteEnemy {
    private final SpriteSheetEnemy spriteSheetEnemy;
    private final Rect rect;

    public SpriteEnemy(SpriteSheetEnemy spriteSheetEnemy, Rect rect) {
        this.spriteSheetEnemy = spriteSheetEnemy;
        this.rect = rect;

    }

    public void draw(Canvas canvas, int x, int y) {
        canvas.drawBitmap(
                spriteSheetEnemy.getBitmap(),
                rect,
                new Rect(x,y,x+getWidth()*2,y+getHeight()*2),
                null
        );
    }

    public int getHeight() {
        return rect.height();
    }
    public int getWidth() {
        return rect.width();
    }
}
