package com.webaculous.pro.lecturenotifier;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends Activity {
    EditText et_email,et_pass;
    private PendingIntent mEverydayPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        et_email = (EditText) findViewById(R.id.txt_email);
        et_pass = (EditText) findViewById(R.id.txt_pass);
        setRepeatedNotification(1,9,40,0);
        setRepeatedNotification(2,10,40,0);
        setRepeatedNotification(3,12,10,0);
        setRepeatedNotification(4,13,10,0);
        setRepeatedNotification(5,14,20,0);
        setRepeatedNotification(6,15,15,0);


    }


public  void userReg(View view)
{
    startActivity(new Intent(this,Registration.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

}

    public  void userLogin(View view)
    {
        String email = et_email.getText().toString();
        String pass = et_pass.getText().toString();
        String method = "login";
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Fill All the Details", Toast.LENGTH_LONG).show();
            return;
        }
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,email,pass);


    }

    private void setRepeatedNotification(int ID, int hh, int mm, int ss) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        alarmIntent.putExtra("ID",ID);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, ID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hh);
        calendar.set(Calendar.MINUTE, mm);
        calendar.set(Calendar.SECOND, ss);

        //check whether the time is earlier than current time. If so, set it to tomorrow. Otherwise, all alarms for earlier time will fire

        if(calendar.before(now)){
            calendar.add(Calendar.DATE, 1);
        }

        mEverydayPendingIntent = pendingIntent;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, mEverydayPendingIntent);



    }
}
