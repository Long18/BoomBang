package com.william.boom.GamePanel;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.william.boom.Common.Common;
import com.william.boom.Common.Utils;
import com.william.boom.Game;
import com.william.boom.GameLoop;
import com.william.boom.MainActivity;
import com.william.boom.R;

public class Menu {

    private GameLoop gameLoop;
    private Game game;
    private Context context;
    private boolean isPressed;


    private int menuPositionX;
    private int menuPositionY;
    private double menuPosition;
    private boolean recentState;

    public Menu(Context context, GameLoop gameLoop, int x, int y) {
        this.context = context;
        this.gameLoop = gameLoop;

        menuPositionX = x;
        menuPositionY = y;

    }

    public void update() {
        if (isPressed) {
            showDialog();
        }
    }

    public void draw(Canvas canvas) {
        drawMenu(canvas);
    }

    public void drawMenu(Canvas canvas) {
        Paint paint = new Paint();

        Bitmap bitmap;
        bitmap = BitmapFactory
                .decodeResource(context.getResources(), R.drawable.boxitem);

        bitmap = Common.scaleBitmap(bitmap, 100, 100);


        canvas.drawBitmap(
                bitmap,
                menuPositionX,
                menuPositionY,
                paint);
    }

    public boolean getIsPressed() {
        return isPressed;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean isPressed(double touchPositionX, double touchPositionY) {
        menuPosition = Utils.getDistanceBP(
                menuPositionX,
                menuPositionY,
                touchPositionX,
                touchPositionY);
        return menuPosition < menuPosition - 1;
    }

    public void showDialog() {
        final Dialog menuDialog = new Dialog(context, R.style.df_dialog);
        menuDialog.setContentView(R.layout.menu_dialog);
        recentState = true;

        menuDialog.findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuDialog.hide();
                recentState = false;
            }
        });

        menuDialog.findViewById(R.id.btnExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, MainActivity.class));
            }
        });
        menuDialog.show();
    }

    public boolean getState() {
        return recentState;
    }

    public void gameOverDialog() {
        final Dialog gameOverDialog = new Dialog(context, R.style.df_dialog);
        gameOverDialog.setContentView(R.layout.game_over_dialog);


        gameOverDialog.findViewById(R.id.btnExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, MainActivity.class));
            }
        });
        gameOverDialog.show();


    }
}
