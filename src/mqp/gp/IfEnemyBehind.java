package mqp.gp;

import mqp.mario.EBTAgent;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

/**
 * Command to check if an enemy is behind of Mario
 * @author Ross Foley and Karl Kuhn
 */
public class IfEnemyBehind extends MarioCommand {
    public IfEnemyBehind(GPConfiguration config) throws InvalidConfigurationException {
        super(config);
    }

    @Override
    public int execute_int(ProgramChromosome c, int n, Object[] args) {
        EBTAgent agent = getAgent(c);
        int radius = agent.getRadius();
        boolean enemyBehind = false;

        for (int x = -1*radius; x <= -1; x++) {
            for (int y = -1*radius; y <= radius; y++) {
                enemyBehind = enemyBehind || agent.getEnemy(x, y);
            }
        }

        if (enemyBehind) {
            return c.execute_int(n, 0, args);
        } else {
            return c.execute_int(n, 1, args);
        }
    }

    @Override
    public String toString() {
        return "if enemy(behind) then &1 else &2";
    }

    @Override
    public String getName() {
        return "IfEnemyBehind";
    }
}
