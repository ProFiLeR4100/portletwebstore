package com.portletwebstore.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima_2 on 23.01.2017.
 */
public class CatalogItem {

    private long id;
    private String shortDescription;
    private String longDescription;
    private String smallImage;
    private String bigImage;
    private List<String> additionalOptions;

    public CatalogItem() {
        additionalOptions = new ArrayList<String>();

        for (int i = 1; i <= 5; i++) {
            additionalOptions.add("Additional option " + i);
        }

    }

    public CatalogItem(long id, String shortDescription, String longDescription, String smallImage, String bigImage) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.smallImage = smallImage;
        this.bigImage = bigImage;

        additionalOptions = new ArrayList<String>();

        for (int i = 1; i <= 4; i++) {
            additionalOptions.add("Additional option " + i);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getBigImage() {
        return bigImage;
    }

    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }

    public List<String> getAdditionalOptions() {
        return additionalOptions;
    }

    public void setAdditionalOptions(List<String> additionalOptions) {
        this.additionalOptions = additionalOptions;
    }
}
