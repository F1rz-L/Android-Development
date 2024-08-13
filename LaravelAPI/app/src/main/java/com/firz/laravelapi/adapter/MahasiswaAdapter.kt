package com.firz.laravelapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firz.laravelapi.databinding.ListMahasiswaAdapterBinding
import com.firz.laravelapi.model.DataMahasiswa
import com.firz.laravelapi.model.ResponseListMahasiswa

class MahasiswaAdapter(
    private val listMahasiswa: List<DataMahasiswa?>?,
) : RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>() {
    inner class ViewHolder(val ListMahasiswaAdapterBinding: ListMahasiswaAdapterBinding) :
        RecyclerView.ViewHolder(ListMahasiswaAdapterBinding.root) {
        fun onBindItem(dataMahasiswa: DataMahasiswa?) {
            ListMahasiswaAdapterBinding.Nama.text = dataMahasiswa?.namamahasiswa
            ListMahasiswaAdapterBinding.Nim.text = dataMahasiswa?.nim
            ListMahasiswaAdapterBinding.Alamat.text = dataMahasiswa?.alamat
            ListMahasiswaAdapterBinding.Gender.text = dataMahasiswa?.gender
            ListMahasiswaAdapterBinding.Agama.text = dataMahasiswa?.agama
            ListMahasiswaAdapterBinding.Usia.text = dataMahasiswa?.usia
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaAdapter.ViewHolder {
        val binding =
            ListMahasiswaAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MahasiswaAdapter.ViewHolder, position: Int) {
        holder.onBindItem(listMahasiswa?.get(position))
    }

    override fun getItemCount(): Int {
        return listMahasiswa?.size?:0
    }

}