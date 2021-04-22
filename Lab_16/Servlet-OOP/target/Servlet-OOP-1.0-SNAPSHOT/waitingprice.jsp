<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <title> Peyment </title>

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
        }
        .order{
            width: 20%;
            height: 150px;
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
            font-size:30px;
            font-family: Century Gothic;
            background-color: bisque;
            border: none;
            outline: none;
        }
        .price{
            width: 100%;
            height: 20%;
            padding-left: 1%;
        }
        .price input{
            font-family: Century Gothic;
            font-size: 20px;
            height: 40%;
            width: 60%;
            padding: 0;
            background-color: rgb(255, 249, 242);
            border: none;
            outline: none;
        }
        .price p{
            margin-left: -1%;
            font-family: Century Gothic;
            font-size: 20px;
        }
        .button-link{
            margin-top: 3%;
            margin-left: 12%;
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
        response.addCookie(cookie);
    }
%>
    <body>
        <header>
            <h1> Peyment </h1>
        </header>

        <div class="order">
            <form action="check-status">
                <div class="order-header"> <h2> Your order: </h2> </div>
                <div class="positions">
                    <p> Buckwheat (portions): <%= buckwheat%></p>
                    <p> Rice (portions): <%= rice%> </p>
                    <p> Compote (glasses): <%= compote%> </p>
                    <p> Cyber Cutlet (units): <%= cutlet%> </p>
                    <div class="price"> <p>Total price: <input type="text" name="price" value="Waiting for Price" readonly> </p> </div>
                </div>
                <div class="button-link"> <input type="submit" value="Refresh"> </div>
            </form>
        </div>
    </body>
</html>