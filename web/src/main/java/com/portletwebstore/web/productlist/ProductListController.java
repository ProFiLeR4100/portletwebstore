
package com.portletwebstore.web.productlist;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleDisplay;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journalcontent.util.JournalContentUtil;
import com.portletwebstore.repository.Catalog;
import com.portletwebstore.repository.CatalogStub;
import com.portletwebstore.repository.SelectedItemsContainer;
import com.portletwebstore.web.cart.CartController;
import com.portletwebstore.web.cart.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import javax.portlet.*;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("VIEW")
public class ProductListController {

	private static Log LOG = LogFactoryUtil.getLog(CartController.class);
	public static final String DYNAMIC_ELEMENT_NAME = "/root/dynamic-element[@name='";
	public static final String PRODUCT_IMAGE_URL = "productImage";
	public static final String PRODUCT_DESCRIPTION = "productDescription";
	public static final String DYNAMIC_CONTENT = "']/dynamic-content";

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
		model.addAttribute("utils", new Utils());

		////////

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		String content = StringPool.BLANK;
		try {
			/*JournalArticle journalArticle = JournalArticleLocalServiceUtil.getArticleByUrlTitle(themeDisplay.getScopeGroupId(), articleName);
			String articleId = journalArticle.getArticleId();
			JournalArticleDisplay articleDisplay = JournalContentUtil.getDisplay(themeDisplay.getScopeGroupId(), articleId,
					StringPool.BLANK, StringPool.BLANK, themeDisplay);
			content = articleDisplay.getContent();*/

			/*Long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
			DLFolder dir = DLFolderLocalServiceUtil.getFolder(themeDisplay.getScopeGroupId());

			List<JournalArticle> articles = JournalArticleLocalServiceUtil.getArticles(themeDisplay.getScopeGroupId(), dir.getFolderId());

			for (JournalArticle item : articles) {

				Document document = SAXReaderUtil.read(item.getContent());

				String imageUrl = "", productDescription = "", xpathExpression = "";

				xpathExpression = DYNAMIC_ELEMENT_NAME + PRODUCT_IMAGE_URL + DYNAMIC_CONTENT;
				if (document.selectSingleNode(xpathExpression) != null) {
					imageUrl = document.selectSingleNode(xpathExpression).getText();
				}

				xpathExpression = DYNAMIC_ELEMENT_NAME + PRODUCT_DESCRIPTION + DYNAMIC_CONTENT;
				if (document.selectSingleNode(xpathExpression) != null) {
					productDescription = document.selectSingleNode(xpathExpression).getText();
				}
				System.out.println("---------------------------------");
				System.out.println("productDescription " + productDescription);
				System.out.println("imageUrl " + imageUrl);
				System.out.println("getArticleId " + item.getArticleId());
				System.out.println("getArticleId" + item.getArticleId());
				System.out.println("---------------------------------");

			}*/

			//String articleName = "24371";

			//JournalArticle journalArticle = JournalArticleLocalServiceUtil.getArticleByUrlTitle(themeDisplay.getScopeGroupId(), articleName);
			//String articleId = journalArticle.getArticleId();

			String articleId = "24371";

			/*JournalArticleDisplay articleDisplay = JournalContentUtil.getDisplay(themeDisplay.getScopeGroupId(), articleId,
				StringPool.BLANK, StringPool.BLANK, themeDisplay);
			content = articleDisplay.getContent();

			System.out.println(content);*/

			JournalArticle article = JournalArticleLocalServiceUtil.getArticle(themeDisplay.getScopeGroupId(), articleId);

			Document document = SAXReaderUtil.read(article.getContent());

			String imageUrl = "", productDescription = "", xpathExpression = "";

			xpathExpression = DYNAMIC_ELEMENT_NAME + PRODUCT_IMAGE_URL + DYNAMIC_CONTENT;
			if (document.selectSingleNode(xpathExpression) != null) {
				imageUrl = document.selectSingleNode(xpathExpression).getText();
			}

			xpathExpression = DYNAMIC_ELEMENT_NAME + PRODUCT_DESCRIPTION + DYNAMIC_CONTENT;
			if (document.selectSingleNode(xpathExpression) != null) {
				productDescription = document.selectSingleNode(xpathExpression).getText();
			}

			System.out.println("---------------------------------");
			System.out.println("productDescription " + productDescription);
			System.out.println("imageUrl " + imageUrl);
			System.out.println("---------------------------------");

		} catch (Exception e) {
			LOG.error("Error while retrieving article :", e);
		}

		///////

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