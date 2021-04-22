package com.company;

import java.util.ArrayList;

public class Company {
    private ArrayList<Product> products;
    private String name;

    public Company() {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProduct() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product){
        this.products.removeIf(value -> value.getName().equals(product.getName()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
