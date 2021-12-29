package com.william.boom.Graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.william.boom.R;

/**
 * SpriteSheet
 */
public class SpriteSheet {
    private Bitmap bitmap;

    public SpriteSheet(Context context){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_player,bitmapOptions);

    }

    public Sprite[] getPlayerSpriteArray(){
        Sprite[] spriteArray = new Sprite[5];
        spriteArray[0] = new Sprite(this,new Rect(0*64,0,1*64,64));
        spriteArray[1] = new Sprite(this,new Rect(1*64,0,2*64,64));
        spriteArray[2] = new Sprite(this,new Rect(2*64,0,3*64,64));
        spriteArray[3] = new Sprite(this,new Rect(3*64,0,4*64,64));
        spriteArray[4] = new Sprite(this,new Rect(4*64,0,5*64,64));
        return spriteArray;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
