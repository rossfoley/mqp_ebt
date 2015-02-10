package mqp;

import ch.idsia.benchmark.mario.engine.sprites.Mario;

public class RightAction extends MarioAction {
    public RightAction() {
        remainingFrames = 1;
        action[Mario.KEY_RIGHT] = true;
    }
}
