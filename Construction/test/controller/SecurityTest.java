package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class SecurityTest {
    private PaymentController paymentController;
    @BeforeEach
    void setUp() {
        paymentController = new PaymentController();
    }
    @ParameterizedTest
    @CsvSource({
            "910, true",
            "nguyen, false",
            "nguyen duc tu, false",
            "kstn_group10_2021, false",
            "<script>alert(1)</script>, false",
            "OR 1=1, false",
            "null,false"
    })
    void test(String securityCode, boolean expected) {
        //when
        boolean isValid = paymentController.validateSecurityCode(securityCode);
        //then
        assertEquals(expected, isValid);
    }
}