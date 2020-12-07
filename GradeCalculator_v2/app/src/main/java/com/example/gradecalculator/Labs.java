package com.example.gradecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Vector;

public class Labs extends AppCompatActivity implements View.OnClickListener {
    //Creating value to be carried to final screen
    public static final String TAG_LAB_GRADE = "lab grade";
    public static final String TAG_LAB_TOTAL = "lab total";
    public static final String TAG_QUIZ_GRADE = "quiz grade";
    public static final String TAG_QUIZ_TOTAL = "quiz total";
    public static final String TAG_HW_GRADE = "hw grade";
    public static final String TAG_HW_TOTAL = "hw total";
    double homeworkGrade, quizGrade;
    double homeworkTotal, quizTotal;

    //button to push to next screen
    private Button labButton;

    //Creating EditText Objects for lab grade inputs, percentage of class, and dropped assignments
    private EditText lab1, lab2, lab3, lab4, lab5, lab6, lab7, lab8, lab9, lab10, labpercent, labdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets the layout
        setContentView(R.layout.activity_labs);
        getSupportActionBar().hide();

        homeworkGrade = getIntent().getExtras().getDouble(Quizzes.TAG_HW_GRADE);
        homeworkTotal = getIntent().getExtras().getDouble(Quizzes.TAG_HW_TOTAL);
        quizGrade = getIntent().getExtras().getDouble(Quizzes.TAG_QUIZ_GRADE);
        quizTotal = getIntent().getExtras().getDouble(Quizzes.TAG_QUIZ_TOTAL);

        //button linked to button in .xml
        labButton = (Button) findViewById(R.id.nextButton4);

        labButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        //Checking for blank text boxes
        labpercent = (EditText) findViewById(R.id.percentTotalLabs);
        labdrop = (EditText) findViewById(R.id.numDroppedLabs);
        String droplab = labdrop.getText().toString();
        String lp = labpercent.getText().toString();
        if(droplab.equals("") || lp.equals(""))
        {
            Toast.makeText(Labs.this,"Please fill out all required boxes before proceeding!", Toast.LENGTH_LONG).show();
            return;
        }

        //Vector created to hold inputs
        Vector<String> lab = new Vector<String>();
        //Vector created to hold doubles of values
        Vector<Double> labs = new Vector<Double>();
        int i = 0;

        //Linking EditText values to objects
        lab1 = (EditText) findViewById(R.id.lab1Grade);
        lab2 = (EditText) findViewById(R.id.lab2Grade);
        lab3 = (EditText) findViewById(R.id.lab3Grade);
        lab4 = (EditText) findViewById(R.id.lab4Grade);
        lab5 = (EditText) findViewById(R.id.lab5Grade);
        lab6 = (EditText) findViewById(R.id.lab6Grade);
        lab7 = (EditText) findViewById(R.id.lab7Grade);
        lab8 = (EditText) findViewById(R.id.lab8Grade);
        lab9 = (EditText) findViewById(R.id.lab9Grade);
        lab10 = (EditText) findViewById(R.id.lab10Grade);

        //Adds names of strings to vector of strings
        lab.add(lab1.getText().toString());
        lab.add(lab2.getText().toString());
        lab.add(lab3.getText().toString());
        lab.add(lab4.getText().toString());
        lab.add(lab5.getText().toString());
        lab.add(lab6.getText().toString());
        lab.add(lab7.getText().toString());
        lab.add(lab8.getText().toString());
        lab.add(lab9.getText().toString());
        lab.add(lab10.getText().toString());

        //for loop to remove any unfilled text boxes
        for(i = 9; i >= 0; i--)
        {
            if(lab.get(i).equals(""))
            {
                lab.remove(i);
            }
        }

        //for loop to convert string to double and add to vector of doubles
        for(i = 0; i < lab.size(); i++)
        {
            labs.add(Double.parseDouble(lab.get(i)));
        }

        //Get lab percentage as double
        double LabsPercent = Double.parseDouble(lp);

        //Gets number of labs dropped
        int labsDropped = Integer.parseInt(droplab);

        if(v.getId() == R.id.nextButton4)
        {
            GradeCalculation(labs,LabsPercent, labsDropped);
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
        Intent examScreen = new Intent(Labs.this,Exams.class);
        examScreen.putExtra(TAG_LAB_GRADE, sectionGrade);
        examScreen.putExtra(TAG_LAB_TOTAL, overallGrade);
        examScreen.putExtra(TAG_QUIZ_GRADE, quizGrade);
        examScreen.putExtra(TAG_QUIZ_TOTAL, quizTotal);
        examScreen.putExtra(TAG_HW_GRADE, homeworkGrade);
        examScreen.putExtra(TAG_HW_TOTAL, homeworkTotal);
        startActivity(examScreen);
    }
}
