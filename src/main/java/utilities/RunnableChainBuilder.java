package utilities;

import java.util.ArrayList;
import java.util.List;

public final class RunnableChainBuilder {
    private final List<Runnable> runnables;

    public RunnableChainBuilder(Runnable runnable) {
        runnables = new ArrayList<>(List.of(runnable));
    }

    public RunnableChainBuilder add(Runnable runnable) {
        runnables.add(runnable);
        return this;
    }

    public void execute() {
        runnables.forEach(Runnable::run);
    }
}
