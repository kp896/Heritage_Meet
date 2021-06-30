package com.example.o2meet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class profile extends AppCompatActivity {

    TextView name, guardians_name, batch, dob, branch, g_phone_no ;
    TextView unique_id, email_id, s_phone_no, s_whatsapp_no, course ;
    private FirebaseFirestore db;       // declaration
    Map<String,Object> map = new HashMap<>();
    String value="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        guardians_name = findViewById(R.id.guardians_name);
        g_phone_no = findViewById(R.id.g_phone_no);
        dob = findViewById(R.id.dob);
        branch = findViewById(R.id.branch);
        unique_id = findViewById(R.id.unique_id);
        email_id = findViewById(R.id.email_id);
        s_phone_no = findViewById(R.id.s_phone_no);
        s_whatsapp_no = findViewById(R.id.s_whatsapp_no);
        batch = findViewById(R.id.batch);
        course = findViewById(R.id.course);

        Intent intent = getIntent();
        value = intent.getStringExtra(DashboardActivity.DashtoProfile);
        db = FirebaseFirestore.getInstance();       // initialisation


        DocumentReference docRef = db.collection("Users").document(value);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        map =  document.getData();
                        Log.d(String.valueOf(profile.this),"DocumentSnapshot data: "+map);

                        //userinfo.setText( String.valueOf(info));

                        name.setText("Name : "+ String.valueOf(map.get("name")));
                        guardians_name.setText("Guardian Name : "+ String.valueOf(map.get("guardians_name")));
                        batch.setText("Batch : "+ String.valueOf(map.get("batch")));
                        dob.setText("Date Of Birth : "+ String.valueOf(map.get("dob")));
                        course.setText("Course : "+ String.valueOf(map.get("course")));
                        unique_id.setText("Unique ID. : "+ String.valueOf(map.get("unique_id")));
                        email_id.setText("Email ID : "+ String.valueOf(map.get("email_id")));
                        s_phone_no.setText("Student Phone No. : "+ String.valueOf(map.get("s_phone_no")));
                        branch.setText("Branch : "+ String.valueOf(map.get("branch")));
                        s_whatsapp_no.setText("Student WhatsApp No. : "+ String.valueOf(map.get("s_whatsapp_no")));
                        g_phone_no.setText("Guardian Phone No. : "+ String.valueOf(map.get("g_phone_no")));

                        //.setText(info);

                    } else {
                        Log.d(String.valueOf(profile.this), "No such document"+value);
                    }
                } else {
                    Log.d(String.valueOf(profile.this), "get failed with ", task.getException());
                }
            }
        });


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home:
                startActivity(new Intent(profile.this, DashboardActivity.class));
                return true;

            case R.id.help:
                startActivity(new Intent(profile.this, help.class));
                return true;

            case R.id.logout:
                startActivity(new Intent(profile.this, LoginActivity.class));
                profile.this.finish();

                Intent i1=new Intent(getApplicationContext(),LoginActivity.class);
                i1.setAction(Intent.ACTION_MAIN);
                i1.addCategory(Intent.CATEGORY_HOME);
                i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i1);
                finish();
                return true;

            /*case R.id.menuSupport:
                startActivity(new Intent(profile.this, aboutUs.class));
                return true;*/

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}