package com.example.gradecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class Results extends Activity implements View.OnClickListener {
    //button to go to next screen
    Button finishButton;

    //Text Views to display results
    private TextView homeworkText, quizzesText, labsText, examsText, projectsText, totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets the layout
        setContentView(R.layout.?????);

        //button linked to button in .xml
        finishButton = (Button) findViewById(R.id.?????);

        //Setting the Text Views
        homeworkText = (TextView) findViewById(R.id.?????);
        quizzesText = (TextView) findViewById(R.id.?????);
        labsText = (TextView) findViewById(R.id.?????);
        examsText = (TextView) findViewById(R.id.?????);
        projectsText = (TextView) findViewById(R.id.?????);
        totalText = (TextView) findViewById(R.id.?????);

        //Runs the DisplayGrades method
        DisplayGrades();

        finishButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.?????)
        {
            Intent hereWeGoAgain = new Intent(Results.this,MainActivity.class);
            startActivity(hereWeGoAgain);
        }
    }

    private void DisplayGrades()
    {
        //Gets values pushed from hw section
        double homeworkGrade = getIntent().getExtras().getDouble(Homework.TAG_HW_GRADE);
        double homeworkTotal = getIntent().getExtras().getDouble(Homework.TAG_HW_TOTAL);
        //Gets values pushed from quiz section
        double quizGrade = getIntent().getExtras().getDouble(Quizzes.TAG_QUIZ_GRADE);
        double quizTotal = getIntent().getExtras().getDouble(Quizzes.TAG_QUIZ_TOTAL);
        //Gets values pushed from labs section
        double labGrade = getIntent().getExtras().getDouble(Labs.TAG_LAB_GRADE);
        double labTotal = getIntent().getExtras().getDouble(Labs.TAG_LAB_TOTAL);
        //Gets values pushed from exam section
        double examGrade = getIntent().getExtras().getDouble(Exams.TAG_EXAM_GRADE);
        double examTotal = getIntent().getExtras().getDouble(Exams.TAG_EXAM_TOTAL);
        //Gets values pushed from project section
        double projectGrade = getIntent().getExtras().getDouble(Projects.TAG_PROJECT_GRADE);
        double projectTotal = getIntent().getExtras().getDouble(Projects.TAG_PROJECT_TOTAL);

        //Calculates value of total grade
        double totalGrade = homeworkTotal + quizTotal + labTotal + examTotal + projectTotal;

        //Displays total grade
        String gradeText = totalText.getText().toString();
        gradeText = gradeText + totalGrade +"%";
        totalText.setText(gradeText);

        //Displays hw grade
        String hwText = homeworkText.getText().toString();
        hwText = hwText + homeworkGrade +"%";
        homeworkText.setText(hwText);

        //Displays quiz grade
        String qzText = quizzesText.getText().toString();
        qzText = qzText + totalGrade +"%";
        quizzesText.setText(qzText);

        //Displays lab grade
        String labText = labsText.getText().toString();
        labText = labText + totalGrade +"%";
        labsText.setText(labText);

        //Displays exam grade
        String exText = examsText.getText().toString();
        exText = exText + totalGrade +"%";
        examsText.setText(exText);

        //Displays project grade
        String proText = projectsText.getText().toString();
        proText = proText + totalGrade +"%";
        projectsText.setText(proText);

    }


}