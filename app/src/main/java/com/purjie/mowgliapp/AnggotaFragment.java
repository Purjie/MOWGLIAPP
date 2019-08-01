package com.purjie.mowgliapp;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import com.example.mowgli.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnggotaFragment extends Fragment {

    private RecyclerView rv_anggota;
    private FirebaseDatabase anggotaDatabase;
    private DatabaseReference databaseReference;
    private EditText edt_search;
    private Button btn_search;
    FirebaseRecyclerAdapter<ModelAnggota,AnggotaViewHolder> adapterAnggota;


    public AnggotaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_anggota, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("anggota");
        rv_anggota = view.findViewById(R.id.rv_anggota);
        rv_anggota.setHasFixedSize(true);
        rv_anggota.setLayoutManager(new LinearLayoutManager(getContext()));
        edt_search = view.findViewById(R.id.edt_search);
        btn_search = view.findViewById(R.id.btn_search);

        tampilListAnggota(null);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = edt_search.getText().toString();

                tampilListAnggota(search);
            }
        });
        return view;
    }

    private void tampilListAnggota(String searchText) {

        Query firebaseQuery = databaseReference.orderByChild("nama_angkatan").startAt(searchText).endAt(searchText + "\uf8ff");
        adapterAnggota = new FirebaseRecyclerAdapter<ModelAnggota, AnggotaViewHolder>(
                ModelAnggota.class,
                R.layout.item_anggota,
                AnggotaViewHolder.class,
                firebaseQuery
        ) {
            @Override
            protected void populateViewHolder(AnggotaViewHolder anggotaViewHolder, ModelAnggota modelAnggota, int i) {
                anggotaViewHolder.setDetails(getContext().getApplicationContext(),modelAnggota.getNama(),
                        modelAnggota.getAngkatan_ke(),modelAnggota.getNama_angkatan(),
                        modelAnggota.getTtl(),modelAnggota.getGambar(),modelAnggota.getNo_anggota());
            }
        };
        rv_anggota.setAdapter(adapterAnggota);
    }

    public static class AnggotaViewHolder extends RecyclerView.ViewHolder {
        View view;

        public AnggotaViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
        }

        public void setDetails(Context ctx, String nama, String angkatan_ke, String nama_angkatan,
                               String ttl, String gambar, String no_anggota) {

            TextView txt_nama = view.findViewById(R.id.txt_nama);
            TextView txt_no_anggota = view.findViewById(R.id.txt_no_anggota);
            TextView txt_nama_angkata = view.findViewById(R.id.txt_nama_angkatan);
            TextView txt_ttl = view.findViewById(R.id.txt_ttl);
            TextView txt_angkatan = view.findViewById(R.id.txt_no_angkatan);
            ImageView img_anggota = view.findViewById(R.id.img_anggota);


            txt_nama.setText(nama);
            txt_no_anggota.setText(no_anggota);
            txt_nama_angkata.setText(nama_angkatan);
            txt_ttl.setText(ttl);
            txt_angkatan.setText(angkatan_ke);

            Glide.with(ctx).load(gambar).into(img_anggota);
        }
    }

}
