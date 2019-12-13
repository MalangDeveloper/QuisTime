package com.example.quistime.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quistime.Models.Nilai;
import com.example.quistime.R;

import java.util.ArrayList;

public class NilaiMhsAdapter extends RecyclerView.Adapter<NilaiMhsAdapter.MyViewHolder> {

    private ArrayList<Nilai> daftarNilai;
    private Context context;

    public NilaiMhsAdapter(ArrayList<Nilai> daftarNilai, Context context) {
        this.daftarNilai = daftarNilai;
        this.context = context;
    }

    @NonNull
    @Override
    public NilaiMhsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listnilai, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NilaiMhsAdapter.MyViewHolder holder, int position) {
        Nilai nilai = daftarNilai.get(position);
        holder.txtMatkulList.setText("Matkul\t: "+nilai.getMatkul());
        holder.txtNilaiList.setText("Nilai\t: "+nilai.getNilai());
        holder.txtTanggalList.setText(nilai.getTanggal());
    }

    @Override
    public int getItemCount() {
        return (daftarNilai != null) ? daftarNilai.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtMatkulList;
        TextView txtNilaiList;
        TextView txtTanggalList;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMatkulList = itemView.findViewById(R.id.txtMatkulList);
            txtNilaiList = itemView.findViewById(R.id.txtNilaiList);
            txtTanggalList = itemView.findViewById(R.id.txtTanggalList);
        }
    }
}
