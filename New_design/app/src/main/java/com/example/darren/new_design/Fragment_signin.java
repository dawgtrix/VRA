package com.example.darren.new_design;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_signin extends Fragment {

    TextView in_signin;
    Button in_Login;
    FragmentManager fm;
    EditText in_name, in_pass;
    Database_Manager db;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View InputFragmentView = inflater.inflate(R.layout.signin, container, false);

        db = new Database_Manager(getActivity());

        in_signin = (TextView) InputFragmentView.findViewById(R.id.in_signup);
        in_Login = (Button) InputFragmentView.findViewById(R.id.in_Login);
        in_name = (EditText) InputFragmentView.findViewById(R.id.in_name);
        in_pass = (EditText) InputFragmentView.findViewById(R.id.in_pass);

        fm = getFragmentManager();
        in_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left)
                        .replace(R.id.content_login, new Fragment_signup())
                        .addToBackStack(null)
                        .commit();
            }
        });

        in_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.open();

                // Checking if the username and password is correct - if so toast message and go to next activity
                if (db.getUser(in_name.getText().toString(),in_pass.getText().toString())) {

                    Toast.makeText(getActivity().getApplicationContext(),
                            "Successful Login", Toast.LENGTH_SHORT).show();

                    db.close();

                    Intent intent = new Intent(getActivity(), Activity_container.class);
                    startActivity(intent);

                }
                // otherwise - toast sayin error !
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "No matching username and password found",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        return InputFragmentView;
    }
}