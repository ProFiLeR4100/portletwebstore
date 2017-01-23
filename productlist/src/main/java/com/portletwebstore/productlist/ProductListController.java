
package com.portletwebstore.productlist;

import com.portletwebstore.repository.Catalog;
import com.portletwebstore.repository.CatalogStub;
import com.portletwebstore.repository.SelectedItemsContainer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Controller
@RequestMapping("VIEW")
public class ProductListController {

	@RenderMapping
	public String view(RenderRequest request, RenderResponse response, Model model) {

		Catalog catalog = CatalogStub.getCatalog();

		SelectedItemsContainer selectedItems = (SelectedItemsContainer)request.getPortletSession().getAttribute("selectedItems");

		if (selectedItems == null) {
			selectedItems = new SelectedItemsContainer();
			request.getPortletSession().setAttribute("selectedItems", selectedItems);
		}

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
		SelectedItemsContainer selectedItems = (SelectedItemsContainer)actionRequest.getPortletSession().getAttribute("selectedItems");

		if ("processCheck".equals(action)) {
			selectedItems.addItem(selectedId);
		} else if ("processUncheck".equals(action)) {
			selectedItems.removeItem(selectedId);
		}

		System.out.println("action=processCheck & id = " + selectedId);
		System.out.println("selectedItems " + selectedItems.getSelectedItems());

	}


}