package com.example.mirre.lab1;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Observable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    public ListView list;
    public EditText edit;
    public Button send;
    public ArrayList<String> chatList = new ArrayList<String>();
    protected static final String ACTIVITY_NAME = "ChatWindow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = (ListView)findViewById(R.id.listView);
        send = (Button)findViewById(R.id.chatButton);
        edit = (EditText)findViewById(R.id.editChat);



       /** Add a callback handler for your Send button so that whenever the user clicks it,
        * you get the text in the EditText field, and add it to your array list variable*/



         class ChatAdapter extends ArrayAdapter<String> {

             public ChatAdapter(Context ctx) {

                 super(ctx, 0);
             }

            public String getItem(int position ){

                return chatList.get(position);

            }
             public int getCount() {

                 return chatList.size();
             }

             public View getView(int position, View convertView, ViewGroup parent) {

                 LayoutInflater inflater = ChatWindow.this.getLayoutInflater();

                 View result = null;
                 if (position % 2 == 0)
                     result = inflater.inflate(R.layout.chat_row_incoming, null);
                 else
                     result = inflater.inflate(R.layout.chat_row_outgoing, null);

                 // From the resulting view, get the TextView which holds the string message:
                 TextView message = (TextView) result.findViewById(R.id.messageView);
                 message.setText(getItem(position)); // get the string at position, should I be using the get position?
                 return result;
             }//end of view
         }//end of chatadapter

                 //	In your ChatWindow.java onCreate() function, set the data source of the listView
                 // to be a new ChatAdapter object:ow,
                 //in this case, “this” is the ChatWind
                 // which is-A Context object ChatAdapter messageAdapter =new ChatAdapter( this );
               // ChatAdapter object;
        final   ChatAdapter messageAdapter = new ChatAdapter( this );
        list.setAdapter (messageAdapter);


        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                chatList.add(edit.getText().toString());
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView()
                edit.setText("");
            }
        });

    }//end of con create
}//End of class

