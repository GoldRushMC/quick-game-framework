package implement.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * User: InspiredIdealist
 * Date: 5/3/2014
 */
public class MarkBlueprintCommand extends GameCommand {

    public MarkBlueprintCommand() {
        requiredArgs = 2;
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
