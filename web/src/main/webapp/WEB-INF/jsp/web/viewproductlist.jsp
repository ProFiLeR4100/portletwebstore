<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects/>


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
</style>

<div class="productlist-wrapper">
    <c:if  test="${!empty catalogItemsChunks}">
        <c:forEach items="${catalogItemsChunks}" var="catalogItems">
            <div class="row-fluid" data-product-id="${item.id}">
                <c:forEach items="${catalogItems}" var="item">
                    <div class="span4 product" data-product-id="${item.id}">
                        <div class="row-fluid">
                            <div class="span12 product-image"><img src="<%= request.getContextPath()%>/img/${item.id}.jpg" /></div>
                            <div class="span12">
                                <h2 class="product-short-desc">${item.shortDescription}</h2>
                                <div class="product-long-desc">${item.longDescription}</div>
                            </div>
                            <div class="span12 product-action">
                                <c:set var="linkText" value="Check"/>
                                <c:if test="${!empty selectedItems}">
                                    <c:set var="itemWasSelected" value="0"></c:set>
                                    <c:forEach items="${selectedItems.getSelectedItems()}" var="selItem">
                                        <c:if test="${selItem == item.id}">
                                            <c:set var="itemWasSelected" value="1"></c:set>
                                        </c:if>
                                    </c:forEach>
                                    <c:choose><c:when test="${itemWasSelected.equals('1')}">
                                        <portlet:actionURL var="processUncheckURL">
                                            <portlet:param name="action" value="processUncheck"/>
                                            <portlet:param name="id" value="${item.id}"/>
                                        </portlet:actionURL>
                                        <div class="btn-toolbar">
                                            <div class="btn-group">
                                                <a class="btn" href="#" onclick="return false;">Item Already in Cart</a>
                                                <a href="${processUncheckURL}" class="btn btn-danger text-center"><i class="icon-trash"></i></a>
                                            </div>
                                        </div>

                                    </c:when><c:otherwise>
                                        <portlet:actionURL var="processCheckURL">
                                            <portlet:param name="action" value="processCheck"/>
                                            <portlet:param name="id" value="${item.id}"/>
                                        </portlet:actionURL>
                                        <a href="${processCheckURL}" class="btn btn-success">Add to Cart</a>
                                    </c:otherwise></c:choose>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </c:if>
</div>