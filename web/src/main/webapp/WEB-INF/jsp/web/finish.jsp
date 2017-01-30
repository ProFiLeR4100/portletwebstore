<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects/>

<portlet:renderURL var="orderURL" windowState="MAXIMIZED">
    <portlet:param name="action" value="order"/>
</portlet:renderURL>

<portlet:renderURL var="processUserDataURL" windowState="MAXIMIZED">
    <portlet:param name="action" value="processUserData"/>
</portlet:renderURL>

<c:if test="${not empty customer}">
    <h1>Congrats, ${customer.getFirstName()}</h1>
    <p>You personal data has been saved.</p>
    <p>You able to back to your <a href="${orderURL}">last order</a></p>
</c:if>
<c:if test="${empty customer}">
    <h1>Sorry, something wrong!</h1>
    <p>Please, try to set personal data <a href="${processUserDataURL}">again</a> or call to administrator about.</p>
</c:if>


