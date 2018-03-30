package com.example.hp2.driver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.firebase.client.Firebase;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.editTextName)
    EditText eTxtName;

    @InjectView(R.id.editTextEmail)
    EditText eTxtEmail;

    @InjectView(R.id.editTextPassword)
    EditText eTxtPassword;

    @InjectView(R.id.radioButtonMale)
    RadioButton rbMale;

    @InjectView(R.id.radioButtonFemale)
    RadioButton rbFemale;
    @InjectView(R.id.editText2)
    EditText number;

    @InjectView(R.id.spinnerCity)
    Spinner spCity;

    @InjectView(R.id.buttonSignUp)
    Button btnSignUp;
    Firebase f3;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.inject(this);
        f3=new Firebase("https://driverz-a56f8.firebaseio.com/user");
        user = new User();
        btnSignUp.setOnClickListener(this);
        rbMale.setOnClickListener(this);
        rbFemale.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.buttonSignUp:

                user.setName(eTxtName.getText().toString().trim());
                user.setEmail(eTxtEmail.getText().toString().trim());
                user.setPassword(eTxtPassword.getText().toString().trim());
               // user.setNumber(Integer.parseInt(number.getText().toString().trim()));

                insertUser();

                break;

            case R.id.radioButtonMale:
                user.setGender("Male");
                break;

            case R.id.radioButtonFemale:
                user.setGender("Female");
                break;
        }


    }
    void insertUser(){
        String value=eTxtName.getText().toString();
      //  Firebase f4=f3.child(user.getEmail());
        f3.push().setValue(user);

}}
