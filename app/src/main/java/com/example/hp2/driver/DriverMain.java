package com.example.hp2.driver;

import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.client.DataSnapshot;
        import com.firebase.client.Firebase;
        import com.firebase.client.FirebaseError;
        import com.firebase.client.ValueEventListener;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;

        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;


        import java.util.ArrayList;
        import java.util.Map;

        import butterknife.ButterKnife;
        import butterknife.InjectView;

public class DriverMain extends AppCompatActivity {

    Firebase f3;
    //@InjectView(R.id.textView2)
    //TextView value;
    ArrayList<User> userList;
    FirebaseAuth mAuth,autha;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference mDatabaseReference;
    String userID;

    private DatabaseReference mDr;
    @InjectView(R.id.heading)
    TextView heading;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        userList = new ArrayList<>();
        mAuth= FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        ButterKnife.inject(this);
        f3 = new Firebase("https://driverz-a56f8.firebaseio.com/AppDriver");
        mAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    Intent login=new Intent(DriverMain.this,logindriver.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(login);
                }
            }
        };
        mDr = FirebaseDatabase.getInstance().getReference().child("AppDriver").child(userID).child("name");
        // Read from the database
f3.child(userID).child("name").addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        String name = dataSnapshot.getValue(String.class);
        heading.setText(name);
        Log.v("E_value", "emaillllll" +name);
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }
});
        Data();
    }

    //f3.addValueEventListener(new ValueEventListener() {

          /*  public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,String> map =dataSnapshot.getValue(Map.class);
                String email= map.get("email");
                String gender= map.get("gender");

                String name= map.get("name");
                String password= map.get("password");
                Log.v("E_value", "email" + email);
                Log.v("E_value", "email" +gender);
                Log.v("E_value", "name" +name);


            }*/

    //here user will sign in using his name so it is based on user, directory will change dynamically

               /* public void onDataChange(DataSnapshot dataSnapshot) {

                  //  User user = dataSnapshot.getValue(User.class);

                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                  //  String name = (String) messageSnapshot.child("name").getValue();
                   // String ema = (String) messageSnapshot.child("email").getValue();
                   // Map<String, Object> map = (Map<String, Object>) messageSnapshot.getValue();
                   user = messageSnapshot.getValue(User.class);
                   // int id =user.getId();
                    String name =user.getName();
                    String email =user.getEmail();
                    String password =user.getPassword();
                    String gender =user.getGender();
                    String city =user.getCity();
                    //String a = (String) map.get("email");
                   //String b = (String) map.get("name");

                    Log.v("E_value", "emailll" +city);
                   /// userList.add(new User(id,name,email,password,gender,city));
                    for(User d:userList) {
                        System.out.println(d);
                        Log.v("E_value", "ema" +d);
                        // prints [Tommy, tiger]
                    }
                }
                }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }


        });

*/






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.driver,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.allSongs:
                FirebaseAuth.getInstance().signOut();

                break;
            case R.id.fav:
                Intent login1=new Intent(DriverMain.this,DriverMapActivity.class);
                login1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login1);
                break;
            case R.id.artist:
                Intent login2=new Intent(DriverMain.this,DriverSettingsActivity.class);
                login2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login2);
                break;
            case R.id.views:
                Intent intent=new Intent(DriverMain.this,HistoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("customerOrDriver", "Drivers");
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
    public void Data(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            // Name, email address, and profile photo Url
            mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("AppDriver").child(uid);


            com.google.firebase.database.ValueEventListener post = new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    User user= dataSnapshot.getValue(User.class);
                    String name =user.getName();
                    String email =user.getEmail();
                    final String pass =user.getPassword();
                    Log.v("E_value", "email" +pass);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }





            };
            mDatabaseReference.addValueEventListener(post);



        }

    }

}
