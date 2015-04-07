package com.jtruscott.biztag.fragments;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.jtruscott.biztag.R;
import com.jtruscott.biztag.library.DatabaseHandler;
import com.jtruscott.biztag.library.UserFunctions;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Josh on 2/12/2015.
 */
public class LoginFragment extends Fragment {
    Button btnLogin;
    Button btnLinkToRegister;
    EditText inputEmail;
    EditText inputPassword;
    TextView loginErrorMsg;

    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";


    public LoginFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //StrictMode fixes EditText focus issue
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        View v = inflater.inflate(R.layout.fragment_login, container, false);
        inputEmail = (EditText) v.findViewById(R.id.loginEmail);
        inputPassword = (EditText) v.findViewById(R.id.loginPassword);
        btnLogin = (Button) v.findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) v.findViewById(R.id.btnLinkToRegisterScreen);
        loginErrorMsg = (TextView) v.findViewById(R.id.login_error);
        UserFunctions uf = new UserFunctions();

        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Go to registration fragment
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_content, new RegisterFragment());
                ft.commit();

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                UserFunctions userFunction = new UserFunctions();
                JSONObject json = userFunction.loginUser(email, password);
                Log.i("JSON", json.toString());
                // check for login response
                try {
                    if (json.getString(KEY_SUCCESS) != null) {
                        loginErrorMsg.setText("");
                        String res = json.getString(KEY_SUCCESS);
                        if (Integer.parseInt(res) == 1) {
                            // user successfully logged in
                            // Store user details in SQLite Database
                            DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());
                            JSONObject json_user = json.getJSONObject("user");

                            // Clear all previous data in database
                            userFunction.logoutUser(getActivity().getApplicationContext());
                            db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));

                            // Go to profile fragment
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.fragment_content, new ProfileFragment());
                            ft.commit();
                        } else {
                            // Error in login
                            loginErrorMsg.setText("Incorrect username/password");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return v;
    }


}
