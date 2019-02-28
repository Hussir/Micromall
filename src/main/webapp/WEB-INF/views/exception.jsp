<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Free Bootstrap Error Template</title>

    <script src="../../js/jquery-1.11.3.min.js"></script>
    <script src="../../js/bootstrap.js"></script>

    <link href="../../css/bootstrap.css" rel="stylesheet" type='text/css' />
    <link href="../../css/font-awesome.min.css" rel="stylesheet" type='text/css' />
    <link href="../../css/style.css" rel="stylesheet" type='text/css' />
    <link href='http://fonts.googleapis.com/css?family=Nova+Flat' rel='stylesheet' type='text/css' />

    <style>
        body {
            font-family: 'Nova Flat', cursive;
            background-color: #B396FF;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="container">

        <div class="row pad-top text-center">
            <div class="col-md-6 col-md-offset-3 text-center">
                <h1> What have you done? </h1>
                <h5> Now Go Back Using Below LInk</h5>
                <br/>
                <p style="font-size:20px;">${message}</p>
                <br/>
            </div>
        </div>

        <div class="row text-center">
            <div class="col-md-8 col-md-offset-2">
                <h3><i class="fa fa-lightbulb-o fa-5x"></i></h3>
                <a href="../../index.jsp" class="btn btn-primary">GO TO HOME PAGE</a>
            </div>
        </div>
    </div>
</body>
</html>
