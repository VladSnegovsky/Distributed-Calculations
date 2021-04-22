package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static int getNextIDCompany(ArrayList<Company> companies) {
        int id = 0;
        if (companies != null) {
            for (Company company : companies) {
                if (company.getId() > id) { id = company.getId() + 1; }
                if (company.getId() == id) { id += 1; }
            }
        }
        return id;
    }

    public static int getNextIDProduct(ArrayList<Company> companies) {
        int id = 0;
        if (companies != null) {
            for (Company company : companies) {
                ArrayList<Product> products = company.getProduct();
                if (products != null) {
                    for (Product product : products) {
                        if (product.getId() > id) { id = product.getId() + 1; }
                    }
                }
            }
        }
        return id;
    }

    public static void changeCompanyName(ArrayList<Company> companies, String company, String newName){
        for (Company value : companies) {
            if (value.getName().equals(company)) {
                value.setName(newName);
            }
        }
    }

    public static void changeProductName(ArrayList<Company> companies, String company, String product, String newName){
        for (Company value : companies) {
            if (value.getName().equals(company)) {
                for (int j = 0; j < value.getProduct().size(); j++) {
                    if (value.getProduct().get(j).getName().equals(product)) {
                        value.getProduct().get(j).setName(newName);
                    }
                }
            }
        }
    }

    public static void changeProductPrice(ArrayList<Company> companies, String company, String product, String newPrice){
        for (Company value : companies) {
            if (value.getName().equals(company)) {
                for (int j = 0; j < value.getProduct().size(); j++) {
                    if (value.getProduct().get(j).getName().equals(product)) {
                        value.getProduct().get(j).setPrice(newPrice);
                    }
                }
            }
        }
    }

    public static void addCompany(ArrayList<Company> companies, String company) {
        Company newCompany = new Company(getNextIDCompany(companies), company);
        companies.add(newCompany);
    }

    public static void addProduct(ArrayList<Company> companies, String company, String product, String price) {
        for (Company value : companies) {
            if (value.getName().equals(company)) {
                Product newProduct = new Product(getNextIDProduct(companies), product, price);
                value.addProduct(newProduct);
            }
        }
    }

    public static void removeProduct(ArrayList<Company> companies, String company, String product) {
        for (Company value : companies) {
            if (value.getName().equals(company)) {
                for (int j = 0; j < value.getProduct().size(); j++) {
                    if (value.getProduct().get(j).getName().equals(product)) {
                        value.removeProduct(value.getProduct().get(j));
                    }
                }
            }
        }
    }

    public static void removeCompany(ArrayList<Company> companies, String company) {
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getName().equals(company)) {
                for (int j = 0; j < companies.get(i).getProduct().size(); j++) {
                    removeProduct(companies, company, companies.get(i).getProduct().get(j).getName());
                }
                companies.remove(companies.get(i));
            }
        }
    }

    public static void createXML(ArrayList<Company> companies) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = factory.newDocumentBuilder().newDocument();

        Element list = doc.createElement("list");
        Element comp = doc.createElement("companies");
        for (Company company : companies) {
            Element newCompany = doc.createElement("company");
            newCompany.setAttribute("name", company.getName());

            for (Product product : company.getProduct()) {
                Element newSoftware = doc.createElement("Software");
                newSoftware.setAttribute("product", product.getName());

                Element price = doc.createElement("price");
                price.setTextContent(product.getPrice());

                newSoftware.appendChild(price);
                newCompany.appendChild(newSoftware);
            }
            comp.appendChild(newCompany);
        }
        list.appendChild(comp);
        doc.appendChild(list);

        File file = new File("D:\\University\\IPS-31\\2 semester\\Distributed-Calculations\\Lab_11\\database.xml");

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(file));
    }

    public static void main(String[] args) throws TransformerException, ParserConfigurationException {
        ConnectionPool connectionPool = new ConnectionPool();
        Database database = new Database(connectionPool);
        ArrayList<Company> companies = database.getInformation();

//        for (Company company : companies) {
//            System.out.println(company.getName() + "{" + company.getId() + "}");
//            for (int i = 0; i < company.getProduct().size(); i++) {
//                System.out.println(" " + company.getProduct().get(i).getName() + " " + company.getProduct().get(i).getPrice() + "$ {" + company.getProduct().get(i).getId() + "}");
//            }
//        }
//
//        changeCompanyName(companies, "Samsung", "New Oppo");
//        changeProductName(companies, "New Oppo", "Note 12+", "Note 12");
//        changeProductPrice(companies, "New Oppo", "Note 12", "10000");
//        removeCompany(companies, "Hewlett Packard");
//        addCompany(companies, "OnePlus");
//        addProduct(companies, "OnePlus", "9 Pro", "1400");
//        removeProduct(companies, "Apple", "Iphone 12 Pro Max");
//
//        System.out.println("\n\n\n");
//
//        for (Company company : companies) {
//            System.out.println(company.getName() + "{" + company.getId() + "}");
//            for (int i = 0; i < company.getProduct().size(); i++) {
//                System.out.println(" " + company.getProduct().get(i).getName() + " " + company.getProduct().get(i).getPrice() + "$ {" + company.getProduct().get(i).getId() + "}");
//            }
//        }

        createXML(companies);

        database.setInformation(companies);
    }



//    Samsung{1}
// Note 12+ 1200 {1}
// Samsung Galaxy Book Flex Alpha 1900 {2}
//Apple{2}
// Iphone 12 Pro Max 1200 {3}
// MacBook Pro 2500 {4}
// Apple Watch Series 6 500 {5}
//Hewlett Packard{3}
// HP Envy Laptop 800 {6}
}
