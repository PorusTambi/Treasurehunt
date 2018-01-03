package com.example.porus.treasurehunt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button login;
    private EditText email;
    private EditText password;
    private TextView textviewsignup;
    private ProgressDialog progressdialog ; //to show progress when user register as this app uses internet
   private FirebaseAuth firebaseAuth; //firebase authentication object
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.login);
        email = (EditText) findViewById(R.id.emailText);
        password = (EditText) findViewById(R.id.passwordText);
        textviewsignup = (TextView) findViewById(R.id.textviewsignup);
        progressdialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null)
        {  //means from this ip address some user has been logged in previously which has not logged out
            finish();
            startActivity(new Intent(getApplicationContext(),profile_activity.class));

        }


        login.setOnClickListener(this);
        textviewsignup.setOnClickListener(this);

    }

    private void loginuser() {
        String s1 = email.getText().toString();
        String s2 = password.getText().toString();

        if (s1.equals("")) {
            //strings are empty
            //pop up that to enter email and return from this function
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (s2.equals("")) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;

        }
        if (s2.length() < 6) {
            Toast.makeText(this, "password must be at least 6 characters", Toast.LENGTH_LONG).show();
            return;

        }

        progressdialog.setMessage("Authenticating....");  //set message for progress dialog
        progressdialog.show(); // to show it make visible
        firebaseAuth.signInWithEmailAndPassword(s1, s2).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    progressdialog.dismiss(); //if let say user type email pppp then progress wont stop and error so to stop it
                    //start profile activity
                    //before starting it we finish current activity
                    finish();
                    //since now we are in Oncomplete method and it doesnt have this as argument
                    startActivity(new Intent(getApplicationContext(),profile_activity.class));
                }
                else
                {
                    progressdialog.dismiss(); //if let say user type email pppp then progress wont stop and error so to stop it
                    Toast.makeText(MainActivity.this,"invalid details try again",Toast.LENGTH_LONG).show();

                }


            }
        });

    }


    @Override
    public void onClick(View v) {
        if (v == login) {

            loginuser();

        } else if (v == textviewsignup)

        {
            finish();  //to finish this activity close it
            startActivity(new Intent(this,register_activity.class)); // start register activity
            //it gets this as arg as its non static method of same class

        }

    }
}
