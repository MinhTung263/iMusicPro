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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.minhtung.imusicpro.Adapter.AdapterDanhsachnhactuychon;
import com.minhtung.imusicpro.Adapter.DanhSachNhacAdapter;
import com.minhtung.imusicpro.Model.Danhsachbaihat;
import com.minhtung.imusicpro.Model.TheLoai;
import com.minhtung.imusicpro.R;
import com.minhtung.imusicpro.Server.API;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihattuychonActivity extends AppCompatActivity {
Toolbar toolbar;
AdapterDanhsachnhactuychon adapterDanhsachnhactuychon;
TheLoai theLoai;
ArrayList<Danhsachbaihat> mangbaihat=new ArrayList<>();
RecyclerView recyclerViewDanhsachbaihattuychon;
ImageView imgtuychon;
TextView tvTuyChon;
ImageButton btntuychon ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihattuychon);
        toolbar=findViewById(R.id.toolbarDanhsachnhactuychon);
        recyclerViewDanhsachbaihattuychon=findViewById(R.id.recyclerViewDSBHTuyChon);
        imgtuychon=findViewById(R.id.imgtuychon);
        tvTuyChon=findViewById(R.id.tvtuychon);
        btntuychon=findViewById(R.id.btntuychon);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getDataTheLoaiIntent();
        if (theLoai!=null&& !theLoai.getTenTheLoai().equals("")){
            tvTuyChon.setText(theLoai.getTenTheLoai());
            getSupportActionBar().setTitle(theLoai.getTenTheLoai());
            Picasso.with(this).load(theLoai.getHinhTheLoai()).into(imgtuychon);
            getDataIdTheLoai(theLoai.getIdTheLoai());
        }
        btntuychon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DanhsachbaihattuychonActivity.this, PlayNhacActivity.class);
                intent.putExtra("cacbaihat",mangbaihat);
                startActivity(intent);
            }
        });
    }
    private void getDataIdTheLoai(String idTheLoai){
        API.initRetrofit().create(API.ApiInterface.class).getIDTheLoai(idTheLoai).enqueue(new Callback<List<Danhsachbaihat>>() {
            @Override
            public void onResponse(Call<List<Danhsachbaihat>> call, Response<List<Danhsachbaihat>> response) {
                mangbaihat = (ArrayList<Danhsachbaihat>) response.body();
                adapterDanhsachnhactuychon=new AdapterDanhsachnhactuychon(DanhsachbaihattuychonActivity.this,mangbaihat);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(DanhsachbaihattuychonActivity.this,LinearLayoutManager.VERTICAL,false);
                recyclerViewDanhsachbaihattuychon.setLayoutManager(linearLayoutManager);
                recyclerViewDanhsachbaihattuychon.setAdapter(adapterDanhsachnhactuychon);
            }

            @Override
            public void onFailure(Call<List<Danhsachbaihat>> call, Throwable t) {

            }
        });
    }
    private  void  getDataTheLoaiIntent(){
        Intent intent=getIntent();
        if (intent!=null){
            if (intent.hasExtra("itemtheloai")){
                theLoai= (TheLoai) intent.getSerializableExtra("itemtheloai");
            }
        }
    }
}
