package com.example.gradecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Vector;

public class Exams extends Activity implements View.OnClickListener {
    //Creating value to be carried to final screen
    public static final String TAG_EXAM_GRADE = "exam grade";
    public static final String TAG_EXAM_TOTAL = "exam total";

    //button to push to next screen
    private Button examButton;

    //Creating EditText Objects for Exam grade inputs, percentage of class, and dropped assignments
    private EditText ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10, expercent, exdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets the layout
        setContentView(R.layout.activity_exams);

        //button linked to button in .xml
        examButton = (Button) findViewById(R.id.nextButton5);

        examButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        //Vector created to hold inputs
        Vector<String> ex = new Vector<>();
        //Vector created to hold doubles of values
        Vector<Double> exam = new Vector<>();
        int i = 0;

        //Linking EditText values to objects
        ex1 = (EditText) findViewById(R.id.?????);
        ex2 = (EditText) findViewById(R.id.?????);
        ex3 = (EditText) findViewById(R.id.?????);
        ex4 = (EditText) findViewById(R.id.?????);
        ex5 = (EditText) findViewById(R.id.?????);
        ex6 = (EditText) findViewById(R.id.?????);
        ex7 = (EditText) findViewById(R.id.?????);
        ex8 = (EditText) findViewById(R.id.?????);
        ex9 = (EditText) findViewById(R.id.?????);
        ex10 = (EditText) findViewById(R.id.?????);
        expercent = (EditText) findViewById(R.id.percentTotalExams);
        exdrop = (EditText) findViewById(R.id.?????);

        //Adds names of strings to vector of strings
        ex.add(ex1.getText().toString());
        ex.add(ex2.getText().toString());
        ex.add(ex3.getText().toString());
        ex.add(ex4.getText().toString());
        ex.add(ex5.getText().toString());
        ex.add(ex6.getText().toString());
        ex.add(ex7.getText().toString());
        ex.add(ex8.getText().toString());
        ex.add(ex9.getText().toString());
        ex.add(ex10.getText().toString());

        //for loop to remove any unfilled text boxes
        for(i = 9; i >= 0; i--)
        {
            if(ex.get(i).equals(""))
            {
                ex.remove(i);
            }
        }

        //for loop to convert string to double and add to vector of doubles
        for(i = 0; i < ex.size(); i++)
        {
            exam.add(Double.parseDouble(ex.get(i)));
        }

        //Get exam percentage as double
        String ep = expercent.getText().toString();
        if(ep.equals("")) //Toast to make sure a value is inputted
        {
            Toast.makeText(Exams.this,"Please fill out all required boxes before proceeding!", Toast.LENGTH_LONG).show();
        }
        double ExamPercent = Double.parseDouble(ep);

        //Gets number of exams dropped
        String dropex = exdrop.getText().toString();
        if(dropex.equals(""))
        {
            Toast.makeText(Exams.this,"Please fill out all required boxes before proceeding!", Toast.LENGTH_LONG).show();
        }
        int examsDropped = Integer.parseInt(dropex);

        if(v.getId() == R.id.nextButton5)
        {
            GradeCalculation(exam,ExamPercent, examsDropped);
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

        Intent examResults = new Intent(Exams.this,Results.class);
        //Push exam section grade and percent of total grade to result screen
        examResults.putExtra(TAG_EXAM_GRADE, sectionGrade);
        examResults.putExtra(TAG_EXAM_TOTAL, overallGrade);

        Intent projectScreen = new Intent(Exams.this,Projects.class);
        startActivity(projectScreen);
    }
}

