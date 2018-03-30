package com.example.hp2.driver;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by hp 2 on 07-10-2017.
 */

public class driver extends Application {
public void onCreate(){
    super.onCreate();
    Firebase.setAndroidContext(this);
}

}
