<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<style type="text/css">
    .data, .data td {
        border-collapse: collapse;
        <!--width: 100%;-->
        border: 1px solid #aaa;
        margin: 2px;
        padding: 2px;
    }
    .data th {
        font-weight: bold;
        background-color: #5C82FF;
        color: white;
    }
</style>

<h3>Items</h3>


<c:if  test="${!empty catalogItems}">
    <table class="data">
        <tr>
            <th>#</th>
            <th>Short Desc</th>
            <th>Long Desc</th>
            <th>Check / Uncheck</th>
        </tr>

        <c:forEach items="${catalogItems}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.shortDescription}</td>
                <td>${item.longDescription}</td>
                <c:set var="linkText" value="Check"/>

                <c:if test="${!empty selectedItems}">
                    <c:set var="itemWasSelected" value="0"></c:set>
                    <c:forEach items="${selectedItems.getSelectedItems()}" var="selItem">
                        <c:if test="${selItem == item.id}">
                            <c:set var="itemWasSelected" value="1"></c:set>
                        </c:if>
                    </c:forEach>

                    <c:choose>

                        <c:when test="${itemWasSelected.equals('1')}">

                            <portlet:actionURL var="processUncheckURL">
                                <portlet:param name="action" value="processUncheck"/>
                                <portlet:param name="id" value="${item.id}"/>
                            </portlet:actionURL>

                            <td><a href="${processUncheckURL}">Uncheck</a></td>

                        </c:when>

                        <c:otherwise>

                            <portlet:actionURL var="processCheckURL">
                                <portlet:param name="action" value="processCheck"/>
                                <portlet:param name="id" value="${item.id}"/>
                            </portlet:actionURL>

                            <td><a href="${processCheckURL}">Check</a></td>

                        </c:otherwise>


                    </c:choose>

                </c:if>

        </c:forEach>
    </table>

    <form>

    </form>

</c:if>