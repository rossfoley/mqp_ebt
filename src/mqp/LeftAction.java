package mqp;

import ch.idsia.benchmark.mario.engine.sprites.Mario;

public class LeftAction extends MarioAction {
    public LeftAction() {
        remainingFrames = 1;
        action[Mario.KEY_LEFT] = true;
    }
}
