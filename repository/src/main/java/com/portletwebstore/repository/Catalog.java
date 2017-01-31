package com.portletwebstore.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima_2 on 23.01.2017.
 */
public class Catalog implements Serializable {

    private List<CatalogItem> catalogItems;

    public Catalog() {
        catalogItems = new ArrayList<CatalogItem>();
    }

    public List<CatalogItem> getCatalogItems() {
        return catalogItems;
    }

    public List<List<CatalogItem>> getPartitionedItems(final int batchSize) {
        return partitionItems(batchSize);
    }

    public void setCatalogItems(List<CatalogItem> catalogItems) {
        this.catalogItems = catalogItems;
    }

    public void addItem(CatalogItem catalogItem) {
        //TODO
        //check if there is no item with same id
        catalogItems.add(catalogItem);
    }

    public void changeItemCheckedFlag(long itemId, long optionId, boolean checked) {

        for (CatalogItem item : catalogItems) {
            if (item.getId() == itemId) {
                for (AdditionalOption option : item.getAdditionalOptions()) {
                    if (option.getId() == optionId) {
                        option.setOptionSelected(checked);
                        break;
                    }
                }
                break;
            }
        }

    }

    private List<List<CatalogItem>> partitionItems(final int batchSize)
    {
        assert(batchSize > 0);
        assert(catalogItems != null);
        assert(catalogItems.size() + batchSize <= Integer.MAX_VALUE); //avoid overflow

        int idx = 0;

        List<List<CatalogItem>> result = new ArrayList<List<CatalogItem>>();

        for (idx = 0; idx + batchSize <= catalogItems.size(); idx += batchSize) {
            result.add(catalogItems.subList(idx, idx + batchSize));
        }
        if (idx < catalogItems.size()) {
            result.add(catalogItems.subList(idx, catalogItems.size()));
        }

        return result;
    }
}
