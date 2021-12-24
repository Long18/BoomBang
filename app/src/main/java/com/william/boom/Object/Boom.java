package com.william.boom.Object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.william.boom.GameLoop;
import com.william.boom.R;

public class Boom extends Circle {

    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;


    public Boom(Context context, Player dropBoom) {
        super(
                context,
                ContextCompat.getColor(context, R.color.boom),
                dropBoom.positionX,
                dropBoom.positionY,
                25
        );

        velocityX = dropBoom.getDirectionX()*MAX_SPEED;
        velocityY = dropBoom.getDirectionY()*MAX_SPEED;
    }

    @Override
    public void update() {
        positionX += velocityX;
        positionY += velocityY;
    }
}
