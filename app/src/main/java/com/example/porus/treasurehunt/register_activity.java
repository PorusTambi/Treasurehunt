package com.example.porus.treasurehunt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register_activity extends AppCompatActivity implements View.OnClickListener {

    private Button register;
    private EditText email;
    private EditText password;
    private TextView textviewsignin;
    private ProgressDialog progressdialog ; //to show progress when user register as this app uses internet
    private FirebaseAuth firebaseAuth; //firebase authentication object


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);

        register = (Button) findViewById(R.id.createAccount);
        email = (EditText) findViewById(R.id.emailText);
        password = (EditText) findViewById(R.id.passwordText);
        textviewsignin = (TextView) findViewById(R.id.textviewsignin);
        progressdialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();



        register.setOnClickListener(this);
        textviewsignin.setOnClickListener(this);

    }

    private void registeruser()
    {
        String s1=email.getText().toString();
        String s2=password.getText().toString();

        if(s1.equals(""))
        {
            //strings are empty
            //pop up that to enter email and return from this function
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if(s2.equals(""))
        {
            Toast.makeText(this,"Please enter password", Toast.LENGTH_LONG).show();
            return;

        }
        if(s2.length()<6)
        {
            Toast.makeText(this,"password must be at least 6 characters", Toast.LENGTH_LONG).show();
            return;

        }

        progressdialog.setMessage("Registering User....");  //set message for progress dialog
        progressdialog.show(); // to show it make visible
        firebaseAuth.createUserWithEmailAndPassword(s1,s2).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {

                // task is argument here
                if(task.isSuccessful())
                {
                    progressdialog.dismiss(); //if let say user type email pppp then progress wont stop and error so to stop it
                    Toast.makeText(register_activity.this,"Account created Successfully",Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),profile_activity.class));


                }
                else
                {
                    progressdialog.dismiss(); //if let say user type email pppp then progress wont stop and error so to stop it
                    Toast.makeText(register_activity.this,"Registration Error invalid details",Toast.LENGTH_LONG).show();

                }

            }
        }); // to create a user in firebase auth onComplete method executes when registration is completed

    }

    @Override
    public void onClick(View v) {
        if (v == register) {

            registeruser();



        } else if (v == textviewsignin)

        {      finish();
               startActivity(new Intent(this,MainActivity.class));
        }

    }
}

