package com.william.boom.GamePanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;

import androidx.core.content.ContextCompat;

import com.william.boom.GameObject.Player;
import com.william.boom.Graphics.GameDisplay;
import com.william.boom.R;

/**
 * HealthBar display the players health to the screen
 */
public class HealthBar {
    private Player player;
    private Paint borderPaint, healthPaint, nameRoundPaint;
    private TextPaint namePaint;
    private int width, height, margin;

    private String nameCharacter = "William";

    public HealthBar(Context context, Player player) {
        this.player = player;
        this.width = 200;
        this.height = 30;
        this.margin = 10;

        this.borderPaint = new Paint();
        int borderColor = ContextCompat.getColor(context, R.color.healthBarBorder);
        borderPaint.setColor(borderColor);

        this.healthPaint = new Paint();
        int healthColor = ContextCompat.getColor(context, R.color.healthBarHealth);
        healthPaint.setColor(healthColor);

        this.nameRoundPaint = new Paint();
        int nameRoundColor = ContextCompat.getColor(context, R.color.nameRoundColor);
        nameRoundPaint.setColor(nameRoundColor);

        this.namePaint = new TextPaint();
        int nameColor = ContextCompat.getColor(context, R.color.namePaint);
        namePaint.setTextSize(36);
        namePaint.setTextAlign(Paint.Align.LEFT);
        namePaint.setColor(nameColor);
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        float x = (float) player.getPositionX();
        float y = (float) player.getPositionY();
        float distanceToPlayer = 50;
        float healthPointPercentage = (float) player.getHealth() / player.MAX_HEALTH;

        // Draw border
        float borderLeft, borderTop, borderRight, borderBottom;
        borderLeft = x - width / 2;
        borderRight = x + width / 2;
        borderBottom = y - distanceToPlayer;
        borderTop = borderBottom - height;
        canvas.drawRect(
                (float) gameDisplay.gameToDisplayX(borderLeft),
                (float) gameDisplay.gameToDisplayY(borderTop),
                (float) gameDisplay.gameToDisplayX(borderRight),
                (float) gameDisplay.gameToDisplayY(borderBottom),
                borderPaint);

        // Draw health
        float healthLeft, healthTop, healthRight, healthBottom, healthWidth, healthHeight;
        healthWidth = width - 2 * margin;
        healthHeight = height - 2 * margin;
        healthLeft = borderLeft + margin;
        healthRight = healthLeft + healthWidth * healthPointPercentage;
        healthBottom = borderBottom - margin;
        healthTop = healthBottom - healthHeight;
        canvas.drawRect(
                (float) gameDisplay.gameToDisplayX(healthLeft),
                (float) gameDisplay.gameToDisplayY(healthTop),
                (float) gameDisplay.gameToDisplayX(healthRight),
                (float) gameDisplay.gameToDisplayY(healthBottom),
                healthPaint);


        // Draw border name
        float borderLeftName, borderTopName, borderRightName, borderBottomName;
        borderLeftName = x - width / 2;
        borderRightName = x + width / 2;
        borderBottomName = y + distanceToPlayer * 3;
        borderTopName = borderBottomName - height;

        RectF rect = new RectF(
                (float) gameDisplay.gameToDisplayX(borderLeftName), // left
                (float) gameDisplay.gameToDisplayY(borderTopName), // top
                /*canvas.getWidth() -*/ (float) gameDisplay.gameToDisplayX(borderRightName), // right
                /*canvas.getHeight() -*/ (float) gameDisplay.gameToDisplayY(borderBottomName) // bottom
        );
        CharSequence nameChar = TextUtils.ellipsize(nameCharacter, namePaint, canvas.getWidth(), TextUtils.TruncateAt.END);
        canvas.drawRoundRect(rect, 20, 20, nameRoundPaint);



        //Draw Name
        float textHeight = namePaint.descent() - namePaint.ascent();
        float textOffset = textHeight - namePaint.descent() + 2;

        canvas.drawText(nameChar,
                0,
                nameChar.length(),
                /*canvas.getWidth() +*/ (int) gameDisplay.gameToDisplayX(borderLeftName) + textOffset,
                canvas.getHeight() - (int) gameDisplay.gameToDisplayY(borderBottomName) / 1.75f,
                namePaint);
    }

}