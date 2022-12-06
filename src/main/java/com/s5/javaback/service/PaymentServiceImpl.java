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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${access.token}")
    private String accessToken;
    @Autowired
    private TurnService turnService;

    @Override
    public Preference createPreference(Long turnId) {
        Turn turn = turnService.findById(turnId);
        //Le paso el token de la aplicacion
        MercadoPagoConfig.setAccessToken(accessToken);
        // Crea un objeto de preferencia
        PreferenceClient preferenceClient = new PreferenceClient();
        //Creo un iten de la preferencia que serian los datos del turno
        List<PreferenceItemRequest> items = new ArrayList<>();
        PreferenceItemRequest item = PreferenceItemRequest.builder()
                .title(turn.getHomeEvent().getName())
                .quantity(1)
                .unitPrice(BigDecimal.valueOf(turn.getPrice()))
                .description("Turno para la cas" + turn.getHomeEvent().getName())
                .currencyId("ARS")
                .build();
        items.add(item);

        return null;
    }
}
