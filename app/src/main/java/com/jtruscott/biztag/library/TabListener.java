package com.jtruscott.biztag.library;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import com.jtruscott.biztag.R;

public class TabListener implements ActionBar.TabListener {

    Fragment fragment;

    public TabListener(Fragment fragment) {
        // TODO Auto-generated constructor stub
        this.fragment = fragment;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        ft.replace(R.id.fragment_content, fragment);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        ft.remove(fragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub

    }
}