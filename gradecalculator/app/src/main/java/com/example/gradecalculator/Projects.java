package com.example.gradecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Vector;

public class Projects extends Activity implements View.OnClickListener {
    //Creating value to be carried to final screen
    public static final String TAG_PROJECT_GRADE = "exam grade";
    public static final String TAG_PROJECT_TOTAL = "exam total";

    //button to push to next screen
    private Button projButton;

    //Creating EditText Objects for Project grade inputs, percentage of class, and dropped assignments
    private EditText p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, projpercent, projdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets the layout
        setContentView(R.layout.activity_projects);

        //button linked to button in .xml
        projButton = (Button) findViewById(R.id.nextButton6);

        projButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        /*
        //Vector created to hold inputs
        Vector<String> proj = new Vector<String>();
        //Vector created to hold doubles of values
        Vector<Double> projects = new Vector<Double>();
        int i = 0;

        //Linking EditText values to objects
        p1 = (EditText) findViewById(R.id.?????);
        p2 = (EditText) findViewById(R.id.?????);
        p3 = (EditText) findViewById(R.id.?????);
        p4 = (EditText) findViewById(R.id.?????);
        p5 = (EditText) findViewById(R.id.?????);
        p6 = (EditText) findViewById(R.id.?????);
        p7 = (EditText) findViewById(R.id.?????);
        p8 = (EditText) findViewById(R.id.?????);
        p9 = (EditText) findViewById(R.id.?????);
        p10 = (EditText) findViewById(R.id.?????);
        projpercent = (EditText) findViewById(R.id.percentTotalProjects);
        projdrop = (EditText) findViewById(R.id.?????);

        //Adds names of strings to vector of strings
        proj.add(p1.getText().toString());
        proj.add(p2.getText().toString());
        proj.add(p3.getText().toString());
        proj.add(p4.getText().toString());
        proj.add(p5.getText().toString());
        proj.add(p6.getText().toString());
        proj.add(p7.getText().toString());
        proj.add(p8.getText().toString());
        proj.add(p9.getText().toString());
        proj.add(p10.getText().toString());

        //for loop to remove any unfilled text boxes
        for(i = 9; i >= 0; i--)
        {
            if(proj.get(i).equals(""))
            {
                proj.remove(i);
            }
        }

        //for loop to convert string to double and add to vector of doubles
        for(i = 0; i < proj.size(); i++)
        {
            projects.add(Double.parseDouble(ex.get(i)));
        }

        //Get project percentage as double
        String pp = projpercent.getText().toString();
        if(pp.equals("")) //Toast to make sure a value is inputted
        {
            Toast.makeText(Projects.this,"Please fill out all required boxes before proceeding!", Toast.LENGTH_LONG).show();
        }
        double ProjectPercent = Double.parseDouble(pp);

        //Gets number of projects dropped
        String dropp = projdrop.getText().toString();
        if(dropp.equals(""))
        {
            Toast.makeText(Projects.this,"Please fill out all required boxes before proceeding!", Toast.LENGTH_LONG).show();
        }
        int projectsDropped = Integer.parseInt(dropp);

        if(v.getId() == R.id.nextButton6)
        {
            GradeCalculation(projects, ProjectPercent, projectsDropped);
        }

         */
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

        Intent projectResults = new Intent(Projects.this,Results.class);
        //Push project section grade and percent of total grade to result screen
        projectResults.putExtra(TAG_PROJECT_GRADE, sectionGrade);
        projectResults.putExtra(TAG_PROJECT_TOTAL, overallGrade);

        Intent resultsScreen = new Intent(Projects.this,Results.class);
        startActivity(resultsScreen);
    }
}


