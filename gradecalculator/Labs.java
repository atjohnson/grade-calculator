package com.example.gradecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Vector;

public class Labs extends Activity implements View.OnClickListener {
    //Creating value to be carried to final screen
    public static final String TAG_LAB_GRADE = "lab grade";
    public static final String TAG_LAB_TOTAL = "lab total";

    //button to push to next screen
    private Button labButton;

    //Creating EditText Objects for lab grade inputs, percentage of class, and dropped assignments
    private EditText lab1, lab2, lab3, lab4, lab5, lab6, lab7, lab8, lab9, lab10, labpercent, labdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets the layout
        setContentView(R.layout.?????);

        //button linked to button in .xml
        labButton = (Button) findViewById(R.id.?????);

        labButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        //Vector created to hold inputs
        Vector<String> lab = new Vector<String>();
        //Vector created to hold doubles of values
        Vector<Double> labs = new Vector<Double>();
        int i = 0;

        //Linking EditText values to objects
        lab1 = (EditText) findViewById(R.id.?????);
        lab2 = (EditText) findViewById(R.id.?????);
        lab3 = (EditText) findViewById(R.id.?????);
        lab4 = (EditText) findViewById(R.id.?????);
        lab5 = (EditText) findViewById(R.id.?????);
        lab6 = (EditText) findViewById(R.id.?????);
        lab7 = (EditText) findViewById(R.id.?????);
        lab8 = (EditText) findViewById(R.id.?????);
        lab9 = (EditText) findViewById(R.id.?????);
        lab10 = (EditText) findViewById(R.id.?????);
        labpercent = (EditText) findViewById(R.id.?????);
        labdrop = (EditText) findViewById(R.id.?????);

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
        String lp = labpercent.getText().toString();
        if(lp.equals("")) //Toast to make sure a value is inputted
        {
            Toast.makeText(Labs.this,"Please enter a value before proceeding!", Toast.LENGTH_LONG).show();
        }
        double LabsPercent = Double.parseDouble(lp);

        //Gets number of labs dropped
        String droplab = labdrop.getText().toString();
        if(droplab.equals(""))
        {
            Toast.makeText(Labs.this,"Please enter a value before proceeding!", Toast.LENGTH_LONG).show();
        }
        int labsDropped = Integer.parseInt(droplab);

        if(v.getId() == R.id.?????)
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

        Intent labResults = new Intent(Labs.this,Results.class);
        //Push exam section grade and percent of total grade to result screen
        labResults.putExtra(TAG_LAB_GRADE, sectionGrade);
        labResults.putExtra(TAG_LAB_TOTAL, overallGrade);

        Intent examScreen = new Intent(Labs.this,Exams.class);
        startActivity(examScreen);
    }
}

