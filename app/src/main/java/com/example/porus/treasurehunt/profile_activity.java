package com.example.porus.treasurehunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile_activity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private Button logout;
    private EditText firstname;
    private EditText lastname;
    private Button gameplay;
//    private Firebase f;
  private  Button getdetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);
        firebaseAuth = FirebaseAuth.getInstance();
        firstname = (EditText) findViewById(R.id.firstNameText);
        lastname = (EditText) findViewById(R.id.lastNameText);
        gameplay =(Button) findViewById(R.id.gameplay);
      //  f=new Firebase("https://treasurehunt-457a1.firebaseio.com/");


        logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(this);
        gameplay.setOnClickListener(this);
        FirebaseUser currentuser=firebaseAuth.getCurrentUser();//know current user logged in as we have to log him out


    }


    @Override
    public void onClick(View v) {
        if (v == logout) {

        firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

        if(v==getdetails)
        {

             //will implement later

        }

        if(v==gameplay)
        {
            finish();
            startActivity(new Intent(this,gameplay_activity.class));

        }

    }
}
