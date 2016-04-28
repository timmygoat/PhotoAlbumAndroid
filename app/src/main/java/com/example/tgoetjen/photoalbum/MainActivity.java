package com.example.tgoetjen.photoalbum;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String m_Text = "";
    HashMap<String, String> hm = new HashMap<String,String>();

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<user.albums.size();i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("album_name",user.albums.get(i).getName());
            hm.put("thumbnail",Integer.toString(R.drawable.image1)); //actual image to be grabbed when we figure out image class in android
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = {"album_name", "thumbnail"};

        // Ids of views in listview_layout
        int[] to = {R.id.album_name, R.id.thumbnail};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList,R.layout.listview_layout, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = ( ListView ) findViewById(R.id.listview);

        // Setting the adapter to the listView
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_create_album){
            showAddAlbum();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showAddAlbum(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new album name:");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                Album a = new Album(m_Text);
                user.addAlbum(a);
                List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

                for(int i=0;i<user.albums.size();i++){
                    HashMap<String, String> hm = new HashMap<String,String>();
                    hm.put("album_name",user.albums.get(i).getName());
                    hm.put("thumbnail",Integer.toString(R.drawable.image1)); //actual image to be grabbed when we figure out image class in android
                    aList.add(hm);
                }

                // Keys used in Hashmap
                String[] from = {"album_name", "thumbnail"};

                // Ids of views in listview_layout
                int[] to = {R.id.album_name, R.id.thumbnail};

                // Instantiating an adapter to store each items
                // R.layout.listview_layout defines the layout of each item
                SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList,R.layout.listview_layout, from, to);

                // Getting a reference to listview of main.xml layout file
                ListView listView = ( ListView ) findViewById(R.id.listview);

                // Setting the adapter to the listView
                listView.setAdapter(adapter);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
