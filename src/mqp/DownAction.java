package mqp;

import ch.idsia.benchmark.mario.engine.sprites.Mario;

public class DownAction extends MarioAction {
    public DownAction() {
        remainingFrames = 1;
        action[Mario.KEY_DOWN] = true;
    }
}
