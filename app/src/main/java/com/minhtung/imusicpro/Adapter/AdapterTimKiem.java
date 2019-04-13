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

public class AdapterTimKiem  extends RecyclerView.Adapter<AdapterTimKiem.ViewHolder>{
    Context context;
    ArrayList<Danhsachbaihat> danhsachbaihatArrayList;

    public AdapterTimKiem(Context context, ArrayList<Danhsachbaihat> danhsachbaihatArrayList) {
        this.context = context;
        this.danhsachbaihatArrayList = danhsachbaihatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_tim_kiem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Danhsachbaihat danhsachbaihat=danhsachbaihatArrayList.get(position);
        viewHolder.BaiHatTiKiem.setText(danhsachbaihat.getTenBH());
        viewHolder.CaSiTimKiem.setText(danhsachbaihat.getCaSi());
        Picasso.with(context).load(danhsachbaihat.getHinhAnh()).into(viewHolder.imageViewTimKiem);
    }

    @Override
    public int getItemCount() {
        return danhsachbaihatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView BaiHatTiKiem,CaSiTimKiem;
        ImageView imageViewTimKiem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BaiHatTiKiem=itemView.findViewById(R.id.tvBaiHatTimKiem);
            CaSiTimKiem=itemView.findViewById(R.id.tvCasiTimKiem);
            imageViewTimKiem=itemView.findViewById(R.id.imgTimKiem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",danhsachbaihatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
