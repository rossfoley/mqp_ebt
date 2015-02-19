package mqp.gp.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

/**
 * Command to check if a block is at the specified position
 * @author Ross Foley and Karl Kuhn
 */
public class IfBlockAtPosition extends MarioCommand {
    private final int x, y;

    public IfBlockAtPosition(GPConfiguration config, int x, int y) throws InvalidConfigurationException {
        super(config);
        this.x = x;
        this.y = y;
    }

    @Override
    public int execute_int(ProgramChromosome c, int n, Object[] args) {
        if (getAgent(c).getTerrain(x, y)) {
            return c.execute_int(n, 0, args);
        } else {
            return c.execute_int(n, 1, args);
        }
    }

    @Override
    public String toString() {
        return "if block_at(" + x + ", " + y + ") then &1 else &2";
    }

    @Override
    public String getName() {
        return "IfBlockAtPosition(" + x + ", " + y + ")";
    }
}
