package mqp.gp;

import mqp.mario.EBTAgent;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

/**
 * Command to check if an enemy is ahead of Mario
 * @author Ross Foley and Karl Kuhn
 */
public class IfEnemyAhead extends MarioCommand {
    public IfEnemyAhead(GPConfiguration config) throws InvalidConfigurationException {
        super(config);
    }

    @Override
    public int execute_int(ProgramChromosome c, int n, Object[] args) {
        EBTAgent agent = getAgent(c);
        int radius = agent.getRadius();
        boolean enemyAhead = false;

        for (int x = 1; x <= radius; x++) {
            for (int y = -1*radius; y <= radius; y++) {
                enemyAhead = enemyAhead || agent.getEnemy(x, y);
            }
        }

        if (enemyAhead) {
            return c.execute_int(n, 0, args);
        } else {
            return c.execute_int(n, 1, args);
        }
    }

    @Override
    public String toString() {
        return "if enemy(ahead) then &1 else &2";
    }

    @Override
    public String getName() {
        return "IfEnemyAhead";
    }
}
