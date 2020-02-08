<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
    	<jsp:include page="head.jsp">
			<jsp:param name="title"  value="Person Assignment Login page" />
		</jsp:include>
    </head>
    <body>
    <div>
		<nav class="navbar navbar-ct-red navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">						
						<a class="navbar-brand" th:href="@{/}">Person Assignment</a>
					</div>				
				</div>
		</nav>
			
			<div class="content">
				<div class="container">
					<div class="row">
						<c:if test="${!empty param.error}">
							<div class="alert alert-danger alert-dismissible">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>Danger!</strong>  Invalid username and/or password
							</div>
						</c:if>
					</div>

					<div class="row">
						<div class="col-md-4 col-sm-6 col-md-offset-4 col-sm-offset-3">
							<form:form  action="${pageContext.request.contextPath}/login" method="post">
								<div class="card">

									<h4 class="title" style="text-align: center"> Login </h4>

									<div class="content">
										<div class="form-group">
											 <label for="username">User Name</label>
                                			<input type="text"  id="username" name="username" class="form-control" placeholder="Username">
										</div>
										<div class="form-group">
											 <label for="password">Password</label>
                                <input type="password" name="password" class="form-control" id="password" placeholder="Password">
										</div>
										<div class="form-group">
											<span class="hide text-danger" id="error-panel"> Please provide username and password</span>
										</div>
									</div>
									<div class="footer text-center">
										<button class="btn btn-fill btn-danger btn-wd" type="submit" onclick="login()">Login</button>
									</div>
									<div class="header text-center"></div>
								</div>
							</form:form>
						</div>
					</div>
				</div>	
		
			
		</div>
	</div>
	<%@include file="static-js.jsp" %>
<script>
   function login() {
	   var username =  $("#username").val();
       var password =  $("#password").val();
       if(password == "" || username == "") {
           event.preventDefault();
           $("#error-panel").removeClass("hide");
           return false;
	   } else {
           return true;
	   }
   }

</script>
   </body>

</html>