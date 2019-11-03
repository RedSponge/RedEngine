package com.redsponge.redengine.screen;

import com.redsponge.redengine.utils.GameAccessor;
import com.strongjoshua.console.annotation.ConsoleDoc;

public class TestTest extends ScreenCommandExecutor {

    public TestTest(AbstractScreen screen, GameAccessor ga) {
        super(screen, ga);
    }

    public void test2() {
        console.log("This is so sad alexa play decpacito 2");
    }

    @ConsoleDoc(description = "Adds two numbers together", paramDescriptions = {"First number", "Second number"})
    public void add(int a, int b)
    {
        console.log(String.format("%d + %d = %d", a, b, a+b));
    }

    public void clear() {
        console.clear();
    }
}
