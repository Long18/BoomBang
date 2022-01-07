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

    public SpritePlayer[][] getPlayerSpriteArray(){
        SpritePlayer[][] spritePlayerArray = new SpritePlayer[4][5];
        spritePlayerArray[0][0] = new SpritePlayer(this,new Rect(0*64,0,1*64,64));
        spritePlayerArray[0][1] = new SpritePlayer(this,new Rect(1*64,0,2*64,64));
        spritePlayerArray[0][2] = new SpritePlayer(this,new Rect(2*64,0,3*64,64));
        spritePlayerArray[0][3] = new SpritePlayer(this,new Rect(3*64,0,4*64,64));
        spritePlayerArray[0][4] = new SpritePlayer(this,new Rect(4*64,0,5*64,64));

        spritePlayerArray[1][0] = new SpritePlayer(this,new Rect(0*64,0,1*64,64));
        spritePlayerArray[1][1] = new SpritePlayer(this,new Rect(1*64,0,2*64,64));
        spritePlayerArray[1][2] = new SpritePlayer(this,new Rect(2*64,0,3*64,64));
        spritePlayerArray[1][3] = new SpritePlayer(this,new Rect(3*64,0,4*64,64));
        spritePlayerArray[1][4] = new SpritePlayer(this,new Rect(4*64,0,5*64,64));

        spritePlayerArray[2][0] = new SpritePlayer(this,new Rect(0*64,0,1*64,64));
        spritePlayerArray[2][1] = new SpritePlayer(this,new Rect(1*64,0,2*64,64));
        spritePlayerArray[2][2] = new SpritePlayer(this,new Rect(2*64,0,3*64,64));
        spritePlayerArray[2][3] = new SpritePlayer(this,new Rect(3*64,0,4*64,64));
        spritePlayerArray[2][4] = new SpritePlayer(this,new Rect(4*64,0,5*64,64));

        spritePlayerArray[3][0] = new SpritePlayer(this,new Rect(0*64,0,1*64,64));
        spritePlayerArray[3][1] = new SpritePlayer(this,new Rect(1*64,0,2*64,64));
        spritePlayerArray[3][2] = new SpritePlayer(this,new Rect(2*64,0,3*64,64));
        spritePlayerArray[3][3] = new SpritePlayer(this,new Rect(3*64,0,4*64,64));
        spritePlayerArray[3][4] = new SpritePlayer(this,new Rect(4*64,0,5*64,64));

        return spritePlayerArray;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
