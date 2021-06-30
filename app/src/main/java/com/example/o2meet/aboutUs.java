package com.example.o2meet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


public class aboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);



        Element adsElement = new Element();
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.meeting)
                .setDescription(" O2 Academia \n We Take Utmost Care To Give Attention To Individual Students Along With Imparting The Right Knowledge.\n Take A Quick Ride Of O2 Academia, Not Just Distributing Study Materials, The Institute Offers Demo Sessions To Get A Flair Of What Actually Happens On The Insides. ")

                .addGroup("CONNECT WITH US!")
                .addEmail("contact@o2academia.org")
                .addWebsite("www.o2academia.org/")
                .addYoutube("UCt-TDBEj2HHZ7zQepJAsleg")

                .addGroup("Privacy Policy !!")
                .addWebsite("www.o2academia.org/privacy.php")
                .addGroup(" Terms and Conditions !!")
                .addWebsite("www.o2academia.org/terms.php")

                .addItem(new Element().setTitle("Version 1.1"))
                .addItem(createCopyright())
                .create();
        setContentView(aboutPage);
    }

    private Element createCopyright()
    {

        Element copyright = new Element();
        @SuppressLint("DefaultLocale") final String copyrightString = String.format("Developed by TMIS", Calendar.getInstance().get(Calendar.YEAR));
        copyright.setTitle(copyrightString);
        // copyright.setIcon(R.mipmap.ic_launcher);
        copyright.setGravity(Gravity.CENTER);
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(aboutUs.this,copyrightString,Toast.LENGTH_SHORT).show();
            }
        });
        return copyright;
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
                startActivity(new Intent(aboutUs.this, DashboardActivity.class));
                return true;

            case R.id.help:
                startActivity(new Intent(aboutUs.this, help.class));
                return true;

            case R.id.logout:
                startActivity(new Intent(aboutUs.this, LoginActivity.class));
                aboutUs.this.finish();

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
                Toast.makeText(aboutUs.this, "Already at Support", Toast.LENGTH_LONG).show();
                return true;*/

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}