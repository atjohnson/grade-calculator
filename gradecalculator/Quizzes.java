package com.example.gradecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Vector;

public class Quizzes extends Activity implements View.OnClickListener {
    //Creating values to be carried to final screen
    public static final String TAG_QUIZ_GRADE = "quiz grade";
    public static final String TAG_QUIZ_TOTAL = "quiz total";

    //button to push to next screen
    private Button quizButton;

    //Creating EditText Objects for quiz grade inputs, percentage of class, and dropped assignments
    private EditText quiz1, quiz2, quiz3, quiz4, quiz5,  quizpercent, quizdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets the layout
        setContentView(R.layout.activity_quizzes);

        //button linked to button in .xml
        quizButton = (Button) findViewById(R.id.nextButton3);

        quizButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        //Vector created to hold inputs
        Vector<String> qz = new Vector<String>();
        //Vector created to hold doubles of values
        Vector<Double> quizzes = new Vector<Double>();
        int i = 0;

        //Linking EditText values to objects
        quiz1 = (EditText) findViewById(R.id.quiz1Grade);
        quiz2 = (EditText) findViewById(R.id.quiz2Grade);
        quiz3 = (EditText) findViewById(R.id.quiz3Grade);
        quiz4 = (EditText) findViewById(R.id.quiz4Grade);
        quiz5 = (EditText) findViewById(R.id.quiz5Grade);
        quizpercent = (EditText) findViewById(R.id.percentTotalQuizzes);
        quizdrop = (EditText) findViewById(R.id.numDroppedQuizzes);

        //Adds names of strings to vector of strings
        qz.add(quiz1.getText().toString());
        qz.add(quiz2.getText().toString());
        qz.add(quiz3.getText().toString());
        qz.add(quiz4.getText().toString());
        qz.add(quiz5.getText().toString());

        //for loop to remove any unfilled text boxes
        for(i = 4; i >= 0; i--)
        {
            if(qz.get(i).equals(""))
            {
                qz.remove(i);
            }
        }

        //for loop to convert string to double and add to vector of doubles
        for(i = 0; i < qz.size(); i++)
        {
            quizzes.add(Double.parseDouble(qz.get(i)));
        }

        //Get hw percentage as double
        String qp = quizpercent.getText().toString();
        if(qp.equals("")) //Toast to make sure a value is inputted
        {
            Toast.makeText(Quizzes.this,"Please fill out all required boxes before proceeding!", Toast.LENGTH_LONG).show();
        }
        double QZPercent = Double.parseDouble(qp);

        //Gets number of hws dropped
        String dropqz = quizdrop.getText().toString();
        if(dropqz.equals(""))
        {
            Toast.makeText(Quizzes.this,"Please fill out all required boxes before proceeding!", Toast.LENGTH_LONG).show();
        }
        int qzDropped = Integer.parseInt(dropqz);

        if(v.getId() == R.id.nextButton3)
        {
            GradeCalculation(quizzes,QZPercent, qzDropped);
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

        Intent qzResults = new Intent(Quizzes.this,Results.class);
        //Push exam section grade and percent of total grade to result screen
        qzResults.putExtra(TAG_QUIZ_GRADE, sectionGrade);
        qzResults.putExtra(TAG_QUIZ_TOTAL, overallGrade);

        Intent labsScreen = new Intent(Quizzes.this,Labs.class);
        startActivity(labsScreen);
    }
}

