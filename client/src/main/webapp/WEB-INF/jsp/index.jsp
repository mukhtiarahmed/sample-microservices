<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="title" value="Person Assignment Dashboard" scope="request" />
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
        <%@include file="navbar.jsp" %>


        <div class="main-content">
            <c:if test="${!empty param.success}">
                <div class="alert alert-info alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Success!</strong>  ${param['success']}
                </div>
            </c:if>

            <c:if test="${!empty param.error}">
                <div class="alert alert-danger alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Danger!</strong>  ${error}
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>

</html>
