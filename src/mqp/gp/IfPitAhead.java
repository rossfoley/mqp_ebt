package mqp.gp;

import mqp.mario.EBTAgent;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

/**
 * Command to check if a pit is ahead of Mario
 * @author Ross Foley and Karl Kuhn
 */
public class IfPitAhead extends MarioCommand {
    public IfPitAhead(GPConfiguration config) throws InvalidConfigurationException {
        super(config);
    }

    @Override
    public int execute_int(ProgramChromosome c, int n, Object[] args) {
        EBTAgent agent = getAgent(c);
        int radius = agent.getRadius();
        boolean pitAhead = false;

        // Check for a pit that spans an entire column
        for (int x = 1; x <= radius; x++) {
            boolean columnPit = true;
            for (int y = -1*radius; y <= -1; y++) {
                columnPit = columnPit && (!agent.getTerrain(x, y));
            }
            pitAhead = pitAhead || columnPit;
        }

        if (pitAhead) {
            return c.execute_int(n, 0, args);
        } else {
            return c.execute_int(n, 1, args);
        }
    }

    @Override
    public String toString() {
        return "if pit(ahead) then &1 else &2";
    }

    @Override
    public String getName() {
        return "IfPitAhead";
    }
}
