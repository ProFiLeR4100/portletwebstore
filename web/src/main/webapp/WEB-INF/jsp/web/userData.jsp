<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects/>

<portlet:actionURL var="processUserDataURL">
    <portlet:param name="action" value="processUserData"/>
</portlet:actionURL>

<h2>Customer details</h2>
<br/>
<form action="${processUserDataURL}" method="post">
    First name: <input type="text" name="firstname"/><br/>
    Last name: <input type="text" name="lastname"/><br/>
    Address: <input type="text" name="address"/><br/>
    ZIP code: <input type="text" name="zipcode"/><br/>
    <input type="submit" value="Submit"/>
</form>