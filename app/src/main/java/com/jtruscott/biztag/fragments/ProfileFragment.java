package com.jtruscott.biztag.fragments;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jtruscott.biztag.R;
import com.jtruscott.biztag.library.DatabaseHandler;
import com.jtruscott.biztag.library.UserFunctions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.jtruscott.biztag.R.id.rowLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private ListView mListView;
    private List<String> profileList;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        mListView = (ListView) v.findViewById(R.id.profileListView);
//            });
        profileList = new ArrayList<String>();
        profileList.add("My first list item");
        profileList.add("More items");
        profileList.add("More items");
        profileList.add("More items");
        profileList.add("More items");
        profileList.add("More items");
        profileList.add("More items");
        profileList.add("More items");
        profileList.add("More items");
        profileList.add("More items");
        profileList.add("More items");
        profileList.add("More items");
        profileList.add("More items");
        profileList.add("More items");
        profileList.add("More items");
        profileList.add("More items");
        profileList.add("More items");
        profileList.add("More items");
        profileList.add("More items");

        mListView.setAdapter(new ProfileAdapter());

        DatabaseHandler dbh = new DatabaseHandler(getActivity().getApplicationContext());
        HashMap<String,String> userInfo = dbh.getUserDetails();
        Log.i("DBH",userInfo.toString());
            return v;

    }
    private class ProfileAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return profileList.size();
        }

        @Override
        public Object getItem(int position) {
            return profileList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View rowView = getActivity().getLayoutInflater().inflate(R.layout.row,null);
            LinearLayout linearLayout = (LinearLayout) rowView.findViewById(R.id.rowLayout);
            ImageView imageView = new ImageView(getActivity().getApplicationContext());
            imageView.setImageResource(R.drawable.facebookicon);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
            imageView.setLayoutParams(layoutParams);
            linearLayout.addView(imageView);
            return linearLayout;
        }
    }

    private View drawRow(View v, int id){


    }
}