package com.example.ffbfv14;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddEatery extends AppCompatActivity {
    EditText name, desc;
    Button save;

    // Database reference object
    DatabaseReference dbref;
    ImageView im_add;
    Uri im_path;
    StorageReference sref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_eatery);
        name = findViewById(R.id.edt_name);
        desc = findViewById(R.id.edt_desc);
        save = findViewById(R.id.btn_save);
        im_add = findViewById(R.id.img_add);
        Intent i = getIntent();
        String pk = i.getStringExtra("PK");
        String url = i.getStringExtra("URL");
        // linking the DB reference object to the node we wantto search in Firebase
        dbref = FirebaseDatabase.getInstance().getReference("_eateries_");//

        save.setOnClickListener(new View.OnClickListener() {//
            @Override
            public void onClick(View v) {//
                if(name.getText().length() > 0 && desc.getText().length() >0 ) //
                {
                    // use db ref to save
                    Eatery eatery = new Eatery(name.getText().toString(), desc.getText().toString(), url, "Foodstall");
                    //checking if foodstall already exists
                    final ArrayList<Eatery> eateryList  = new ArrayList<>();
                    Query dbref_check = FirebaseDatabase.getInstance()
                            .getReference("_eateries_").orderByChild("name").equalTo(name.getText().toString());
                    dbref_check.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dss: snapshot.getChildren()){
                                eateryList.add(dss.getValue(Eatery.class));
                            }
                            //if the entry does not exists in database it is saved
                            if(eateryList.size() == 0){
                                dbref.child(dbref.push().getKey()).setValue(eatery); //
                                Toast.makeText(AddEatery.this, "Entry saved", Toast.LENGTH_LONG).show();
                            }
                            else if(eateryList.size() !=0){
                                boolean exists = false;
                                for(int i = 0; i < eateryList.size(); i++){
                                    if(name.getText().toString().compareToIgnoreCase(eateryList.get(i).getName()) == 0){
                                        exists = true;
                                    }
                                }
                                if(exists == false){
                                    dbref.child(dbref.push().getKey()).setValue(eatery); //
                                    Toast.makeText(AddEatery.this, "Entry saved", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(AddEatery.this, "Foodstall of that name already exists.", Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });




//                    dbref.child(dbref.push().getKey()).setValue(eatery); //
//                    Toast.makeText(AddEatery.this, "Entry saved", Toast.LENGTH_SHORT).show();
                }

            }
        });

        im_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddEatery.this, UploadImage.class));
            }
        });

    }

}