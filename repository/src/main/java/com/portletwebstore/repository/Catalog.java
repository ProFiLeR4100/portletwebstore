package com.portletwebstore.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima_2 on 23.01.2017.
 */
public class Catalog {

    private List<CatalogItem> catalogItems;

    public Catalog() {
        catalogItems = new ArrayList<CatalogItem>();
    }

    public List<CatalogItem> getCatalogItems() {
        return catalogItems;
    }

    public void setCatalogItems(List<CatalogItem> catalogItems) {
        this.catalogItems = catalogItems;
    }

    public void addItem(CatalogItem catalogItem) {
        //TODO
        //check if there is no item with same id
        catalogItems.add(catalogItem);
    }
}
