package mqp;

import ch.idsia.benchmark.mario.engine.sprites.Mario;

public class JumpAction extends MarioAction {
    public JumpAction() {
        remainingFrames = 1;
        action[Mario.KEY_JUMP] = true;
    }
}
