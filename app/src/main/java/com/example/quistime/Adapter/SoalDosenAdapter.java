package com.example.quistime.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quistime.Menu.MatkulActivity;
import com.example.quistime.Menu.SoalActivity;
import com.example.quistime.Models.Matkul;
import com.example.quistime.Models.SoalDosen;
import com.example.quistime.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SoalDosenAdapter extends RecyclerView.Adapter<SoalDosenAdapter.MyViewHolder>{

    private ArrayList<SoalDosen> daftarSoal;
    private Context context;
    int No =0;
    SoalActivity listener;


    public SoalDosenAdapter(ArrayList<SoalDosen> daftarSoal, Context context) {
        this.daftarSoal = daftarSoal;
        this.context = context;
        this.listener = (SoalActivity) context;
    }

    @NonNull
    @Override
    public SoalDosenAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listsoal, parent, false);
        SoalDosenAdapter.MyViewHolder viewHolder = new SoalDosenAdapter.MyViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SoalDosenAdapter.MyViewHolder holder, final int position) {
        SoalDosen soalDosen = daftarSoal.get(position);
        String urutan = Integer.toString(No+1);
        holder.txtNo.setText(urutan+". ");
        holder.txtSoal.setText(soalDosen.getSoal());
        holder.txtA.setText("A. "+soalDosen.getA());
        holder.txtB.setText("B. "+soalDosen.getB());
        holder.txtC.setText("C. "+soalDosen.getC());
        holder.txtD.setText("D. "+soalDosen.getD());
        holder.txtE.setText("E. "+soalDosen.getE());
        holder.txtKunci.setText(" "+soalDosen.getJawaban());
        No = No+1;
        final SoalDosen sd = daftarSoal.get(position);
        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDeleteData(daftarSoal.get(position), position);
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                updateSoal(sd);
            }
        });
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
        TextView txtNo;
        Button btnHapus;
        Button btnEdit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSoal = itemView.findViewById(R.id.txtSoal);
            txtA = itemView.findViewById(R.id.txtA);
            txtB = itemView.findViewById(R.id.txtB);
            txtC = itemView.findViewById(R.id.txtC);
            txtD = itemView.findViewById(R.id.txtD);
            txtE = itemView.findViewById(R.id.txtE);
            txtKunci = itemView.findViewById(R.id.txtKunci);
            txtNo = itemView.findViewById(R.id.txtNo);
            btnHapus = itemView.findViewById(R.id.btnHapus);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateSoal(final SoalDosen soal) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_tambah_soal, null);
        builder.setView(view);

        final EditText editSoal = view.findViewById(R.id.txtTSoal);
        final EditText editTA = view.findViewById(R.id.txtTA);
        final EditText editTB = view.findViewById(R.id.txtTB);
        final EditText editTC = view.findViewById(R.id.txtTC);
        final EditText editTD = view.findViewById(R.id.txtTD);
        final EditText editTE = view.findViewById(R.id.txtTE);
        final Spinner txtkunci = view.findViewById(R.id.spinnerKunci);
        final TextView headerTambah = view.findViewById(R.id.headertambah);
        final Button btnTambah = view.findViewById(R.id.btnTambah);
        final Button btnUpdate = view.findViewById(R.id.btnTambah);

        editSoal.setText(soal.getSoal());
        editTA.setText(soal.getA());
        editTB.setText(soal.getB());
        editTC.setText(soal.getC());
        editTD.setText(soal.getD());
        editTE.setText(soal.getE());
        headerTambah.setText("Edit Soal");
        btnTambah.setText("Update");

        final AlertDialog dialog = builder.create();
        dialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soal.setSoal(editSoal.getText().toString().trim());
                soal.setA(editTA.getText().toString().trim());
                soal.setB(editTB.getText().toString().trim());
                soal.setC(editTC.getText().toString().trim());
                soal.setD(editTD.getText().toString().trim());
                soal.setE(editTE.getText().toString().trim());
                soal.setJawaban(txtkunci.getSelectedItem().toString().trim());

                listener.onUpdateData(soal, context);
                dialog.dismiss();
            }
        });
    }
}
