package com.minhtung.imusicpro.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minhtung.imusicpro.Model.Danhsachbaihat;
import com.minhtung.imusicpro.R;

import java.util.ArrayList;

public class PlaynhacAdapter extends RecyclerView.Adapter<PlaynhacAdapter.ViewHoler> {
    Context context;
    ArrayList<Danhsachbaihat> mangbaihat;

    public PlaynhacAdapter(Context context, ArrayList<Danhsachbaihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_play_bai_hat,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int position) {
        Danhsachbaihat danhsachbaihat=mangbaihat.get(position);
        viewHoler.tvplaynhacsobaihat.setText(position+1+"");
        viewHoler.tvplaynhactenbaihat.setText(danhsachbaihat.getTenBH());
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class  ViewHoler extends RecyclerView.ViewHolder {
        TextView tvplaynhacsobaihat,tvplaynhactenbaihat;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvplaynhacsobaihat=itemView.findViewById(R.id.tvplaynhacSobaihat);
            tvplaynhactenbaihat=itemView.findViewById(R.id.tvplaynhactenbaihat);
        }
    }
}
