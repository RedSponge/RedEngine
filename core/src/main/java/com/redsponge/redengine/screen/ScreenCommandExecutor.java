package com.redsponge.redengine.screen;

import com.redsponge.redengine.utils.GameAccessor;
import com.redsponge.redengine.utils.Logger;
import com.strongjoshua.console.CommandExecutor;
import com.strongjoshua.console.annotation.ConsoleDoc;

public class ScreenCommandExecutor extends CommandExecutor {

    private AbstractScreen screen;
    private GameAccessor ga;

    public ScreenCommandExecutor(AbstractScreen screen, GameAccessor ga) {
        this.screen = screen;
        this.ga = ga;
    }

    @ConsoleDoc(description = "This is a test command")
    public void test() {
        Logger.log(this, "Wow it works!");
    }

    public void reset() {
        screen.show();
    }

    public void hp() {
        console.printCommands();
    }

}
