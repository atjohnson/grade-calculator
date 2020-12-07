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

public class Projects extends AppCompatActivity implements View.OnClickListener {
    //Creating value to be carried to final screen
    public static final String TAG_PROJECT_GRADE = "project grade";
    public static final String TAG_PROJECT_TOTAL = "project total";
    public static final String TAG_EXAM_GRADE = "exam grade";
    public static final String TAG_EXAM_TOTAL = "exam total";
    public static final String TAG_LAB_GRADE = "lab grade";
    public static final String TAG_LAB_TOTAL = "lab total";
    public static final String TAG_QUIZ_GRADE = "quiz grade";
    public static final String TAG_QUIZ_TOTAL = "quiz total";
    public static final String TAG_HW_GRADE = "hw grade";
    public static final String TAG_HW_TOTAL = "hw total";
    double homeworkGrade, quizGrade, labGrade, examGrade;
    double homeworkTotal, quizTotal, labTotal, examTotal;

    //button to push to next screen
    private Button projButton;

    //Creating EditText Objects for Project grade inputs, percentage of class, and dropped assignments
    private EditText p1, projpercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets the layout
        setContentView(R.layout.activity_projects);
        getSupportActionBar().hide();

        homeworkGrade = getIntent().getExtras().getDouble(Exams.TAG_HW_GRADE);
        homeworkTotal = getIntent().getExtras().getDouble(Exams.TAG_HW_TOTAL);
        quizGrade = getIntent().getExtras().getDouble(Exams.TAG_QUIZ_GRADE);
        quizTotal = getIntent().getExtras().getDouble(Exams.TAG_QUIZ_TOTAL);
        labGrade = getIntent().getExtras().getDouble(Exams.TAG_LAB_GRADE);
        labTotal = getIntent().getExtras().getDouble(Exams.TAG_LAB_TOTAL);
        examGrade = getIntent().getExtras().getDouble(Exams.TAG_EXAM_GRADE);
        examTotal = getIntent().getExtras().getDouble(Exams.TAG_EXAM_TOTAL);


        //button linked to button in .xml
        projButton = (Button) findViewById(R.id.nextButton6);

        projButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        //Checking for blank text boxes
        projpercent = (EditText) findViewById(R.id.percentTotalProjects);
        String pp = projpercent.getText().toString();
        if(pp.equals("")) //Toast to make sure a value is inputted
        {
            Toast.makeText(Projects.this,"Please fill out all required boxes before proceeding!", Toast.LENGTH_LONG).show();
            return;
        }

        //Vector created to hold inputs
        Vector<String> proj = new Vector<String>();
        //Vector created to hold doubles of values
        Vector<Double> projects = new Vector<Double>();
        int i = 0;

        //Linking EditText values to objects
        p1 = (EditText) findViewById(R.id.finalProjectGrade);

        //Adds names of strings to vector of strings
        proj.add(p1.getText().toString());

        //remove any unfilled text boxes
        if(proj.get(0).equals(""))
        {
            proj.remove(0);
        }

        //for loop to convert string to double and add to vector of doubles
        for(i = 0; i < proj.size(); i++)
        {
            projects.add(Double.parseDouble(proj.get(i)));
        }

        //Get project percentage as double
        double ProjectPercent = Double.parseDouble(pp);

        if(v.getId() == R.id.nextButton6)
        {
            GradeCalculation(projects, ProjectPercent, 0);
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
        Intent resultsScreen = new Intent(Projects.this,Results.class);
        resultsScreen.putExtra(TAG_PROJECT_GRADE, sectionGrade);
        resultsScreen.putExtra(TAG_PROJECT_TOTAL, overallGrade);
        resultsScreen.putExtra(TAG_EXAM_GRADE, examGrade);
        resultsScreen.putExtra(TAG_EXAM_TOTAL, examTotal);
        resultsScreen.putExtra(TAG_LAB_GRADE, labGrade);
        resultsScreen.putExtra(TAG_LAB_TOTAL, labTotal);
        resultsScreen.putExtra(TAG_QUIZ_GRADE, quizGrade);
        resultsScreen.putExtra(TAG_QUIZ_TOTAL, quizTotal);
        resultsScreen.putExtra(TAG_HW_GRADE, homeworkGrade);
        resultsScreen.putExtra(TAG_HW_TOTAL, homeworkTotal);
        startActivity(resultsScreen);
    }
}

