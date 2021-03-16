package com.miriem.assignment3.models;

/**
 * Defines an order as seen in the database
 */
public class Order {
    private int id;
    private String clientName;
    private String productName;
    private int quantity;

    public Order() {
    }

    /**
     * @param id id
     * @param clientName client name
     * @param productName product name
     * @param quantity product quantity
     */
    public Order(int id, String clientName, String productName, int quantity) {
        this.id = id;
        this.clientName = clientName;
        this.productName = productName;
        this.quantity = quantity;
    }

    /**
     * @param clientName client name
     * @param productName product name
     * @param quantity product quantity
     */
    public Order(String clientName, String productName, int quantity) {
        this.clientName = clientName;
        this.productName = productName;
        this.quantity = quantity;
    }

    /**
     * gets id
     * @return id
     */
    public int getId() { return id; }

    /**
     * sets id
     * @param id id
     */
    public void setId(int id) { this.id = id; }

    /**
     * gets client name
     * @return  client name
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * sets client name
     * @param clientName client name
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * gets product name
     * @return product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * sets product name
     * @param productName product name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * gets quantity
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * sets quantity
     * @param quantity quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

