package com.example.fitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword;
    private Button signupButton;
    private TextView loginRedirectText;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);


        mProgressDialog = new ProgressDialog(SignUpActivity.this);
        mProgressDialog.setTitle("Please Wait");
        mProgressDialog.setMessage("register");

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = signupEmail.getText().toString().trim();
                final String pass = signupPassword.getText().toString().trim();

                try {
                    registerUser(email, pass);
                } catch (Exception e) {

                    Toast.makeText(SignUpActivity.this, "exception is", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

    private void registerUser(String email, String pass) {
        mProgressDialog.show();

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "SignUp Successfully", Toast.LENGTH_SHORT).show();
                    mProgressDialog.dismiss();
                    Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                }
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUpActivity.this, "error", Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }
        });
    }
}