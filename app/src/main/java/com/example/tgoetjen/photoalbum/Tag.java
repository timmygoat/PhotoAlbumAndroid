package com.example.tgoetjen.photoalbum;

/**
 * Created by tgoet on 4/28/2016.
 */
//Tim Goetjen and Kinh Hoang
import java.io.Serializable;

public class Tag implements Comparable<Tag>, Serializable {
    private static final long serialVersionUID = 1L;
    public String key;
    public String value;

    public Tag(String k, String v){
        this.key = k;
        this.value = v;
    }

    public int compareTo(Tag t){
        return key.compareTo(t.key);
    }
}

