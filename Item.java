package com.annguyen.riki;

import android.net.Uri;
import android.widget.ImageView;

public class Item {
    private String imagePath;
    private String name;
    private String description;

    public Item(String imagePath, String name, String description) {
        this.imagePath = imagePath;
        this.name = name;
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

