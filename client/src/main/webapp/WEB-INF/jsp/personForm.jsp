<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="content row">
    <div class="col-md-6">
        <div class="row">
            <div class="form-group col-md-4">
                <label for="firstName" class="col-form-label">First Name *</label>
            </div>
            <div class="form-group col-md-8">
                <spring:bind path="firstName">
                    <form:input path="firstName"  class="form-control"
                                id="firstName" placeholder="First Name "
                                 maxlength="25"/>
                    <c:if test="${status.error}">
						<span class="text-danger">
						<form:errors path="firstName" cssClass="text-danger"/> </span>
                    </c:if>
                </spring:bind>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-4">
                <label for="lastName" class="col-form-label">Last Name *</label>
            </div>
            <div class="form-group col-md-8">
                <spring:bind path="lastName">
                    <form:input path="lastName"  class="form-control"
                                id="lastName" placeholder="Last Name"  maxlength="25"/>
                    <c:if test="${status.error}">
						<span class="text-danger">
							<form:errors path="lastName" cssClass="text-danger"/> </span>
                    </c:if>
                </spring:bind>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-4">
                <label for="dob" class="col-form-label">Birth Day *</label>
            </div>
            <div class="form-group col-md-8">
                <spring:bind path="dob">
                    <form:input path="dob"  data-format="YYYY-MM-DD"
                                class="form-control"
                                id="dob" placeholder="Birth Day" maxlength="10"/>
                    <c:if test="${status.error}">
								<span class="text-danger">
								<form:errors path="dob" cssClass="text-danger"/> </span>
                    </c:if>
                </spring:bind>
            </div>
        </div>

    </div>

    <div class="col-md-6">
        <div class="row">
            <div class="form-group col-md-4">
                <label for="colourId" class="col-form-label">Colour</label>
            </div>
            <div class="form-group col-md-8">
                <spring:bind path="colourId">
                    <form:select path="colourId" class="form-control">
                        <c:forEach items='${colours}' var='colour'>
                            <form:option value='${colour.id}' label='${colour.name}' />
                        </c:forEach>
                    </form:select>

                    <c:if test="${status.error}">
							<span class="text-danger">
							<form:errors path="colourId" cssClass="text-danger"/> </span>
                    </c:if>
                </spring:bind>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-4">
                <label for="hobbies" class="col-form-label">Hooby</label>
            </div>
            <div class="form-group col-md-8">
                <spring:bind path="colourId">
                    <form:select path="hobbies" class="form-control" multiple="multiple">
                        <c:forEach items='${hobbyList}' var='hobby'>
                            <form:option value='${hobby.id}' label='${hobby.name}' />
                        </c:forEach>
                    </form:select>

                    <c:if test="${status.error}">
							<span class="text-danger">
							<form:errors path="hobbies" cssClass="text-danger"/> </span>
                    </c:if>
                </spring:bind>
            </div>
        </div>

    </div>
</div>