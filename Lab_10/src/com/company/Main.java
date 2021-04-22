package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Company> getInfo(Document document) {
        NodeList companiesList = document.getElementsByTagName("company");
        ArrayList<Company> allCompanies = new ArrayList<>();

        for (int i = 0; i < companiesList.getLength(); i++){
            if (companiesList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element companyElement = (Element) companiesList.item(i);

                Company newCompany = new Company();
                newCompany.setName(companyElement.getAttribute("name"));

                NodeList productNodes = companyElement.getChildNodes();
                for (int j = 0; j < productNodes.getLength(); j++) {
                    if (productNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        Element productElement = (Element) productNodes.item(j);

                        String name = productElement.getAttribute("product");

                        NodeList softwareNodes = productElement.getChildNodes();
                        for (int m = 0; m < softwareNodes.getLength(); m++){
                            if (softwareNodes.item(m).getNodeType() == Node.ELEMENT_NODE) {
                                Product newProduct = new Product();
                                newProduct.setName(name);
                                Element softwareElement = (Element) softwareNodes.item(m);

                                newProduct.setPrice(softwareElement.getTextContent());
                                newCompany.addProduct(newProduct);
                            }
                        }
                    }
                }

                allCompanies.add(newCompany);
            }
        }

        return allCompanies;
    }

    public static void showInfo(ArrayList<Company> companies) {
        for (Company company : companies) {
            System.out.println(company.getName() + ":");
            for (int j = 0; j < company.getProduct().size(); j++) {
                System.out.println(" " + company.getProduct().get(j).getName() + " " + company.getProduct().get(j).getPrice() + "$");
            }
        }
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
        Company newCompany = new Company();
        newCompany.setName(company);
        companies.add(newCompany);
    }

    public static void addProduct(ArrayList<Company> companies, String company, String product, String price) {
        for (Company value : companies) {
            if (value.getName().equals(company)) {
                Product newProduct = new Product();
                newProduct.setName(product);
                newProduct.setPrice(price);
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

        File file = new File("D:\\University\\IPS-31\\2 semester\\Distributed-Calculations\\Lab_10\\database.xml");

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(file));
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        File file = new File("database.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);


        ArrayList<Company> companies = getInfo(document);
        changeCompanyName(companies, "Samsung", "New Oppo");
        changeProductName(companies, "New Oppo", "Note 12+", "Laptop");
        changeProductPrice(companies, "New Oppo", "Laptop", "10000");
        addCompany(companies, "OnePlus");
        addProduct(companies, "OnePlus", "9 Pro", "1400");
        removeProduct(companies, "Apple", "Iphone 12 Pro Max");
        removeCompany(companies, "Hewlett Packard");
        showInfo(companies);

        createXML(companies);
    }
}
