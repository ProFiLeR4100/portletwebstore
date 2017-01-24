package com.portletwebstore.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima_2 on 23.01.2017.
 */
public class SelectedItemsContainer implements Serializable {

    private List<Long> selectedItems = new ArrayList<Long>();

    public void addItem(long id) {

        //TODO
        //check added

        if (!selectedItems.contains(id)) {
            selectedItems.add(id);
        }
    }

    public void setItemsFromArray(Long[] array) {
        selectedItems.clear();
        for (Long item : array) {
            selectedItems.add(item);
        }
    }

    public Long[] getItemsAsArray() {
        return selectedItems.toArray(new Long[selectedItems.size()]);
    }

    public void removeItem(long id) {

        if (selectedItems.contains(id)) {
            selectedItems.remove(id);
        }
    }

    public List<Long> getSelectedItems() {
        return selectedItems;
    }

    public int getSelectedCount() {
        return selectedItems.size();
    }

}
