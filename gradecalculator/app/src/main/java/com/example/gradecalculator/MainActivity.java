package com.example.gradecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.View.OnClickListener;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button startButton;
    //button to go to next screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main); //sets the layout

        startButton = (Button) findViewById(R.id.startButton); //button linked to button in .xml

        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.startButton)
                {
                    Intent introScreen = new Intent(MainActivity.this,IntroScreen.class);
                    startActivity(introScreen);
                }
            }
        });
    }


/*
    @Override
    public void onClick(View v)
    {
            if(v.getId() == R.id.startButton)
            {
                Intent introScreen = new Intent(MainActivity.this,IntroScreen.class);
                startActivity(introScreen);
            }
    }

 */
}