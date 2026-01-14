package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText etGuess;
    Button btnGuess, btnReset;
    TextView tvResult, tvAttempts;

    int randomNumber;
    int attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etGuess = findViewById(R.id.etGuess);
        btnGuess = findViewById(R.id.btnGuess);
        btnReset = findViewById(R.id.btnReset);
        tvResult = findViewById(R.id.tvResult);
        tvAttempts = findViewById(R.id.tvAttempts);

        startNewGame();

        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = etGuess.getText().toString();

                if (input.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                int guess = Integer.parseInt(input);
                attempts++;
                tvAttempts.setText("Attempts: " + attempts);

                if (guess > randomNumber) {
                    tvResult.setText("⬆️ Too High! Try again.");
                    tvResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                } else if (guess < randomNumber) {
                    tvResult.setText("⬇️ Too Low! Try again.");
                    tvResult.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
                } else {
                    tvResult.setText("🎉 Correct! You won!");
                    tvResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                    btnGuess.setEnabled(false);
                }

                etGuess.setText("");
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });
    }

    private void startNewGame() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attempts = 0;
        tvAttempts.setText("Attempts: 0");
        tvResult.setText("");
        etGuess.setText("");
        btnGuess.setEnabled(true);
    }
}
