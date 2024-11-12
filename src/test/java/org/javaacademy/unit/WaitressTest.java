package org.javaacademy.unit;

import org.javaacademy.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javaacademy.burger.dish.DishType.BURGER;
import static com.javaacademy.burger.dish.DishType.FUAGRA;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("3. Тестирование официанта")
public class WaitressTest extends BaseTest {
    @Test
    @DisplayName("3.1: Был запрошен бургер, официант принял заказ")
    public void waitressTakeOrderSuccess() {
        assertTrue(waitress.giveOrderToKitchen(BURGER, kitchenMock));
    }

    @Test
    @DisplayName("3.2: Была запрошена фуагра, официант не принял заказ")
    public void waitressTakeOrderFail() {
        assertFalse(waitress.giveOrderToKitchen(FUAGRA, kitchenMock));
    }
}
