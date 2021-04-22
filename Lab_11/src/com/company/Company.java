package com.company;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private int id;
    private String name;
    private ArrayList<Product> products;

    public Company(int id, String name) {
        this.id = id;
        this.name = name;
        products = new ArrayList<>();
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

    public ArrayList<Product> getProduct() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product){
        this.products.removeIf(value -> value.getName().equals(product.getName()));
    }
}
