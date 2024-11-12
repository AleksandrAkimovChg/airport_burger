package org.javaacademy;

import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.PayTerminal;
import com.javaacademy.burger.Steakhouse;
import com.javaacademy.burger.Waitress;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public abstract class BaseTest {
    protected Kitchen kitchen;
    protected Waitress waitress;
    protected PayTerminal payTerminal;
    protected Steakhouse steakhouse;
    protected  Waitress waitressMock;
    protected Kitchen kitchenMock;
    protected PayTerminal payTerminalMock;

    @BeforeEach
    void setup() {
        kitchen = new Kitchen();
        waitress = new Waitress();
        payTerminal = new PayTerminal();
        steakhouse = new Steakhouse(waitress, kitchen, payTerminal);
        waitressMock = mock(Waitress.class);
        kitchenMock = mock(Kitchen.class);
        payTerminalMock = mock(PayTerminal.class);
    }
}
