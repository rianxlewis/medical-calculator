package com.example.medicalcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final double CONVERSION_RATE = 2.2;

    private EditText inputWeight;
    private RadioButton lbToKiloButton;
    private RadioButton kiloToLbButton;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }

        inputWeight = findViewById(R.id.etWeight);
        lbToKiloButton = findViewById(R.id.rbLbToKilo);
        kiloToLbButton = findViewById(R.id.rbKiloToLb);
        resultView = findViewById(R.id.tvResult);
        Button convertButton = findViewById(R.id.btConvert);

        convertButton.setOnClickListener(v -> convertWeight());
    }

    private void convertWeight() {
        String inputText = inputWeight.getText().toString().trim();
        if (inputText.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_empty_input), Toast.LENGTH_SHORT).show();
            return;
        }

        double weight;
        try {
            weight = Double.parseDouble(inputText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, getString(R.string.error_invalid_input), Toast.LENGTH_SHORT).show();
            return;
        }

        DecimalFormat formatter = new DecimalFormat("#.#");

        if (lbToKiloButton.isChecked()) {
            if (weight <= 500) {
                double result = weight / CONVERSION_RATE;
                resultView.setText(getString(R.string.result_kilograms, formatter.format(result)));
            } else {
                Toast.makeText(this, getString(R.string.error_pounds_too_high), Toast.LENGTH_LONG).show();
            }
        } else if (kiloToLbButton.isChecked()) {
            if (weight <= 225) {
                double result = weight * CONVERSION_RATE;
                resultView.setText(getString(R.string.result_pounds, formatter.format(result)));
            } else {
                Toast.makeText(this, getString(R.string.error_kilos_too_high), Toast.LENGTH_LONG).show();
            }
        }
    }
}
