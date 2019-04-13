package com.minhtung.imusicpro.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.minhtung.imusicpro.Adapter.AdapterTheLoai;
import com.minhtung.imusicpro.Model.TheLoai;
import com.minhtung.imusicpro.R;
import com.minhtung.imusicpro.Server.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_TheLoai extends Fragment {
    RecyclerView recyclerViewTheLoai;
    AdapterTheLoai adapterTheLoai;
    ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();

    private ProgressBar progressBar;
    private TextView tvNotify;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_theloai, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewTheLoai = view.findViewById(R.id.recyclerViewTheLoai);
        progressBar = view.findViewById(R.id.progressBar);
        tvNotify = view.findViewById(R.id.tvNotify);

        adapterTheLoai = new AdapterTheLoai(getContext(), theLoaiArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTheLoai.setLayoutManager(linearLayoutManager);
        recyclerViewTheLoai.setAdapter(adapterTheLoai);

        getDataTheLoai();
    }

    private void getDataTheLoai() {
        beginLoadData();
        API.initRetrofit().create(API.ApiInterface.class).getTheLoai().enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                if (response != null && response.body() != null) {
                    theLoaiArrayList.addAll(response.body());
                }
                String msg = "";
                if (theLoaiArrayList.size() == 0) msg = getString(R.string.no_data);
                adapterTheLoai.notifyDataSetChanged();
                finishLoadData(msg);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {
                finishLoadData("Error " + t.getMessage());
                Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void finishLoadData(String msg) {
        progressBar.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(msg)) {
            tvNotify.setVisibility(View.VISIBLE);
            tvNotify.setText(msg);
        } else {
            tvNotify.setVisibility(View.GONE);
        }
    }

    private void beginLoadData() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
