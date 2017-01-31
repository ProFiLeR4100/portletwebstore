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

        String[] ids = {"24371", "24381", "24391", "24401", "24411", "24421", "24431", "24441"};

        if (id > 0 && id <= 8) {

            CatalogItem item = new CatalogItem(id, "short desc" + id, "<b>Long description " + id + "</b> "
                            + "<ul><li>Detailed option1</li><li>Detailed option2</li><li>Detailed option3</li></ul>" ,
                    id + ".jpg", id + "b.jpg");

            item.setArticleId(ids[(int)id-1]);

            return item;
        } else {
            return null;
        }
    }
}
