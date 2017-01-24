<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects/>

<portlet:renderURL var="orderURL" windowState="MAXIMIZED">
    <portlet:param name="action" value="order"/>
</portlet:renderURL>

<c:choose>

    <c:when test="${!empty selectedItems}" >
        <b>Items in cart: ${selectedItems.getSelectedCount()}</b>
    </c:when>

    <c:otherwise>
        <b>Cart is empty!</b>
    </c:otherwise>

</c:choose>

<br/>

<a href="<%=orderURL.toString()%>">Order >>></a>
