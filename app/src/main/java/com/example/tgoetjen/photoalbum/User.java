package com.example.tgoetjen.photoalbum;

/**
 * Created by tgoet on 4/28/2016.
 */
/*
 * @author Tim Goetjen
 * @author Kinh Hoang
 */
        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.io.Serializable;
        import java.util.ArrayList;

/**
 * User Model class
 *
 * @field  serialVersionUID version used for serialization
 * @field  albums user albums
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    public ArrayList<Album> albums = new ArrayList<Album>();
    public int numAlbums;

    /**
     * User Constructor.
     *
     * @return User
     */
    public User() {
        numAlbums = 0;
        albums.add(new Album("Stuff"));
    }

    /**
     * Adds album to user
     *
     * @param a album to add
     */
    public void addAlbum(Album a) {
        albums.add(a);
        numAlbums++;
    }

    /**
     * Removes album from user
     *
     * @param a album to remove
     */
    public void removeAlbum(Album a) {
        albums.remove(a);
        numAlbums--;
    }
}