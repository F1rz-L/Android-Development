package com.firz.sqlitedatabase;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Database db;
    EditText etBarang, etStok, etHarga;
    TextView tvPilihan;

    List<Barang> dataBarang = new ArrayList<Barang>();
    BarangAdapter adapter;
    RecyclerView rcvBarang;

    String idbarang;

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
        selectData();
    }

    public void load() {
        db = new Database(this);
        db.buatTabel();

        etBarang = findViewById(R.id.etBarang);
        etStok = findViewById(R.id.etStok);
        etHarga = findViewById(R.id.etHarga);
        tvPilihan = findViewById(R.id.tvPilihan);
        rcvBarang = findViewById(R.id.rcvBarang);

        rcvBarang.setLayoutManager(new LinearLayoutManager(this));
        rcvBarang.setHasFixedSize(true);
    }

    public void selectData() {
        String sql = "SELECT * FROM tblBarang ORDER BY barang ASC";
        Cursor cursor = db.select(sql);
        dataBarang.clear();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String idbarang = cursor.getString(cursor.getColumnIndex("idbarang"));
                @SuppressLint("Range") String barang = cursor.getString(cursor.getColumnIndex("barang"));
                @SuppressLint("Range") String stok = cursor.getString(cursor.getColumnIndex("stok"));
                @SuppressLint("Range") String harga = cursor.getString(cursor.getColumnIndex("harga"));

                dataBarang.add(new Barang(idbarang, barang, stok, harga));
            }
            adapter = new BarangAdapter(this, dataBarang);
            rcvBarang.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            pesan("Data kothong!");
        }
    }

    public void simpan(View view) {
        String barang = etBarang.getText().toString();
        String stok = etStok.getText().toString();
        String harga = etHarga.getText().toString();
        String pilihan = tvPilihan.getText().toString();

        if (barang.isEmpty() || stok.isEmpty() || harga.isEmpty()) {
            pesan("Isi semua kolom!");
        } else {
            if (pilihan.equals("Insert")) {
                String sql = "INSERT INTO tblBarang (barang, stok, harga) VALUES ('" + barang + "', " + stok + ", " + harga + ")";
                if (db.runSQL(sql)) {
                    pesan("Insert successful!");
                    selectData();
                } else {
                    pesan("Insert failed!");
                }
            } else {
                String sql = "UPDATE tblBarang\nSET barang = '" + barang +
                        "', stok = " + stok + ", harga = " + harga + "\nWHERE idbarang = " + idbarang + ";";

                if (db.runSQL(sql)) {
                    pesan("Update Success!");
                    selectData();
                } else {
                    pesan("Update Failed!");
                }
            }
        }

        etBarang.setText("");
        etStok.setText("");
        etHarga.setText("");
        tvPilihan.setText("Insert");
    }

    public void pesan(String isi) {
        Toast.makeText(this, isi, Toast.LENGTH_SHORT).show();
    }

    public void deleteData(String id) {
        idbarang = id;

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Peringatan");
        alert.setMessage("Yakin menghapus?");
        alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String sql = "DELETE FROM tblBarang WHERE idbarang = " + idbarang + ";";
                if (db.runSQL(sql)) {
                    pesan("Data sudah dihapus");
                    selectData();
                } else {
                    pesan("Data gagal dihapus");
                }
            }
        });
        alert.show();
    }

    @SuppressLint("Range")
    public void selectUpdate(String id) {
        idbarang = id;
        String sql = "SELECT * FROM tblBarang WHERE idbarang = " + id + ";";

        Cursor cursor = db.select(sql);
        cursor.moveToNext();
        etBarang.setText(cursor.getString(cursor.getColumnIndex("barang")));
        etStok.setText(cursor.getString(cursor.getColumnIndex("stok")));
        etHarga.setText(cursor.getString(cursor.getColumnIndex("harga")));
        tvPilihan.setText("Update");
    }
}