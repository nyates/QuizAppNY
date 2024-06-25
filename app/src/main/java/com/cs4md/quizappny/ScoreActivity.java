package com.cs4md.quizappny;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScoreActivity extends AppCompatActivity {

    TextView scoreTV;
    Button emailBTN;
    Intent incomingIntent;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreTV = (TextView) findViewById(R.id.scoreTV);
        emailBTN = (Button) findViewById(R.id.emailBTN);
        incomingIntent = getIntent();
        score = incomingIntent.getIntExtra("score",0);
        scoreTV.setText("Score: " + score);

        emailBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] addresses = new String[] {"yates.code@gmail.com"};
                String subject = "New score on Pi Quiz App";
                String body = "I just scored a " + score + " out of 5 on Nick's Pi Quiz App!";
                composeEmail(addresses, subject, body);
            }
        });

    }

    private void composeEmail(String[] addresses, String subject, String body) {
        Log.d("AAAAAAAAAAAAAAA", "inside method");
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // Only email apps handle this.
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            Log.d("AAAAAAAAAAAAAAA", "activity resolved");
            startActivity(intent);

        }
    }

}
