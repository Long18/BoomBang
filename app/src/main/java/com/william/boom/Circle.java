package com.william.boom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;


import com.william.boom.Common.Common;

/**
 * Circle is an abstract class which implement a draw method from
 * Game Object for drawing the object as a circle
 */
public abstract class Circle extends GameObject {

    protected double radius;
    protected Paint paint;
    protected Context context;

    public Circle(Context context, int color, double positionX, double positionY, double radius) {
        super(positionX, positionY);

        this.radius = radius;
        this.context = context;

        //Set color for circle
        paint = new Paint();
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {

        //Get drawable and draw in screen
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomber_down);
        bitmap = Common.scaleBitmap(bitmap, 100, 100);

        //Get width and height of bitmap
        float bitmapX = (float) bitmap.getWidth();
        float bitmapY = (float) bitmap.getHeight();

        //get position of bitmap
        float boardPosX = (float) (positionX - bitmapX);
        float boardPosY = (float) (positionY - bitmapY);

        canvas.drawBitmap(bitmap, boardPosX, boardPosY, null);
    }
}
