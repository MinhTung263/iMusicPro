package com.minhtung.imusicpro.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.minhtung.imusicpro.Activity.DanhSachBaiHatActivity;
import com.minhtung.imusicpro.Adapter.DanhSachNhacAdapter;
import com.minhtung.imusicpro.Adapter.PlaynhacAdapter;
import com.minhtung.imusicpro.Activity.PlayNhacActivity;
import com.minhtung.imusicpro.Model.Danhsachbaihat;
import com.minhtung.imusicpro.R;
import com.minhtung.imusicpro.Server.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Play_Danh_Sach_Bai_Hat extends Fragment {
    View view;
    RecyclerView recyclerViewPlayNhac;
    PlaynhacAdapter playnhacAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_play_danh_sach_bai_hat,container,false);
        recyclerViewPlayNhac=view.findViewById(R.id.recyclerViewPlayBaiHat);
        if (PlayNhacActivity.mangbaihat.size()>0){
            playnhacAdapter=new PlaynhacAdapter(getActivity(), PlayNhacActivity.mangbaihat);
            recyclerViewPlayNhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewPlayNhac.setAdapter(playnhacAdapter);
        }
        return view;
    }
}
