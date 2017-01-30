package com.portletwebstore.repository;

import java.io.Serializable;

/**
 * Created by dima_2 on 30.01.2017.
 */
public class AdditionalOption implements Serializable {

    private long id;
    private String name;
    private Boolean optionSelected = true;

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

    public Boolean getOptionSelected() {
        return optionSelected;
    }

    public void setOptionSelected(Boolean optionSelected) {
        this.optionSelected = optionSelected;
    }
}
