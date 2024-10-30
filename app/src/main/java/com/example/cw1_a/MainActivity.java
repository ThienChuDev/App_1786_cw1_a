package com.example.cw1_a;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private EditText inputValue;
    private Spinner unitFrom, unitTo;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        unitFrom = findViewById(R.id.unitFrom);
        unitTo = findViewById(R.id.unitTo);
        resultTextView = findViewById(R.id.resultTextView);
        Button convertButton = findViewById(R.id.convertButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.length_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitFrom.setAdapter(adapter);
        unitTo.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
            }
        });
    }

    private void convert() {
        String valueStr = inputValue.getText().toString();
        if (valueStr.isEmpty()) {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        double value = Double.parseDouble(valueStr);
        String fromUnit = unitFrom.getSelectedItem().toString();
        String toUnit = unitTo.getSelectedItem().toString();

        double result = convertLength(value, fromUnit, toUnit);
        resultTextView.setText(String.format("%.2f %s", result, toUnit));
    }

    private double convertLength(double value, String fromUnit, String toUnit) {
        double meters = 0;
        switch (fromUnit) {
            case "Meters":
                meters = value;
                break;
            case "Millimeters":
                meters = value / 1000;
                break;
            case "Miles":
                meters = value * 1609.34;
                break;
            case "Feet":
                meters = value * 0.3048;
                break;
        }

        switch (toUnit) {
            case "Meters":
                return meters;
            case "Millimeters":
                return meters * 1000;
            case "Miles":
                return meters / 1609.34;
            case "Feet":
                return meters / 0.3048;
        }

        return 0;
    }
}