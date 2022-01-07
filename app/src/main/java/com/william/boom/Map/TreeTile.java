package com.william.boom.Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.william.boom.Graphics.Sprite;
import com.william.boom.Graphics.SpriteSheet;

public class TreeTile extends Tile {
    private final Sprite treeSprite,grassSprite,stoneSprite;

    public TreeTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        treeSprite = spriteSheet.getTreeTileSprite();
        grassSprite = spriteSheet.getGrassSprite();
        stoneSprite = spriteSheet.getStoneTileSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        treeSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
        grassSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
        stoneSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
