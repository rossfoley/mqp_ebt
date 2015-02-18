package mqp.gp;

import mqp.mario.EBTAgent;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

/**
 * Command to check if a wall is ahead of Mario
 * @author Ross Foley and Karl Kuhn
 */
public class IfWallAhead extends MarioCommand {
    public IfWallAhead(GPConfiguration config) throws InvalidConfigurationException {
        super(config);
    }

    @Override
    public int execute_int(ProgramChromosome c, int n, Object[] args) {
        EBTAgent agent = getAgent(c);
        int radius = agent.getRadius();
        boolean wallAhead = false;

        for (int x = 1; x <= radius; x++) {
            for (int y = 0; y <= radius; y++) {
                wallAhead = wallAhead || agent.getTerrain(x, y);
            }
        }

        if (wallAhead) {
            return c.execute_int(n, 0, args);
        } else {
            return c.execute_int(n, 1, args);
        }
    }

    @Override
    public String toString() {
        return "if wall(ahead) then &1 else &2";
    }

    @Override
    public String getName() {
        return "IfWallAhead";
    }
}
