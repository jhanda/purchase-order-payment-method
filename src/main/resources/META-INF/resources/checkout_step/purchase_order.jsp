<%@ include file="/init.jsp" %>

<%
    PurchaseOrderCheckoutStepDisplayContext purchaseOrderCheckoutStepDisplayContext = (PurchaseOrderCheckoutStepDisplayContext)request.getAttribute(CommerceCheckoutWebKeys.COMMERCE_CHECKOUT_STEP_DISPLAY_CONTEXT);
    String purchaseOrderNumber = purchaseOrderCheckoutStepDisplayContext.getPurchaseOrderNumber();
%>

<portlet:actionURL name="savePurchaseOrder" var="savePurchaseOrderActionURL" />

<aui:form action="<%= savePurchaseOrderActionURL %>" method="post" name="fm">
    <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

    <commerce-ui:panel>
        <commerce-ui:info-box title="poNumber">
            <aui:input class="w-50" id="purchase-order-number" label="purchase-order-number" name="purchase-order-number" value="<%= purchaseOrderNumber %>"/>
        </commerce-ui:info-box>
    </commerce-ui:panel>
</aui:form>

