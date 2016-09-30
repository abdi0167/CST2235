package com.example.mirre.lab1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "LoginActivity";
    protected static final String PREF = "com.example.mirre.lab1";

     Button login;
     EditText loginName ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Bundle are used for passing data between various android activities
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "In onStart()");

        setContentView(R.layout.activity_login);




        login = (Button)findViewById(R.id.loginbutton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Saving the Data to Context
                SharedPreferences sharedPrefs = getSharedPreferences(PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                loginName = (EditText)findViewById(R.id.email);

                // Putting this string as deafult email, then retreiving it
                editor.putString("DefaultEmail",loginName.getText().toString() );
                editor.commit();


                // first parameter: back page; second parameter: what is the next page
                Intent intent = new Intent(LoginActivity.this,ListItemsActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");

    }
    @Override
    protected void onStart(){


        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");

        // Saving the Data to Context
        SharedPreferences sharedPrefs = getSharedPreferences(PREF, Context.MODE_PRIVATE);

        // On start set the deafult email to deafult email
        String defaultEmail = sharedPrefs.getString("DefaultEmail", "email@domain.com");
        loginName = (EditText)findViewById(R.id.email);
        loginName.setText(defaultEmail);


    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

}

