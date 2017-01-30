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
        background: url('<%= request.getContextPath()%>/img/check.png') center center no-repeat;
    }
</style>

<h1>Order details</h1>

<c:if test="${!empty catalog.catalogItems}">
    <div class="orderdetails-wrapper">
        <c:forEach items="${catalog.catalogItems}" var="catalogItem">
            <div class="row-fluid product" data-product-id="${catalogItem.id}">
                <div class="span4 product-image"><img src="<%= request.getContextPath()%>/img/${catalogItem.bigImage}" /></div>
                <div class="span6">
                    <h2 class="product-short-desc">${catalogItem.shortDescription}</h2>
                        <div class="product-options">
                            <h3>Choose options:</h3>
                            <ul>
                               <c:forEach items="${catalogItem.additionalOptions}" var="additionalOption">
                                   <portlet:resourceURL id="optionFlagChanged" var="optionFlagChangedURLChecked">
                                       <portlet:param name="id" value="${catalogItem.id}" />
                                       <portlet:param name="optionid" value="${additionalOption.id}" />
                                       <portlet:param name="checked" value="true"/>
                                   </portlet:resourceURL>
                                   <portlet:resourceURL id="optionFlagChanged" var="optionFlagChangedURLUnchecked">
                                       <portlet:param name="id" value="${catalogItem.id}" />
                                       <portlet:param name="optionid" value="${additionalOption.id}" />
                                       <portlet:param name="checked" value="false"/>
                                   </portlet:resourceURL>
                                   <c:choose>
                                       <c:when test="${additionalOption.optionSelected}">
                                           <li><label><input type="checkbox" class="product-options-checkbox" name="options[]" value="${additionalOption.name}"
                                                             data-url-checked="${optionFlagChangedURLChecked}"
                                                             data-url-unchecked="${optionFlagChangedURLUnchecked}"
                                                             checked="true">${additionalOption.name}</label></li>
                                       </c:when>
                                       <c:otherwise>
                                           <li><label><input type="checkbox" class="product-options-checkbox" name="options[]" value="${additionalOption.name}"
                                                             data-url-checked="${optionFlagChangedURLChecked}"
                                                             data-url-unchecked="${optionFlagChangedURLUnchecked}"
                                                            >${additionalOption.name}</label></li>
                                       </c:otherwise>
                                   </c:choose>

                                </c:forEach>
                            </ul>
                        </div>
                </div>
                <div class="span2">
                    <portlet:resourceURL var="deleteFromOrderURL" id="removeFromCartDetailed">
                        <portlet:param name="id" value="${catalogItem.id}" />
                    </portlet:resourceURL>
                    <button data-url="${deleteFromOrderURL}" class="btn btn-default btn-delete" data-product-id="${catalogItem.id}">
                        Delete
                    </button>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>

<a href="${cartURL.toString()}">Previous</a><a href="${processUserDataURL.toString()}" style="float:right;">Specify your data</a>