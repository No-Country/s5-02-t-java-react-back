package com.s5.javaback.controller;

import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.s5.javaback.service.abstraction.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin("*")
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{turnId}")
    public ResponseEntity<Preference> createPreference(@PathVariable Long turnId) throws MPException, MPApiException {
        Preference preference = paymentService.createPreference(turnId);
        return ResponseEntity.status(HttpStatus.CREATED).body(preference);
    }

    @GetMapping("/success")
    public ModelAndView success( @RequestParam String payment_id,
                                 @RequestParam String status,
                                 @RequestParam String external_reference,
                                 @RequestParam String merchant_account_id) {

        ModelAndView mav = new ModelAndView("respuesta");
        mav.addObject("estado", "SUCCESSS");
        mav.addObject("paymentId", payment_id);
        mav.addObject("status", status);
        mav.addObject("externalReference", external_reference);
        mav.addObject("comerciantOrderId", merchant_account_id);
        /*
        mav.addObject("collection_id", collection_id);
        mav.addObject("collection_status", collection_status);
        mav.addObject("payment_id", payment_id);
        mav.addObject("status", status);
        mav.addObject("external_reference", external_reference);
        mav.addObject("payment_type", payment_type);
        mav.addObject("merchant_order_id", merchant_order_id);
        mav.addObject("preference_id", preference_id);
        mav.addObject("site_id", site_id);
        mav.addObject("processing_mode", processing_mode);
        mav.addObject("merchant_account_id", merchant_account_id);
       */
        return mav;
    }

}
