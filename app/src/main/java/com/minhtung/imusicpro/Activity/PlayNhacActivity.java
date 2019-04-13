package com.minhtung.imusicpro.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.minhtung.imusicpro.Adapter.PhatNhac_Pager_Adapter;
import com.minhtung.imusicpro.Fragment.Fragment_Dia_Nhac;
import com.minhtung.imusicpro.Fragment.Fragment_Loi_Bai_Hat;
import com.minhtung.imusicpro.Fragment.Fragment_Play_Danh_Sach_Bai_Hat;
import com.minhtung.imusicpro.Model.Danhsachbaihat;
import com.minhtung.imusicpro.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class PlayNhacActivity extends AppCompatActivity {
    TextView txtTimeSong,txtTimeTotal;
    TabLayout tablayoutplaynhac;
    SeekBar sbBH;
    Toolbar toolbar;
    ActionBar actionBar;
    ViewPager viewPagerplaynhac;
    ImageButton btnPre,btnPlay,btnNext;
    MediaPlayer player;
    int position=0;
    boolean next=false;
    public  static ArrayList<Danhsachbaihat> mangbaihat=new ArrayList<>();
    public static PhatNhac_Pager_Adapter adapternhac;
    Fragment_Dia_Nhac fragment_diaNhac;
    Fragment_Play_Danh_Sach_Bai_Hat fragment_play_danh_sach_bai_hat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDaTaIntent();
        AnhXa();
        setSupportActionBar(toolbar);
        actionBar=getSupportActionBar();
        actionBar.setTitle(null);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                player.stop();
                mangbaihat.clear();
            }
        });
        toolbar.setSubtitleTextColor(Color.WHITE);
        eventClick();

    }

    private void eventClick() {
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1)!=null){
                    if (mangbaihat.size()>0){
                        fragment_diaNhac.PhatNhac(
                                mangbaihat.get(position).getHinhAnh(),
                                mangbaihat.get(position).getTenBH(),
                                mangbaihat.get(position).getCaSi());
                        handler.removeCallbacks(this);
                    }else{
                        handler.postDelayed(this,300);
                    }

                }
            }
        },500);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.isPlaying()){
                    player.pause();
                    btnPlay.setImageResource(R.drawable.playsong);
                }else {
                    player.start();
                    btnPlay.setImageResource(R.drawable.pause);

                }
            }
        });
        sbBH.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.seekTo(seekBar.getProgress());
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size()>0){
                    if (player.isPlaying()||player!=null){
                        player.stop();
                        player.release();
                        player=null;
                    }
                    if (position<mangbaihat.size()){
                        btnPlay.setImageResource(R.drawable.pause);
                        position++;
                        if (position>(mangbaihat.size()-1)){
                            position=0;
                        }
                        new phatNhac().execute(mangbaihat.get(position).getLinkBH());
                        fragment_diaNhac.PhatNhac(
                                mangbaihat.get(position).getHinhAnh(),
                                mangbaihat.get(position).getTenBH(),
                                mangbaihat.get(position).getCaSi());
                        updataThoiGian();
                    }
                }
                btnPre.setClickable(false);
                btnNext.setClickable(false);
                Handler handler1=new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnPre.setClickable(true);
                        btnNext.setClickable(true);
                    }
                },300);
            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size()>0){
                    if (player.isPlaying()||player!=null){
                        player.stop();
                        player.release();
                        player=null;
                    }if (position<mangbaihat.size()){
                        btnPlay.setImageResource(R.drawable.pause);
                        position--;
                        if (position<0){
                            position=mangbaihat.size()-1;
                        }
                        new phatNhac().execute(mangbaihat.get(position).getLinkBH());
                        fragment_diaNhac.PhatNhac(
                                mangbaihat.get(position).getHinhAnh(),
                                mangbaihat.get(position).getTenBH(),
                                mangbaihat.get(position).getCaSi());
                        updataThoiGian();
                    }
                }
                btnPre.setClickable(false);
                btnNext.setClickable(false);
                Handler handler1=new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnPre.setClickable(true);
                        btnNext.setClickable(true);
                    }
                },300);
            }
        });
    }

    private void GetDaTaIntent() {
        Intent intent=getIntent();
        mangbaihat.clear();
        if (intent!=null){
            if (intent.hasExtra("cakhuc")){
                Danhsachbaihat danhsachbaihat=intent.getParcelableExtra("cakhuc");
                mangbaihat.add(danhsachbaihat);
            }
            if (intent.hasExtra("cacbaihat")){
                ArrayList<Danhsachbaihat> baiHatArrayList=intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat=baiHatArrayList;
            }
        }
    }
    private void AnhXa() {
        toolbar=(Toolbar)findViewById(R.id.myToolBar);
        txtTimeSong=findViewById(R.id.txtTimeSong);
        txtTimeTotal=findViewById(R.id.txtTimeTotal);
        sbBH=findViewById(R.id.sbSong);
        btnPre=findViewById(R.id.btnPre);
        btnPlay=findViewById(R.id.btnPlay);
        btnNext=findViewById(R.id.btnNext);
        viewPagerplaynhac=findViewById(R.id.viewPagerplaynhac);
        tablayoutplaynhac=findViewById(R.id.tablayoutplaynhac);
        fragment_play_danh_sach_bai_hat=new Fragment_Play_Danh_Sach_Bai_Hat();
        fragment_diaNhac=new Fragment_Dia_Nhac();
        adapternhac=new PhatNhac_Pager_Adapter(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_play_danh_sach_bai_hat);
        adapternhac.AddFragment(fragment_diaNhac);
        adapternhac.AddFragment(new Fragment_Loi_Bai_Hat());
        tablayoutplaynhac.setupWithViewPager(viewPagerplaynhac);
        tablayoutplaynhac.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerplaynhac.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0){
                    toolbar.setBackgroundColor(ContextCompat.getColor(PlayNhacActivity.this,R.color.colorMenu));
                    tablayoutplaynhac.setBackgroundColor(ContextCompat.getColor(PlayNhacActivity.this,R.color.colorMenu));
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        getWindow().setStatusBarColor(ContextCompat.getColor(PlayNhacActivity.this,R.color.colorMenu));
                    }
                }else if (tab.getPosition()==1){
                    toolbar.setBackgroundColor(ContextCompat.getColor(PlayNhacActivity.this,R.color.colorDanhsachnhac));
                    tablayoutplaynhac.setBackgroundColor(ContextCompat.getColor(PlayNhacActivity.this,R.color.colorDanhsachnhac));
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        getWindow().setStatusBarColor(ContextCompat.getColor(PlayNhacActivity.this,R.color.colorDanhsachnhac));
                    }
                }else{
                    toolbar.setBackgroundColor(ContextCompat.getColor(PlayNhacActivity.this,R.color.colorMenu));
                    tablayoutplaynhac.setBackgroundColor(ContextCompat.getColor(PlayNhacActivity.this,R.color.colorMenu));
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        getWindow().setStatusBarColor(ContextCompat.getColor(PlayNhacActivity.this,R.color.colorMenu));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPagerplaynhac.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayoutplaynhac));

        viewPagerplaynhac.setAdapter(adapternhac);
        CircleIndicator circleIndicator=(CircleIndicator) findViewById(R.id.ci_phatnhac);
        circleIndicator.setViewPager(viewPagerplaynhac);
        fragment_diaNhac= (Fragment_Dia_Nhac) adapternhac.getItem(1);

        if (mangbaihat.size()>0){
            new phatNhac().execute(mangbaihat.get(0).getLinkBH());
            btnPlay.setImageResource(R.drawable.pause);
        }

    }
    class phatNhac extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try{
                player=new MediaPlayer();
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        player.stop();
                        player.reset();
                    }
                });
                player.setDataSource(baihat);
                player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.start();
            TimeSong();
            updataThoiGian();

        }
    }
    private  void TimeSong(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(simpleDateFormat.format(player.getDuration()));
        sbBH.setMax(player.getDuration());
    }
    private void updataThoiGian(){
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (player!=null){
                    sbBH.setProgress(player.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(player.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next=true;
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        final Handler handler1=new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next==true){
                    if (position<mangbaihat.size()){
                        btnPlay.setImageResource(R.drawable.pause);
                        position++;
                        if (position>(mangbaihat.size()-1)){
                            position=0;
                        }
                        new phatNhac().execute(mangbaihat.get(position).getLinkBH());
                        fragment_diaNhac.PhatNhac(
                                mangbaihat.get(position).getHinhAnh(),
                                mangbaihat.get(position).getTenBH(),
                                mangbaihat.get(position).getCaSi());
                    }
                btnPre.setClickable(false);
                btnNext.setClickable(false);
                Handler handler1=new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnPre.setClickable(true);
                        btnNext.setClickable(true);
                    }
                },300);
                next=false;
                handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this,300);
                }
            }
        },300);
    }
}
