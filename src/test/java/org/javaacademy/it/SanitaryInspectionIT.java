package org.javaacademy.it;

import com.javaacademy.burger.Paycheck;
import com.javaacademy.burger.Steakhouse;
import com.javaacademy.burger.dish.Dish;
import org.javaacademy.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javaacademy.burger.Currency.RUB;
import static com.javaacademy.burger.dish.DishType.RIBS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("6. Поверка работы ресторана санэпидемстанцией")
public class SanitaryInspectionIT extends BaseTest {
    @Test
    @DisplayName("6.1: клиент захотел купить ребра за рубли. " +
            "Заказал ребра, получил чек(700 руб, ребра, рубли), подошел за заказом, получил ребра.")
    public void makeOrderSuccess() {
        steakhouse = new Steakhouse(waitress, kitchen, payTerminalMock);
        when(payTerminalMock.pay(RIBS, RUB)).thenReturn(new Paycheck(RIBS.getPrice(), RUB, RIBS));

        Paycheck actualPaycheck = steakhouse.makeOrder(RIBS, RUB);
        Dish actualDish = steakhouse.takeOrder(actualPaycheck);

        assertEquals(RIBS, actualDish.getDishType());
    }
}
