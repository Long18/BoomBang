package com.william.boom.GameObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;


import com.william.boom.Common.Common;
import com.william.boom.Graphics.GameDisplay;
import com.william.boom.R;

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

    /**
     * isColliding check if two circle object are colliding
     * based on their positions and radius
     *
     * @param obj1 is Enemy List
     * @param obj2 is the Player
     * @return
     */
    public static boolean isColliding(Circle obj1, Circle obj2) {
        double distance = getDistanceBetweenObject(obj1, obj2);
        double distanceToCollision = obj1.getRadius() + obj2.getRadius();
        if (distance < distanceToCollision) {
            return true;
        } else {
            return false;
        }
    }

    private double getRadius() {
        return radius;
    }

    public void draw(Canvas canvas, String obj, GameDisplay gameDisplay) {

        //Get drawable and draw in screen
        Bitmap bitmap;
        if (obj == "player") {
            bitmap = BitmapFactory
                    .decodeResource(context.getResources(), R.drawable.bomber_down);
        } else if (obj == "enemy") {
            bitmap = BitmapFactory
                    .decodeResource(context.getResources(), R.drawable.boss_down);
        } else /*if (obj == "boom")*/ {
            bitmap = BitmapFactory
                    .decodeResource(context.getResources(), R.drawable.boom1);
        }
        bitmap = Common.scaleBitmap(bitmap, 100, 100);

        //Get width and height of bitmap
        float bitmapX = (float) bitmap.getWidth();
        float bitmapY = (float) bitmap.getHeight();

        //get position of bitmap
        float boardPosX = (float) (positionX - bitmapX);
        float boardPosY = (float) (positionY - bitmapY);

        canvas.drawBitmap(
                bitmap,
                (float) gameDisplay.gameToDisplayX(boardPosX),
                (float) gameDisplay.gameToDisplayY(boardPosY),
                paint);
    }


}
