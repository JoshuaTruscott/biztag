package com.jtruscott.biztag.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jtruscott.biztag.R;
import com.jtruscott.biztag.library.DatabaseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.view.View.OnFocusChangeListener;
import static com.jtruscott.biztag.R.id.editText;
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

        mListView.setAdapter(new ProfileAdapter());

        DatabaseHandler dbh = new DatabaseHandler(getActivity().getApplicationContext());
        HashMap<String,String> userInfo = dbh.getUserDetails();
        Log.i("DBH",userInfo.toString());
            return v;

    }
    private class ProfileAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 6;
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

            switch (position){
                case 0:
                    drawStringRow(rowView,"Full Name");
                    break;
                case 1:
                    drawStringRow(rowView,"Contact Number");
                    break;
                case 2:
                    drawStringRow(rowView,"Email Address");
                    break;
                case 3:
                    drawImageRow(rowView,R.drawable.facebookicon);
                    break;
                case 4:
                    drawImageRow(rowView,R.drawable.twittericon);
                    break;
                case 5:
                    drawImageRow(rowView,R.drawable.instagramicon);
                    break;

            }
            return rowView;
        }
    }

    private void drawImageRow(View v, int id) {
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.rowLayout);
        ImageView imageView = new ImageView(getActivity().getApplicationContext());
        imageView.setImageResource(id);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        layoutParams.topMargin = 10;
        imageView.setLayoutParams(layoutParams);
        linearLayout.addView(imageView,0);
    }
    private void drawStringRow(View v, String s){
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.rowLayout);
        TextView textView = new TextView(getActivity().getApplicationContext());
        textView.setText(s);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setTextSize(20);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        layoutParams.topMargin = 10;
        textView.setLayoutParams(layoutParams);
        linearLayout.addView(textView,0);
    }
}