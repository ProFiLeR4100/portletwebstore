package com.portletwebstore.repository;

import java.util.List;

/**
 * Created by dima_2 on 23.01.2017.
 */
public class CatalogStub {

    public static Catalog getCatalog() {

        Catalog catalog = new Catalog();

        catalog.addItem(new CatalogItem(1, "short desc1", "Long description 1", "1.jpg", "1b.jpg"));
        catalog.addItem(new CatalogItem(2, "short desc2", "Long description 2", "2.jpg", "2b.jpg"));
        catalog.addItem(new CatalogItem(3, "short desc3", "Long description 3", "3.jpg", "3b.jpg"));
        catalog.addItem(new CatalogItem(4, "short desc4", "Long description 4", "4.jpg", "4b.jpg"));
        catalog.addItem(new CatalogItem(5, "short desc5", "Long description 5", "5.jpg", "5b.jpg"));
        catalog.addItem(new CatalogItem(6, "short desc6", "Long description 6", "6.jpg", "6b.jpg"));
        catalog.addItem(new CatalogItem(7, "short desc7", "Long description 7", "7.jpg", "7b.jpg"));
        catalog.addItem(new CatalogItem(8, "short desc8", "Long description 8", "8.jpg", "8b.jpg"));

        return catalog;
    }

    public CatalogItem getCatalogItemById(long id) {
        if (id > 0 && id <= 8) {
            return new CatalogItem(id, "short desc" + id, "Long description " + id, id + ".jpg", id + "b.jpg");
        } else {
            return null;
        }
    }
}
