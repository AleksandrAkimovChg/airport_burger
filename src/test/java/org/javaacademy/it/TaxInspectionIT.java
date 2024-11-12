package org.javaacademy.it;

import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.PayTerminal;
import com.javaacademy.burger.Paycheck;
import com.javaacademy.burger.Steakhouse;
import com.javaacademy.burger.dish.DishType;
import org.javaacademy.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static com.javaacademy.burger.Currency.*;
import static com.javaacademy.burger.dish.DishType.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


@DisplayName("7. Поверка работы ресторана налоговой инспекцией")
public class TaxInspectionIT extends BaseTest {
    private PayTerminal payTerminalSpy;
    private Steakhouse steakhouse;

    @BeforeEach
    void setup() {
        payTerminalSpy = spy(PayTerminal.class);
        steakhouse = new Steakhouse(waitressMock, kitchenMock, payTerminalSpy);
        when(waitressMock.giveOrderToKitchen(any(DishType.class), any(Kitchen.class))).thenReturn(true);
    }

    @Test
    @DisplayName("7.1: клиент захотел купить ребра за рубли. " +
            "Заказал ребра, получил чек(сумма - 700, тип заказа - ребра, валюта - рубли).")
    public void payOrderRubSuccess() {
        Paycheck actualPaycheck = steakhouse.makeOrder(RIBS, RUB);

        assertAll("Проверка позиций чека",
                () -> assertEquals(BigDecimal.valueOf(700), actualPaycheck.getTotalAmount()),
                () -> assertEquals(RIBS, actualPaycheck.getDishType()),
                () -> assertEquals(RUB, actualPaycheck.getCurrency())
        );
    }

    @Test
    @DisplayName("7.2: клиент захотел купить картошку за доллары. " +
            "Заказал картошку, получил чек(1, картошка, доллар)")
    public void payOrderUsdSuccess() {
        Mockito.doReturn(new Paycheck(BigDecimal.valueOf(1), USD, FRIED_POTATO))
                .when(payTerminalSpy).pay(FRIED_POTATO, USD);

        Paycheck actualPaycheck = steakhouse.makeOrder(FRIED_POTATO, USD);

        assertAll("Проверка позиций чека",
                () -> assertEquals(BigDecimal.valueOf(1), actualPaycheck.getTotalAmount()),
                () -> assertEquals(FRIED_POTATO, actualPaycheck.getDishType()),
                () -> assertEquals(USD, actualPaycheck.getCurrency())
        );
    }

    @Test
    @DisplayName("7.3: клиент захотел купить бургер за мозамбикские доллары. " +
            "Заказал бургер, получил чек(1, бургер, мозамбикский доллар)")
    public void payOrderMozambicanUsdSuccess() {
        Mockito.doReturn(new Paycheck(BigDecimal.valueOf(1), MOZAMBICAN_DOLLARS, BURGER))
                .when(payTerminalSpy).pay(BURGER, MOZAMBICAN_DOLLARS);

        Paycheck actualPaycheck = steakhouse.makeOrder(BURGER, MOZAMBICAN_DOLLARS);

        assertAll("Проверка позиций чека",
                () -> assertEquals(BigDecimal.valueOf(1), actualPaycheck.getTotalAmount()),
                () -> assertEquals(BURGER, actualPaycheck.getDishType()),
                () -> assertEquals(MOZAMBICAN_DOLLARS, actualPaycheck.getCurrency())
        );
    }
}
