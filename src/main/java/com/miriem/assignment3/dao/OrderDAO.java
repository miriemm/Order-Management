package com.miriem.assignment3.dao;

import com.miriem.assignment3.models.Order;

import java.util.List;

/**
 * Class that extends AbstractDAO class
 * Specifies CRUD operations for the Order table
 * @see AbstractDAO
 */
public class OrderDAO extends AbstractDAO<Order>{


    public Order insert(Order t) {
        return super.insert(t);
    }

    public Order findById(int t) {
        return super.findById(t);
    }

    public Order update(Order t) {
        return super.update(t);
    }

    public void deleteById(int id) {
        super.deleteById(id);
    }

    public List<Order> getOrders(){
        return super.findAll();
    }
}

