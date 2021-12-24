package com.william.boom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity: ","onCreate");
        super.onCreate(savedInstanceState);

        //Set fullscreen (hide status bar)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Set content view to game, so that object in the Game class can be rendered to the screen
        game = new Game(this);
        setContentView(game);

    }

    @Override
    protected void onStart() {
        Log.d("MainActivity: ","onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity: ","onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("MainActivity: ","onPause");
        game.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("MainActivity: ","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("MainActivity: ","onDestroy");
        super.onDestroy();
    }
}