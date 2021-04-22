<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <title> Payment Done </title>

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
        .information{
            width: 40%;
            height: 80px;
            margin-left: 30%;
            margin-top: 10%;
            text-align: center;
        }
        .information h2{
            font-family: Century Gothic;
            font-size: 50px;
        }
        .button-link{
            margin-top: 0.5%;
            width: 100%;
            height: 15%;
        }
        .button-link a{
            margin-left: 47%;
            font-size: 30px;
            font-family: Century Gothic;
            color: black;
            text-decoration: none;
        }
        .button-link a:hover{
            color: rgb(128, 103, 72);
        }
    </style>
</head>

    <body>
    <%
        Cookie[] cookies = request.getCookies();
        StringBuilder allcookies = new StringBuilder();

        for (Cookie cookie : cookies) { response.addCookie(cookie); }
    %>
        <div class="information">
            <h2> Successfully Paid </h2>
        </div>
        <div class="button-link">
            <a href="menu.jsp"> Menu </a>
        </div>
        <p> <%= allcookies %> </p>
    </body>
</html>