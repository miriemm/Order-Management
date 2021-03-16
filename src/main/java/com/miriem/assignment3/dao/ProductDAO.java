package com.miriem.assignment3.dao;

import com.miriem.assignment3.models.Product;

import java.util.List;

/**
 * Class that extends AbstractDAO
 *  Specifies CRUD operations for the Product table
 * @see AbstractDAO
 */
public class ProductDAO extends AbstractDAO<Product> {
    public Product insert(Product t) {
        return super.insert(t);
    }

    public Product findById(int t) {
        return super.findById(t);
    }

    public Product update(Product t) {
        return super.update(t);
    }

    public void deleteById(int id) {
        super.deleteById(id);
    }

    public List<Product> findAll() {
        return super.findAll();
    }

}
