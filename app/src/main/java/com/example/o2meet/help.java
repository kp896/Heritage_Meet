package com.example.o2meet;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class help extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

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
                startActivity(new Intent(help.this, DashboardActivity.class));
                return true;

            case R.id.help:
                Toast.makeText(help.this, "Already at Help", Toast.LENGTH_LONG).show();
                return true;

            case R.id.logout:
                startActivity(new Intent(help.this, LoginActivity.class));
                help.this.finish();

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
                startActivity(new Intent(help.this, aboutUs.class));
                return true;*/

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}