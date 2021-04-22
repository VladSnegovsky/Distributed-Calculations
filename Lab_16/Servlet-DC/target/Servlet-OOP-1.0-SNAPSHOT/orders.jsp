<%@ page import="com.example.Servlet_OOP.ConnectionPool" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 21.03.2021
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <title> Orders </title>

    <style>
        html, body, div, span, applet, object, iframe,
        h1, h2, h3, h4, h5, h6, p, blockquote, pre,
        a, abbr, acronym, address, big, cite, code,
        del, dfn, em, img, ins, kbd, q, s, samp,
        small, strike, strong, sub, sup, tt, var,
        b, u, i, center,
        dl, dt, dd, ol, ul, li,
        fieldset, form, label, legend,
        table, caption, tbody, tfoot, thead, tr, th, td,
        article, aside, canvas, details, embed,
        figure, figcaption, footer, header, hgroup,
        menu, nav, output, ruby, section, summary,
        time, mark, audio, video {
            margin: 0;
            padding: 0;
            border: 0;
            font-size: 100%;
            font: inherit;
            vertical-align: baseline;
        }
        /* All */
        body {
            background-color: bisque;
        }
        header {
            margin-left: 20%;
            height: 140px;
            width: 60%;
            text-align: center;
            margin-top: 3%;
        }
        h1 {
            font-size: 600%;
            font-family:cursive;
        }
        .order{
            width: 20%;
            height: 200px;
            margin-left: 40%;
            background-color: rgb(255, 249, 242);
            border-radius: 10px;
            border: 2px solid black;
        }
        .order-header{
            width: 100%;
            height: 20%;
        }
        .order-header h2{
            padding-left: 2%;
            font-family: Century Gothic;
            font-size: 30px;
        }
        .positions{
            width: 100%;
            height: 80%;
        }
        .positions p{
            padding-left: 2%;
            width: 98%;
            height: 20%;
            font-family: Century Gothic;
            font-size: 17px;
        }
        .positions input{
            font-family: Century Gothic;
            font-size: 20px;
            height: 57%;
            width: 50%;
            background-color: rgb(255, 249, 242);
            border: none;
            outline: none;
        }
        /* Button */
        .button-link{
            margin-top: 1%;
            margin-left: -18%;
            width: 100%;
            height: 15%;
        }
        .button-link input{
            margin-left: 69%;
            font-size:30px;
            font-family: Century Gothic;
            color: black;
            text-decoration: none;
            background-color: bisque;
            border: none;
            outline: none;
        }
        .button-link input:hover{
            color: rgb(128, 103, 72);
        }
    </style>
</head>

    <body>
        <%
            Cookie[] cookies = request.getCookies();

            String buckwheat = "";
            String rice = "";
            String compote = "";
            String cutlet = "";

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("buckwheat"))
                    buckwheat = cookie.getValue();
                else if (cookie.getName().equals("rice"))
                    rice = cookie.getValue();
                else if (cookie.getName().equals("compote"))
                    compote = cookie.getValue();
                else if (cookie.getName().equals("cutlet"))
                    cutlet = cookie.getValue();
                else {
                    response.addCookie(cookie);
                }
            }
        %>
        <form action="index.jsp">
            <div class="button-link" style="margin-left: 14%;"> <input type="submit" value="Log out"> </div>
        </form>
        <header>
            <h1> Management </h1>
        </header>

        <form action="set-price">
            <div class="order">
                <div class="order-header"> <h2> Visitor order: </h2> </div>
                <div class="positions">
                    <p> Buckwheat (portions): <%= buckwheat %> (1$) </p>
                    <p> Rice (portions): <%= rice %> (2$) </p>
                    <p> Compote (glasses): <%= compote %> (1$) </p>
                    <p> Cyber Cutlet (units): <%= cutlet %> (10$) </p>
                    <p> Total cost: <input type="number" name="price" placeholder="cost" min=0> </p>
                </div>
            </div>
            <div class="button-link"> <input type="submit" value="Calculate"> </div>
        </form>
    </body>
</html>
