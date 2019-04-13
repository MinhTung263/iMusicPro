package com.minhtung.imusicpro.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PhatNhac_Pager_Adapter extends FragmentPagerAdapter {
    public final ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    public PhatNhac_Pager_Adapter(FragmentManager fm) {
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

    public void AddFragment( Fragment fragment){
        fragmentArrayList.add(fragment);
    }
}
