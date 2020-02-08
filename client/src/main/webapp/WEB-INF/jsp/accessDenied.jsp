<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="title" value="Error Page" scope="request" />
<html>
<head>
    <head>
        <jsp:include page="head.jsp">
            <jsp:param name="title"  value="${title}" />
        </jsp:include>
    </head>
</head>
<body>

<div class="wrapper">
    <%@include file="sidebar.jsp" %>

    <div class="main-panel ps ps--active-y">
        <%@include file="navbar.jsp" %>s

        <div class="main-content">
            <div class="alert alert-danger alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Danger!</strong> You do not have rights to access this resource.
            </div>

        </div>
    </div>
</div>
</body>

</html>