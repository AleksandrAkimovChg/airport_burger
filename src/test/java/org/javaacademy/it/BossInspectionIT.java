package org.javaacademy.it;

import com.javaacademy.burger.Paycheck;
import com.javaacademy.burger.dish.Dish;
import org.javaacademy.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.javaacademy.burger.Currency.RUB;
import static com.javaacademy.burger.dish.DishType.BURGER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("5. Поверка работы ресторана владельцем")
public class BossInspectionIT extends BaseTest {

    @Test
    @DisplayName("5.1: клиент захотел купить бургер за рубли. " +
            "Заказал бургер, получил чек (300 руб, бургер, рубли), подошел за заказом, получил бургер. " +
            "Проверить чек, проверить блюдо(что это именно бургер!).")
    public void makeOrderSuccess() {
        Paycheck actualPaycheck = steakhouse.makeOrder(BURGER, RUB);
        Dish actualDish = steakhouse.takeOrder(actualPaycheck);

        assertAll("Проверка позиций чека",
                () -> assertEquals(BigDecimal.valueOf(300), actualPaycheck.getTotalAmount()),
                () -> assertEquals(BURGER, actualPaycheck.getDishType()),
                () -> assertEquals(RUB, actualPaycheck.getCurrency())
        );

        assertEquals(BURGER, actualDish.getDishType());
    }
}
