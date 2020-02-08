<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="title" value="Person Edit" scope="request" />
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
			<div class="main-content">

				<div class="card">
					<div class="header">
						<h4>Person Edit (${person.firstName}) </h4>
					</div>

					<form:form action="${context}/person/update/${person.id}" modelAttribute="person"
						  enctype="multipart/form-data" method="post">
					<jsp:include page="personForm.jsp"  />
					<div class="content row">

						<div class="col-md-offset-9 col-md-3">
							<a href="${context}/person/list" class="btn btn-fill btn-danger">Back</a>

							<input type="button" class="btn btn-fill btn-danger" id="resetBtn"
								   value="Reset" onclick="location.reload();">
							<input type="submit" class="btn btn-fill btn-danger" id="submitPersonBtn"
							value="Save">
						</div>
					</div>

					</form:form>
				</div>
			</div>

		</div>
	</div>
</div>
<%@include file="static-js.jsp" %>
<script>
    $(document).ready(function() {
        $('#dob').datetimepicker({
            format : "YYYY-MM-DD",

            icons : {
                time : "fa fa-clock-o",
                date : "fa fa-calendar",
                up : "fa fa-chevron-up",
                down : "fa fa-chevron-down",
                previous : 'fa fa-chevron-left',
                next : 'fa fa-chevron-right',
                today : 'fa fa-screenshot',
                clear : 'fa fa-trash',
                close : 'fa fa-remove'
            }
        }).on('dp.change', function () {
            $(this).data("DateTimePicker").hide();
        });
    });

</script>
</body>

</html>

