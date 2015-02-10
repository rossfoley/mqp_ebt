package mqp;

import ch.idsia.benchmark.mario.engine.sprites.Mario;

public class FireAction extends MarioAction {
    public FireAction() {
        remainingFrames = 1;
        action[Mario.KEY_SPEED] = true;
    }
}
