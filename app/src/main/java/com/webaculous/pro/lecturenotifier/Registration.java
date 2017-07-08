package com.webaculous.pro.lecturenotifier;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.webaculous.pro.lecturenotifier.R.id.spin_branch;
import static com.webaculous.pro.lecturenotifier.R.id.spin_sem;

public class Registration extends Activity {
    EditText et_name,et_email,et_pass,et_cpass;
    Spinner spin_branch,spin_sem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        spin_branch = (Spinner) findViewById(R.id.spin_branch);
        spin_sem = (Spinner) findViewById(R.id.spin_sem);

        et_name = (EditText) findViewById(R.id.txt_name);
        et_email = (EditText) findViewById(R.id.txt_email);
        et_pass = (EditText) findViewById(R.id.txt_pass);
        et_cpass = (EditText) findViewById(R.id.txt_cpass);


        ArrayAdapter<CharSequence> branch = ArrayAdapter.createFromResource(this,
                R.array.branch_array, android.R.layout.simple_spinner_item);
        branch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_branch.setAdapter(branch);

        ArrayAdapter<CharSequence> sem = ArrayAdapter.createFromResource(this,
                R.array.sem_array, android.R.layout.simple_spinner_item);
        sem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_sem.setAdapter(sem);

        spin_branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int position, long id) {

                TextView tmpView = (TextView) spin_branch.getSelectedView().findViewById(android.R.id.text1);
                tmpView.setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spin_sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int position, long id) {

                TextView tmpView = (TextView) spin_sem.getSelectedView().findViewById(android.R.id.text1);
                tmpView.setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public  void  userReg(View view)
    {
        String name = et_name.getText().toString();
        String email = et_email.getText().toString();
        String pass = et_pass.getText().toString();
        String cpass = et_cpass.getText().toString();
        String branch = spin_branch.getSelectedItem().toString();
        String sem = spin_sem.getSelectedItem().toString();
        if(TextUtils.isEmpty(name) || sem.matches("Select Semester") || branch.matches("Select Branch") || TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(cpass)) {
            Toast.makeText(this, "Fill All the Details", Toast.LENGTH_LONG).show();
            return;
        }
        else if(!pass.equals(cpass))
        {
            Toast.makeText(this, "Password Doesn't Match", Toast.LENGTH_LONG).show();
            return;
        }
        String method = "register";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,name,email,pass,branch,sem);



    }
    public  void userLogin(View view)
    {
        startActivity(new Intent(this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

    }

}
