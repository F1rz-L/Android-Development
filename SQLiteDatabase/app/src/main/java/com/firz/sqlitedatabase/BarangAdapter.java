package com.firz.sqlitedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder>{

    Context context;
    List<Barang> barangList;

    public BarangAdapter(Context context, List<Barang> barangList) {
        this.context = context;
        this.barangList = barangList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvBarang.setText(barangList.get(position).getBarang());
        holder.tvStok.setText(barangList.get(position).getStok());
        holder.tvHarga.setText(barangList.get(position).getHarga());

        holder.tvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(context, holder.tvMenu);
                popup.inflate(R.menu.menu_item);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.ubah) {

                            ((MainActivity)context).selectUpdate(barangList.get(holder.getAdapterPosition()).getIdbarang());
                        } else if (menuItem.getItemId() == R.id.hapus) {
                            ((MainActivity)context).deleteData(barangList.get(holder.getAdapterPosition()).getIdbarang());
                        }

                        return false;
                    }
                });

                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return barangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvBarang,  tvHarga, tvStok, tvMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBarang = itemView.findViewById(R.id.tvBarang);
            tvStok = itemView.findViewById(R.id.tvStok);
            tvHarga = itemView.findViewById(R.id.tvHarga);
            tvMenu = itemView.findViewById(R.id.tvMenu);
        }
    }
}
