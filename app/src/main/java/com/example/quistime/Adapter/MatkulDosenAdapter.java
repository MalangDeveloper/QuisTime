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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quistime.Menu.MatkulActivity;
import com.example.quistime.Menu.SoalActivity;
import com.example.quistime.Models.Matkul;
import com.example.quistime.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MatkulDosenAdapter extends RecyclerView.Adapter<MatkulDosenAdapter.MyViewHolder> {

    public static final String MATKUL = "Matkul";
    private ArrayList<Matkul> daftarMatkul;
    private Context context;
    MatkulActivity listener;

    public MatkulDosenAdapter(ArrayList<Matkul> daftarMatkul, Context context) {
        this.daftarMatkul = daftarMatkul;
        this.context = context;
        this.listener = (MatkulActivity) context;
    }

    @NonNull
    @Override
    public MatkulDosenAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listmatkul, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MatkulDosenAdapter.MyViewHolder holder, final int position) {
        Matkul matkul = daftarMatkul.get(position);
        holder.txtNama.setText(matkul.getMatkul());
        holder.txtCode.setText(matkul.getCode());
        holder.txtTanggal.setText(matkul.getTanggal());

        final Matkul mk = daftarMatkul.get(position);
        final String nama = holder.txtNama.getText().toString();
        final String key = mk.getKey();

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Matkul m = new Matkul(nama, key);
                Context context = view.getContext();
                Intent intent = new Intent(context, SoalActivity.class);
                intent.putExtra(MATKUL, m);
                context.startActivity(intent);
            }
        });

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDeleteData(daftarMatkul.get(position), position);
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                updateMatkul(mk);
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
        TextView btnEdit;
        TextView btnHapus;
        ConstraintLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtCode = itemView.findViewById(R.id.txtCode);
            txtTanggal = itemView.findViewById(R.id.txtTanggal);
            btnEdit = itemView.findViewById(R.id.btneditmatkul);
            btnHapus = itemView.findViewById(R.id.btnhapusmatkul);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateMatkul(final Matkul matkul) {
        System.out.println("hghjn "+matkul.getKey());
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_edit_matkul, null);
        builder.setView(view);

        final EditText editTextName = view.findViewById(R.id.editmatkul);
//        final EditText editCodeMatkul = view.findViewById(R.id.editCodeMatkul);
        final Button btnUpdate = view.findViewById(R.id.btneditmatkul);
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        editTextName.setText(matkul.getMatkul());
//        editCodeMatkul.setText(matkul.getCode());

        final AlertDialog dialog = builder.create();
        dialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
//                String code = editCodeMatkul.getText().toString().trim();

                if (name.isEmpty()) {
                    editTextName.setError("Matkul tidak boleh kosong");
                    editTextName.requestFocus();
                    return;
                }
//                if (code.isEmpty()){
//                    editCodeMatkul.setError("Matkul tidak boleh kosong");
//                    editCodeMatkul.requestFocus();
//                    return;
//                }

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String txtTanggal = sdf.format(cal.getTime());

                matkul.setMatkul(name);
//                matkul.setCode(code);
                matkul.setTanggal(txtTanggal);
                database.child("Matkul").child(matkul.getKey()).setValue(matkul).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Matkul Updated", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.dismiss();
            }
        });
    }
}
