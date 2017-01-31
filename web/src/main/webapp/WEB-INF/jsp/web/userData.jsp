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

<%--<style>
    .error {
        color: #ff0000;
        margin-left: 5px;
    }
</style>

<h2>Customer details</h2>
<br/>
<form action="<%=processUserDataURL%>" method="post" id="userForm">
    <div>
        First name: <input type="text" class="userFormField" name="<portlet:namespace/>firstname"/><span class="error"></span>
    </div>
    <div>
        Last name: <input type="text" class="userFormField" name="<portlet:namespace/>lastname"/><span class="error"></span>
    </div>
    <div>
        Address: <input type="text" class="userFormField" name="<portlet:namespace/>address"/><span class="error"></span>
    </div>
    <div>
        ZIP code: <input type="text" class="userFormField" name="<portlet:namespace/>zipcode"/><span class="error"></span>
    </div>
    <div>
        eMail: <input type="text" class="userFormField" name="<portlet:namespace/>eMail"/><span class="error"></span>
    </div>
    <div>
        Phone Number: <input type="text" class="userFormField" name="<portlet:namespace/>phoneNum"/><span class="error"></span>
    </div>
    <div>
        <button type="submit" id="saveUserData">Save</button>
    </div>
</form>--%>

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
        height: 20px;
        padding: 1px;
        margin: 20px;
    }

    .error {
        color: #ff0000;
        margin-left: 5px;
    }

</style>

<h2>Customer details</h2>
<br/>

<div class="row-fluid">
    <form action="${processUserDataURL}" method="post" id="userForm">

        <div class="main">
            <div class="field">
                <label>First name: </label>
                <input type="text" name="<portlet:namespace/>firstname" class="userFormField"/>
                <span class="error"></span>
            </div>
            <div class="field">
                <label>Last name: </label>
                <input type="text" name="<portlet:namespace/>lastname" class="userFormField"/>
                <span class="error"></span>
            </div>
            <div class="field">
                <label>Address: </label>
                <input type="text" name="<portlet:namespace/>address" class="userFormField"/>
                <span class="error"></span>
            </div>
            <div class="field">
                <label>eMail: </label>
                <input type="text" name="<portlet:namespace/>eMail" class="userFormField"/>
                <span class="error"></span>
            </div>
            <div class="field">
                <label>Phone Number: </label>
                <input type="text" name="<portlet:namespace/>phoneNum" class="userFormField"/>
                <span class="error"></span>
            </div>
            <input type="submit" value="Submit" id="saveUserData"/><a href="${orderURL}" class="button">Cancel</a>
        </div>
    </form>
</div>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>

<script>
    $(document).ready(function() {
        var validator = new UserFieldValidator($('.userFormField'));

        $('#saveUserData').on('click', function(event) {
            event.preventDefault();
            if (validator.isValid()) {
                $('#userForm').submit();
            }
        });
    });

    UserFieldValidator = function (inputs) {
        this._inputs = [];
        this._validCounter = 0;

        this._init = function() {
            if (inputs == undefined) {
                return undefined;
            }
            this._inputs = inputs;
            return this;
        }

        this._validate = function() {
            var self = this;
            this._validCounter = this._inputs.length;

            this._inputs.each(function(index, element) {
                if ($(element).val() == '') {
                    $(element).next('.error').html('Field should be empty');
                    return true;
                }
                --self._validCounter;
            });

            return (this._validCounter > 0) ? false : true;
        }
        
        this._cleanError = function () {
            $('.error').each(function(index, element) {
                $(element).html('');
            });
        }

        this.isValid = function() {
            this._cleanError();
            return this._validate();
        }

        this._init();
    }
</script>