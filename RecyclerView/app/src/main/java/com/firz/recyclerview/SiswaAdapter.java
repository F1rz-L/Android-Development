package com.firz.recyclerview;

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

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> {

    private Context context;
    private List<Siswa> siswaList;

    public SiswaAdapter(Context context, List<Siswa> siswaList) {
        this.context = context;
        this.siswaList = siswaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_siswa, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Siswa siswa = siswaList.get(position);
        holder.tvNama.setText(siswa.getNama());
        holder.tvAlamat.setText(siswa.getAlamat());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "Nama: "+siswa.getNama() + "\n" + "Alamat: "+siswa.getAlamat(), Toast.LENGTH_SHORT).show();
//            }
//        });

        holder.tvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, holder.tvMenu);
                popupMenu.inflate(R.menu.menu_option);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.menu_simpan) {
                            Toast.makeText(context, "Menyimpan menu " + siswa.getNama(), Toast.LENGTH_SHORT).show();
                        } else if (menuItem.getItemId() == R.id.menu_hapus) {
                            siswaList.remove(holder.getAdapterPosition());
                            notifyItemRemoved(holder.getAdapterPosition());
                            Toast.makeText(context, "Menghapus data " + siswa.getNama(), Toast.LENGTH_SHORT).show();
                        }

                        return false;
                    }
                });

                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return siswaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama, tvAlamat, tvMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tvNama);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
            tvMenu = itemView.findViewById(R.id.tvMenu);
        }
    }
}
