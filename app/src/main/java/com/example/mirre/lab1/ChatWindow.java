package com.example.mirre.lab1;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.Observable;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    Context ctx;
    public static final String ACTIVITY_NAME = "Query";
    public static final String SQL_MESSAGE="SQL MESSAGE:";
    public static final String COLUMN_COUNT = "Cursor\'s  column count= ";

    ChatDatabaseHelper chatDatabaseHelper;
    SQLiteDatabase dataBase;


    Cursor cursor;

    public ListView list;
    public EditText edit;
    public Button send;
    public ArrayList<String> chatList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        chatDatabaseHelper = new ChatDatabaseHelper(this);
        //open database
        dataBase = chatDatabaseHelper.getWritableDatabase();

        //This creates a string array for the
        String[] allColumns = { ChatDatabaseHelper.KEY_ID, ChatDatabaseHelper.KEY_MESSAGE };
        cursor = dataBase.query(chatDatabaseHelper.TABLE_NAME,allColumns, null, null, null, null, null);

        cursor.moveToFirst();


        while(!cursor.isAfterLast() ) {

            String newMessage = cursor.getString(cursor.getColumnIndex(chatDatabaseHelper.KEY_MESSAGE));
            chatList.add(newMessage);
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(chatDatabaseHelper.KEY_MESSAGE)));
            // String thisName = results1.geString(nameColumnIndex);
            cursor.moveToNext();
        }


        for(int x = 0; x < cursor.getColumnCount(); x++){
            cursor.getColumnName(x);
            Log.i(ACTIVITY_NAME, "Cursors  column count =" + cursor.getColumnCount() );

        }


        list = (ListView) findViewById(R.id.listView);
        send = (Button) findViewById(R.id.chatButton);
        edit = (EditText) findViewById(R.id.editChat);

        /** Add a callback handler for your Send button so that whenever the user clicks it,
         * you get the text in the EditText field, and add it to your array list variable*/
        //Create an inner class of ChatWindow, called ChatAdapter, and it should extend
        class ChatAdapter extends ArrayAdapter<String> {

            public ChatAdapter(Context ctx) {

                super(ctx, 0);
            }

            //Returning the position of the chat list
            public String getItem(int position) {

                return chatList.get(position);

            }

            //Getting the count of the chat list size
            public int getCount() {

                return chatList.size();
            }

            public View getView(int position, View convertView, ViewGroup parent) {

                LayoutInflater inflater = ChatWindow.this.getLayoutInflater();

                //If the remainder of the position, module 2, is equal to zero, a message is incoming
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
        final ChatAdapter messageAdapter = new ChatAdapter(this);
        list.setAdapter(messageAdapter);


        /**
         * Modify the sendButton’s onClickListener callback function so that whenever a message is added to the ArrayList,
         * it also inserts the new message into the database. Use a ContentValues object to put the new message.
         *
         * You shouldn’t have to insert the ID since your creation statement should have specified the ID as autoincrement.
         */
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Once send is clicked, get and add the text from the chat box
                chatList.add(edit.getText().toString());

                //Modify the sendButton’s onClickListener callback function so that whenever a message is added to the ArrayList,
                // it also inserts the new message into the database. Use a ContentValues object to put the new message.
                        ContentValues contentValues = new ContentValues();

                contentValues.put(chatDatabaseHelper.KEY_MESSAGE, edit.getText().toString());
                dataBase.insert(chatDatabaseHelper.TABLE_NAME, "null", contentValues);

                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView()
                edit.setText("");

            }
        });

        /*Now modify the ChatWindow.java class. Write the onCreate() function so that it creates a temporary ChatDatabaseHelper object,
         which then gets a writeable database and stores that as an instance variable.

         After opening the database, execute a query for any existing chat messages and add them into the ArrayList of messages that was created in Lab 4.

          You can either use the rawQuery() method, or query() to build the query statement yourself.
          Add Log.i() messages for each message that you retrieve from the Cursor object:
        */

    } //End of onCreate


        @Override
        protected void onDestroy() {
            Log.i(ACTIVITY_NAME, "In onDestroy()");
            super.onDestroy();
            cursor.close();
            dataBase.close();
        }

}//End of class

