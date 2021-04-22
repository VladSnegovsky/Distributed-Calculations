package com.company;

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

                try (Connection connection1 = connectionPool.getConnection();){
                    String sql1 = "delete from companies where id=?;";
                    PreparedStatement statement1 = connection1.prepareStatement(sql1);
                    statement1.setInt(1, id);
                    statement1.executeUpdate();
                    statement1.close();
                    connectionPool.releaseConnection(connection1);
                } catch (InterruptedException | SQLException e) {
                    e.printStackTrace();
                }

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

                    try (Connection connection1 = connectionPool.getConnection();){
                        String sql1 = "delete from products where idproducts=?;";
                        PreparedStatement statement1 = connection1.prepareStatement(sql1);
                        statement1.setInt(1, id);
                        statement1.executeUpdate();
                        statement1.close();
                        connectionPool.releaseConnection(connection1);
                    } catch (InterruptedException | SQLException e) {
                        e.printStackTrace();
                    }

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

    public void setInformation(ArrayList<Company> companies) {
        for (Company company : companies) {
            try (Connection connection = connectionPool.getConnection();){
                String sql = "insert into companies (id, name) values(?, ?);";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, company.getId());
                statement.setString(2, company.getName());
                statement.executeUpdate();

                statement.close();
                connectionPool.releaseConnection(connection);
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }

            for (Product product : company.getProduct()) {
                try (Connection connection = connectionPool.getConnection();){
                    String sql = "insert into products (idproducts, companyname, productname, price) values(?, ?, ?, ?);";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, product.getId());
                    statement.setString(2, company.getName());
                    statement.setString(3, product.getName());
                    statement.setString(4, product.getPrice());
                    statement.executeUpdate();

                    statement.close();
                    connectionPool.releaseConnection(connection);
                } catch (InterruptedException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
