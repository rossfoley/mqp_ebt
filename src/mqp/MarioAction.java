package mqp;

public abstract class MarioAction {
    protected int remainingFrames = 0;
    protected boolean[] action = {false, false, false, false, false, false};

    public MarioAction() {}

    public boolean isComplete() {
        return (remainingFrames == 0);
    }

    public boolean[] getAction() {
        remainingFrames--;
        return action;
    }
}
