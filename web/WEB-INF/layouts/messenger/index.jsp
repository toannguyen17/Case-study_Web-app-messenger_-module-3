<%--
  Created by IntelliJ IDEA.
  User: toanv
  Date: 26/06/2020
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <meta name="description" content="${requestScope["desc"].getElement()}">

    <meta name="theme-color" content="#E0E0E0">
    <meta name="copyright" content="CodeGym">
    <meta name="author" content="RedT">

    <title>${requestScope["title"].getElement()}></title>
    <jsp:include page="../themes/style.jsp"></jsp:include>
</head>
<body>
<header class="fixed-top shadow-sm bg-light">
    <div class="reg_box">
        <nav class="navbar navbar-expand-sm navbar-light">
            <div class="navbar-brand">
                <div class="logo-header">
                    <jsp:include page="../themes/icons/logo.jsp"></jsp:include>
                </div>
                Messenger
            </div>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <div class="mr-auto"></div>
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Dropdown
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</header>
<section class="box-content">
    <section class="container-fluid row">
        <article class="col-md-3">12</article>
        <section class="col-md-9">12</section>
    </section>
</section>
</body>
</html>
