package com.kgisl.spring1.controller;

import java.sql.Date;

public class Item {

    private int itemId;
    private String itemName;
    private Date expDate;
    private int price;
    private String category;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Item [itemId=" + itemId + ", itemName=" + itemName + ", expDate=" + expDate + ", price=" + price
                + ", category=" + category + "]";
    }

    public Item() {
        this.itemId = itemId;
        this.itemName = itemName;
        this.expDate = expDate;
        this.price = price;
        this.category = category;
    }

}