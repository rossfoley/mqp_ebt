package mqp.gp;

import org.apache.commons.lang.StringUtils;
import org.jgap.InvalidConfigurationException;
import org.jgap.RandomGenerator;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;
import org.jgap.util.CloneException;
import org.jgap.util.ICloneable;

import java.util.ArrayList;

/**
 * Command to represent button presses
 * @author Ross Foley and Karl Kuhn
 */
public class ButtonPress extends CommandGene implements IMutateable, ICloneable {
    private static final String CVS_REVISION = "$Revision: 1.0 $";

    protected int currentValue = 0;
    protected int lowerBound = 0;
    protected int upperBound = 63;

    public transient final String[] buttons = {"Left", "Right", "Down", "Jump", "Run/Fire", "Up"};

    public ButtonPress(GPConfiguration config) throws InvalidConfigurationException {
        super(config, 0, CommandGene.IntegerClass, 0, null);
    }

    public void setValue(int value) {
        currentValue = value;
    }

    protected void setRandomValue() {
        RandomGenerator randomGen = getGPConfiguration().getRandomGenerator();
        double randomValue = randomGen.nextDouble() * (upperBound - lowerBound) + lowerBound;
        currentValue = (int) Math.round(randomValue);
    }

    public int execute_int(ProgramChromosome c, int n, Object[] args) {
        return currentValue;
    }

    public Class getChildType(IGPProgram gp, int chromNum) {
        return null;
    }

    public CommandGene applyMutation(int index, double percentage) throws InvalidConfigurationException {
        if (percentage > 0.85d) {
            // If percentage is very high, just set a random value
            setRandomValue();
        } else {
            // Otherwise, generate a new value based on the current value
            int newValue;
            RandomGenerator rand = getGPConfiguration().getRandomGenerator();
            double range = (upperBound - lowerBound) * percentage;

            // Compute a new value
            if (currentValue >= (upperBound - lowerBound) / 2) {
                newValue = currentValue - (int) Math.round(rand.nextInt() * range);
            } else {
                newValue = currentValue + (int) Math.round(rand.nextFloat() * range);
            }

            // Ensure that is within the bounds
            if (newValue < lowerBound || newValue > upperBound) {
                setRandomValue();
            } else {
                setValue(newValue);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        if (currentValue == 0) {
            return "None";
        }

        ArrayList<String> pressed = new ArrayList<String>();
        for (int i = 0; i < buttons.length; i++) {
            if ((currentValue & (1 << i)) > 0) {
                pressed.add(buttons[i]);
            }
        }

        return StringUtils.join(pressed.toArray(), ", ");
    }

    @Override
    public String getName() {
        return "ButtonPress";
    }

    public Object clone() {
        try {
            ButtonPress result = new ButtonPress(getGPConfiguration());
            result.currentValue = currentValue;
            return result;
        } catch (Throwable t) {
            throw new CloneException(t);
        }
    }
}
