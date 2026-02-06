package com.example.mealplanner.presentation.search.view;

public class SearchItem {

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public SearchItem(String itemId, String itemName, String itemImage) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemImage = itemImage;
    }

    private String itemId;
    private String itemName;
    private String itemImage;

    @Override
    public String toString() {
        return "SearchItem{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemImage='" + itemImage + '\'' +
                '}';
    }
}
