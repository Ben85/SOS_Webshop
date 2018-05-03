package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductCategoryDaoDatabase extends DatabaseConnection implements ProductCategoryDao{

    String table = "product_categories";

    @Override
    public void add(ProductCategory category) {
        String name = category.getName();
        String description = category.getDescription();
        String[] parameters = {name, description};

    }

    @Override
    public ProductCategory find(int id) {
        HashMap<String, Object> productCatecory = new HashMap<String, Object>(select(id));

        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }

    private HashMap<String, Object> select(int id){
        String queryString = "SELECT * FROM " + table + " WHERE id = " + id;
        return executeQuery(queryString, null);
    }
}
