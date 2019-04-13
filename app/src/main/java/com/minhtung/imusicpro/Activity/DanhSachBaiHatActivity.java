package com.minhtung.imusicpro.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.minhtung.imusicpro.Adapter.DanhSachNhacAdapter;
import com.minhtung.imusicpro.Model.Danhsachbaihat;
import com.minhtung.imusicpro.Model.TheLoai;
import com.minhtung.imusicpro.R;
import com.minhtung.imusicpro.Server.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnPlayAllmusic;
    RecyclerView recyclerViewDanhSachBaiHat;
    DanhSachNhacAdapter danhSachNhacAdapter;
    ArrayList<Danhsachbaihat> mangbaihat=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        toolbar=findViewById(R.id.toolbarDanhsachnhac);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thư viện nhạc");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(ContextCompat.getColor(DanhSachBaiHatActivity.this,R.color.colorMenu));
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(ContextCompat.getColor(DanhSachBaiHatActivity.this,R.color.colorMenu));
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnPlayAllmusic=findViewById(R.id.btnplayAllmusic);
        recyclerViewDanhSachBaiHat=findViewById(R.id.recyclerViewDSBaiHat);
        getData();
        eventClick();
    }
    private void eventClick() {
        btnPlayAllmusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DanhSachBaiHatActivity.this, PlayNhacActivity.class);
                intent.putExtra("cacbaihat",mangbaihat);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        API.initRetrofit().create(API.ApiInterface.class).getDanhsachbh().enqueue(new Callback<List<Danhsachbaihat>>() {
            @Override
            public void onResponse(Call<List<Danhsachbaihat>> call, Response<List<Danhsachbaihat>> response) {
                mangbaihat = (ArrayList<Danhsachbaihat>) response.body();
                danhSachNhacAdapter=new DanhSachNhacAdapter(DanhSachBaiHatActivity.this,mangbaihat);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(DanhSachBaiHatActivity.this,LinearLayoutManager.VERTICAL,false);
                recyclerViewDanhSachBaiHat.setLayoutManager(linearLayoutManager);
                recyclerViewDanhSachBaiHat.setAdapter(danhSachNhacAdapter);
            }

            @Override
            public void onFailure(Call<List<Danhsachbaihat>> call, Throwable t) {
                Toast.makeText(DanhSachBaiHatActivity.this, "Không có kết nối mạng", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
