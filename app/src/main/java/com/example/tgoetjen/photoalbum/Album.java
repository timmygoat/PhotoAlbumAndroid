package com.example.tgoetjen.photoalbum; /**
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
import java.util.Calendar;

/*
 * com.example.tgoetjen.photoalbum.Album class used to develop album objects
 * @field serialVersionUID used for serialization of album data
 * @field photos used as arraylist holder of picture objects
 * @field o_photos used for displaying photos in UI
 * @field name used as name of album
 * @field numPhotos used to keep track of number of photos in album
 * @field oldDate used to hold the oldest date out of all photos in album
 * @field newDate used to hold the most recent date out of all photos in album
 */
public class Album implements Comparable<Album>, Serializable{
    private static final long serialVersionUID = 1L;
    public ArrayList<Picture> photos = new ArrayList<Picture>();
    private String name;
    private int numPhotos;
    private Calendar oldDate;
    private Calendar newDate;

    /*
     * com.example.tgoetjen.photoalbum.Album constructor to set up album object
     * @param name used to set name
     */
    public Album(String name){
        this.name = name;
        this.numPhotos = 0;
        this.oldDate = Calendar.getInstance();
        this.newDate = Calendar.getInstance();
    }

    /*
     * Getter for name field
     * @return name as String
     */
    public String getName(){
        return name;
    }

    /*
     * Setter for name field
     * @param name to set as
     */
    public void setName(String name){
        this.name = name;
    }

    /*
     * Getter for the number of photos in album as String
     * @return number of photos as String
     */
    public String getNumPhotos(){
        return Integer.toString(numPhotos);
    }

    /*
     * Getter for oldDate formatted MM/DD/YYYY
     * @return oldDate in String format
     */
    public String getOldDate(){
        return oldDate.get(Calendar.MONTH)+"/"+oldDate.get(Calendar.DAY_OF_MONTH)+"/"+oldDate.get(Calendar.YEAR);
    }

    /*
     * Getter for newDate formatted MM/DD/YYYY
     * @return newDate in String format
     */
    public String getNewDate(){
        return newDate.get(Calendar.MONTH)+"/"+newDate.get(Calendar.DAY_OF_MONTH)+"/"+newDate.get(Calendar.YEAR);
    }

    /*
     * Add picture to both lists in album, updates dates and numPhotos
     * @param p picture to add to album
     */
    public void addPicture(Picture p){
        if(numPhotos==0){
            oldDate = p.getCal();
            newDate = p.getCal();
        }
        else{
            if(p.getCal().compareTo(oldDate)<0) oldDate = p.getCal();
            if(p.getCal().compareTo(newDate)>0) newDate = p.getCal();
        }
        photos.add(p);
        this.numPhotos++;
    }

    /*
     * Removes picture from both lists in album, updates dates and numPhotos
     * @param p picture to remove from album
     */
    public void removePicture(Picture p){
        if(photos.size() == 1){
            photos.remove(p);
            oldDate = null;
            newDate = null;
        }
        if(p.getCal().equals(oldDate)){
            photos.remove(p);
            oldDate = photos.get(0).getCal();
            for(int i = 1; i < photos.size(); i++){
                if(photos.get(i).getCal().compareTo(oldDate)<0) oldDate = photos.get(i).getCal();
            }
        }
        if(p.getCal().equals(newDate)){
            photos.remove(p);
            newDate = photos.get(0).getCal();
            for(int i = 1; i < photos.size(); i++){
                if(photos.get(i).getCal().compareTo(newDate)>0) newDate = photos.get(i).getCal();
            }
        }
        this.numPhotos--;
    }

    /*
     * Compare function used to compare album names for sort
     * @param a album to compare to
     * @return see standard
     */
    public int compareTo(Album a){
        return this.getName().toLowerCase().compareTo(a.getName().toLowerCase());
    }
}

