package com.portletwebstore.repository;

import java.util.List;

/**
 * Created by dima_2 on 23.01.2017.
 */
public class CatalogStub {

    public static Catalog getCatalog() {

        Catalog catalog = new Catalog();

        catalog.addItem(new CatalogItem(1, "short desc1", "<ul><li>Detailed option1</li><li>Detailed option2</li><li>Detailed option3</li></ul>", "1.jpg", "1b.jpg"));
        catalog.addItem(new CatalogItem(2, "short desc2", "<ul><li>Detailed option1</li><li>Detailed option2</li><li>Detailed option3</li></ul>", "2.jpg", "2b.jpg"));
        catalog.addItem(new CatalogItem(3, "short desc3", "<ul><li>Detailed option1</li><li>Detailed option2</li><li>Detailed option3</li></ul>", "3.jpg", "3b.jpg"));
        catalog.addItem(new CatalogItem(4, "short desc4", "<ul><li>Detailed option1</li><li>Detailed option2</li><li>Detailed option3</li></ul>", "4.jpg", "4b.jpg"));
        catalog.addItem(new CatalogItem(5, "short desc5", "<ul><li>Detailed option1</li><li>Detailed option2</li><li>Detailed option3</li></ul>", "5.jpg", "5b.jpg"));
        catalog.addItem(new CatalogItem(6, "short desc6", "<ul><li>Detailed option1</li><li>Detailed option2</li><li>Detailed option3</li></ul>", "6.jpg", "6b.jpg"));
        catalog.addItem(new CatalogItem(7, "short desc7", "<ul><li>Detailed option1</li><li>Detailed option2</li><li>Detailed option3</li></ul>", "7.jpg", "7b.jpg"));
        catalog.addItem(new CatalogItem(8, "short desc8", "<ul><li>Detailed option1</li><li>Detailed option2</li><li>Detailed option3</li></ul>", "8.jpg", "8b.jpg"));

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
