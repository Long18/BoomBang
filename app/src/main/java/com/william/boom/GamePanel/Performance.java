package com.william.boom.GamePanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.william.boom.GameLoop;
import com.william.boom.R;

/**
 * Performance is the place to draw information of device
 */
public class Performance {

    private GameLoop gameLoop;
    private Context context;

    public Performance(Context context,GameLoop gameLoop){
        this.context = context;
        this.gameLoop = gameLoop;
    }

    public void draw(Canvas canvas) {
        drawUPS(canvas);
        drawFPS(canvas);
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.red);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("UPS: " + averageUPS, 50, 50, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.red);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("FPS: " + averageFPS, 50, 100, paint);
    }
}
