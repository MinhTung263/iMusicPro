package com.minhtung.imusicpro.Activity;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.minhtung.imusicpro.Adapter.Home_Pager_Adapter;
import com.minhtung.imusicpro.Fragment.Fragment_KhamPha;
import com.minhtung.imusicpro.Fragment.Fragment_Music_Offline;
import com.minhtung.imusicpro.Fragment.Fragment_Music_Online;
import com.minhtung.imusicpro.R;

public class MainActivity extends AppCompatActivity {
ViewPager viewPagerHome;
TabLayout tabLayoutHome;
Home_Pager_Adapter home_pager_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.colorAccent));
        }
        home_pager_adapter=new Home_Pager_Adapter(getSupportFragmentManager());
        home_pager_adapter.addFragment(new Fragment_Music_Offline());
        home_pager_adapter.addFragment(new Fragment_KhamPha());
        home_pager_adapter.addFragment(new Fragment_Music_Online());

        tabLayoutHome.setupWithViewPager(viewPagerHome);
        viewPagerHome.setAdapter(home_pager_adapter);
        tabLayoutHome.getTabAt(0).setIcon(R.drawable.icon_canhan);
        tabLayoutHome.getTabAt(1).setIcon(R.drawable.icon_khampha);
        tabLayoutHome.getTabAt(2).setIcon(R.drawable.icon_search);
        tabLayoutHome.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerHome.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0){
                    tabLayoutHome.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorMenu));
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.colorMenu));
                    }
                }else if (tab.getPosition()==1){
                    tabLayoutHome.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorPlaynhac));
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.colorPlaynhac));
                    }
                }
                else  {
                    tabLayoutHome.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPlaynhac));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPlaynhac));
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
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.colorMenu));
        }
        viewPagerHome.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutHome));

    }

    private void AnhXa() {
        viewPagerHome=findViewById(R.id.viewPagerHome);
        tabLayoutHome=findViewById(R.id.tabLayoutHome);
    }

}


