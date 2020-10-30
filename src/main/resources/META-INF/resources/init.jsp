<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.commerce.constants.CommerceCheckoutWebKeys" %>
<%@ page import="com.liferay.commerce.demo.payment.method.purchase.order.display.context.PurchaseOrderCheckoutStepDisplayContext" %>
<%@ page import="com.liferay.portal.kernel.settings.LocalizedValuesMap" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.LocalizationUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>

<%@ page import="com.liferay.commerce.demo.payment.method.purchase.order.display.context.PurchaseOrderCheckoutStepDisplayContext" %>
<%@ page import="com.liferay.commerce.constants.CommerceCheckoutWebKeys" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>

<liferay-frontend:defineObjects />
<liferay-theme:defineObjects />

<%
    String redirect = ParamUtil.getString(request, "redirect");
%>