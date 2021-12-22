package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class UsernameTest {
    private CardLoginController cardLoginController;
    @BeforeEach
    void setUp() throws Exception{
        cardLoginController = new CardLoginController();
    }
    @ParameterizedTest
    @CsvSource({
            "nguyendt, true",
            "nguyen, false",
            "nguyen duc tu, false",
            "nguyen123, true",
            "$#nguyen, true",
            "<script>alert(1)</script>, false",
            "OR 1=1, false",
            "null,false"
    })
    void test(String userName, boolean expected) {
        //when
        boolean isValid = cardLoginController.validateUsernameCard(userName);
        //then
        assertEquals(expected, isValid);
    }
}