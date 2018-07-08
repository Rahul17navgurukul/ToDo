package com.example.rk.todo_dailog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Start_activity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    Button Login;
    private EditText mLoginEmail;
    private EditText mLoginPass;
   TextView Singup;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activity);
        mAuth = FirebaseAuth.getInstance();

//        welcom = findViewById(R.id.welcom_tv);
        Singup = findViewById(R.id.Sing_Up);
        mLoginEmail = findViewById(R.id.username);
        mLoginPass = findViewById(R.id.password);
        Login = findViewById(R.id.login_button);

        Singup.setOnClickListener(this);
        mProgressDialog = new ProgressDialog(this);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Taking in String and geting Edite value
                String email = mLoginEmail.getText().toString();
                String password = mLoginPass.getText().toString();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){
                    // Progress bar
                    mProgressDialog.setTitle("Logging");
                    mProgressDialog.setMessage("Please wait while we checking our account");
                    mProgressDialog.show();
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    // Making methord for Login user
                    loginUser(email,password);
                }
            }
        });

    }
    // Methord Login
    private void loginUser(String email, String password) {

        // Firebase

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //progress bar
                mProgressDialog.dismiss();

                if (task.isSuccessful()){
                    // Itent for Main activity
                    Intent main = new Intent(Start_activity.this,MainActivity.class);
                    startActivity(main);
                }
                else {
                    // Progress bar
                    mProgressDialog.hide();
                    Toast.makeText(Start_activity.this,"Cannot Long in Please check and Try Again later",Toast.LENGTH_LONG).show();

                }
            }
        });



//        myAnimation = AnimationUtils.loadAnimation(this, R.anim.blink);
//
////        welcom.setOnClickListener(new View.OnClickListener(){
////
////            @Override
////            public void onClick(View arg0) {
//                welcom.startAnimation(myAnimation);
////            }});


    }



    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("No", null).show();


    }


    @Override
    public void onClick(View v) {
        Intent register = new Intent(Start_activity.this,Register_activity.class);
        startActivity(register);
    }
}
