package mqp.gp.command;

import mqp.mario.EBTAgent;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

/**
 * Base class for all of our function genes
 * @author Ross Foley and Karl Kuhn
 */
public abstract class MarioCommand extends CommandGene {
    private static final String CVS_REVISION = "$Revision: 1.0 $";

    public MarioCommand(final GPConfiguration a_conf) throws InvalidConfigurationException {
        super(a_conf, 2, CommandGene.IntegerClass);
    }

    public Class getChildType(IGPProgram a_ind, int a_chromNum) {
        return CommandGene.IntegerClass;
    }

    public EBTAgent getAgent(ProgramChromosome chrom) {
        return (EBTAgent) chrom.getIndividual().getApplicationData();
    }
}
