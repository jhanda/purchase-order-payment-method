package com.liferay.commerce.demo.payment.method.purchase.order;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.constants.CommercePaymentConstants;
import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.commerce.payment.request.CommercePaymentRequest;
import com.liferay.commerce.payment.result.CommercePaymentResult;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import org.osgi.service.component.annotations.Component;

import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author jhanda
 */
@Component(
        immediate = true,
        property = "commerce.payment.engine.method.key=" + PurchaseOrderCommercePaymentMethod.KEY,
        service = CommercePaymentMethod.class
)
public class PurchaseOrderCommercePaymentMethod implements CommercePaymentMethod {

    public static final String KEY = "Purchase Order";

    @Override
    public String getDescription(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle("content.Language", locale, getClass());

        return LanguageUtil.get(
                resourceBundle, "purchase-order-method-description");
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public String getName(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
                "content.Language", locale, getClass());

        return LanguageUtil.get(resourceBundle, "purchase-order");
    }

    @Override
    public int getPaymentType() {
        return CommercePaymentConstants.COMMERCE_PAYMENT_METHOD_TYPE_OFFLINE;
    }

    @Override
    public String getServletPath() {
        return null;
    }

    @Override
    public boolean isCompleteEnabled() {
        return true;
    }



    @Override
    public CommercePaymentResult completePayment(
            CommercePaymentRequest commercePaymentRequest)
            throws Exception {

        return new CommercePaymentResult(
                null, commercePaymentRequest.getCommerceOrderId(),
                CommerceOrderConstants.PAYMENT_STATUS_PENDING, false, null, null,
                Collections.emptyList(), true);
    }

    @Override
    public CommercePaymentResult processPayment(
            CommercePaymentRequest commercePaymentRequest)
            throws Exception {

        return new CommercePaymentResult(
                null, commercePaymentRequest.getCommerceOrderId(),
                CommerceOrderConstants.PAYMENT_STATUS_AUTHORIZED, false, null, null,
                Collections.emptyList(), true);
    }

    private ResourceBundle _getResourceBundle(Locale locale) {
        return ResourceBundleUtil.getBundle(
                "content.Language", locale, getClass());
    }
}