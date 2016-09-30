package com.example.mirre.lab1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {

    //Static string to hold the object of start activity
    public static final String ACTIVITY_NAME = "StartActivity";
    //an image button  to hold the value of the image from the camera
    public ImageButton imageButton;
    //???????just reqursting a picture from the camera?
    static final int REQUEST_IMAGE_CAPTURE = 1;
    //creating a public instance of the switch
    public Switch theSwitch;
    //creating a public instance of the ehckedbox
    public CheckBox checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

        Log.i(ACTIVITY_NAME, "In onCreate()");


        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //This process involves three pieces:
                // The Intent itself, a call to start the external Activity,
                // and some code to handle the image data when focus returns to your activity.
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        theSwitch = (Switch) findViewById(R.id.switchbuton);
        theSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        CharSequence text;
                        int duration;
                        if(isChecked){
                            text = "Switch is On";
                            duration = Toast.LENGTH_SHORT;
                        }else {
                            text = "Switch is Off";
                            duration = Toast.LENGTH_LONG;
                        }
                Toast toast = Toast.makeText(ListItemsActivity.this, text, duration); //this is the ListActivity
                toast.show(); //Displaying the message Box


            }
        });

        CheckBox checked = (CheckBox)findViewById(R.id.checkBox);
        checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);

                    // 2. Chain together various setter methods to set the dialog characteristics
                    builder.setMessage(R.string.dialog_message) //Add a dialog message to strings.xml


                            .setTitle(R.string.dialog_title)
                            //If the user selects yes (the positive button), then call finish()
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent resultIntent = new Intent();
                                    resultIntent.putExtra("Response", "My information to share");
                                    setResult(Activity.RESULT_OK, resultIntent);
                                    finish();
                                }

                            })
                            //If the user selects no (the negative button), then call cancel()
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                            .show();

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageButton.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "were in onResume");
    }

    @Override
    protected void onStart(){


        super.onStart();

        Log.i(ACTIVITY_NAME, "were in onResume");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "were in onStop");

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "onDestry");

    }
}
