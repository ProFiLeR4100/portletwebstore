<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects/>

<portlet:actionURL var="processUserDataURL">
    <portlet:param name="action" value="processUserData"/>
</portlet:actionURL>

<portlet:renderURL var="cartURL" windowState="Normal">
    <portlet:param name="action" value="previousPage"/>
</portlet:renderURL>

<style>
    .error {
        color: #ff0000;
    }
</style>

<h2>Customer details</h2>
<br/>
<form action="<%=processUserDataURL%>" method="post">
    <div>
        First name: <input type="text" class="userForm" name="<portlet:namespace/>firstname"/><span class="error"></span>
    </div>
    <div>
        Last name: <input type="text" class="userForm" name="<portlet:namespace/>lastname"/><span class="error"></span>
    </div>
    <div>
        Address: <input type="text" class="userForm" name="<portlet:namespace/>address"/><span class="error"></span>
    </div>
    <div>
        ZIP code: <input type="text" class="userForm" name="<portlet:namespace/>zipcode"/><span class="error"></span>
    </div>
    <div>
        eMail: <input type="text" class="userForm" name="<portlet:namespace/>eMail"/><span class="error"></span>
    </div>
    <div>
        Phone Number: <input type="text" class="userForm" name="<portlet:namespace/>phoneNum"/><span class="error"></span>
    </div>
    <div>
        <button type="submit" id="saveUserData">Save</button>
    </div>
</form>
<a href="${cartURL.toString()}">Cart</a>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>

<script>
    $(document).ready(function() {
        var validator = new UserFieldValidator($('.userForm'));
        validator.init();

        $('#saveUserData').on('click', function(event) {
            event.preventDefault();
            if (validator.isValid()) {
                $(this).submit();
            }
        });
    });

    UserFieldValidator = function (inputs) {
        this._inputs = [];
        this.invalid = false;
        this.self = this;

        this.init = function() {
            console.log('init');
            this._inputs = inputs;
            console.log(this._inputs);
        }

        this._validate = function() {
            this._inputs.each(function(index, element) {
                console.log($(element), 1);
                if ($(element).val() == '') {
                    $(element).closest('span.error').html('Field should be empty');
                    self.invalid = true;
                }
            });
        }

        this.isValid = function() {
            console.log('isValid');
            this._validate();
            return this.invalid;
        }
    }
</script>