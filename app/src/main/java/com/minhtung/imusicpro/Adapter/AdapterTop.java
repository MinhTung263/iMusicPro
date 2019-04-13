package com.minhtung.imusicpro.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.minhtung.imusicpro.Model.TheLoai;
import com.minhtung.imusicpro.Model.Top100;
import com.minhtung.imusicpro.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterTop extends RecyclerView.Adapter<AdapterTop.ViewHolder> {
    Context context;
    ArrayList<Top100> top100ArrayList;

    public AdapterTop(Context context, ArrayList<Top100> top100ArrayList) {
        this.context = context;
        this.top100ArrayList = top100ArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.dong_top100,parent,false);
        ViewHolder holder=new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Top100 top100=top100ArrayList.get(position);
        viewHolder.txtTenTop.setText(top100.getTenTop());
        Picasso.with(context).load(top100.getHinhAnhTop()).into(viewHolder.txtHinhAnhTop);
    }

    @Override
    public int getItemCount() {
        return top100ArrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenTop;
        ImageView txtHinhAnhTop;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenTop=itemView.findViewById(R.id.tvTop);
            txtHinhAnhTop=itemView.findViewById(R.id.AnhTop);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, top100ArrayList.get(0).getIdTop(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
