package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class CardNumberTest {
    private PaymentController paymentController;
    @BeforeEach
    void setUp() {
        paymentController = new PaymentController();
    }
    @ParameterizedTest
    @CsvSource({
            "nguyendt, false",
            "nguyen, false",
            "nguyen duc tu, false",
            "nguyen123, false",
            "kstn_group10_2021, true",
            "<script>alert(1)</script>, false",
            "OR 1=1, false",
            "null,false"
    })
    void test(String cardNumber, boolean expected) {
        //when
        boolean isValid = paymentController.validateCardNumber(cardNumber);
        //then
        assertEquals(expected, isValid);
    }
}