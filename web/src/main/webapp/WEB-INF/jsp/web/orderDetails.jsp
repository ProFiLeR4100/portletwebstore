<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects/>

<portlet:renderURL var="cartURL" windowState="Normal">
    <portlet:param name="action" value="previousPage"/>
</portlet:renderURL>

<style type="text/css">
    .productlist-wrapper .product {
        margin-bottom: 20px;
    }
    .productlist-wrapper .product .product-long-desc {
        margin-bottom: 10px;
    }
    .productlist-wrapper .product .btn {
        width: 100%;
        display: block;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }
    .productlist-wrapper .product .btn-toolbar .btn-group {
        width: 100%;
    }
    .productlist-wrapper .product .btn-toolbar .btn-group .btn {
        float: left;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }
    .productlist-wrapper .product .btn-toolbar .btn-group .btn:nth-child(1) {
        width: 80%;
    }
    .productlist-wrapper .product .btn-toolbar .btn-group .btn:nth-last-child(1) {
        width: 20%;
        z-index: 1;
        position: relative;
    }

    a.button {
        -webkit-appearance: button;
        -moz-appearance: button;
        appearance: button;

        text-decoration: none;
        color: initial;
    }
</style>

<b>Order details</b>
<br/>
<br/>
<br/>

<c:if test="${!empty catalogItems}">
    <c:forEach items="${catalogItems}" var="item">
        <div class="span12 product-image"><img src="<%= request.getContextPath()%>/img/${item.bigImage}" /></div>
        <h2 class="product-short-desc">${item.shortDescription}</h2>
        <div>${item.longDescription}</div>
        <portlet:actionURL var="deleteFromOrderURL">
            <portlet:param name="action" value="deleteFromOrder"/>
            <portlet:param name="id" value="${item.id}"/>
        </portlet:actionURL>
        <div><a href="${deleteFromOrderURL}" class="btn btn-default">Delete</a></div>
        <br/>
        <br/>
        <br/>
    </c:forEach>
</c:if>



<a href="${cartURL.toString()}">Previous</a>