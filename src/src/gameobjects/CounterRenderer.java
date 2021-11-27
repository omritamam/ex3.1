package src.gameobjects;

import danogl.gui.rendering.TextRenderable;
import danogl.util.Counter;

public class CounterRenderer extends TextRenderable {
    private final Counter counter;

    /**
     * constructor
     * @param counter - global lives counter
     */
    public CounterRenderer(Counter counter) {
        super(String.valueOf(counter.value()));
        this.counter = counter;
    }

    /**
     * method to run once a frame
     * @param deltaTime Time since last frame
     */
    @Override
    public void update(double deltaTime) {
        super.setString(String.valueOf(counter.value()));
        super.update(deltaTime);
    }
}
