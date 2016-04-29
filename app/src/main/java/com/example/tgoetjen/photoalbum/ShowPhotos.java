package com.example.tgoetjen.photoalbum;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tgoet on 4/28/2016.
 */
public class ShowPhotos extends AppCompatActivity {
    private ShowPhotos getApp(){
        return this;
    }
    User user = new User();
    int currentAlbum = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.photo_toolbar);
        setSupportActionBar(toolbar);
        user = ((PhotoAlbumApplication)getApplication()).getUser();
        currentAlbum = ((PhotoAlbumApplication)getApplication()).getCurrentAlbum();
        this.setTitle(user.albums.get(currentAlbum).getName());
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < user.albums.get(currentAlbum).photos.size(); i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("photo_name", user.albums.get(currentAlbum).photos.get(i).getName());
            hm.put("thumbnail", Integer.toString(R.drawable.image1)); //actual image to be grabbed when we figure out image class in android
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = {"photo_name", "thumbnail"};

        // Ids of views in listview_layout
        int[] to = {R.id.name, R.id.thumbnail};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = (ListView) findViewById(R.id.photo_listview);

        // Setting the adapter to the listView
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.album_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add_photo:
                showAddPhoto();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void showAddPhoto(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new photo name:");

// Set up the input
        final EditText name = new EditText(this);
        final EditText caption = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        name.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        caption.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);

        builder.setView(name);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String n = name.getText().toString();
                Album a = new Album(n);
                user.addAlbum(a);
                List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

                for (int i = 0; i < user.albums.size(); i++) {
                    HashMap<String, String> hm = new HashMap<String, String>();
                    hm.put("album_name", user.albums.get(i).getName());
                    hm.put("thumbnail", Integer.toString(R.drawable.image1)); //actual image to be grabbed when we figure out image class in android
                    aList.add(hm);
                }

                // Keys used in Hashmap
                String[] from = {"album_name", "thumbnail"};

                // Ids of views in listview_layout
                int[] to = {R.id.name, R.id.thumbnail};

                // Instantiating an adapter to store each items
                // R.layout.listview_layout defines the layout of each item
                SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);

                // Getting a reference to listview of main.xml layout file
                ListView listView = (ListView) findViewById(R.id.listview);

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

        builder.setTitle("Enter caption:");
        builder.setView(caption);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String n = name.getText().toString();
                Album a = new Album(n);
                user.addAlbum(a);
                List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

                for (int i = 0; i < user.albums.size(); i++) {
                    HashMap<String, String> hm = new HashMap<String, String>();
                    hm.put("album_name", user.albums.get(i).getName());
                    hm.put("thumbnail", Integer.toString(R.drawable.image1)); //actual image to be grabbed when we figure out image class in android
                    aList.add(hm);
                }

                // Keys used in Hashmap
                String[] from = {"album_name", "thumbnail"};

                // Ids of views in listview_layout
                int[] to = {R.id.name, R.id.thumbnail};

                // Instantiating an adapter to store each items
                // R.layout.listview_layout defines the layout of each item
                SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);

                // Getting a reference to listview of main.xml layout file
                ListView listView = (ListView) findViewById(R.id.listview);

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

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String n = name.getText().toString();
                Album a = new Album(n);
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
                int[] to = {R.id.name, R.id.thumbnail};

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
