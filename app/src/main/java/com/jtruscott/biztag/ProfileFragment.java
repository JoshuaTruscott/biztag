package com.jtruscott.biztag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jtruscott.biztag.library.UserFunctions;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (new UserFunctions().isUserLoggedIn(getActivity().getApplicationContext())) {
            return inflater.inflate(R.layout.fragmenttab1, container, false);
        } else {
            // user is not logged in show login screen
            return inflater.inflate(R.layout.fragmenttab3, container, false);
        }

    }
//    public void logoutClicked(View v){
//        new UserFunctions().logoutUser(getActivity().getApplicationContext());
//        Intent login = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
//        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(login);
//    }
}