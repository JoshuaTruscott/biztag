package com.jtruscott.biztag.fragments;

import android.content.Intent;
import android.os.Bundle;
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
public class RegisterFragment extends Fragment {

    Button btnRegister;
    Button btnLinkToLogin;
    EditText inputFullName;
    EditText inputEmail;
    EditText inputPassword;
    TextView registerErrorMsg;

    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";

    public RegisterFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        inputFullName = (EditText) v.findViewById(R.id.registerName);
        inputEmail = (EditText) v.findViewById(R.id.registerEmail);
        inputPassword = (EditText) v.findViewById(R.id.registerPassword);
        btnRegister = (Button) v.findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) v.findViewById(R.id.btnLinkToLoginScreen);
        registerErrorMsg = (TextView) v.findViewById(R.id.register_error);

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = inputFullName.getText().toString();
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                UserFunctions userFunction = new UserFunctions();
                JSONObject json = userFunction.registerUser(name, email, password);

                // check for login response
                try {
                    if (json.getString(KEY_SUCCESS) != null) {
                        registerErrorMsg.setText("");
                        String response = json.getString(KEY_SUCCESS);
                        if (Integer.parseInt(response) == 1) {
                            // user successfully registred
                            // Store user details in SQLite Database
                            DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());
                            JSONObject json_user = json.getJSONObject("user");

                            // Clear all previous data in database
                            userFunction.logoutUser(getActivity().getApplicationContext());
                            db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));
                            // Launch Dashboard Screen
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.fragment_content, new ProfileFragment());
                            ft.commit();
                        } else {
                            // Error in registration
                            registerErrorMsg.setText("Error occured in registration");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_content, new LoginFragment());
                ft.commit();
            }
        });
        return v;
    }
}