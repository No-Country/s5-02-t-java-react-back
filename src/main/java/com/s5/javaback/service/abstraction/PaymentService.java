package com.s5.javaback.service.abstraction;

import com.mercadopago.resources.preference.Preference;

public interface PaymentService {
    Preference createPreference(Long turnId);
}
