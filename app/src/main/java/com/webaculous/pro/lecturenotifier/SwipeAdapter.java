package com.webaculous.pro.lecturenotifier;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Farhan on 26-04-2017.
 */

public class SwipeAdapter extends FragmentStatePagerAdapter{
    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment  fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("count",i+1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
    String [] daysArray = new String []{"Monday","Tuesday","Wednesday","Thursday","Friday"};
    @Override
    public CharSequence getPageTitle(int i) {
        return daysArray[i];
    }
}
