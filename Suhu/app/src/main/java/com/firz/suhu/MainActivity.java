package com.firz.suhu;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Spinner satuanSuhu;
    EditText etNilai;
    TextView tvHasil;

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
//        isiSatuan();
    }

    public void isiSatuan() {
        String[] isi = {"Celcius to Reamur", "Celcius to Fahrenheit", "Celcius to Kelvin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, isi);
        satuanSuhu.setAdapter(adapter);
    }

    public void load() {
        satuanSuhu = findViewById(R.id.satuanSuhu);
        etNilai = findViewById(R.id.etNilai);
        tvHasil = findViewById(R.id.tvHasil);
    }

    public void btnConvert(View view) {
        String pilihan = satuanSuhu.getSelectedItem().toString();
        if (etNilai.getText().toString().isEmpty()) {
            Toast.makeText(this, "Masukkan Nilai", Toast.LENGTH_SHORT).show();
        } else {
            switch (pilihan) {
                case "Celcius to Reamur":
                    cToR();
                    break;
                case "Celcius to Fahrenheit":
                    cToF();
                    break;
                case "Celcius to Kelvin":
                    cToK();
                    break;
            }
        }
    }

    public void cToR() {
        double suhu = Double.parseDouble(etNilai.getText().toString());
        double hasil = (4.0 * suhu) / 5.0;
        tvHasil.setText(hasil + "");
    }

    public void cToF() {
        double suhu = Double.parseDouble(etNilai.getText().toString());
        double hasil = (9.0 * suhu) / 5.0 + 32.0;
        tvHasil.setText(hasil + "");
    }

    public void cToK() {
        double suhu = Double.parseDouble(etNilai.getText().toString());
        double hasil = suhu + 273.15;
        tvHasil.setText(hasil + "");
    }
}