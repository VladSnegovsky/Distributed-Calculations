package com.example.Servlet_OOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
    private ConnectionPool connectionPool;

    public Database(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public ArrayList<Company> getInformation() {
        ArrayList<Company> companies = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();){
            String sql = "select * from companies;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = Integer.parseInt(resultSet.getString(1));
                String name = resultSet.getString(2);
                Company company = new Company(id, name);
                companies.add(company);
            }
            statement.close();
            connectionPool.releaseConnection(connection);
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }

        for (Company company : companies) {
            ArrayList<Product> products = new ArrayList<>();
            try (Connection connection = connectionPool.getConnection();){
                String sql = "select * from products where companyname=?;";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, company.getName());
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()){
                    int id = Integer.parseInt(resultSet.getString(1));
                    String name = resultSet.getString(3);
                    String price = resultSet.getString(4);

                    Product product = new Product(id, name, price);
                    products.add(product);
                }
                statement.close();
                connectionPool.releaseConnection(connection);
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
            company.setProducts(products);
        }

        return companies;
    }
}
