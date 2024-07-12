package com.example.Trivago.Repository;

import com.example.Trivago.Entity.PaymentMethod;

import java.util.Optional;

public interface IPaymentMethodRepository {
    Optional<PaymentMethod> findByTypeAndNumber(String type, String number);

    PaymentMethod save(PaymentMethod newPaymentMethod);
}
