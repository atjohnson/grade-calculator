package com.example.gradecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroScreen extends Activity implements View.OnClickListener {
    //button to go to next screen
    private Button IntroButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.?????); //sets the layout

        IntroButton = (Button) findViewById(R.id.?????); //button linked to button in .xml

        IntroButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.?????)
        {
            Intent hwScreen = new Intent(IntroScreen.this,Homework.class);
            startActivity(hwScreen);
        }
    }


}
