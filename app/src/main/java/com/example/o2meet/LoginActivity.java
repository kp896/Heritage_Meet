package com.example.o2meet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    EditText emailBox, passwordBox, rollno;
    Button loginBtn, signupBtn, loginHelpBtn;
    String sms = "";
    public static final String LogintoDash = "Success";
    String e = "", p = "";
    private FirebaseFirestore db;
    Map<String,Object> map = new HashMap<>();

    FirebaseAuth auth;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        auth = FirebaseAuth.getInstance();

        emailBox = findViewById(R.id.emailBox);
        passwordBox = findViewById(R.id.passwordBox);
        rollno = findViewById(R.id.roll_no);
        loginBtn = findViewById(R.id.loginbtn);
        /*signupBtn = findViewById(R.id.createBtn);*/
        db = FirebaseFirestore.getInstance();


        /*loginHelpBtn = findViewById(R.id.loginHelpBtn);
        loginHelpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Enter EmailAddress (User_ID), Unique_ID, Password to Login into the app.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                String email, password;
                if(emailBox.getText().toString().equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Enter Valid Email Address", Toast.LENGTH_LONG).show();
                }
                else if (passwordBox.getText().toString().equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Enter Valid Password", Toast.LENGTH_LONG).show();
                }
                else if (rollno.getText().toString().equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Enter Valid Unique No.", Toast.LENGTH_LONG).show();
                }
                else {
                    email = emailBox.getText().toString();
                    password = passwordBox.getText().toString();
                    sms = rollno.getText().toString();

                    DocumentReference docRef = db.collection("Users").document(sms);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot doc = task.getResult();
                                if (doc.exists()) {
                                    map = doc.getData();
                                    e = String.valueOf(map.get("email_id"));
                                    p = String.valueOf(map.get("security_key"));
                                    if (email.equals(e) && password.equals(p)) {
                                        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                dialog.dismiss();
                                                if (task.isSuccessful()) {
                                                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                                    intent.putExtra(LogintoDash, sms);
                                                    startActivity(intent);

                                                    /*startActivity(new Intent(LoginActivity.this, DashboardActivity.class));*/
                                                    LoginActivity.this.finish();
                                                } else {
                                                    Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                                }

                                            }

                                        });
                                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_LONG).show();

                                    }

                                }
                            }
                        }
                    });

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.loginhelpmenu, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.loginHelpBtn) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.loginHelpBtn:

                Toast.makeText(LoginActivity.this, " Enter Correct Email_Id (User_ID), Unique_ID, Password to Login into the app. ", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

}







 /*signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });*/