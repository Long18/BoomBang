package com.william.boom.GamePanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.william.boom.R;

/**
 * GameOver is a panel which draws the text when the player death
 */
public class GameOver {

    private Context context;

    public GameOver(Context context) {
        this.context = context;
    }

    public void draw(Canvas canvas) {
        String text = "GAME OVER";

        float x = 800f;
        float y = 200f;

        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.gameOver);
        paint.setColor(color);
        float textSize = 150f;
        paint.setTextSize(textSize);
        canvas.drawText(text, x, y, paint);
    }
}
