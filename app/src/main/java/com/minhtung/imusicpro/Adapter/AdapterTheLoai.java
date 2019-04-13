package com.minhtung.imusicpro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.minhtung.imusicpro.Activity.DanhSachBaiHatActivity;
import com.minhtung.imusicpro.Activity.DanhsachbaihattuychonActivity;
import com.minhtung.imusicpro.Activity.PlayNhacActivity;
import com.minhtung.imusicpro.Model.TheLoai;
import com.minhtung.imusicpro.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterTheLoai extends RecyclerView.Adapter<AdapterTheLoai.ViewHolder> {
   Context context;
   ArrayList<TheLoai> theLoaiArrayList;

    public AdapterTheLoai(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.dong_the_loai,parent,false);
        ViewHolder holder=new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
    TheLoai theLoai=theLoaiArrayList.get(position);
    viewHolder.txtTenTheLoai.setText(theLoai.getTenTheLoai());
    Picasso.with(context).load(theLoai.getHinhTheLoai()).into(viewHolder.txtHinhAnhTheLoai);
    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenTheLoai;
        ImageView txtHinhAnhTheLoai;
       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           txtTenTheLoai=itemView.findViewById(R.id.tvTheLoai);
           txtHinhAnhTheLoai=itemView.findViewById(R.id.AnhTheLoai);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent =new Intent(context, DanhsachbaihattuychonActivity.class);
                   intent.putExtra("itemtheloai",theLoaiArrayList.get(getAdapterPosition()));
                   context.startActivity(intent);
               }
           });
       }
   }
}
