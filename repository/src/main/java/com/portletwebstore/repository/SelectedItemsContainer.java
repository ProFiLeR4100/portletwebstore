package com.portletwebstore.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima_2 on 23.01.2017.
 */
public class SelectedItemsContainer {

    private List<Long> selectedItems = new ArrayList<Long>();


    public void addItem(long id) {

        //TODO
        //check added

        if (!selectedItems.contains(id)) {
            selectedItems.add(id);
        }
    }

    public void removeItem(long id) {

        if (selectedItems.contains(id)) {
            selectedItems.remove(id);
        }

    }

    public List<Long> getSelectedItems() {
        return selectedItems;
    }

}
