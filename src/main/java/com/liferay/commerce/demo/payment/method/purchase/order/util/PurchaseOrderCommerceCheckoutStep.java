package com.liferay.commerce.demo.payment.method.purchase.order.util;

import com.liferay.commerce.constants.CommerceCheckoutWebKeys;
import com.liferay.commerce.demo.payment.method.purchase.order.PurchaseOrderCommercePaymentMethod;
import com.liferay.commerce.demo.payment.method.purchase.order.display.context.PurchaseOrderCheckoutStepDisplayContext;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.util.BaseCommerceCheckoutStep;
import com.liferay.commerce.util.CommerceCheckoutStep;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.Portal;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.annotations.Preference;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(
        immediate = true,
        property = {
                "commerce.checkout.step.name=" + PurchaseOrderCommerceCheckoutStep.NAME,
                "commerce.checkout.step.order:Integer=" + (Integer.MAX_VALUE - 100)
        },
        service = CommerceCheckoutStep.class
)
public class PurchaseOrderCommerceCheckoutStep extends BaseCommerceCheckoutStep {

    public static final String NAME = "purchase-order";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean isActive(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse)
            throws Exception {

        CommerceOrder commerceOrder = _getCommerceOrder(httpServletRequest);

        if (PurchaseOrderCommercePaymentMethod.KEY.equals(
                commerceOrder.getCommercePaymentMethodKey())){
            return true;
        }
        return false;
    }

    @Override
    public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        String newPurchaseOrderNumber = ParamUtil.getString(actionRequest, "purchase-order-number");
        String origPurchaseOrderNumber = _commerceOrder.getPurchaseOrderNumber();

        if (!origPurchaseOrderNumber.equalsIgnoreCase(newPurchaseOrderNumber)) {
            _commerceOrderLocalService.updatePurchaseOrderNumber(_commerceOrder.getCommerceOrderId(), newPurchaseOrderNumber);
        }
    }

    @Override
    public void render(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        _purchaseOrderNumber = _getPurchaseOrder(httpServletRequest);
        _commerceOrder = _getCommerceOrder(httpServletRequest);
        PurchaseOrderCheckoutStepDisplayContext purchaseOrderCheckoutStepDisplayContext =
                new PurchaseOrderCheckoutStepDisplayContext(_commerceOrderService, _purchaseOrderNumber, httpServletRequest);

        httpServletRequest.setAttribute(
                CommerceCheckoutWebKeys.COMMERCE_CHECKOUT_STEP_DISPLAY_CONTEXT,
                purchaseOrderCheckoutStepDisplayContext);

        _jspRenderer.renderJSP(_servletContext, httpServletRequest,
                httpServletResponse, "/checkout_step/purchase_order.jsp");
    }

    private CommerceOrder _getCommerceOrder(
            HttpServletRequest httpServletRequest)
            throws PortalException {

        String commerceOrderUuid = ParamUtil.getString(httpServletRequest, "commerceOrderUuid");


        if (Validator.isNotNull(commerceOrderUuid)){
            long groupId = _commerceChannelLocalService.getCommerceChannelGroupIdBySiteGroupId(_portal.getScopeGroupId(httpServletRequest));
            return _commerceOrderService.getCommerceOrderByUuidAndGroupId(commerceOrderUuid, groupId);
        }

        return _commerceOrderHttpHelper.getCurrentCommerceOrder(
                httpServletRequest);
    }

    private String _getPurchaseOrder(HttpServletRequest httpServletRequest)
            throws PortalException {

        return _getCommerceOrder(httpServletRequest).getPurchaseOrderNumber();
    }


    private String _purchaseOrderNumber;

    @Reference
    private CommerceChannelLocalService _commerceChannelLocalService;

    @Reference
    private CommerceOrderHttpHelper _commerceOrderHttpHelper;

    @Reference
    private CommerceOrderService _commerceOrderService;

    @Reference
    private CommerceOrderLocalService _commerceOrderLocalService;

    private CommerceOrder _commerceOrder;

    @Reference
    private JSPRenderer _jspRenderer;

    @Reference
    private Portal _portal;

    @Reference(target = "(osgi.web.symbolicname=com.liferay.commerce.demo.payment.method.purchase.order)")
    private ServletContext _servletContext;


}
