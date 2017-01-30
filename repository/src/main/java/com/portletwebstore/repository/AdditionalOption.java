package com.portletwebstore.repository;

/**
 * Created by dima_2 on 30.01.2017.
 */
public class AdditionalOption {

    private long id;
    private String name;


    public AdditionalOption() {
    }

    public AdditionalOption(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
