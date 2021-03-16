package com.miriem.assignment3.dao;


import com.miriem.assignment3.models.Client;

import java.util.List;

/**
 * Class that extends the generic AbstractDAO class
 * Specifies CRUD operations for the Client table
 * @see AbstractDAO
 */
public class ClientDAO extends AbstractDAO<Client> {

    public Client insert(Client t) {
        return super.insert(t);
    }

    public Client findById(int t) {
        return super.findById(t);
    }

    public Client update(Client t) {
        return super.update(t);
    }

    public void deleteById(int id) {
        super.deleteById(id);
    }

    public List<Client> findAll() {
        return super.findAll();
    }
}
