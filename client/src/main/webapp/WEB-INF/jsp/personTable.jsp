<%@ page import="com.tenhawks.client.util.ClientHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div>
    <c:if test="${listResponse.totalElement == 0}">
        <div class="header">
            <p  class="title">No Task found!</p>
        </div>
    </c:if>
    <c:if test="${listResponse.totalElement > 0}">
        <table class="table table-hover table-striped table-sm" data-total="${listResponse.totalElement}">
        <thead>
        <tr>
            <th id="firstName" class="th-pointer" onclick="sortTable(this, 'person-table')" data-order="">First Name<div></div></th>
            <th id="lastName" class="th-pointer" onclick="sortTable(this, 'person-table')" data-order="">Last Name<div></div></th>
            <th id="dateOfBirth" class="th-pointer" onclick="sortTable(this, 'person-table')" data-order="">Age<div></div></th>
            <th id="colourId" class="th-pointer" onclick="sortTable(this, 'person-table')" data-order="">Favourite Colour<div></div></th>
            <th>Hobbies<div></div></th>
            <th>Action<div></div></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listResponse.data}" var="person">
            <tr>

                <td> ${person.firstName}</td>
                <td> ${person.lastName} </td>
                <td> ${person.age}  </td>
                <td> ${person.favouriteColour} </td>
                <td> <c:out value="${person.getFormatedHobbies()}" />
                </td>
                <td>
                    <a href="${context}/person/view/${person.id}" class="btn btn-danger btn-fill ">
                        <i class="pe-7s-file">View</i></a>
                    <a href="${context}/person/edit/${person.id}" class="btn btn-danger btn-fill ">
                        <i class="pe-7s-settings">Edit</i></a>
                    <a href="javascript:void(0)"  class="btn btn-danger btn-fill"
                       onclick="deletePerson('${context}/person/delete/${person.id}', '${person.firstName} ${person.lastName}')">
                        <i class="pe-7s-close">Delete</i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        </table>
    </c:if>
</div>


