package com.minhtung.imusicpro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minhtung.imusicpro.Activity.PlayNhacActivity;
import com.minhtung.imusicpro.Model.Danhsachbaihat;
import com.minhtung.imusicpro.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachNhacAdapter extends RecyclerView.Adapter<DanhSachNhacAdapter.ViewHolder> {
    Context context;
    ArrayList<Danhsachbaihat> baiHatArrayList;

    public DanhSachNhacAdapter(Context context, ArrayList<Danhsachbaihat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.dong_danh_sach_nhac,parent,false);
        ViewHolder holder=new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Danhsachbaihat danhsachbaihat=baiHatArrayList.get(position);
        viewHolder.txtTenBH.setText(danhsachbaihat.getTenBH());
        viewHolder.txtTenCasi.setText(danhsachbaihat.getCaSi());
        Picasso.with(context).load(danhsachbaihat.getHinhAnh()).into(viewHolder.txtHinhAnh);

    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenBH,txtTenCasi;
        ImageView txtHinhAnh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenBH=itemView.findViewById(R.id.txtDongNhacTenBH);
            txtTenCasi=itemView.findViewById(R.id.txtDongNhacTenCasi);
            txtHinhAnh=itemView.findViewById(R.id.txtDongNhacHinhAnh);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",baiHatArrayList.get(getPosition()));
                    //  intent.putExtra("cakhuc",baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
