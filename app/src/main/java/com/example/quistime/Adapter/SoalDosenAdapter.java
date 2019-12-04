package com.example.quistime.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quistime.Models.SoalDosen;
import com.example.quistime.R;
import java.util.ArrayList;

public class SoalDosenAdapter extends RecyclerView.Adapter<SoalDosenAdapter.MyViewHolder>{

    private ArrayList<SoalDosen> daftarSoal;
    private Context context;

    public SoalDosenAdapter(ArrayList<SoalDosen> daftarSoal, Context context) {
        this.daftarSoal = daftarSoal;
        this.context = context;
    }

    @NonNull
    @Override
    public SoalDosenAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listsoal, parent, false);
        SoalDosenAdapter.MyViewHolder viewHolder = new SoalDosenAdapter.MyViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SoalDosenAdapter.MyViewHolder holder, int position) {
        SoalDosen soalDosen = daftarSoal.get(position);
        holder.txtSoal.setText(soalDosen.getSoal());
        holder.txtA.setText(soalDosen.getA());
        holder.txtB.setText(soalDosen.getB());
        holder.txtC.setText(soalDosen.getC());
        holder.txtD.setText(soalDosen.getD());
        holder.txtE.setText(soalDosen.getE());
    }

    @Override
    public int getItemCount() {
        return (daftarSoal != null) ? daftarSoal.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtSoal;
        TextView txtA;
        TextView txtB;
        TextView txtC;
        TextView txtD;
        TextView txtE;
        TextView txtKunci;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSoal = itemView.findViewById(R.id.txtSoal);
            txtA = itemView.findViewById(R.id.txtA);
            txtB = itemView.findViewById(R.id.txtB);
            txtC = itemView.findViewById(R.id.txtC);
            txtD = itemView.findViewById(R.id.txtD);
            txtE = itemView.findViewById(R.id.txtE);
            txtKunci = itemView.findViewById(R.id.txtKunci);
        }
    }
}
