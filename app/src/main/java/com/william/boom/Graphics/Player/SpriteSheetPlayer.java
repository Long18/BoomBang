package com.william.boom.Graphics.Player;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.william.boom.R;

/**
 * SpriteSheet to draw player
 */
public class SpriteSheetPlayer {
    private Bitmap bitmap;

    public SpriteSheetPlayer(Context context){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_player,bitmapOptions);

    }

    public SpritePlayer[] getPlayerSpriteArray(){
        SpritePlayer[] spritePlayerArray = new SpritePlayer[5];
        spritePlayerArray[0] = new SpritePlayer(this,new Rect(0*64,0,1*64,64));
        spritePlayerArray[1] = new SpritePlayer(this,new Rect(1*64,0,2*64,64));
        spritePlayerArray[2] = new SpritePlayer(this,new Rect(2*64,0,3*64,64));
        spritePlayerArray[3] = new SpritePlayer(this,new Rect(3*64,0,4*64,64));
        spritePlayerArray[4] = new SpritePlayer(this,new Rect(4*64,0,5*64,64));
        return spritePlayerArray;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
