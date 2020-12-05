package com.example.gradecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Vector;

public class Homework extends Activity implements View.OnClickListener {
    //Creating value to be carried to final screen
    public static final String TAG_HW_GRADE = "hw grade";
    public static final String TAG_HW_TOTAL = "hw total";

    //button to push to next screen
    private Button hwButton;

    //Creating EditText Objects for hw grade inputs, percentage of class, and dropped assignments
    private EditText hw1, hw2, hw3, hw4, hw5, hw6, hw7, hw8, hw9, hw10, hwpercent, hwdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets the layout
        setContentView(R.layout.activity_homework);

        //button linked to button in .xml
        hwButton = (Button) findViewById(R.id.button);

        hwButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        //Vector created to hold inputs
        Vector<String> hw = new Vector<String>();
        //Vector created to hold doubles of values
        Vector<Double> homework = new Vector<Double>();
        int i = 0;

        //Linking EditText values to objects
        hw1 = (EditText) findViewById(R.id.hw1Grade);
        hw2 = (EditText) findViewById(R.id.hw2Grade);
        hw3 = (EditText) findViewById(R.id.hw3Grade);
        hw4 = (EditText) findViewById(R.id.hw4Grade);
        hw5 = (EditText) findViewById(R.id.hw5Grade);
        hw6 = (EditText) findViewById(R.id.hw6Grade);
        hw7 = (EditText) findViewById(R.id.hw7Grade);
        hw8 = (EditText) findViewById(R.id.hw8Grade);
        hw9 = (EditText) findViewById(R.id.hw9Grade);
        hw10 = (EditText) findViewById(R.id.hw10Grade);
        hwpercent = (EditText) findViewById(R.id.percentTotalHW);
        hwdrop = (EditText) findViewById(R.id.numDroppedHW);

        //Adds names of strings to vector of strings
        hw.add(hw1.getText().toString());
        hw.add(hw2.getText().toString());
        hw.add(hw3.getText().toString());
        hw.add(hw4.getText().toString());
        hw.add(hw5.getText().toString());
        hw.add(hw6.getText().toString());
        hw.add(hw7.getText().toString());
        hw.add(hw8.getText().toString());
        hw.add(hw9.getText().toString());
        hw.add(hw10.getText().toString());

        //for loop to remove any unfilled text boxes
        for(i = 9; i >= 0; i--)
        {
            if(hw.get(i).equals(""))
            {
                hw.remove(i);
            }
        }

        //for loop to convert string to double and add to vector of doubles
        for(i = 0; i < hw.size(); i++)
        {
            homework.add(Double.parseDouble(hw.get(i)));
        }

        //Get hw percentage as double
        String hp = hwpercent.getText().toString();
        if(hp.equals("")) //Toast to make sure a value is inputted
        {
            Toast.makeText(Homework.this,"Please fill out all required boxes before proceeding!", Toast.LENGTH_LONG).show();
        }
        double HomeworkPercent = Double.parseDouble(hp);

        //Gets number of hws dropped
        String drophw = hwdrop.getText().toString();
        if(drophw.equals(""))
        {
            Toast.makeText(Homework.this,"Please fill out all required boxes before proceeding!", Toast.LENGTH_LONG).show();
        }
        int hwDropped = Integer.parseInt(drophw);

        if(v.getId() == R.id.button)
        {
            GradeCalculation(homework,HomeworkPercent, hwDropped);
        }
    }

    //Calculates the Grade for this section
    private void GradeCalculation(Vector<Double> v, double percent, int numDropped)
    {
        int i = 0; //counter
        double sum = 0, sectionGrade, overallGrade;
        double min = v.get(i); //lowest  grade
        int lowestIndex = 0;  //index of lowest grade
        double newPercent = percent/100.0; //turns percentage into decimal
        while(numDropped!=0)   //for loop to drop inputted # of lowest assignments
        {
            for (i = 0; i < v.size(); i++)
            {
                if (v.get(i) < min)
                {
                    min = v.get(i);
                    lowestIndex = i;
                }
            }
            v.remove(lowestIndex);
            numDropped--;
        }
        for(i = 0; i < v.size(); i++) //gets the total grade in section
        {
            sum += v.get(i);
        }
        double numAssignments = v.size();
        sectionGrade = sum/numAssignments;
        overallGrade = sectionGrade*newPercent;

        //Intent to send to next screen

        Intent hwResults = new Intent(Homework.this,Results.class);
        //Push hw section grade and percent of total grade to result screen
        hwResults.putExtra(TAG_HW_GRADE, sectionGrade);
        hwResults.putExtra(TAG_HW_TOTAL, overallGrade);

        Intent quizScreen = new Intent(Homework.this,Quizzes.class);
        startActivity(quizScreen);
    }
}
