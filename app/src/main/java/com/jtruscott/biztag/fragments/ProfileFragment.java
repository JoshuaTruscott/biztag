package com.jtruscott.biztag.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jtruscott.biztag.R;
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
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
//            Button mButton = (Button) v.findViewById(R.id.registerLinkButton);
//            mButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.i("dBug","onClick() called :)");
//                }
//            });
            return v;

    }
//    public void logoutClicked(View v){
//        new UserFunctions().logoutUser(getActivity().getApplicationContext());
//        Intent login = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
//        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(login);
//    }
}