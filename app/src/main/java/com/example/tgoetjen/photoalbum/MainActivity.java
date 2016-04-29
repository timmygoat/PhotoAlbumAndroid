package com.example.tgoetjen.photoalbum;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String m_Text = "";
    HashMap<String, String> hm = new HashMap<String,String>();
    public User user;

    public void showPhotos(int pos) {
        ((PhotoAlbumApplication)getApplication()).setCurrentAlbum(pos);
        Intent intent = new Intent(this, ShowPhotos.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.listview) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(user.albums.get(info.position).getName());
            String[] menuItems = getResources().getStringArray(R.array.album_menu_array);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.album_menu_array);
        String menuItemName = menuItems[menuItemIndex];
        String listItemName = user.albums.get(info.position).getName();
        if(menuItemName.equals("Rename Album")){
            showRenameAlbum(info.position);
        }
        TextView text = (TextView)findViewById(R.id.footer);
        text.setText(String.format("Selected %s for item %s", menuItemName, listItemName));
        return true;
    }

    public void showAlbumOptions(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = ((PhotoAlbumApplication)getApplication()).getUser();
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
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = ( ListView ) findViewById(R.id.listview);

        // Setting the adapter to the listView
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                        showPhotos(position);
                    }
                });
        listView.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showAlbumOptions();
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void showRenameAlbum(final int  selected){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new name for current album:");

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
                user.albums.get(selected).setName(m_Text);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_create_album:
                showAddAlbum();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    protected void onResume(){
        super.onResume();
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

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView,
                                            View view,
                                            int i,
                                            long l) {
                        showPhotos(i);
                    }
                });
    }
}
