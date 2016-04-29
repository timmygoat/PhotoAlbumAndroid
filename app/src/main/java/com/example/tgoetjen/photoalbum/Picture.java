package com.example.tgoetjen.photoalbum;

/**
 * Created by tgoet on 4/28/2016.
 */
/*
 * @author Tim Goetjen
 * @author Kinh Hoang
 */
        import android.media.Image;

        import java.io.File;
        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.io.Serializable;
        import java.net.URI;
        import java.net.URISyntaxException;
        import java.util.ArrayList;
        import java.util.Calendar;

/*
 * Picture class to develop picture objects
 * @field serialVersionUID used for serialization
 * @field name used for name of picture
 * @field tags used to hold tags for picture
 * @field date used to hold last modified date
 * @field caption used to hold caption
 * @field URL used to hold URL of picture
 * @field o_tags used to display tags
 * @field pic used to hold image
 */
public class Picture implements Comparable<Picture>, Serializable{
    private static final long serialVersionUID = 1L;
    String name;
    ArrayList<Tag> tags = new ArrayList<Tag>();
    Calendar date;
    String caption;
    String URL;
    Image pic;
    /*
     * Constructor for picture oibject
     * @param n name
     * @param caption caption
     * @param URL URL
     */
    public Picture(String n, String caption, String URL){
//            throws URISyntaxException, IllegalArgumentException{
        this.name = n;
        this.caption = caption;
        this.date = Calendar.getInstance();
//        File fp = new File(new URI(URL));
//        date.setTimeInMillis(fp.lastModified());
        date.set(Calendar.MILLISECOND, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND,0);
        date.set(Calendar.HOUR,0);
        date.set(Calendar.MONTH, date.get(Calendar.MONTH) + 1);
        this.URL = URL;
    }

    public Image getImage(){
        return pic;
    }
    /*
     * Utilizes pictures names for comparison
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Picture p) {
        return this.name.toLowerCase().compareTo(p.name.toLowerCase());
    }

    /*
     * Getter for URL string
     * @return URL as String
     */
    public String getURL(){
        return URL;
    }

    /*
     * Getter for date
     * @return date as String
     */
    public String getDateString(){
        return this.date.get(Calendar.MONTH)+"/"+this.date.get(Calendar.DAY_OF_MONTH)+"/"+this.date.get(Calendar.YEAR);
    }

    /*
     * Getter for calendar object
     * @return date
     */
    public Calendar getCal(){
        return this.date;
    }

    /*
     * Getter for caption
     * @return caption as String
     */
    public String getCaption(){
        return this.caption;
    }

    /*
     * Setter for caption
     * @param c new caption
     */
    public void setCaption(String c){
        this.caption = c;
    }

    /*
     * Getter for name of picture
     * @return name as String
     */
    public String getName(){
        return name;
    }

    /*
     * Getter for tag arraylist as String
     * @return tags as String
     */
    public String getTags(){
        return this.tags.toString();
    }

    /*
     * Getter for tag keys
     * @return keys
     */
    public String getTagKeys(){
        String answer = "";
        for(Tag t : tags){
            answer += t.key.toString() + "\n";
        }
        return answer;
    }

    /*
     * Getter for tag vals
     * @return vals
     */
    public String getTagVals(){
        String answer = "";
        for(Tag t : tags){
            answer += t.value.toString() + "\n";
        }
        return answer;
    }

    /*
     * Getter for tag list
     * @return array list of tags
     */
    public ArrayList<Tag> getTagList(){
        return this.tags;
    }

    /*
     * Add tag function
     * @param tag to add
     */
    public void addTag(Tag tag){
        tags.add(tag);
    }

    /*
     * remove tag function
     * @param tag to remove
     */
    public void removeTag(Tag tag){
        tags.remove(tag);
    }
}

