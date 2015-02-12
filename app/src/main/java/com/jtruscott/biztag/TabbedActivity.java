package com.jtruscott.biztag;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class TabbedActivity extends ActionBarActivity {
    // Declare Tab Variable
    android.support.v7.app.ActionBar.Tab Tab1;
    android.support.v7.app.ActionBar.Tab Tab2;
    Fragment fragmentTab1 = new ProfileFragment();
    Fragment fragmentTab2 = new ScanFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        // Hide Actionbar Icon
//        actionBar.setDisplayShowHomeEnabled(false);
//
//        // Hide Actionbar Title
//        actionBar.setDisplayShowTitleEnabled(false);

        // Create Actionbar Tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set Tab Icon and Titles
        Tab1 = actionBar.newTab().setText("My Profile");
        Tab2 = actionBar.newTab().setText("Scan");

        // Set Tab Listeners
        Tab1.setTabListener(new TabListener(fragmentTab1));
        Tab2.setTabListener(new TabListener(fragmentTab2));

        // Add tabs to actionbar
        actionBar.addTab(Tab1);
        actionBar.addTab(Tab2);
    }
}