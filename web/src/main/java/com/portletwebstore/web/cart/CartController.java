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

package com.portletwebstore.web.cart;

import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.util.PortalUtil;
import com.portletwebstore.repository.Catalog;
import com.portletwebstore.repository.CatalogStub;
import com.portletwebstore.repository.Customer;
import com.portletwebstore.repository.SelectedItemsContainer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import javax.portlet.*;
import java.io.IOException;

@Controller
@RequestMapping("VIEW")
public class CartController {

	@RenderMapping
	public String view(RenderRequest request, RenderResponse response, Model model) {

		Long[] selectedItemArray = (Long[]) request.getPortletSession()
				.getAttribute("selectedItems", PortletSession.APPLICATION_SCOPE);

		if (selectedItemArray == null) {
			selectedItemArray = new Long[0];
		}

		SelectedItemsContainer selectedItems = new SelectedItemsContainer();
		selectedItems.setItemsFromArray(selectedItemArray);

		/*SelectedItemsContainer selectedItems = (SelectedItemsContainer) request.getPortletSession()
				.getAttribute("selectedItems", PortletSession.APPLICATION_SCOPE);

		if (selectedItems == null) {
			selectedItems = new SelectedItemsContainer();
		}*/

		model.addAttribute("selectedItems", selectedItems);

		return "web/viewcart";
	}

	@RenderMapping(params = "action=order")
	public String orderDetails(RenderRequest request, RenderResponse response, Model model) {

        Long[] selectedItemArray = (Long[]) request.getPortletSession()
                .getAttribute("selectedItems", PortletSession.APPLICATION_SCOPE);


        Catalog catalog = new Catalog();

        for (Long item : selectedItemArray) {
            catalog.addItem(CatalogStub.getCatalogItemById(item));
        }

        model.addAttribute("catalogItems", catalog.getCatalogItems());
        model.addAttribute("catalog", catalog);

		return "web/orderDetails";
	}

    @RenderMapping(params = "action=processUserData")
    public String processUsedData(RenderRequest request, RenderResponse response, Model model) {
        return "web/userData";
    }

    @RenderMapping(params = "action=finish")
    public String processFihish(RenderRequest request, RenderResponse response, Model model) {
        Customer customer = (Customer) request.getPortletSession().getAttribute("customer");
        model.addAttribute("customer", customer);
        return "web/finish";
    }

	@ActionMapping(params = "action=deleteFromOrder")
    public void processDeleteItemFromOrder(ActionRequest actionRequest, ActionResponse actionResponse) {

        Long selectedId = Long.parseLong(actionRequest.getParameter("id"));

        Long[] selectedItemArray = (Long[]) actionRequest.getPortletSession()
                .getAttribute("selectedItems", PortletSession.APPLICATION_SCOPE);

        if (selectedItemArray == null) {
            selectedItemArray = new Long[0];
        }

        SelectedItemsContainer selectedItems = new SelectedItemsContainer();
        selectedItems.setItemsFromArray(selectedItemArray);

        selectedItems.removeItem(selectedId);

        actionRequest.getPortletSession().setAttribute("selectedItems", selectedItems.getItemsAsArray(), PortletSession.APPLICATION_SCOPE);
        actionResponse.setRenderParameter("action", "order");
    }

    @ActionMapping(params = "action=processUserData")
    public void processUserData(ActionRequest actionRequest, ActionResponse actionResponse) {
        Customer customer = new Customer();
        customer.setFirstName(actionRequest.getParameter("firstname"));
        customer.setLastName(actionRequest.getParameter("lastname"));
        customer.setAddress(actionRequest.getParameter("address"));
        customer.setZipCode(actionRequest.getParameter("zipcode"));
        customer.seteMail(actionRequest.getParameter("eMail"));
        customer.setPhoneNum(actionRequest.getParameter("phoneNum"));

        actionRequest.getPortletSession().setAttribute("customer", customer);
        actionResponse.setRenderParameter("action", "finish");
    }

    @ResourceMapping("removeFromCartDetailed")
    void processRemoveFromCartAction(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException {

        Long selectedId = Long.parseLong(resourceRequest.getParameter("id"));

        Long[] selectedItemArray = (Long[])resourceRequest.getPortletSession().
                getAttribute("selectedItems", PortletSession.APPLICATION_SCOPE);

        SelectedItemsContainer selectedItems = new SelectedItemsContainer();
        selectedItems.setItemsFromArray(selectedItemArray);


        selectedItems.removeItem(selectedId);

        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), "" + selectedItems.getSelectedCount());

        resourceRequest.getPortletSession().setAttribute("selectedItems", selectedItems.getItemsAsArray(), PortletSession.APPLICATION_SCOPE);

    }

    @ResourceMapping("optionFlagChanged")
    void processOptionFlagChanged(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException {

        Long itemId = Long.parseLong(resourceRequest.getParameter("id"));
        Long optionId = Long.parseLong(resourceRequest.getParameter("optionid"));
        Boolean checked = Boolean.parseBoolean(resourceRequest.getParameter("checked"));

        System.out.println("itemId " + itemId);
        System.out.println("optionId " + optionId);
        System.out.println("checked " + checked);
        System.out.println(resourceRequest.getParameter("checked"));
        System.out.println("---");

        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), "ok");

    }

}