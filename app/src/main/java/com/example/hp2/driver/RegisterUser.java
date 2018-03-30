package com.example.hp2.driver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    @InjectView(R.id.heading)
    TextView heading;

    @InjectView(R.id.name)
    EditText name;

    @InjectView(R.id.emaill)
    EditText email;

    @InjectView(R.id.password)
    EditText password;

    @InjectView(R.id.reenter_password)
    EditText reenter;

    @InjectView(R.id.gender)
    TextView gender;

    @InjectView(R.id.female)
    RadioButton female;

    @InjectView(R.id.male)
    RadioButton male;

    @InjectView(R.id.register)
    Button register;

    @InjectView(R.id.address)
    EditText address;

    @InjectView(R.id.city)
    EditText city;

    @InjectView(R.id.zip_code)
    EditText zip_code;

    @InjectView(R.id.phone_no)
    EditText phone_no;

    User user;
    ProgressDialog mprogress;
    Firebase fregister;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth=FirebaseAuth.getInstance();
        ButterKnife.inject(this);
        mprogress=new ProgressDialog(this);

        fregister=new Firebase("https://driverz-a56f8.firebaseio.com/AppUser");

        //object of new user
        user=new User();


        register.setOnClickListener(this);
        male.setOnClickListener(this);
        female.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.register:

               user.setName(name.getText().toString().trim());
                user.setEmail(email.getText().toString().trim());
                user.setPassword(password.getText().toString().trim());
                user.setReenter(reenter.getText().toString().trim());
                user.setAddress(address.getText().toString().trim());
                user.setCity(city.getText().toString().trim());
                user.setZip_code(zip_code.getText().toString().trim());
                user.setPhone_no(phone_no.getText().toString().trim());


                signin();
                //;}
               // else{Toast.makeText(RegisterUser.this,"Password does not match",Toast.LENGTH_LONG);}

                break;

            case R.id.male:
                user.setGender("Male");
                break;

            case R.id.female:
                user.setGender("Female");
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void insertuser(){

        fregister.push().setValue(user);

    }
    public void signin(){

        String emaill=email.getText().toString().trim();
        final String pass= password.getText().toString().trim();
        final String pass1= reenter.getText().toString().trim();

        Log.v("E_value", "email" + emaill);
if(pass.equals(pass1)){mprogress.setMessage("Loading");
    mprogress.show();
        if(TextUtils.isEmpty(emaill)||TextUtils.isEmpty(pass)){
            Log.v("E_value", "emailjp" + emaill);mprogress.dismiss();
            Toast.makeText(RegisterUser.this,"Empty fields",Toast.LENGTH_LONG).show();
        }else{
            mAuth.createUserWithEmailAndPassword(emaill,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String userid=mAuth.getCurrentUser().getUid();
                        Firebase child=fregister.child(userid);
                        child.setValue(user);

                        mprogress.dismiss();
                        Intent login3=new Intent(RegisterUser.this,loginuser.class);
                        login3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(login3);
                       // fregister.push().setValue(user);
                       // Intent main =new Intent(RegisterUser.this,driver.class);
                       // main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       // startActivity(main);
                    }
                    else{Log.v("E_value", "email" +pass);
                        mprogress.dismiss();}
                }
            });}}
    else{
    Toast.makeText(RegisterUser.this,"pass does not match",Toast.LENGTH_LONG).show();

}

    }
}