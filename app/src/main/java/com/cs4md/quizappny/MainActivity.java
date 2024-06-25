package com.cs4md.quizappny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // 1. declaring my UI Elements & other variables
    Button trueBTN, falseBTN, nextBTN;
    TextView questionTV;
    Toast myToast;
    int score;
    String message;
    Question q1, q2, q3, q4, q5, currentQuestion;
    Question[] questions;
    int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. initializing our variables & inflating our UI elements
        trueBTN = (Button) findViewById(R.id.trueBTN);
        falseBTN = (Button) findViewById(R.id.falseBTN);
        nextBTN = (Button) findViewById(R.id.nextBTN);
        questionTV = (TextView) findViewById(R.id.questionTV);
        score = 0;
        message = "";
        q1 = new Question(getString(R.string.q1_text), false);
        q2 = new Question(getString(R.string.pi_is_irrational), true);
        q3 = new Question("Pi is transcendental.", true);
        q4 = new Question("Pi is equal to the circumference divided by the radius.",
                false);
        q5 = new Question("The area of a circle is equal to pi times the radius squared.",
                true);
        questions = new Question[] {q1, q2, q3, q4, q5};
        currentIndex = 0;
        currentQuestion = questions[currentIndex];


        // 3. do whatever you want your app to do with its UI elements!
        trueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentQuestion.isCorrectAnswer() == true) {
                    message = "Correct.";
                    score++;
                }
                else {
                    message = "Incorrect.";
                }

                myToast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        falseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentQuestion.isCorrectAnswer() == false) {
                    message = "Correct.";
                    score++;
                }
                else {
                    message = "Incorrect.";
                }
                myToast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
                myToast.show();

            }
        });

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex++;
                if (currentIndex < questions.length) {
                    // advance and show next question

                    currentQuestion = questions[currentIndex];
                    questionTV.setText(currentQuestion.getqText());
                }
                else {
                    // move to score activity

                    // declare intent
                    Intent myIntent = new Intent(MainActivity.this, ScoreActivity.class);
                    // put extra info in if needed
                    myIntent.putExtra("score", score);
                    // start the new activity
                    startActivity(myIntent);
                }

            }
        });


    }
}