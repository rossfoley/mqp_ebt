package mqp.gp.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

/**
 * Command to check if Mario can jump
 * @author Ross Foley and Karl Kuhn
 */
public class IfMarioCanJump extends MarioCommand {
    public IfMarioCanJump(GPConfiguration config) throws InvalidConfigurationException {
        super(config);
    }

    @Override
    public int execute_int(ProgramChromosome c, int n, Object[] args) {
        if (getAgent(c).getMarioCanJump()) {
            return c.execute_int(n, 0, args);
        } else {
            return c.execute_int(n, 1, args);
        }
    }

    @Override
    public String toString() {
        return "if mario_can(jump) then &1 else &2";
    }

    @Override
    public String getName() {
        return "IfMarioCanJump";
    }
}
