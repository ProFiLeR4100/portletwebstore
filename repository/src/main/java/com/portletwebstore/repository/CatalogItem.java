package com.portletwebstore.repository;

import java.util.List;

/**
 * Created by dima_2 on 23.01.2017.
 */
public class CatalogItem {

    private long id;
    private String shortDescription;
    private String longDescription;

    public CatalogItem() {
    }

    public CatalogItem(long id, String shortDescription, String longDescription) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
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
}
