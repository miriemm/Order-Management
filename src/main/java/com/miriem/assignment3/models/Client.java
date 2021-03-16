package com.miriem.assignment3.models;

/**
 * Defines a client as seen in the database
 */
public class Client {

    private int id;
    private String name;
    private String city;

    public Client() {
    }

    /**
     * @param id client id
     * @param name client name
     * @param city client city
     */
    public Client(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Instantiates a new client. ID is later set from the database
     * @param name client name
     * @param city client city
     */
    public Client(String name, String city) {
        super();
        this.name = name;
        this.city = city;
    }

    /**
     * gets client id
     * @return client id
     */
    public int getId() {
        return id;
    }

    /**
     * sets client id
     * @param id client id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets client name
     * @return client name
     */
    public String getName() {
        return name;
    }

    /**
     * sets client name
     * @param name client name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets client city
     * @return client city
     */
    public String getCity() {
        return city;
    }

    /**
     * sets client city
     * @param city client city
     */
    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return "Client [idClient=" + id + ", clientName=" + name + ", clientCity=" + city + "]";
    }
}
