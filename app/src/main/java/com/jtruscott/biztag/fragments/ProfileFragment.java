package com.jtruscott.biztag.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jtruscott.biztag.R;
import com.jtruscott.biztag.library.UserFunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private ListView mListView;
    private Button btnLogout;
    private UserFunctions userFunctions = new UserFunctions();
    private List<Integer> listItems = new ArrayList<>(Arrays.asList(R.string.full_name,R.string.contact_number,R.string.email,R.drawable.facebookicon,R.drawable.twittericon,R.drawable.instagramicon));
    private LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    private View rowView;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        layoutParams.topMargin = 10;

        mListView = (ListView) v.findViewById(R.id.profileListView);
        mListView.setAdapter(new myListViewAdapter());

        btnLogout = (Button) v.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                userFunctions.logoutUser(getActivity().getApplicationContext());
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_content, new LoginFragment());
                ft.commit();
                // Closing dashboard screen
            }
        });

        return v;

    }
    private class myListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object getItem(int position) {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            rowView = getActivity().getLayoutInflater().inflate(R.layout.row,null);
            int currentResId = listItems.get(position);
            if (getResources().getResourceTypeName(currentResId).equals("drawable"))  drawImageRow(currentResId);
            else drawStringRow(currentResId);
            return rowView;
        }
    }
    private void drawImageRow(int id) {
        LinearLayout linearLayout = (LinearLayout) rowView.findViewById(R.id.rowLayout);
        ImageView imageView = new ImageView(getActivity().getApplicationContext());
        imageView.setImageResource(id);
        imageView.setLayoutParams(layoutParams);
        linearLayout.addView(imageView,0);
    }
    private void drawStringRow(int id){
        LinearLayout linearLayout = (LinearLayout) rowView.findViewById(R.id.rowLayout);
        TextView textView = new TextView(getActivity().getApplicationContext());
        textView.setText(getString(id));
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setTextSize(20);
        textView.setLayoutParams(layoutParams);
        linearLayout.addView(textView,0);


    }
}