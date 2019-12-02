package com.example.quistime.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quistime.MainActivity;
import com.example.quistime.Menu.SoalActivity;
import com.example.quistime.Models.Matkul;
import com.example.quistime.R;

import java.util.ArrayList;

public class MatkulDosenAdapter extends RecyclerView.Adapter<MatkulDosenAdapter.MyViewHolder> {

    public static final String MATKUL = "Matkul";
    private ArrayList<Matkul> daftarMatkul;
    private Context context;

    public MatkulDosenAdapter(ArrayList<Matkul> daftarMatkul, Context context) {
        this.daftarMatkul = daftarMatkul;
        this.context = context;
    }

    @NonNull
    @Override
    public MatkulDosenAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listmatkul, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MatkulDosenAdapter.MyViewHolder holder, int position) {
        Matkul matkul = daftarMatkul.get(position);
        holder.txtNama.setText(matkul.getMatkul());
        holder.txtCode.setText(matkul.getCode());
        holder.txtTanggal.setText(matkul.getTanggal());

        final String nama = holder.txtNama.getText().toString();
        final String code = holder.txtCode.getText().toString();
        final String tanggal = holder.txtTanggal.getText().toString();

        holder.txtNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Matkul m = new Matkul(nama, code, tanggal);
                Context context = view.getContext();
                Intent intent = new Intent(context, SoalActivity.class);
                intent.putExtra(MATKUL, m);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (daftarMatkul != null) ? daftarMatkul.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtNama;
        TextView txtCode;
        TextView txtTanggal;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtCode = itemView.findViewById(R.id.txtCode);
            txtTanggal = itemView.findViewById(R.id.txtTanggal);
        }
    }
}
