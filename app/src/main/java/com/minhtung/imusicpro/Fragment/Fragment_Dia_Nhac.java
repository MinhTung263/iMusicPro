package com.minhtung.imusicpro.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.minhtung.imusicpro.Model.Danhsachbaihat;
import com.minhtung.imusicpro.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Dia_Nhac extends Fragment  {
    View view;
    LinearLayout lnlayouttop;
    ImageView imgHinhAnh;
    TextView txtTenBH,txtCaSi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_dianhac,container,false);
        imgHinhAnh=view.findViewById(R.id.imgHinhAnh);
        txtTenBH=view.findViewById(R.id.tvTenBH);
        txtCaSi=view.findViewById(R.id.tvCaSi);
        lnlayouttop=view.findViewById(R.id.lnlayouttop);
        return view;
    }
    public void PhatNhac(String hinhanh,String tenbh,String casi){
        Picasso.with(getContext()).load(hinhanh).into(imgHinhAnh);
        txtTenBH.setText(tenbh);
        txtCaSi.setText(casi);
        Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.text_animation);
        txtTenBH.startAnimation(animation);

    }

}
