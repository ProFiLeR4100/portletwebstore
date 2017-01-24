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

package com.portletwebstore.cart;

import com.liferay.portal.util.PortalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import com.portletwebstore.repository.SelectedItemsContainer;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("VIEW")
public class CartController {

	@RenderMapping
	public String view(RenderRequest request, RenderResponse response, Model model) {

		//SelectedItemsContainer selectedItems = (SelectedItemsContainer)request.getPortletSession().
		//		getAttribute("selectedItems", PortletSession.APPLICATION_SCOPE);

		SelectedItemsContainer selectedItems = null;

		model.addAttribute("selectedItems", selectedItems);

		HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
		HttpSession session = httpReq.getSession(true);

		String test = (String ) session.getAttribute("test");


		System.out.println("cart view");
		System.out.println("cart " + selectedItems);
		System.out.println("cart " + test);


		return "cart/view";
	}

}