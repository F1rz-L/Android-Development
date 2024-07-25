package com.firz.messagedialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        System.out.println("Created!");
    }

    public void showToast(String pesan) {
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();
    }
    public void showAlert(String pesan) {
        AlertDialog.Builder buatAlert = new AlertDialog.Builder(this);
        buatAlert.setTitle("Kecemasan");
        buatAlert.setMessage(pesan);
        buatAlert.show();
    }
    public void showAlertButton(String pesan) {
        AlertDialog.Builder showAlertButton = new AlertDialog.Builder(this);
        showAlertButton.setTitle("DIAM!!");
        showAlertButton.setMessage(pesan);
        showAlertButton.setPositiveButton("iya kak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("Oke Ihsan");
            }
        });
        showAlertButton.setNegativeButton("tidak kak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("Jancok Ihsan");
            }
        });
        showAlertButton.show();
    }

    public void btnToast(View view) {
        showToast("Belajar Toast");
    }
    public void btnAlert(View view) {
        showAlert("Belajar Alert");
    }
    public void btnAlertDialogButton(View view) {
        showAlertButton("Nama Saya Ihsan");
    }
}