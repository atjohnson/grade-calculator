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

public class Quizzes extends AppCompatActivity implements View.OnClickListener {
    //Creating values to be carried to final screen
    public static final String TAG_QUIZ_GRADE = "quiz grade";
    public static final String TAG_QUIZ_TOTAL = "quiz total";
    public static final String TAG_HW_GRADE = "hw grade";
    public static final String TAG_HW_TOTAL = "hw total";
    double homeworkGrade;
    double homeworkTotal;

    //button to push to next screen
    private Button quizButton;

    //Creating EditText Objects for quiz grade inputs, percentage of class, and dropped assignments
    private EditText quiz1, quiz2, quiz3, quiz4, quiz5,  quizpercent, quizdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets the layout
        setContentView(R.layout.activity_quizzes);
        getSupportActionBar().hide();

        //homework values
        homeworkGrade = getIntent().getExtras().getDouble(Homework.TAG_HW_GRADE);
        homeworkTotal = getIntent().getExtras().getDouble(Homework.TAG_HW_TOTAL);

        //button linked to button in .xml
        quizButton = (Button) findViewById(R.id.nextButton3);

        quizButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        //Checking for blank text boxes
        quizpercent = (EditText) findViewById(R.id.percentTotalQuizzes);
        quizdrop = (EditText) findViewById(R.id.numDroppedQuizzes);
        String dropqz = quizdrop.getText().toString();
        String qp = quizpercent.getText().toString();
        if(dropqz.equals("") || qp.equals(""))
        {
            Toast.makeText(Quizzes.this,"Please fill out all required boxes before proceeding!", Toast.LENGTH_LONG).show();
            return;
        }

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
        double QZPercent = Double.parseDouble(qp);

        //Gets number of hws dropped
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
        Intent labsScreen = new Intent(Quizzes.this,Labs.class);
        labsScreen.putExtra(TAG_QUIZ_GRADE, sectionGrade);
        labsScreen.putExtra(TAG_QUIZ_TOTAL, overallGrade);
        labsScreen.putExtra(TAG_HW_GRADE, homeworkGrade);
        labsScreen.putExtra(TAG_HW_TOTAL, homeworkTotal);
        startActivity(labsScreen);
    }
}
