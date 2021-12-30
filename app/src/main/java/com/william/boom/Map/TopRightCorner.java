package com.william.boom.Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.william.boom.Graphics.Sprite;
import com.william.boom.Graphics.SpriteSheet;

public class TopRightCorner extends Tile {
    private final Sprite sprite;

    public TopRightCorner(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getTopRightCornerSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
