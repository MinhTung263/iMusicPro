package com.minhtung.imusicpro.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.minhtung.imusicpro.R;

import java.util.ArrayList;

public class Home_Pager_Adapter extends FragmentPagerAdapter {
    public final ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    public Home_Pager_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
    public void addFragment( Fragment fragment){
        fragmentArrayList.add(fragment);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title="Cá nhân";
                break;
            case 1:
                title="Khám phá";
                break;
            case 2:
                title="Tìm kiếm";
                break;

        }
        return title;
    }
}
