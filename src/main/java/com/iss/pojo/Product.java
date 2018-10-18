package com.iss.pojo;

/**
 * 商品类
 *
 * @author SongChong created by 2018/10/9 0009 15:38
 */
public class Product {
    private String id;//商品id
    private String name;//商品名称
    private double price;//商品价格

    public Product() {
    }

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}