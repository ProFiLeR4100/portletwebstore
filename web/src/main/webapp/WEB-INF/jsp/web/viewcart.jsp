<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects/>

<portlet:renderURL var="orderURL" windowState="MAXIMIZED">
    <portlet:param name="action" value="order"/>
</portlet:renderURL>

<style>
    .cart-wrapper .clear:before, .cart-wrapper .clear:after {
        content: "";
        clear: both;
        display: block;
    }
    .cart-wrapper .btn.disabled {
        opacity:.5;
    }
</style>

<div class="cart-wrapper">
    <p>You have <b class="text-success" id="cartSelectedItemCount">${selectedItems.getSelectedCount()}</b> products in cart.</p>
    <div class="clear"><a href="<%=orderURL.toString()%>" class="btn-success btn btn-large pull-right" type="button"><i class="icon-shopping-cart"></i> Proceed to Checkout</a></div>

</div>