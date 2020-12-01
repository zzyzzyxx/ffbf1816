package com.example.ffbfv14;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class LoginAndRegistration extends AppCompatActivity implements View.OnClickListener {
    private TextView register,txt_forgotPass;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_registration);

        register = findViewById(R.id.txt_register);
        register.setOnClickListener(this);

        signIn = findViewById(R.id.reg_btn2);
        signIn.setOnClickListener(this);

        editTextEmail = findViewById(R.id.txt_email2);

        editTextPassword = findViewById(R.id.txt_passw2);

        txt_forgotPass = findViewById(R.id.txt_forgot);
        txt_forgotPass.setOnClickListener(this);

        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.txt_register:
                startActivity(new Intent(this,RegistrationAndLogin.class));
                break;
            case R.id.reg_btn2:
                userLogin();
                break;
            case R.id.txt_forgot:
                startActivity(new Intent(this, ForgotPassword.class));
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter valid email!");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editTextPassword.setError("Min password length is 6 characters.");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //redirect to User profile
                    startActivity( new Intent(LoginAndRegistration.this, Choice.class));
                }else{
                    Toast.makeText(LoginAndRegistration.this, "Failed to login!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}