package com.example.tgoetjen.photoalbum;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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
    SimpleAdapter adapter;
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
        adapter = new SimpleAdapter(this, aList, R.layout.listview_layout, from, to);

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
        final Picture p = new Picture();
        user = ((PhotoAlbumApplication)getApplication()).getUser();
        currentAlbum = ((PhotoAlbumApplication)getApplication()).getCurrentAlbum();
        LayoutInflater factory = LayoutInflater.from(this);

//text_entry is an Layout XML file containing two text field to display in alert dialog
        final View textEntryView = factory.inflate(R.layout.text_entry,null);

        final EditText name = (EditText)textEntryView.findViewById(R.id.EditText1);
        final EditText caption = (EditText)textEntryView.findViewById(R.id.EditText2);

        name.setHint("Enter name:");
        caption.setHint("Enter caption:");

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Enter photo information:");
        alert.setView(textEntryView);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String c = caption.getText().toString();
                String n = name.getText().toString();
                p.caption = c;
                p.name = n;
                user.albums.get(currentAlbum).addPicture(p);
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
                adapter = new SimpleAdapter(getApp(), aList, R.layout.listview_layout, from, to);

                // Getting a reference to listview of main.xml layout file
                ListView listView = (ListView) findViewById(R.id.photo_listview);

                // Setting the adapter to the listView
                listView.setAdapter(adapter);
                }
            });
             alert.setNegativeButton("Cancel",
             new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int whichButton) {dialog.cancel();}});
             alert.show();
    }
}
