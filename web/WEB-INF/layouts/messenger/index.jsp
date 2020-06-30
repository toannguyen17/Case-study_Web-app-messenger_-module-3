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

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.png" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/assets/jquery/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">

    <script src="${pageContext.request.contextPath}/assets/js/messengers/Messages.js"></script>

    <script src="${pageContext.request.contextPath}/assets/js/messengers/Connection.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/messengers/App.js"></script>

    <script src="${pageContext.request.contextPath}/assets/js/util/Toasts.js"></script>

    <script src="${pageContext.request.contextPath}/assets/js/util/Helpers.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/util/Dropdown.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/messengers/storeElement.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/messengers/MessengerManager.js"></script>


    <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
</head>
<body>
<div style="overflow: initial;">
    <div class="page_box">
        <jsp:include page="include/left.jsp"></jsp:include>
        <jsp:include page="include/right.jsp"></jsp:include>
    </div>
</div>
</body>
</html>
