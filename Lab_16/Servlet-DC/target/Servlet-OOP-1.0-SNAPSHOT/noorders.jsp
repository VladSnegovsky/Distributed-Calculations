<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 21.03.2021
  Time: 17:21
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
            margin-top: 5%;
        }
        h1 {
            font-size: 600%;
            font-family:cursive;
        }
        .container{
            padding-top: 2%;
            width: 60%;
            margin-left: 20%;
            height: 100px;
            text-align: center;
        }
        h2 {
            font-family: Century Gothic;
            font-size: 50px;
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
        <form action="index.jsp">
            <div class="button-link" style="margin-left: 14%;"> <input type="submit" value="Log out"> </div>
        </form>
        <header>
            <h1> Management </h1>
        </header>

        <div class="container">
            <h2> No orders </h2>
        </div>
        <form action="getnew-orders">
            <div class="button-link"> <input type="submit" value="Refresh"> </div>
        </form>
    </body>
</html>
