package com.minhtung.imusicpro.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.minhtung.imusicpro.Activity.DanhSachBaiHatActivity;
import com.minhtung.imusicpro.R;
import android.view.MenuItem;
import android.widget.Toast;

public class Fragment_Music_Offline extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    View view;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_music_offline,container,false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavigationView navigationView=view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_danhsachnhac) {
            Intent intent=new Intent(getActivity(), DanhSachBaiHatActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_add) {
        }
        return true;
    }

}
