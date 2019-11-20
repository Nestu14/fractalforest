package com.eclipsekingdom.fractalforest.gen.pop;

import com.eclipsekingdom.fractalforest.Permissions;
import com.eclipsekingdom.fractalforest.gen.Generator;
import com.eclipsekingdom.fractalforest.gen.WorldData;
import com.eclipsekingdom.fractalforest.gen.pop.util.NameValidation;
import com.eclipsekingdom.fractalforest.gui.LiveSessions;
import com.eclipsekingdom.fractalforest.util.PluginHelp;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

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
                        processPopListRequest(player);
                    } else {
                        PluginHelp.showPop(player);
                    }
                } else {
                    PluginHelp.showPop(player);
                }
            } else {
                sender.sendMessage(ChatColor.RED + WARN_NO_PERMISSION.toString());
            }
        }

        return false;
    }

    private void processCreate(Player player, String[] args) {


        String name = args.length > 1 ? args[1] : getDefaultString();
        NameValidation.Status status = NameValidation.clean(name);
        if (!LiveSessions.isBusyPopBusy(name)) {
            if (!PopCache.hasPopulator(name)) {
                if (status == NameValidation.Status.VALID) {
                    TreePopulator pop = TreePopulator.defaultPopulator(name);
                    LiveSessions.launchPop(player, pop, true);
                } else {
                    player.sendMessage(ChatColor.RED + status.message);
                }
            } else {
                player.sendMessage(WARN_TPOP_EXISTS.getColoredFromPop(name, ChatColor.RED));
            }
        } else {
            player.sendMessage(WARN_BUSY_TPOP.getFromPlayer(LiveSessions.getPopEditor(name)));
        }
    }

    private void processEdit(Player player, String[] args) {
        if (args.length > 1) {
            String name = args[1];
            if (PopCache.hasPopulator(name)) {
                if (!LiveSessions.isBusyPopBusy(name)) {
                    TreePopulator pop = PopCache.getPopulator(name);
                    LiveSessions.launchPop(player, pop, false);
                } else {
                    player.sendMessage(WARN_BUSY_TPOP.getFromPlayer(LiveSessions.getPopEditor(name)));
                }
            } else {
                player.sendMessage(WARN_TPOP_NOT_FOUND.getColoredFromPop(name, ChatColor.RED));
            }
        } else {
            player.sendMessage(ChatColor.RED + FORMAT_EDIT_TPOP.toString());
        }
    }

    private void processRename(Player player, String[] args) {
        if (args.length > 2) {
            String from = args[1];
            TreePopulator pop = PopCache.getPopulator(from);
            if (pop != null) {
                String to = args[2];
                NameValidation.Status status = NameValidation.clean(to);
                if (status == NameValidation.Status.VALID) {
                    pop.setName(to);
                    player.sendMessage(SUCCESS_TPOP_RENAME.getColoredFromPop(to, ChatColor.GREEN));
                } else {
                    player.sendMessage(ChatColor.RED + status.message);
                }
            } else {
                player.sendMessage(WARN_TPOP_NOT_FOUND.getColoredFromPop(args[1], ChatColor.RED));
            }
        } else {
            player.sendMessage(ChatColor.RED + SUGGEST_TPOP_HELP.toString());
        }
    }

    private void processRemove(Player player, String[] args) {
        if (args.length > 1) {
            TreePopulator pop = PopCache.getPopulator(args[1]);
            if (pop != null) {
                PopCache.removePopulator(pop);
                for (Map.Entry<World, WorldData> entry : Generator.getWorldToData().entrySet()) {
                    WorldData worldData = entry.getValue();
                    if (pop == worldData.getTreePopulator()) {
                        worldData.setTreePopulator(entry.getKey(), null);
                    }
                }
                player.sendMessage(SUCCESS_TPOP_REMOVE.getColoredFromPop(pop.getName(), ChatColor.GREEN));
            } else {
                player.sendMessage(WARN_TPOP_NOT_FOUND.getColoredFromPop(args[1], ChatColor.RED));
            }
        } else {
            player.sendMessage(ChatColor.RED + SUGGEST_TPOP_HELP.toString());
        }
    }

    public static void processPopListRequest(Player player) {
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
