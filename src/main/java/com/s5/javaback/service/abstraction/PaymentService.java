package com.s5.javaback.service.abstraction;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;

public interface PaymentService {
    Preference createPreference(Long turnId) throws MPException, MPApiException;
}
