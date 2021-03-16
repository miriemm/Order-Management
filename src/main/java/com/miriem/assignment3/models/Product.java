package com.miriem.assignment3.models;

/**
 * Defines a product as seen in the database
 */
public class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;


    public Product() {
    }

    /**
     * Used for database update
     * @param id product id
     * @param name product name
     * @param quantity product quantity
     * @param price product price
     */
    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @param name product name
     * @param quantity product quantity
     * @param price product price
     */
    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }


    /**
     * get product id
     * @return product id
     */
    public int getId() {
        return id;
    }

    /**
     * sets product id
     * @param id product id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets product name
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * sets product name
     * @param name product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets product quantity
     * @return product quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * sets product quantity
     * @param quantity product quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * gets product price
     * @return product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * sets product price
     * @param price product price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
