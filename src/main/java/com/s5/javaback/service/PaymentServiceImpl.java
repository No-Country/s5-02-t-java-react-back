package com.s5.javaback.service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.resources.preference.Preference;
import com.s5.javaback.model.entity.Turn;
import com.s5.javaback.service.abstraction.PaymentService;
import com.s5.javaback.service.abstraction.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${access.token}")
    private String accessToken;
    @Autowired
    private TurnService turnService;

    @Override
    public Preference createPreference(Long turnId) {
        Turn turn = turnService.findById(turnId);
        MercadoPagoConfig.setAccessToken(accessToken);
        PreferenceClient preferenceClient = new PreferenceClient();
        //Creo un iten de la preferencia que serian los datos del turno
        PreferenceItemRequest item = PreferenceItemRequest.builder()

                .build();

        return null;
    }
}
