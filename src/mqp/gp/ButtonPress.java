package mqp.gp;

import org.apache.commons.lang.StringUtils;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Terminal;

import java.util.ArrayList;

/**
 * Command to represent button presses
 * @author Ross Foley and Karl Kuhn
 */
public class ButtonPress extends Terminal {
    public transient final String[] buttons = {"Left", "Right", "Down", "Jump", "Run/Fire", "Up"};

    public ButtonPress(GPConfiguration config) throws InvalidConfigurationException {
        super(config, CommandGene.IntegerClass, 0, 63, true);
    }

    @Override
    public String toString() {
        if (m_value_int == 0) {
            return "None";
        }

        ArrayList<String> pressed = new ArrayList<String>();

        for (int i = 0; i < buttons.length; i++) {
            if ((m_value_int & (1 << i)) > 0) {
                pressed.add(buttons[i]);
            }
        }

        return StringUtils.join(pressed.toArray(), ", ");
    }

    @Override
    public String getName() {
        return "ButtonPress";
    }
}
