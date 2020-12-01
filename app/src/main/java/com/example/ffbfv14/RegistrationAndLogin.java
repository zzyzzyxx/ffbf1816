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
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationAndLogin extends AppCompatActivity implements View.OnClickListener {

    private TextView banner;
    private Button registerUser;
    private EditText editTextFirstName, editTextSurname, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_and_login);

        mAuth = FirebaseAuth.getInstance();

        banner = findViewById(R.id.txt_logo);
        banner.setOnClickListener(this);
        registerUser = findViewById(R.id.reg_btn);
        registerUser.setOnClickListener(this);
        editTextFirstName = findViewById(R.id.txt_fname);
        editTextSurname = findViewById(R.id.txt_surname);
        editTextEmail = findViewById(R.id.txt_email);
        editTextPassword = findViewById(R.id.txt_passw);

        progressBar = findViewById(R.id.progressBar2);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            //by clicking logo user goes back to login screen
            case R.id.txt_logo:
                startActivity(new Intent(this, LoginAndRegistration.class));
                break;
            case R.id.reg_btn:
                // by clicking method below is evoked
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String surname = editTextSurname.getText().toString().trim();

        // validations of user input:
        if(firstName.isEmpty()){
            editTextFirstName.setError("First name is required!");
            editTextFirstName.requestFocus();
            return;
        }
        if(surname.isEmpty()){
            editTextSurname.setError("Surname is required!");
            editTextSurname.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        //we need to vaildate the email address
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email address!");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        //Firebase does not accept passwords shorter than 6 characters:
        if(password.length() < 6){
            editTextPassword.setError("Password is too short!");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String type = "Standard User";
                            User user = new User(firstName, surname, email, type );
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegistrationAndLogin.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                        //redirect to Login activity
                                    }else{
                                        Toast.makeText(RegistrationAndLogin.this, "Registration failed. Try again!", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegistrationAndLogin.this, "Registration failed. Try again!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}