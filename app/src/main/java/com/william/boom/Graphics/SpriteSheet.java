package com.william.boom.Graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.william.boom.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 64;
    private static final int SPRITE_HEIGHT_PIXELS = 64;
    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_tilemap, bitmapOptions);

    }

    public Sprite[] getSpriteArray() {
        Sprite[] spriteArray = new Sprite[5];
        spriteArray[0] = new Sprite(this, new Rect(0 * 64, 0, 1 * 64, 64));
        return spriteArray;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    private Sprite getSpriteByIndex(int indexRow, int indexCol) {
        return new Sprite(this, new Rect(
                indexCol * SPRITE_WIDTH_PIXELS,
                indexRow * SPRITE_HEIGHT_PIXELS,
                (indexCol + 1) * SPRITE_WIDTH_PIXELS,
                (indexRow + 1) * SPRITE_HEIGHT_PIXELS
        ));
    }

    public Sprite getTopLeftCornerSprite() {
        return getSpriteByIndex(0, 1);
    }
    public Sprite getTopRightCornerSprite() {
        return getSpriteByIndex(0, 2);
    }
    public Sprite getBottomLeftCornerSprite() {
        return getSpriteByIndex(0, 3);
    }
    public Sprite getBottomRightCornerSprite() {
        return getSpriteByIndex(0, 4);
    }
    public Sprite getLeftWallSprite() {
        return getSpriteByIndex(0, 5);
    }
    public Sprite getRightWallSprite() {
        return getSpriteByIndex(0, 6);
    }
    public Sprite getTopWallSprite() {
        return getSpriteByIndex(0, 7);
    }
    public Sprite getBottomWallSprite() {
        return getSpriteByIndex(0, 8);
    }
    public Sprite getGrassSprite() {
        return getSpriteByIndex(1, 1);
    }
    public Sprite getTreeTileSprite() {
        return getSpriteByIndex(1, 1);
    }
    public Sprite getStoneTileSprite() {
        return getSpriteByIndex(1, 2);
    }
    public Sprite getLeafTileSprite() {
        return getSpriteByIndex(1, 3);
    }


}
