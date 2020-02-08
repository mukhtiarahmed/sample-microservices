<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="title" value="View  Person  (${person.firstName}  ${person.lastName})" scope="request" />
<html>
<head>

        <jsp:include page="head.jsp">
            <jsp:param name="title"  value="${title}" />
        </jsp:include>

</head>
<body>

<div class="wrapper">
    <%@include file="sidebar.jsp" %>

    <div class="main-panel ps ps--active-y">
        <%@include file="navbar.jsp" %>

        <div class="main-content">
            <div class="card">

                <div class="content row">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="form-group col-md-10">
                                <label class="col-form-label">Person Name</label>
                                <h4 class="title">${person.firstName} ${person.lastName}</h4>
                            </div>
                            <div class="form-group col-md-2 pull-right">
                                <div class="row">
                                    <a href="${context}/person/edit/${person.id}"
                                       class="btn btn-fill btn-danger">Edit</a>
                                    <a href="${context}/person/list" class="btn btn-fill btn-primary">Back</a>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-form-label">Person Age</label>
                                <p class="title">${person.age}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-form-label">Favourite Colour</label>
                                <p class="title">${person.favouriteColour}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-form-label">Person Hobbies</label>
                                <p class="title">
                                    <c:out value="${person.getFormatedHobbies()}" />
                                </p>
                            </div>
                        </div>
                    </div>


                </div>

                <div></div>
            </div>

        </div>
    </div>
</div>
</body>

</html>
