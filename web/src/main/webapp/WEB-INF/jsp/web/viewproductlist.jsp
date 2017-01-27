<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects/>

<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>--%>

<portlet:resourceURL var="resourceURLAdd" id="addToCart">
    <portlet:param name="id" value="_"></portlet:param>
</portlet:resourceURL>

<portlet:resourceURL var="resourceURLRemove" id="removeFromCart">
    <portlet:param name="id" value="_"></portlet:param>
</portlet:resourceURL>




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
    .productlist-wrapper .product  .btn-group {
        width: 100%;
    }
    .productlist-wrapper .product .btn-group .btn {
        float: left;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }
    .productlist-wrapper .product .btn-group .btn:nth-child(1) {
        width: 80%;
    }
    .productlist-wrapper .product .btn-group .btn:nth-last-child(1) {
        width: 20%;
        z-index: 1;
        position: relative;
    }
    .productlist-wrapper .cart-action-toggle[data-selected="0"] .remove-from-cart{
        display: none;
    }
    .productlist-wrapper .cart-action-toggle[data-selected="1"] .add-to-cart{
        display: none;
    }
</style>

<div class="productlist-wrapper">
    <c:if  test="${!empty catalogItemsChunks}">
        <c:forEach items="${catalogItemsChunks}" var="catalogItems">
            <div class="row-fluid" data-product-id="${item.id}">
                <c:forEach items="${catalogItems}" var="item">
                    <div class="span4 product" data-product-id="${item.id}">
                        <div class="row-fluid">
                            <div class="span12 product-image"><img src="<%= request.getContextPath()%>/img/${item.smallImage}" /></div>
                            <div class="span12">
                                <h2 class="product-short-desc">${item.shortDescription}</h2>
                                <%--<div class="product-long-desc">${item.longDescription}</div>--%>
                            </div>
                            <div class="span12 product-action">
                                <c:set var="linkText" value="Check"/>
                                <c:if test="${!empty selectedItems}">

                                    <!-- TODO: MOVE TO CONTROLLER -->
                                        <c:set var="itemWasSelected" value="0"></c:set>
                                        <c:forEach items="${selectedItems.getSelectedItems()}" var="selItem">
                                            <c:if test="${selItem == item.id}">
                                                <c:set var="itemWasSelected" value="1"></c:set>
                                            </c:if>
                                        </c:forEach>
                                    <!-- /MOVE TO CONTROLLER -->
                                    <div class="cart-action-toggle" data-selected="${itemWasSelected}">
                                        <div class="add-to-cart">
                                            <button data-url="${resourceURLAdd}${item.id}" class="btn btn-success cartActionBtn">Add to Cart</button>
                                        </div>
                                        <div class="remove-from-cart">
                                            <div class="btn-group">
                                                <button class="btn" onclick="return false;">Item Already in Cart</button>
                                                <button data-url="${resourceURLRemove}${item.id}" class="btn btn-danger text-center cartActionBtn"><i class="icon-trash"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </c:if>
</div>

