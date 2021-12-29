package com.william.boom.Graphics.Enemy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.william.boom.R;

/**
 * SpriteSheetEnemy to draw enemy
 */
public class SpriteSheetEnemy {
    private Bitmap bitmap;

    public SpriteSheetEnemy(Context context){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_enemy,bitmapOptions);

    }

    public SpriteEnemy[] getEnemySpriteArray(){
        SpriteEnemy[] spriteEnemyArray = new SpriteEnemy[4];
        spriteEnemyArray[0] = new SpriteEnemy(this,new Rect(0*64,0,1*64,64));
        spriteEnemyArray[1] = new SpriteEnemy(this,new Rect(1*64,0,2*64,64));
        spriteEnemyArray[2] = new SpriteEnemy(this,new Rect(2*64,0,3*64,64));
        spriteEnemyArray[3] = new SpriteEnemy(this,new Rect(3*64,0,4*64,64));
        return spriteEnemyArray;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
