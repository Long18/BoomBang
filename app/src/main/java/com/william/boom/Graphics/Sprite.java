package com.william.boom.Graphics;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Sprite is
 */
public class Sprite {

    private final SpriteSheet spriteSheet;
    private final Rect rect;

    public Sprite(SpriteSheet spriteSheet, Rect rect) {
        this.spriteSheet = spriteSheet;
        this.rect = rect;

    }

    public void draw(Canvas canvas, int x, int y) {
        canvas.drawBitmap(
                spriteSheet.getBitmap(),
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
