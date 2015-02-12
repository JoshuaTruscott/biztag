package com.jtruscott.biztag.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jtruscott.biztag.R;

/**
 * Created by Josh on 2/12/2015.
 */
public class RegisterFragment extends Fragment {
    public RegisterFragment(){
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        return v;

    }
}
