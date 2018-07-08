package com.example.rk.todo_dailog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class Register_activity extends AppCompatActivity {

    private EditText mdisplayName;
    private EditText mEmail;
    private EditText mPassword;
    private Button mregBtn;
    private FirebaseAuth mAuth;
    private ProgressDialog mRegProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);
        mAuth = FirebaseAuth.getInstance();

        mdisplayName = findViewById(R.id.reg_name);
        mEmail = findViewById(R.id.reg_email);
        mPassword = findViewById(R.id.reg_password);
        mregBtn = findViewById(R.id.Reg_btn);


            /// here i am setting animation on text creat new account ////
//        myAnimation = AnimationUtils.loadAnimation(this, R.anim.blink);
//
//        creatnewaccount.startAnimation(myAnimation);
//


        mRegProgress = new ProgressDialog(this);


        mregBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String display_name = mdisplayName.getText().toString();
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if (!TextUtils.isEmpty(display_name)|| !TextUtils.isEmpty(email)|| !TextUtils.isEmpty(password)) {

                    mRegProgress.setTitle("Registring User");
                    mRegProgress.setMessage("Please Wait");
                    mRegProgress.show();
                    mRegProgress.setCanceledOnTouchOutside(false);

                    register_user(display_name, email, password);
                }
            }
        });
    }

        private void register_user(String display_name, String email, String password) {

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){

                        mRegProgress.dismiss();
                        Intent main = new Intent(Register_activity.this,MainActivity.class);
                        startActivity(main);
                        finish();

                    }

                    else {
                        mRegProgress.hide();
                        Toast.makeText(Register_activity.this,"Cannot sing in Please check and Try again later  ",Toast.LENGTH_LONG).show();
                    }

                }
            });

        }
}
