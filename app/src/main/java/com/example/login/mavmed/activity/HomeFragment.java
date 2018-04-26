package com.example.login.mavmed.activity;

/**
 * Created by Nhi K luong on 3/3/2018.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.login.mavmed.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeFragment extends Fragment {
    Button logout ;
    Button diagnose;
    Button medicalRecord, makeAppointment,userProfile;
    Button reminders;

    private FragmentDrawer drawerFragment; //Fragment
    // Creating FirebaseAuth.
    FirebaseAuth firebaseAuth ;
    // Creating FirebaseUser.
    FirebaseUser firebaseUser;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        diagnose = (Button) rootView.findViewById(R.id.diag_search);
        logout = (Button) rootView.findViewById(R.id.Logout);
        reminders = (Button) rootView.findViewById(R.id.reminders);
        medicalRecord = (Button) rootView.findViewById(R.id.MedicalRecord);
        makeAppointment = rootView.findViewById(R.id.MakeAppointment);
        userProfile = rootView.findViewById(R.id.User_profile);

        diagnose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;

                fragment = new DiagnosisSearchFragment();
                String title = getString(R.string.title_diagnosissearch);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.commit();

                // set the toolbar title

                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);

            }
        });
        medicalRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;

                fragment = new MedicalRecordFragment();
                String title = getString(R.string.title_medical_record);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.commit();

                // set the toolbar title

                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
            }
        });
        reminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;

                fragment = new Reminder();
                String title = getString(R.string.title_reminder);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.commit();

                // set the toolbar title

                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
            }
        });
        makeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MapsFragment();
                String title = getString(R.string.title_make_appointment);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.commit();

                // set the toolbar title


            }
        });
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new UserProfileFragment();
                String title = getString(R.string.title_user_profile);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                // set the toolbar title
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
            }
        });
        firebaseAuth =  FirebaseAuth.getInstance();
        // On activity start check whether there is user previously logged in or not.
        if(firebaseAuth.getCurrentUser() == null){

            // Finishing current Profile activity.
            getActivity().finish();

            // If user already not log in then Redirect to LoginActivity .
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);

            // Showing toast message.
            Toast.makeText(getActivity(), "Please Log in to continue", Toast.LENGTH_LONG).show();

        }
        // Adding firebaseAuth current user info into firebaseUser object.
        firebaseUser = firebaseAuth.getCurrentUser();
        // Adding click listener on logout button.
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MAYMAV","CHOOSE DRAWER");
                // Destroying login season.
                firebaseAuth.signOut();

                // Finishing current User Profile activity.
                getActivity().finish();

                // Redirect to Login Activity after click on logout button.
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

                // Showing toast message on logout.
                Toast.makeText(getActivity(), "Logged Out Successfully.", Toast.LENGTH_LONG).show();
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}