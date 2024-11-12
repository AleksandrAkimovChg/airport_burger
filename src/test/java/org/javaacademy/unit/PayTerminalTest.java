package org.javaacademy.unit;

import com.javaacademy.burger.Paycheck;
import com.javaacademy.burger.exception.NotAcceptedCurrencyException;
import org.javaacademy.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.javaacademy.burger.Currency.MOZAMBICAN_DOLLARS;
import static com.javaacademy.burger.Currency.RUB;
import static com.javaacademy.burger.dish.DishType.BURGER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("4. Тестирование терминала оплаты")
public class PayTerminalTest extends BaseTest {
    @Test
    @DisplayName("4.1: На оплату поступил бургер, оплата в рублях. " +
            "Вернулся чек с оплатой в котором указано: 300 рублей, валюта - рубль, товар - бургер.")
    public void payTerminalTestSuccess() {
        Paycheck actualPaycheck = payTerminal.pay(BURGER, RUB);

        assertAll("Проверка позиций чека",
                () -> assertEquals(BigDecimal.valueOf(300), actualPaycheck.getTotalAmount()),
                () -> assertEquals(RUB, actualPaycheck.getCurrency()),
                () -> assertEquals(BURGER, actualPaycheck.getDishType())
        );
    }

    @Test
    @DisplayName("4.2: На оплату поступил бургер, " +
            "оплата в мозамбикских долларах, вылетела ошибка NotAcceptedCurrencyException")
    public void payTerminalTestFail() {
        assertThrows(NotAcceptedCurrencyException.class, () -> payTerminal.pay(BURGER, MOZAMBICAN_DOLLARS));
    }
}
