package com.andre.tcd.dto;

public class DtoProduct {

    private int id;
    private String name;
    private String description;
    private String imgUrl;
    private int price;

    public DtoProduct(String name, String description, String imgUrl, int price) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
