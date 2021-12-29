package com.william.boom.Graphics.Player;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Sprite is
 */
public class SpritePlayer {

    private final SpriteSheetPlayer spriteSheetPlayer;
    private final Rect rect;

    public SpritePlayer(SpriteSheetPlayer spriteSheetPlayer, Rect rect) {
        this.spriteSheetPlayer = spriteSheetPlayer;
        this.rect = rect;

    }

    public void draw(Canvas canvas, int x, int y) {
        canvas.drawBitmap(
                spriteSheetPlayer.getBitmap(),
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
