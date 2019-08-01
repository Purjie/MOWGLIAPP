package com.purjie.mowgliapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.mowgli.R;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

public class Login extends AppCompatActivity implements View.OnClickListener {
        private static final String HOME = "Home";
        private static final String ANGGOTA = "Anggota";
        private static final String SOSMED = "Sosmed";

        TapBarMenu tapBarMenu;
        ImageView itemHome, itemAnggota, itemSosmed;
        TextView txtTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tapBarMenu = findViewById(R.id.tapBarmenu);
        itemHome = findViewById(R.id.itemHome);
        itemAnggota = findViewById(R.id.itemAnggota);
        itemSosmed = findViewById(R.id.itemSosmed);

        itemSosmed.setOnClickListener(this);
        itemAnggota.setOnClickListener(this);
        itemHome.setOnClickListener(this);


        tapBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapBarMenu.toggle();
            }
        });

        FragmentManager managerLogin = getSupportFragmentManager();
        FragmentTransaction transactionLogin = managerLogin.beginTransaction();
        HomeFragment homefrag = new HomeFragment();
        transactionLogin.add(R.id.frame_container,homefrag,HomeFragment.class.getSimpleName());

        transactionLogin.commit();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.itemSosmed:
                FragmentManager managerSosmed = getSupportFragmentManager();
                FragmentTransaction transactionSosmed = managerSosmed.beginTransaction();
                SosmedFragment sosmedFragment = new SosmedFragment();
                transactionSosmed.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_left);
                transactionSosmed.replace(R.id.frame_container,sosmedFragment,SosmedFragment.class.getSimpleName());

                transactionSosmed.addToBackStack(null);
                transactionSosmed.commit();

                break;
            case R.id.itemHome :
                FragmentManager managerHome = getSupportFragmentManager();
                FragmentTransaction transtactionHome = managerHome.beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                transtactionHome.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_left);
                transtactionHome.replace(R.id.frame_container,homeFragment,HomeFragment.class.getSimpleName());

                transtactionHome.addToBackStack(null);
                transtactionHome.commit();
                break;

            case R.id.itemAnggota :
                FragmentManager managerAnggota = getSupportFragmentManager();
                FragmentTransaction transtactionAnggota = managerAnggota.beginTransaction();
                AnggotaFragment anggotaFragment = new AnggotaFragment();
                transtactionAnggota.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_left);
                transtactionAnggota.replace(R.id.frame_container,anggotaFragment,AnggotaFragment.class.getSimpleName());

                transtactionAnggota.addToBackStack(null);
                transtactionAnggota.commit();
                break;

        }

    }
}
