package com.portletwebstore.web.cart;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

import javax.portlet.RenderRequest;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by dima_2 on 31.01.2017.
 */

public class Utils {

    public static final String DYNAMIC_ELEMENT_NAME = "/root/dynamic-element[@name='";
    public static final String PRODUCT_IMAGE_URL = "productImage";
    public static final String PRODUCT_DESCRIPTION = "productDescription";
    public static final String DYNAMIC_CONTENT = "']/dynamic-content";

    public static String getImageURLByArticleId(RenderRequest request, String articleId) throws SystemException, PortalException, DocumentException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        String content = StringPool.BLANK;

        JournalArticle article = JournalArticleLocalServiceUtil.getArticle(themeDisplay.getScopeGroupId(), articleId);

        Document document = SAXReaderUtil.read(article.getContent());

//        String imageUrl = (String)article.getExpandoBridge().getAttribute("imageUrl");
//        String productDescription = (String)article.getExpandoBridge().getAttribute("productDescription");

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
        System.out.println("productDescription Utils " + productDescription);
        System.out.println("imageUrl Utils " + imageUrl);
        System.out.println("---------------------------------");

        return imageUrl;
    }

}
