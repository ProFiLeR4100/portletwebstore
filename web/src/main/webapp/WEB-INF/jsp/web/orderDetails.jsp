<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects/>

<portlet:renderURL var="cartURL" windowState="Normal">
    <portlet:param name="action" value="previousPage"/>
</portlet:renderURL>

<b>Order details</b>
<br/>
<br/>
<br/>
<a href="${cartURL.toString()}">Previous</a>