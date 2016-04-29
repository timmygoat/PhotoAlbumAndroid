package com.example.tgoetjen.photoalbum;

import android.app.Application;

/**
 * Created by tgoet on 4/28/2016.
 */
public class PhotoAlbumApplication extends Application {
    private User user = new User();
    private int currentAlbum = -1;

    public User getUser(){return user;}
    public void setUser(User u){this.user = u;}
    public int getCurrentAlbum(){return currentAlbum;}
    public void setCurrentAlbum(int i){currentAlbum = i;}

}
