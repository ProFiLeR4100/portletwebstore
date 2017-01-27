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

import com.portletwebstore.repository.Catalog;
import com.portletwebstore.repository.CatalogStub;
import com.portletwebstore.repository.SelectedItemsContainer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.*;

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

        return "web/orderDetails";
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

}