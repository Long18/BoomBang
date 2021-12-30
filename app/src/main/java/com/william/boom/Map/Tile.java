package com.william.boom.Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.william.boom.Graphics.SpriteSheet;

abstract class Tile {

    protected final Rect mapLocationRect;

    public Tile(Rect mapLocationRect) {
        this.mapLocationRect = mapLocationRect;
    }

    public enum TileType {
        TOP_LEFT_CORNER,
        TOP_RIGHT_CORNER,
        BOTTOM_LEFT_CORNER,
        BOTTOM_RIGHT_CORNER,
        LEFT_WALL,
        RIGHT_WALL,
        TOP_WALL,
        BOTTOM_WALL,
        GRASS_TILE,
        TREE_TILE,
        STONE_TILE,
        LEAF_TILE
    }

    public static Tile getTile(int idxTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {

        switch(TileType.values()[idxTileType]) {
            case TOP_LEFT_CORNER:
                return new TopLeftCorner(spriteSheet, mapLocationRect);
            case TOP_RIGHT_CORNER:
                return new TopRightCorner(spriteSheet, mapLocationRect);
            case BOTTOM_LEFT_CORNER:
                return new BottomLeftCorner(spriteSheet, mapLocationRect);
            case BOTTOM_RIGHT_CORNER:
                return new BottomRightCorner(spriteSheet, mapLocationRect);
            case LEFT_WALL:
                return new LeftWall(spriteSheet, mapLocationRect);
            case RIGHT_WALL:
                return new RightWall(spriteSheet, mapLocationRect);
            case TOP_WALL:
                return new TopWall(spriteSheet, mapLocationRect);
            case BOTTOM_WALL:
                return new BottomWall(spriteSheet, mapLocationRect);
            case GRASS_TILE:
                return new GrassTile(spriteSheet, mapLocationRect);
            case TREE_TILE:
                return new TreeTile(spriteSheet, mapLocationRect);
            case STONE_TILE:
                return new StoneTile(spriteSheet, mapLocationRect);
            case LEAF_TILE:
                return new LeafTile(spriteSheet, mapLocationRect);
            default:
                return null;
        }

    }

    public abstract void draw(Canvas canvas);
}