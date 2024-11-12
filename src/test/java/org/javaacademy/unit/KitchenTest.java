package org.javaacademy.unit;

import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.exception.KitchenHasNoGasException;
import com.javaacademy.burger.exception.UnsupportedDishTypeException;
import org.javaacademy.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javaacademy.burger.dish.DishType.BURGER;
import static com.javaacademy.burger.dish.DishType.FUAGRA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("2. Тестирование кухни")
public class KitchenTest extends BaseTest {

    @Test
    @DisplayName("2.1: Был запрошен на приготовление бургер, " +
            "кухня успешно приготовила бургер (бургер появился на столе готовой еды)")
    public void orderBurgerSuccess() {
        kitchen.cook(BURGER);

        Dish actual = kitchen
                .getCompletedDishes()
                .get(BURGER)
                .peek();

        assertEquals(BURGER, actual.getDishType());
    }

    @Test
    @DisplayName("2.2: Был запрошен на приготовление бургер, " +
            "на кухне выключили газ, вылетела ошибка KitchenHasNoGasException")
    public void cookFailKitchenHasNoGas() {
        kitchen.setHasGas(false);

        assertThrows(KitchenHasNoGasException.class, () -> kitchen.cook(BURGER));
    }

    @Test
    @DisplayName("2.3: Была запрошена на приготовление фуагра, " +
            "вылетела ошибка UnsupportedDishTypeException")
    public void orderFoieGrasFail() {
        assertThrows(UnsupportedDishTypeException.class, () -> kitchen.cook(FUAGRA));
    }
}
