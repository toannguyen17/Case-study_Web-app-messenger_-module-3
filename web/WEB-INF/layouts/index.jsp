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

    <link rel="shortcut icon" href="/favicon.png">

    <jsp:include page="themes/style.jsp"></jsp:include>

    <script src="/assets/js/home/FormLogin.js"></script>
    <script src="/assets/js/home/FormRegister.js"></script>
    <script src="/assets/js/home.js"></script>
</head>
<body>
<header class="fixed-top shadow-sm bg-white">
    <div class="reg_box">
        <nav class="navbar navbar-expand-sm navbar-light">
            <div class="navbar-brand">
                <div class="logo-header">
                    <jsp:include page="themes/icons/logo.jsp"></jsp:include>
                </div>
                Messenger
            </div>
        </nav>
    </div>
</header>
<section class="mt-76 reg_box">
    <div class="row">
        <div class="col col-12 col-md-6 col-lg-7">
            <div class="pl-2 pr-2 bf8">
                <section class="row">
                    <article class="col col-12 col-lg-6">
                        A
                    </article>
                    <article class="col col-12 col-lg-6">
                        B
                    </article>
                </section>
            </div>
        </div>
        <section class="col col-12 col-md-6 col-lg-5">
            <jsp:include page="auth/login_home.jsp"></jsp:include>
        </section>
    </div>
</section>
</body>
</html>
