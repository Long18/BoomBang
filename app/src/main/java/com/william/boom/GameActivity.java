package com.william.boom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {


    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Set fullscreen (hide status bar)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Set content view to game, so that object in the Game class can be rendered to the screen
        game = new Game(this);
        setContentView(game);
    }


    @Override
    protected void onPause() {
        game.pause();
        super.onPause();
    }


}