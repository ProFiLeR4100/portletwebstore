package com.portletwebstore.repository;

import java.util.List;

/**
 * Created by dima_2 on 23.01.2017.
 */
public class CatalogStub {

    public static Catalog getCatalog() {

        Catalog catalog = new Catalog();

        catalog.addItem(new CatalogItem(1, "short desc", "long desc"));
        catalog.addItem(new CatalogItem(2, "short desc", "long desc"));
        catalog.addItem(new CatalogItem(3, "short desc", "long desc"));
        catalog.addItem(new CatalogItem(4, "short desc", "long desc"));
        catalog.addItem(new CatalogItem(5, "short desc", "long desc"));
        catalog.addItem(new CatalogItem(6, "short desc", "long desc"));
        catalog.addItem(new CatalogItem(7, "short desc", "long desc"));
        catalog.addItem(new CatalogItem(8, "short desc", "long desc"));

        return catalog;
    }
}
