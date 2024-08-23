package com.example.dueo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText btn1, btn2;
    TextView textbtn1;
    Spinner operationSpinner;
    Button performButton;
    String selectedOperation = "Addition"; // Default operation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        textbtn1 = findViewById(R.id.textbtn1);
        operationSpinner = findViewById(R.id.operationSpinner);
        performButton = findViewById(R.id.performButton);

        operationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOperation = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });

        performButton.setOnClickListener(this::performOperation);
    }

    private void performOperation(View view) {
        try {
            float n1 = Float.parseFloat(btn1.getText().toString());
            float n2 = Float.parseFloat(btn2.getText().toString());
            float result = 0;

            switch (selectedOperation) {
                case "Addition":
                    result = n1 + n2;
                    break;
                case "Subtraction":
                    result = n1 - n2;
                    break;
                case "Multiplication":
                    result = n1 * n2;
                    break;
                case "Division":
                    if (n2 == 0) {
                        showToast("Cannot divide by zero");
                        return;
                    }
                    result = n1 / n2;
                    break;
                default:
                    showToast("Unknown operation");
                    return;
            }

            textbtn1.setText(Float.toString(result));
        } catch (NumberFormatException e) {
            showToast("Invalid input");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}