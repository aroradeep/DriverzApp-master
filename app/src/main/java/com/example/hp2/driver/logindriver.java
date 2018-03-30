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
        import android.widget.Toast;

        import com.firebase.client.Firebase;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;

        import butterknife.ButterKnife;
        import butterknife.InjectView;

public class logindriver extends AppCompatActivity implements  View.OnClickListener,AdapterView.OnItemSelectedListener {

    @InjectView(R.id.username)
    EditText username;

    @InjectView(R.id.password)
    EditText password;

    @InjectView(R.id.login)
    Button login;
    @InjectView(R.id.loginreg)
    Button loginreg;
    Firebase fregister;
    ProgressDialog mprogress;
    User user;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logindriver);
        ButterKnife.inject(this);
        user = new User();
        mAuth = FirebaseAuth.getInstance();
        ButterKnife.inject(this);
        mprogress = new ProgressDialog(this);
        login.setOnClickListener(this);
        loginreg.setOnClickListener(this);

        fregister = new Firebase("https://driverz-a56f8.firebaseio.com/AppDriver");
    }

    public void onClick(View view) {

        int id = view.getId();

        switch (id) {
            case R.id.login:


                user.setEmail(username.getText().toString().trim());
                user.setPassword(password.getText().toString().trim());


                loginn();
                //;}
                // else{Toast.makeText(RegisterUser.this,"Password does not match",Toast.LENGTH_LONG);}

                break;

            case R.id.loginreg:
                Intent login1 = new Intent(logindriver.this, RegisterDriver.class);
                login1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login1);
                break;


        }
    }


    public void loginn() {

        final String emaill = username.getText().toString().trim();
        final String pass = password.getText().toString().trim();


        Log.v("E_value", "email" + emaill);

        if (TextUtils.isEmpty(emaill) || TextUtils.isEmpty(pass)) {
            Log.v("E_value", "emailjp" + emaill);
            Toast.makeText(logindriver.this, "Empty fields", Toast.LENGTH_LONG).show();
        } else {
            mprogress.setMessage("Login");
            mprogress.show();

            mAuth.signInWithEmailAndPassword(emaill, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        mprogress.dismiss();
                        String userid = mAuth.getCurrentUser().getUid();

                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);

                        // fregister.push().setValue(user);
                        // Intent main =new Intent(RegisterUser.this,driver.class);
                        // main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        // startActivity(main);
                    } else {
                        mprogress.dismiss();
                        Toast.makeText(logindriver.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                }

            });
        }


    }

    private void updateUI(FirebaseUser user) {

        if (user != null) {


            Intent login = new Intent(logindriver.this,DriverMain.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(login);

        } else {
            Toast.makeText(logindriver.this, "Please login.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}