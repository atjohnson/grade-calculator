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

public class Exams extends AppCompatActivity implements View.OnClickListener {
    //Creating value to be carried to final screen
    public static final String TAG_EXAM_GRADE = "exam grade";
    public static final String TAG_EXAM_TOTAL = "exam total";
    public static final String TAG_LAB_GRADE = "lab grade";
    public static final String TAG_LAB_TOTAL = "lab total";
    public static final String TAG_QUIZ_GRADE = "quiz grade";
    public static final String TAG_QUIZ_TOTAL = "quiz total";
    public static final String TAG_HW_GRADE = "hw grade";
    public static final String TAG_HW_TOTAL = "hw total";
    double homeworkGrade, quizGrade, labGrade;
    double homeworkTotal, quizTotal, labTotal;

    //button to push to next screen
    private Button examButton;

    //Creating EditText Objects for Exam grade inputs, percentage of class, and dropped assignments
    private EditText ex1, expercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets the layout
        setContentView(R.layout.activity_exams);
        getSupportActionBar().hide();

        homeworkGrade = getIntent().getExtras().getDouble(Labs.TAG_HW_GRADE);
        homeworkTotal = getIntent().getExtras().getDouble(Labs.TAG_HW_TOTAL);
        quizGrade = getIntent().getExtras().getDouble(Labs.TAG_QUIZ_GRADE);
        quizTotal = getIntent().getExtras().getDouble(Labs.TAG_QUIZ_TOTAL);
        labGrade = getIntent().getExtras().getDouble(Labs.TAG_LAB_GRADE);
        labTotal = getIntent().getExtras().getDouble(Labs.TAG_LAB_TOTAL);

        //button linked to button in .xml
        examButton = (Button) findViewById(R.id.nextButton5);

        examButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        //Checking for blank text boxes
        expercent = (EditText) findViewById(R.id.percentTotalExams);
        String ep = expercent.getText().toString();
        if(ep.equals("")) //Toast to make sure a value is inputted
        {
            Toast.makeText(Exams.this,"Please fill out all required boxes before proceeding!", Toast.LENGTH_LONG).show();
            return;
        }

        //Vector created to hold inputs
        Vector<String> ex = new Vector<>();
        //Vector created to hold doubles of values
        Vector<Double> exam = new Vector<>();
        int i = 0;

        //Linking EditText values to objects
        ex1 = (EditText) findViewById(R.id.finalExamGrade);

        //Adds names of strings to vector of strings
        ex.add(ex1.getText().toString());

        //remove any unfilled text boxes
        if(ex.get(0).equals(""))
        {
            ex.remove(0);
        }

        //for loop to convert string to double and add to vector of doubles
        for(i = 0; i < ex.size(); i++)
        {
            exam.add(Double.parseDouble(ex.get(i)));
        }

        //Get exam percentage as double
        double ExamPercent = Double.parseDouble(ep);

        if(v.getId() == R.id.nextButton5)
        {
            GradeCalculation(exam,ExamPercent, 0);
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

        Intent projScreen = new Intent(Exams.this,Projects.class);
        //Push exam section grade and percent of total grade to result screen
        projScreen.putExtra(TAG_EXAM_GRADE, sectionGrade);
        projScreen.putExtra(TAG_EXAM_TOTAL, overallGrade);
        projScreen.putExtra(TAG_LAB_GRADE, labGrade);
        projScreen.putExtra(TAG_LAB_TOTAL, labTotal);
        projScreen.putExtra(TAG_QUIZ_GRADE, quizGrade);
        projScreen.putExtra(TAG_QUIZ_TOTAL, quizTotal);
        projScreen.putExtra(TAG_HW_GRADE, homeworkGrade);
        projScreen.putExtra(TAG_HW_TOTAL, homeworkTotal);
        startActivity(projScreen);
    }
}
