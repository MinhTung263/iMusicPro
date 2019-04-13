package com.minhtung.imusicpro.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.minhtung.imusicpro.Adapter.AdapterTop;
import com.minhtung.imusicpro.Model.Top100;
import com.minhtung.imusicpro.R;
import com.minhtung.imusicpro.Server.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Top100 extends Fragment {
    RecyclerView recyclerViewTop;
    AdapterTop adapterTop;
    ArrayList<Top100> top100ArrayList = new ArrayList<>();
    private ProgressBar progressBar;
    private TextView tvNotify;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top100, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewTop = view.findViewById(R.id.recyclerViewTop100);
        progressBar = view.findViewById(R.id.progressBar);
        tvNotify  = view.findViewById(R.id.tvNotify);

        adapterTop = new AdapterTop(getContext(), top100ArrayList);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewTop.setLayoutManager(gridLayoutManager);
        recyclerViewTop.setAdapter(adapterTop);

        getDataTop100();
    }

    private void getDataTop100() {
        beginLoadData();
        top100ArrayList.clear();

        API.initRetrofit().create(API.ApiInterface.class).getTop100().enqueue(new Callback<List<Top100>>() {
            @Override
            public void onResponse(Call<List<Top100>> call, Response<List<Top100>> response) {
                if(response!=null && response.body()!=null) {
                    top100ArrayList.addAll(response.body());
                }
                String msg = "";
                if(top100ArrayList.size() == 0) msg = getString(R.string.no_data);
                adapterTop.notifyDataSetChanged();
                finishLoadData(msg);
            }

            @Override
            public void onFailure(Call<List<Top100>> call, Throwable t) {
                finishLoadData("Error " + t.getMessage());
                Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void finishLoadData(String msg) {
        progressBar.setVisibility(View.GONE);
        if(!TextUtils.isEmpty(msg)) {
            tvNotify.setVisibility(View.VISIBLE);
            tvNotify.setText(msg);
        }else {
            tvNotify.setVisibility(View.GONE);
        }
    }

    private void beginLoadData() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
