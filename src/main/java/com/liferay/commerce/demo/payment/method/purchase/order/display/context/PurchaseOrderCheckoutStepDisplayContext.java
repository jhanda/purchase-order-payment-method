package com.liferay.commerce.demo.payment.method.purchase.order.display.context;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.servlet.http.HttpServletRequest;

public class PurchaseOrderCheckoutStepDisplayContext {

    public PurchaseOrderCheckoutStepDisplayContext(
            CommerceOrderService commerceOrderService,
            String purchaseOrderNumber,
            HttpServletRequest httpServletRequest) {

        _commerceOrderService = commerceOrderService;
        _purchaseOrderNumber = purchaseOrderNumber;
        _httpServletRequest = httpServletRequest;

    }

    public String getPurchaseOrderNumber(){
        return _purchaseOrderNumber;
    }

    protected CommerceOrder getCommerceOrder() throws PortalException {
        if (_commerceOrder != null) {
            return _commerceOrder;
        }

        long commerceOrderId = ParamUtil.getLong(
                _httpServletRequest, "commerceOrderId");

        _commerceOrder = _commerceOrderService.getCommerceOrder(
                commerceOrderId);

        return _commerceOrder;
    }

    private CommerceOrder _commerceOrder;
    private CommerceOrderService _commerceOrderService;
    private String _purchaseOrderNumber;
    private HttpServletRequest _httpServletRequest;
}
