package com.example.hp2.driver;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button b1;
    Firebase f1;
    EditText eTxtName, eTxtPhone;
    
FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener ma;
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
       mAuth.addAuthStateListener(ma);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
mAuth=FirebaseAuth.getInstance();
        b1=(Button)findViewById(R.id.button2);
        eTxtName = (EditText)findViewById(R.id.editTextName);
        eTxtPhone = (EditText)findViewById(R.id.editText);
        ma=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){

                    startActivity(new Intent(MainActivity.this,Main3Activity.class));
                }
            }
        };

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              signin();
            }
        });
    }
    public void signin(){

       String email= eTxtName.getText().toString();
        String pass= eTxtPhone.getText().toString();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)){

            Toast.makeText(MainActivity.this,"Problem222",Toast.LENGTH_LONG);
        }else{
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this,"Problem",Toast.LENGTH_LONG);
                }
            }
        });}
    }
}
