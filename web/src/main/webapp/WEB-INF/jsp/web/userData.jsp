<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects/>

<portlet:actionURL var="processUserDataURL">
    <portlet:param name="action" value="processUserData"/>
</portlet:actionURL>

<portlet:renderURL var="orderURL" windowState="MAXIMIZED">
    <portlet:param name="action" value="order"/>
</portlet:renderURL>

<style type="text/css">

    .field {
        clear:both;
        text-align:right;
        line-height:25px;
    }

    label {
        float:left;
        padding-right:10px;
    }

    .main {
        float:left
    }

    a.button {
        -webkit-appearance: button;
        -moz-appearance: button;
        appearance: button;
        text-decoration: none;
        text-align: center;
        color: initial;
        width: 60px;
        padding: 1px;
        margin: 20px;
    }

</style>

<h2>Customer details</h2>
<br/>

<div class="row-fluid">

    <form action="<%=processUserDataURL%>" method="post">

        <div class="main">
            <div class="field">
                <label>First name: </label>
                <input type="text" name="<portlet:namespace/>firstname"/>
            </div>
            <div class="field">
                <label>Last name: </label>
                <input type="text" name="<portlet:namespace/>lastname"/>
            </div>
            <div class="field">
                <label>Address: </label>
                <input type="text" name="<portlet:namespace/>address"/>
            </div>
            <div class="field">
                <label>eMail: </label>
                <input type="text" name="<portlet:namespace/>eMail"/>
            </div>
            <div class="field">
                <label>Phone Number: </label>
                <input type="text" name="<portlet:namespace/>phoneNum"/>
            </div>
            <input type="submit" value="Submit"/><a href="${orderURL}" class="button">Cancel</a>
        </div>

    </form>

</div>
