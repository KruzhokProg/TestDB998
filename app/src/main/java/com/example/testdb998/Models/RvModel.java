package com.example.testdb998.Models;

import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RvModel {

    private String name;
    private Integer image;

    public RvModel() { }

    public RvModel(String name, Integer image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
