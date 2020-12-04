package com.example.ffbfv14;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.UUID;

public class    ProfileActivity extends AppCompatActivity {
    private Button logout, back;
    private ImageView profilepic;
    private FirebaseUser user;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private DatabaseReference reference;
    public Uri imageUri;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profilepic = findViewById(R.id.iv_profilepic);

        // instantiate our storage object
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();

            }
        });
        back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, Choice.class));
            }
        });

        logout = findViewById(R.id.signOut);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, LoginAndRegistration.class));
            }
        });
       user =  FirebaseAuth.getInstance().getCurrentUser();
       reference = FirebaseDatabase.getInstance().getReference("Users");
       userID = user.getUid();

       //objects of type final  accessed within class
       final TextView emailAddressdisplay = findViewById(R.id.emailAddressdisplay);
       final TextView firstNamedisplay = findViewById(R.id.firstNamedisplay);
       final TextView surnamedisplay = findViewById(R.id.surnamedisplay);
       final TextView typeDisplay = findViewById(R.id.typeDisplay);
       //getting reference for the user;
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                //if profile exists
                if(userProfile != null){
                    String firstName = userProfile.firstName;
                    String surname = userProfile.surname;
                    String email = userProfile.email;
                    String type = userProfile.type;

                    //after getting the data  set it to the layout
                    firstNamedisplay.setText(firstName);
                    surnamedisplay.setText(surname);
                    emailAddressdisplay.setText(email);
                    typeDisplay.setText(type);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Unexpected occurence", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void choosePicture() {
        Intent intent  = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData()!=null){
            imageUri = data.getData();
            profilepic.setImageURI(imageUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {



        final String randomKey = UUID.randomUUID().toString();
        StorageReference sRef = storageReference.child("images/" + randomKey);

        sRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // successful upload

                        Toast.makeText(ProfileActivity.this, "Image uploaded", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(ProfileActivity.this, "Upload failure", Toast.LENGTH_LONG).show();
                    }
                });
    }
}