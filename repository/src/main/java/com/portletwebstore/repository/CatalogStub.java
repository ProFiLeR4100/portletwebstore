package com.portletwebstore.repository;

import java.util.List;

/**
 * Created by dima_2 on 23.01.2017.
 */
public class CatalogStub {

    public static Catalog getCatalog() {

        Catalog catalog = new Catalog();

        for (int i = 1; i <= 8; i++) {
            catalog.addItem(getCatalogItemById(i));
        }

        return catalog;
    }

    public static CatalogItem getCatalogItemById(long id) {
        if (id > 0 && id <= 8) {
            return new CatalogItem(id, "short desc" + id, "<b>Long description " + id + "</b> "
                    + "<ul><li>Detailed option1</li><li>Detailed option2</li><li>Detailed option3</li></ul>" ,
                    id + ".jpg", id + "b.jpg");
        } else {
            return null;
        }
    }
}
