package com.eclipsekingdom.fractalforest.populator;

import com.eclipsekingdom.fractalforest.Permissions;
import com.eclipsekingdom.fractalforest.gui.pop.session.LivePopSessions;
import com.eclipsekingdom.fractalforest.populator.validation.NameStatus;
import com.eclipsekingdom.fractalforest.populator.validation.NameValidation;
import com.eclipsekingdom.fractalforest.util.PluginHelp;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.eclipsekingdom.fractalforest.util.language.Message.*;

public class CommandTPop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Permissions.canEditPop(player)) {
                if (args.length > 0) {
                    String subCommand = args[0];
                    if (subCommand.equalsIgnoreCase("help")) {
                        PluginHelp.showPop(player);
                    } else if (subCommand.equalsIgnoreCase("create")) {
                        processCreate(player, args);
                    } else if (subCommand.equalsIgnoreCase("edit")) {
                        processEdit(player, args);
                    } else if (subCommand.equalsIgnoreCase("rename")) {
                        processRename(player, args);
                    } else if (subCommand.equalsIgnoreCase("delete")) {
                        processRemove(player, args);
                    } else if (subCommand.equalsIgnoreCase("list")) {
                        processStormListRequest(player);
                    } else {
                        PluginHelp.showPop(player);
                    }
                } else {
                    PluginHelp.showPop(player);
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission for this command");
            }
        }

        return false;
    }

    private void processCreate(Player player, String[] args) {
        String name = args.length > 1 ? args[1] : getDefaultString();
        NameStatus status = NameValidation.clean(name);
        if(!PopCache.hasPopulator(name)){
            if (status == NameStatus.VALID) {
                TreePopulator pop = TreePopulator.defaultPopulator(name, player.getWorld());
                LivePopSessions.launch(player, pop, true);
            } else {
                player.sendMessage(ChatColor.RED + status.message);
            }
        }else{
            player.sendMessage(WARN_TPOP_EXISTS.getFromPop(name));
        }
    }

    private void processEdit(Player player, String[] args) {
        if (args.length > 1) {
            String name = args[1];
            if (PopCache.hasPopulator(name)) {
                TreePopulator pop = PopCache.getPopulator(name);
                LivePopSessions.launch(player, pop, false);
            } else {
                player.sendMessage(WARN_TPOP_NOT_FOUND.getFromPop(name));
            }
        } else {
            player.sendMessage(FORMAT_EDIT_TPOP.toString());
        }
    }

    private void processRename(Player player, String[] args) {
        if (args.length > 2) {
            String from = args[1];
            TreePopulator pop = PopCache.getPopulator(from);
            if (pop != null) {
                String to = args[2];
                NameStatus status = NameValidation.clean(to);
                if (status == NameStatus.VALID) {
                    pop.setName(to);
                    player.sendMessage(SUCCESS_TPOP_RENAME.getFromPop(to));
                } else {
                    player.sendMessage(ChatColor.RED + status.message);
                }
            } else {
                player.sendMessage(WARN_TPOP_NOT_FOUND.getFromPop(args[1]));
            }
        } else {
            player.sendMessage(SUGGEST_TPOP_HELP.toString());
        }
    }

    private void processRemove(Player player, String[] args) {
        if (args.length > 1) {
            TreePopulator pop = PopCache.getPopulator(args[1]);
            if (pop != null) {
                PopCache.removePopulator(pop);
                player.sendMessage(SUCCESS_TPOP_REMOVE.getFromPop(pop.getName()));
            } else {
                player.sendMessage(WARN_TPOP_NOT_FOUND.getFromPop(args[1]));
            }
        } else {
            player.sendMessage(SUGGEST_TPOP_HELP.toString());
        }
    }

    public static void processStormListRequest(Player player) {
        player.sendMessage(ChatColor.GOLD + "- - - Tree Populators - - -");
        boolean found = false;
        for (TreePopulator pop : PopCache.getPopulators()) {
            player.sendMessage(ChatColor.YELLOW + pop.getName());
            found = true;
        }
        if (!found) {
            player.sendMessage("-");
        }
    }

    private String getDefaultString() {
        String stormBase = ARG_TPOP + "_";
        int num = 1;
        String attempt = stormBase + num;
        while (PopCache.hasPopulator(attempt)) {
            num++;
            attempt = stormBase + num;
        }
        return attempt;
    }

}
