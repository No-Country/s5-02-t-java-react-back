package com.s5.javaback.controller;

import com.mercadopago.resources.preference.Preference;
import com.s5.javaback.service.abstraction.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{turnId}")
    public ResponseEntity<Preference> createPreference(@PathVariable Long turnId){
        Preference preference = paymentService.createPreference(turnId);
        return ResponseEntity.status(HttpStatus.CREATED).body(preference);
    }

}
