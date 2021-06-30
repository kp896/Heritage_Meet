package com.example.o2meet;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class introductoryPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory_page);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.loginmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.introLoginButton:

                //Toast.makeText(LoginActivity.this, " LoginWorking ", Toast.LENGTH_LONG).show();

                startActivity(new Intent(introductoryPage.this, LoginActivity.class));
                return true;

            case R.id.contact:

                //Toast.makeText(LoginActivity.this, " LoginWorking ", Toast.LENGTH_LONG).show();

                startActivity(new Intent(introductoryPage.this, introductoryContactPage.class));
                return true;


            default:
                return super.onOptionsItemSelected(item);

        }
    }
}