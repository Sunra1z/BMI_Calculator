package com.sunra1z.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private Button calculateButton;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText HeightEditText;
    private EditText weightEditText;
    private EditText ageEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews(){

        resultText = findViewById(R.id.text_view_showresult);

        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);

        HeightEditText = findViewById(R.id.edit_text_cm);
        weightEditText = findViewById(R.id.edit_text_weight);
        ageEditText = findViewById(R.id.edit_text_age);

        calculateButton = findViewById(R.id.calculate);


    }

    private void setupButtonClickListener(){
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double bmiResult = calculateBmi();

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if(age >= 18){
                    displayResult(bmiResult);
                } else{
                    displayGuidance(bmiResult);
                }
            }
        });

    }

    private double calculateBmi() {
       String heightText = HeightEditText.getText().toString();
       String weightText = weightEditText.getText().toString();

       // Converting the number 'Strings' into 'Integer'
       int height = Integer.parseInt(heightText);
       int weight = Integer.parseInt(weightText);


       double heightMeters = height * 0.01;

        return weight / (heightMeters * heightMeters);
    }
    private void displayResult(double bmi){
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if(bmi < 18.5){
            // Display underweight
            fullResultString = bmiTextResult + " - You are underweight";
        } else if (bmi > 25) {
            // Display overweight
            fullResultString = bmiTextResult + " - You are overweight";
        } else{
            // Display healthy :)
            fullResultString = bmiTextResult + " - You are healthy!";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if(maleButton.isChecked()){
            // Display boy guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range for boys";
        } else if (femaleButton.isChecked()){
            // Display girl guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range for girls";
        } else{
            // Display general guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range";
        }
        resultText.setText(fullResultString);
    }


}