package com.minhtung.imusicpro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minhtung.imusicpro.Activity.PlayNhacActivity;
import com.minhtung.imusicpro.Model.Danhsachbaihat;
import com.minhtung.imusicpro.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterDanhsachnhactuychon extends RecyclerView.Adapter<AdapterDanhsachnhactuychon.viewHolder>  {
    Context context;
    ArrayList<Danhsachbaihat> baiHatArrayList;

    public AdapterDanhsachnhactuychon(Context context, ArrayList<Danhsachbaihat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override

    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_danh_sach_bai_hat_tuy_chon,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int position) {
        Danhsachbaihat danhsachbaihat=baiHatArrayList.get(position);
        viewHolder.txtTenBH.setText(danhsachbaihat.getTenBH());
        viewHolder.txtCaSi.setText(danhsachbaihat.getCaSi());
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txtTenBH,txtCaSi;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenBH=itemView.findViewById(R.id.tvTenBHTuyChon);
            txtCaSi=itemView.findViewById(R.id.tvCaSiTuyChon);
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
