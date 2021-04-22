<%@ page import="com.example.Servlet_OOP.ConnectionPool" %>
<%@ page import="com.example.Servlet_OOP.Database" %>
<%@ page import="com.example.Servlet_OOP.Company" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <title> Log in </title>
    <style>
        table.iksweb caption{font-size: 40px; padding: 2%;font-family: Monospace;}
        table.iksweb{text-decoration: none;border-collapse:collapse;width:100%;text-align:center; width:50%; margin-left: 25%;}
        table.iksweb th{font-weight:normal;font-size:31px; color:#ffffff;background-color:#354251;}
        table.iksweb td{font-size:19px;color:#354251;}
        table.iksweb td,table.iksweb th{white-space:pre-wrap;padding:15px 19px;line-height:19px;vertical-align: middle;border: 2px solid #354251;}	table.iksweb tr:hover{background-color:#f9fafb}
        table.iksweb tr:hover td{color:#354251;cursor:pointer;}
    </style>
</head>

    <body>
        <%
            ConnectionPool connectionPool = new ConnectionPool();
            Database database = new Database(connectionPool);
            ArrayList<Company> companies = database.getInformation();

            String createdTable = "";

            for (Company company : companies) {
                createdTable += "<table class=\"iksweb\">\n";
                createdTable += "<caption>" + company.getName() + "</caption>\n";
                createdTable += "<tr>\n<th>Product</th><th>Price</th>\n</tr>\n";
                for (int i = 0; i < company.getProduct().size(); i++) {
                    createdTable += "<tr>\n<td>" + company.getProduct().get(i).getName() + "</td><td>" + company.getProduct().get(i).getPrice() + "</td></tr>\n";
                }
                createdTable += "</table>\n";
            }

            System.out.println(createdTable);
        %>
        <%=createdTable%>
    </body>
</html>