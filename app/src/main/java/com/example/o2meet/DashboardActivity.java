package com.example.o2meet;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.getbase.floatingactionbutton.FloatingActionButton;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Random;

public class DashboardActivity extends AppCompatActivity {


    EditText secretCodeBox , subject;
    Button joinBtn, autoid, shareBtn, schedule;
    String s="";
    String x="";
    String appTime , appDate;
    String sub = "";
    public FloatingActionButton floatingActionButton;
    String message = "";
    public static final String DashtoProfile = "Success1";
    TextView chooseTime,chooseDate;
    DatePickerDialog.OnDateSetListener chooseDateSetListener;
    TimePickerDialog.OnTimeSetListener chooseTimeSetListener;
    Dialog dialog;
    private Button button;



    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        secretCodeBox = findViewById(R.id.codeBox);
        joinBtn = findViewById(R.id.joinBtn);
        autoid = findViewById(R.id.autoid);
        shareBtn = findViewById(R.id.shareBtn);
        subject= findViewById(R.id.subject);
        floatingActionButton = findViewById(R.id.floatbutton_profile);
        chooseTime = findViewById(R.id.chTime);
        chooseDate = findViewById(R.id.chDate);
        schedule = findViewById(R.id.scheduleBtn);


        dialog = new Dialog(DashboardActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        Button OKAY = dialog.findViewById(R.id.btnok);
        OKAY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(secretCodeBox.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();

                JitsiMeetActivity.launch(DashboardActivity.this, options);
                Toast.makeText(DashboardActivity.this,"Joining",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });




        URL serverURL;

        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions =
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverURL)
                            .setWelcomePageEnabled(false)
                            .setAudioMuted(false)
                            .setVideoMuted(false)
                            .setFeatureFlag("kick-out.enabled",false)
                            .setFeatureFlag("invite.enabled",false)
                            .setFeatureFlag("live-streaming.enabled",false)
                            .setFeatureFlag("calender.enabled",false)
                            .setFeatureFlag("meeting-name.enabled",true)
                            .setFeatureFlag("audio-focus.disabled",true)
                            .setFeatureFlag("help.enabled",false)
                            .setFeatureFlag("add-people.enabled",true)
                            .setFeatureFlag("video-share.enabled", false)
                            .build();

            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Intent intent = getIntent();
        message = intent.getStringExtra(LoginActivity.LogintoDash);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, profile.class);
                intent.putExtra(DashtoProfile, message);
                startActivity(intent);
            }
        });



        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secretCodeBox.getText().toString().equals("")) {
                    Toast.makeText(DashboardActivity.this, "Enter Meeting ID", Toast.LENGTH_LONG).show();
                } else {
                    dialog.show();
                }
            }
        });




        autoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random r = new Random();
                Random R = new Random();
                for(int i=0;i<=3;i++) {
                    int low = 65;
                    int high = 90;
                    int result = r.nextInt(high-low) + low;
                    s+= (char)result+"";
                }
                s+= "-";

                for(int i=4;i<=7;i++)
                {
                    int low = 1;
                    int high = 9;
                    int result = r.nextInt(high-low) + low;
                    s+= result+"";

                }
                s+= "-";

                for(int i=8;i<=11;i++)
                {
                    int low = 65;
                    int high = 90;
                    int result = r.nextInt(high-low) + low;
                    s+= (char)result+"";
                }

                secretCodeBox.setText(s);
                x=s;
                s="";
            }
        });




        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseTime.setVisibility(View.VISIBLE);
                chooseDate.setVisibility(View.VISIBLE);
                subject.setVisibility(View.VISIBLE);

                chooseTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar cal = Calendar.getInstance();
                        int hour = cal.get(Calendar.HOUR_OF_DAY);
                        int min =  cal.get(Calendar.MINUTE);
                        int ampm = cal.get(Calendar.AM_PM);
                        TimePickerDialog dialog = new TimePickerDialog(DashboardActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                                (TimePickerDialog.OnTimeSetListener)chooseTimeSetListener,hour,min,false);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                    }
                });
                chooseTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hr, int min) {
                        String ampm;
                        if (hr>12) {
                            ampm = "PM";
                            hr=hr-12;
                        }else {
                            ampm = "AM";
                        }
                        appTime = " "+hr+" : "+min+" "+ampm;
                        chooseTime.setText(appTime);
                    }
                };
                chooseDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar cal = Calendar.getInstance();
                        int year = cal.get(Calendar.YEAR);
                        int month = cal.get(Calendar.MONTH);
                        int day = cal.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(DashboardActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                                (DatePickerDialog.OnDateSetListener)chooseDateSetListener,year,month,day);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                    }
                });
                chooseDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        appDate = " "+dayOfMonth + "/" + month + "/" + year+" ";
                        chooseDate.setText(appDate);
                    }
                };

                schedule.setVisibility(View.GONE);
                shareBtn.setVisibility(View.VISIBLE);

                shareBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sub = subject.getText().toString();
                        String a=secretCodeBox.getText()+"";
                        if(a.equals(""))
                        {
                            Toast.makeText(DashboardActivity.this, "Generate a AutoId to Share",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Intent in = new Intent(Intent.ACTION_SEND);
                            in.setData(Uri.parse("mailto:"));
                            in.setType("text/plain");

                            //in.putExtra(Intent.EXTRA_EMAIL,new String[]{docmailid});

                            in.putExtra(Intent.EXTRA_SUBJECT, "Invitation of Meeting");
                            in.putExtra(Intent.EXTRA_TEXT, " O2 VideoMeeting \n Joining Info \n\n Subject Name - " + sub + "  \n\n Meeting ID - " + x + "\n\n Date & Time - " + appDate+" & " + appTime);
                            startActivity(in);
                            //Toast.makeText(DashboardActivity.this, a,Toast.LENGTH_LONG).show();
                        }
                    }
                });
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
                Toast.makeText(DashboardActivity.this, "Already at Home", Toast.LENGTH_LONG).show();
                return true;


            case R.id.help:
                startActivity(new Intent(DashboardActivity.this, help.class));
                return true;


            case R.id.logout:
                startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
                DashboardActivity.this.finish();

                Intent i1=new Intent(getApplicationContext(), LoginActivity.class);
                i1.setAction(Intent.ACTION_MAIN);
                i1.addCategory(Intent.CATEGORY_HOME);
                i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i1);
                finish();
                return true;


            /*case R.id.menuSupport:
                startActivity(new Intent(DashboardActivity.this, aboutUs.class));
                return true;*/


            default:
                return super.onOptionsItemSelected(item);

        }
    }
}