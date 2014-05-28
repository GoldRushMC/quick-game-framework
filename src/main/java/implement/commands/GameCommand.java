package implement.commands;

import framework.utility.GameAuth;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * User: InspiredIdealist
 * Date: 4/18/2014
 */
public abstract class GameCommand implements CommandExecutor {

    protected static String DEFAULT_PERMISSION = "DEFAULT";
    protected Permission permissionName;
    protected boolean isPlayerOnly = false;
    protected int requiredArgs = 0;

    public GameCommand() {
        GameAuth.getAuthsByCommand(this);
        permissionName = new Permission(DEFAULT_PERMISSION);
    }

    public GameCommand(Permission permissionName) {
        this.permissionName = permissionName;

    }

    public GameCommand(Permission permissionName, int requiredArgs) {
        this(permissionName);
        this.requiredArgs = requiredArgs;
    }

    public GameCommand(Permission permissionName, int requiredArgs, boolean isPlayerOnly) {
        this(permissionName, requiredArgs);
        this.isPlayerOnly = isPlayerOnly;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Validates that the sender can indeed do this command. Then tries to do it.
        return (isPlayerOnly && sender instanceof Player) && sender.hasPermission(permissionName) && executeCommand(sender, command, label, args);
    }

    public abstract boolean executeCommand(CommandSender sender, Command command, String label, String[] args);
}
