package com.example.hp2.driver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;

import butterknife.InjectView;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    @InjectView(R.id.usertextView)
    TextView Logintextview;




    @InjectView(R.id.Userbutton)
    Button uLogin;



    @InjectView(R.id.drivertextview)
    TextView dLogin;


    @InjectView(R.id.Driverbutton)
    Button dregister;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ButterKnife.inject(this);



        user=new User();

        dregister.setOnClickListener(this);

        uLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.Userbutton:



                Intent login6=new Intent(Main4Activity.this,loginuser.class);
                login6.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login6);



                //;}
                // else{Toast.makeText(RegisterUser.this,"Password does not match",Toast.LENGTH_LONG);}

                break;

            case R.id.Driverbutton:
                Intent login1=new Intent(Main4Activity.this,logindriver.class);
                login1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login1);
                break;


        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


