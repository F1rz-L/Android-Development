package com.firz.laravelapi.activity.tambahData

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.firz.laravelapi.R
import com.firz.laravelapi.config.NetworkConfig
import com.firz.laravelapi.databinding.ActivityTambahMahasiswaBinding
import com.firz.laravelapi.model.SubmitMahasiswa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahMahasiswaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTambahMahasiswaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTambahMahasiswaBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val items = listOf(
            "Sunni Islam",
            "Syia Islam",
            "Kristen Protestan",
            "Kristen Katolik",
            "Kristen Ortodoks"
        )
        val adapter = ArrayAdapter(this, R.layout.list_agama, items)
        binding.dropdownField.setAdapter(adapter)

        binding.saveButton.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val agama = binding.dropdownField.text.toString()
        val rbLaki = binding.rbLaki
        val rbPerempuan = binding.rbPerempuan

        val gender = if (rbLaki.isChecked) {
            rbLaki.text.toString()
        } else {
            rbPerempuan.text.toString()
        }

        val namalengkap = binding.editTextName.text.toString()
        val nim = binding.editTextNim.text.toString()
        val alamat = binding.editTextAlamat.text.toString()
        val usia = binding.editTextUsia.text.toString()
        Toast.makeText(applicationContext, "Tunggu sebentar...", Toast.LENGTH_LONG).show()

        val retrofit = NetworkConfig().getService()
        if (namalengkap.isNotEmpty() || nim.isNotEmpty() || alamat.isNotEmpty() || usia.isNotEmpty() || gender.isNotEmpty() || agama.isNotEmpty()) {
            retrofit.postMahasiswa(namalengkap, nim, alamat, gender, agama, usia)
                .enqueue(object : Callback<SubmitMahasiswa> {
                    override fun onResponse(
                        call: Call<SubmitMahasiswa>,
                        response: Response<SubmitMahasiswa>
                    ) {
                        if (response.isSuccessful) {
                            val hasil = response.body()
                            Toast.makeText(applicationContext, hasil!!.message, Toast.LENGTH_SHORT)
                                .show()
                            namalengkap.isEmpty()
                            nim.isEmpty()
                            alamat.isEmpty()
                            gender.isEmpty()
                            agama.isEmpty()
                            usia.isEmpty()
                        }
                    }

                    override fun onFailure(call: Call<SubmitMahasiswa>, t: Throwable) {
                        Toast.makeText(
                            applicationContext,
                            "Data Gagal: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
        } else {
            Toast.makeText(applicationContext, "Field tidak boleh kosong", Toast.LENGTH_SHORT)
                .show()
        }
    }
}