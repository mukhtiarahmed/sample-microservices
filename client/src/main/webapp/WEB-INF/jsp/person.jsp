<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="title" value="Company Master" scope="request" />
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

            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <c:if test="${listResponse.totalElement == 0}">
                                <div  class="header">
                                    <p class="pull-right">
                                        <a class="btn btn-fill btn-danger"  href="${context}/person/add"> <i
                                                class="pe-7s-plus"></i>   New
                                        </a>
                                    </p>
                                    <h4  class="title">No Company Master yet!</h4>
                                    <p class="category"><br/></p>
                                </div>
                            </c:if>
                            <c:if test="${listResponse.totalElement > 0}">
                                <div>
                                    <div class="header">

                                        <h4 class="title">Company Master List  </h4>
                                        <div class="pull-right">
                                            <form class="form-inline md-form mr-auto mb-4" onsubmit="searchPerson(); return false;">

                                                <input id="search" type="text" class="form-control mr-sm-2" placeholder="Search..">
                                                <a class="btn btn-fill btn-danger"  href="javascript:void(0)" id="searchBtn"> <i class="pe-7s-search"></i> Go
                                                </a>
                                                <a class="btn btn-fill btn-danger" href="${context}/person/add"> <i class="pe-7s-plus"></i>  New  </a>
                                            </form>
                                        </div>
                                    </div>

                                    <div class="content table-responsive table-full-width" id="person-table">
                                            <jsp:include page="personTable.jsp" />
                                    </div>
                                </div>

                                <div  class="header">
                                    <p id="pagination"> </p>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<%@include file="models.jsp" %>
<%@include file="static-js.jsp" %>

<script>

    var url = '${context}/person/list';
    var search = "";
    var page = 0;
    var sortColumn = "";
    var sortOrder = "";
    var pageSize = ${searchCriteria.pageSize} ;
    $(function() {

        $("#pagination").pagination({
            items:   ${listResponse.totalElement},
            itemsOnPage: pageSize,
            cssStyle: 'light-theme',
            onPageClick: function(pageNumber) {
                page = (pageNumber-1);
                $.post( url , { page: page, searchString: search, sortColumn: sortColumn, sortOrder: sortOrder}, function(response) {
                    $("#person-table").html(response);
                    var element = $("#"+sortColumn);
                    setIcon(element, sortOrder);
                });

            }

        });

        $("#searchBtn").on("click", function() {
            debugger
            search = $("#search").val();
            console.log("search : " + search);
            sortColumn = "";
            sortOrder = "";
            $.post( url , { page: 0, searchString: search, sortColumn: sortColumn, sortOrder: sortOrder}, function(response) {
                $("#person-table").html(response);
                var total =  $("#person-table").find('table').data("total")
                console.log("total : " +  total);
                $("#pagination").pagination('updateItems', total);
                $("#pagination").pagination('drawPage', 1);
            });

        });

    });

    function searchPerson() {
        $("#searchBtn").click();
    }

</script>
</body>

</html>
