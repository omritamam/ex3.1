package brick_strategies;

import danogl.gui.rendering.TextRenderable;
import danogl.util.Counter;

public class CounterRenderer extends TextRenderable {
    private Counter counter;

    public CounterRenderer(Counter counter) {
        super(String.valueOf(counter.value()));
        this.counter = counter;
    }

    @Override
    public void update(double deltaTime) {
        super.setString(String.valueOf(counter.value()));
        super.update(deltaTime);
    }
}
