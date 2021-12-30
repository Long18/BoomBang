package com.william.boom.Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.william.boom.Graphics.Sprite;
import com.william.boom.Graphics.SpriteSheet;

public class LeftWall extends Tile {
    private final Sprite sprite;

    public LeftWall(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getLeftWallSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
