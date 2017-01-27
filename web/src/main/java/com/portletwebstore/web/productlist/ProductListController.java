
package com.portletwebstore.web.productlist;

import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.util.PortalUtil;
import com.portletwebstore.repository.Catalog;
import com.portletwebstore.repository.CatalogStub;
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

		/*SelectedItemsContainer selectedItems = (SelectedItemsContainer) request.getPortletSession()
				.getAttribute("selectedItems", PortletSession.APPLICATION_SCOPE);

		if (selectedItems == null) {
			selectedItems = new SelectedItemsContainer();
			request.getPortletSession().setAttribute("selectedItems", selectedItems, PortletSession.APPLICATION_SCOPE);
		}*/

		model.addAttribute("catalogItemsChunks", catalog.getPartitionedItems(3));
		model.addAttribute("selectedItems", selectedItems);

		return "web/viewproductlist";
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

		/*SelectedItemsContainer selectedItems = (SelectedItemsContainer) actionRequest.getPortletSession()
				.getAttribute("selectedItems", PortletSession.APPLICATION_SCOPE);*/

		if ("processCheck".equals(action)) {
			selectedItems.addItem(selectedId);
		} else if ("processUncheck".equals(action)) {
			selectedItems.removeItem(selectedId);
		}

		actionRequest.getPortletSession().setAttribute("selectedItems", selectedItems.getItemsAsArray(), PortletSession.APPLICATION_SCOPE);

	}

	@ResourceMapping("addToCart")
	public void processAddToCartAction(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException {
        processResourceAction(resourceRequest, resourceResponse, "addToCart");

	}

	@ResourceMapping("removeFromCart")
	public void processRemoveFromCartAction(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException {
        processResourceAction(resourceRequest, resourceResponse, "removeFromCart");
	}

	private void processResourceAction(ResourceRequest resourceRequest, ResourceResponse resourceResponse, String action) throws IOException {

        Long selectedId = Long.parseLong(resourceRequest.getParameter("id").replace("_", ""));

        Long[] selectedItemArray = (Long[])resourceRequest.getPortletSession().
                getAttribute("selectedItems", PortletSession.APPLICATION_SCOPE);

        SelectedItemsContainer selectedItems = new SelectedItemsContainer();
        selectedItems.setItemsFromArray(selectedItemArray);

        if ("addToCart".equals(action)) {
            selectedItems.addItem(selectedId);
        } else if ("removeFromCart".equals(action)) {
            selectedItems.removeItem(selectedId);
        }

        ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), "" + selectedItems.getSelectedCount());

        resourceRequest.getPortletSession().setAttribute("selectedItems", selectedItems.getItemsAsArray(), PortletSession.APPLICATION_SCOPE);
    }

}