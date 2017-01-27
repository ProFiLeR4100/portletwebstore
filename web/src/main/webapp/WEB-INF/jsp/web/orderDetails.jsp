<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects/>

<portlet:renderURL var="cartURL" windowState="Normal">
    <portlet:param name="action" value="previousPage"/>
</portlet:renderURL>

<portlet:renderURL var="processUserDataURL" windowState="MAXIMIZED">
    <portlet:param name="action" value="processUserData"/>
</portlet:renderURL>

<style type="text/css">
    .orderdetails-wrapper .product {
        margin-bottom: 20px;
        padding-bottom: 20px;
        border-bottom: 1px lightgray dashed;
    }
    .orderdetails-wrapper .product .product-options ul {
        margin-left: 0;
    }
    .orderdetails-wrapper .product .product-options ul li {
        list-style-type: none;
    }
    .orderdetails-wrapper .product .product-options-checkbox {
        width: 15px;
        height: 15px;
        -webkit-border-radius:3px;
        -moz-border-radius:3px;
        border-radius:3px;
        margin-right: 10px;
        border: 2px solid #f4f4f4;
        background: #fff;
        cursor: pointer;
        top: 4px;
        -webkit-transition: all 400ms;
        -moz-transition: all 400ms;
        -ms-transition: all 400ms;
        -o-transition: all 400ms;
        transition: all 400ms;
    }
    .orderdetails-wrapper .product .product-options-checkbox .jq-checkbox__div {
        -webkit-transition: all 400ms;
        -moz-transition: all 400ms;
        -ms-transition: all 400ms;
        -o-transition: all 400ms;
        transition: all 400ms;
    }
    .orderdetails-wrapper .product .product-options-checkbox.checked .jq-checkbox__div {
        width: 100%;
        height: 100%;
        margin: 0;
        background: url('/web-1/img/check.png') center center no-repeat;
    }
</style>

<h1>Order details</h1>

<c:if test="${!empty catalogItems}">
    <div class="orderdetails-wrapper">
        <c:forEach items="${catalogItems}" var="item">
            <div class="row-fluid product" data-product-id="${item.id}">
                <div class="span4 product-image"><img src="<%= request.getContextPath()%>/img/${item.bigImage}" /></div>
                <div class="span6">
                    <h2 class="product-short-desc">${item.shortDescription}</h2>
                    <div class="product-long-desc">${item.longDescription}</div>
                    <c:if test="${!empty catalogItems}">
                        <div class="product-options">
                            <h3>Choose options:</h3>
                            <ul>
                                <li><label><input type="checkbox" class="product-options-checkbox" name="options[]" value="1">1</label></li>
                                <li><label><input type="checkbox" class="product-options-checkbox" name="options[]" value="1">2</label></li>
                                <li><label><input type="checkbox" class="product-options-checkbox" name="options[]" value="1">3</label></li>
                            </ul>
                            <ul>
                                <c:forEach items="${item.getAdditionalOptions()}" var="opt">
                                    <li><label><input type="checkbox" class="product-options-checkbox" name="options[]" value="${opt}">${opt}</label></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>

                </div>
                <div class="span2">
                    <portlet:actionURL var="deleteFromOrderURL">
                        <portlet:param name="action" value="deleteFromOrder"/>
                        <portlet:param name="id" value="${item.id}"/>
                    </portlet:actionURL>
                    <button data-url="${deleteFromOrderURL}" class="btn btn-default">Delete</button>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>


<div>

</div>
<a href="${cartURL.toString()}">Previous</a><a href="${processUserDataURL.toString()}" style="float:right;">Specify your data</a>