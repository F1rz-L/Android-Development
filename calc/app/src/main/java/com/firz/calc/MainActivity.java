package com.firz.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    TextView textHasil;
    EditText bil1, bil2;
    double hasil = 0;

    public void load() {
        textHasil = findViewById(R.id.textHasil);
        bil1 = findViewById(R.id.bil1);
        bil2 = findViewById(R.id.bil2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        load();
    }

    public void jumlah(View view) {
        if (bil1.getText().toString().equals("") || bil2.getText().toString().equals("")) {
            Toast.makeText(this, "Ada Kolom Kosong", Toast.LENGTH_SHORT).show();
        } else {
            double var1 = Double.parseDouble(bil1.getText().toString());
            double var2 = Double.parseDouble(bil2.getText().toString());
            hasil = var1 + var2;

            textHasil.setText(hasil + "");
        }
    }
    public void kurang(View view) {
        if (bil1.getText().toString().equals("") || bil2.getText().toString().equals("")) {
            Toast.makeText(this, "Ada Kolom Kosong", Toast.LENGTH_SHORT).show();
        } else {
            double var1 = Double.parseDouble(bil1.getText().toString());
            double var2 = Double.parseDouble(bil2.getText().toString());
            hasil = var1 - var2;

            textHasil.setText(hasil + "");
        }
    }
    public void kali(View view) {
        if (bil1.getText().toString().equals("") || bil2.getText().toString().equals("")) {
            Toast.makeText(this, "Ada Kolom Kosong", Toast.LENGTH_SHORT).show();
        } else {
            double var1 = Double.parseDouble(bil1.getText().toString());
            double var2 = Double.parseDouble(bil2.getText().toString());
            hasil = var1 * var2;

            textHasil.setText(hasil + "");
        }
    }
    public void bagi(View view) {
        if (bil1.getText().toString().equals("") || bil2.getText().toString().equals("")) {
            Toast.makeText(this, "Ada Kolom Kosong", Toast.LENGTH_SHORT).show();
        } else {
            double var1 = Double.parseDouble(bil1.getText().toString());
            double var2 = Double.parseDouble(bil2.getText().toString());
            hasil = var1 / var2;

            textHasil.setText(hasil + "");
        }
    }
}