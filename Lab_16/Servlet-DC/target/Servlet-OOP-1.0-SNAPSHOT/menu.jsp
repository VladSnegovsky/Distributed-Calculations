<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <title> Menu </title>

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
        }
        h1 {
            font-size: 600%;
            font-family:cursive;
        }
        .menu{
            margin-left: 10%;
            width: 80%;
            height: 350px;
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            grid-auto-rows: minmax(150px, auto);
            grid-gap: 30px;
        }
        /* Options */
        .menu-option {
            margin-top: 1%;
            margin-left: 35%;
            width: 30%;
            height: 70%;
            background-color: rgb(255, 249, 242);
            border-radius: 10px;
            border: 2px solid black;
        }
        .part-header{
            width: 100%;
            height: 20%;
            text-align: center;
        }
        .part-header h2{
            font-family: Century Gothic;
            font-size: 30px;
            padding-top: 1%;
        }
        .part-description{
            width: 100%;
            height: 40%;
        }
        .part-description p{
            font-family: Century Gothic;
            font-size: 20px;
            padding: 1%;
        }
        .part-price{
            width: 100%;
            height: 10%;
        }
        .part-price p{
            font-family: Century Gothic;
            font-size: 20px;
            padding: 1%;
        }
        .part-quantity{
            width: 100%;
            height: 30%;
            padding: 2%;
        }
        .part-quantity input{
            font-family: Century Gothic;
            font-size: 20px;
            height: 40%;
            width: 50%;
            background-color: rgb(255, 249, 242);
            border: none;
            outline: none;
        }
        .part-quantity p{
            padding-top: 5%;
            margin-left: -1%;
            font-family: Century Gothic;
            font-size: 20px;
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

            for (Cookie cookie : cookies) {
                response.addCookie(cookie);
            }
        %>
        <header>
            <h1> Menu </h1>
        </header>

        <div class="menu">
            <form action="get-order">
                <div class="menu-option">
                    <div class="part-header"> <h2> Buckwheat </h2> </div>
                    <div class="part-description"> <p> Buy a portion of this heavenly manna and get a fork as a gift! You will never forget this taste. </p> </div>
                    <div class="part-price"> <p> Cost: 1$ </p> </div>
                    <div class="part-quantity"> <p> To order <input type="text" name="buckwheat" placeholder="quantity" required> </p> </div>
                </div>
                <div class="menu-option">
                    <div class="part-header"> <h2> Rice </h2> </div>
                    <div class="part-description"> <p> Restores attentiveness and fatigue. As a bonus you will receive a code from WiFi! </p> </div>
                    <div class="part-price"> <p> Cost: 2$ </p> </div>
                    <div class="part-quantity"> <p> To order <input type="text" name="rice" placeholder="quantity" required> </p> </div>
                </div>
                <div class="menu-option">
                    <div class="part-header"> <h2> Compote </h2> </div>
                    <div class="part-description"> <p> Real Ukrainian Coke. One glass of this drink kills all germs and protects from radiation. </p> </div>
                    <div class="part-price"> <p> Cost: 1$ </p> </div>
                    <div class="part-quantity"> <p> To order <input type="text" name="compote" placeholder="quantity" required> </p> </div>
                </div>
                <div class="menu-option">
                    <div class="part-header"> <h2> Cyber Cutlet </h2> </div>
                    <div class="part-description"> <p> This cutlet makes you a real cyber monster. Eat one and write your own Google! </p> </div>
                    <div class="part-price"> <p> Cost: 10$ </p> </div>
                    <div class="part-quantity"> <p> To order <input type="text" name="cutlet" placeholder="quantity" required> </p> </div>
                </div>
                <div class="button-link"> <input type="submit" value="Make order"> </div>
            </form>
        </div>
    </body>
</html>