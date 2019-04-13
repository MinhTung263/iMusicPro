package com.minhtung.imusicpro.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.minhtung.imusicpro.Activity.MainActivity;
import com.minhtung.imusicpro.Adapter.AdapterTimKiem;
import com.minhtung.imusicpro.Model.Danhsachbaihat;
import com.minhtung.imusicpro.R;
import com.minhtung.imusicpro.Server.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Music_Online extends Fragment{
    View view;
    Toolbar toolbarsearchnhac;
    RecyclerView recyclerViewTimKiem;
    TextView tvThongBao;
    AdapterTimKiem adapterTimKiem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_music_online,container,false);
        toolbarsearchnhac=view.findViewById(R.id.toolbarSearchNhac);
        recyclerViewTimKiem=view.findViewById(R.id.recyclerViewTimKiem);
        tvThongBao=view.findViewById(R.id.tvThongBao);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarsearchnhac);
        toolbarsearchnhac.setTitle("");
        setHasOptionsMenu(true);
        return  view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_item,menu);
        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                TimKiem(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu,inflater);
    }
    private  void TimKiem(String query){
        API.initRetrofit().create(API.ApiInterface.class).getTimKiemBaiHat(query).enqueue(new Callback<List<Danhsachbaihat>>() {
            @Override
            public void onResponse(Call<List<Danhsachbaihat>> call, Response<List<Danhsachbaihat>> response) {
                ArrayList<Danhsachbaihat> danhsachbaihatArrayList=(ArrayList<Danhsachbaihat>)response.body();
                if (danhsachbaihatArrayList.size()>0){
                    adapterTimKiem=new AdapterTimKiem(getActivity(),danhsachbaihatArrayList);
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                    recyclerViewTimKiem.setLayoutManager(linearLayoutManager);
                    recyclerViewTimKiem.setAdapter(adapterTimKiem);
                    tvThongBao.setVisibility(View.GONE);
                    recyclerViewTimKiem.setVisibility(View.VISIBLE);
                }else{
                    recyclerViewTimKiem.setVisibility(View.GONE);
                    tvThongBao.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Danhsachbaihat>> call, Throwable t) {

            }
        });
    }
}
