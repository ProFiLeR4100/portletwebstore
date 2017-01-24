
package com.portletwebstore.productlist;

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
public class ProductListController {

	@RenderMapping
	public String view(RenderRequest request, RenderResponse response, Model model) {

		Catalog catalog = CatalogStub.getCatalog();

		Long[] selectedItemArray = (Long[]) request.getPortletSession()
				.getAttribute("selectedItems", PortletSession.APPLICATION_SCOPE);

		if (selectedItemArray == null) {
			selectedItemArray = new Long[0];
			request.getPortletSession().setAttribute("selectedItems", selectedItemArray, PortletSession.APPLICATION_SCOPE);
		}

		SelectedItemsContainer selectedItems = new SelectedItemsContainer();
		selectedItems.setItemsFromArray(selectedItemArray);

		model.addAttribute("catalogItems", catalog.getCatalogItems());
		model.addAttribute("selectedItems", selectedItems);

		return "productlist/view";
	}

	@ActionMapping(params = "action=processCheck")
	public void processCheck(ActionRequest actionRequest, ActionResponse actionResponse, Model model) {
		processAction(actionRequest, actionResponse, model, "processCheck");
	}

	@ActionMapping(params = "action=processUncheck")
	public void processUncheck(ActionRequest actionRequest, ActionResponse actionResponse, Model model) {
		processAction(actionRequest, actionResponse, model, "processUncheck");
	}

	private void processAction(ActionRequest actionRequest, ActionResponse actionResponse, Model model, String action) {

		Long selectedId = Long.parseLong(actionRequest.getParameter("id"));

		Long[] selectedItemArray = (Long[])actionRequest.getPortletSession().
				getAttribute("selectedItems", PortletSession.APPLICATION_SCOPE);

		SelectedItemsContainer selectedItems = new SelectedItemsContainer();
		selectedItems.setItemsFromArray(selectedItemArray);

		if ("processCheck".equals(action)) {
			selectedItems.addItem(selectedId);
		} else if ("processUncheck".equals(action)) {
			selectedItems.removeItem(selectedId);
		}

		actionRequest.getPortletSession().setAttribute("selectedItems", selectedItems.getItemsAsArray(), PortletSession.APPLICATION_SCOPE);

	}


}