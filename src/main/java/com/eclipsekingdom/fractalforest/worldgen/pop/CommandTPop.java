package com.eclipsekingdom.fractalforest.worldgen.pop;

import com.eclipsekingdom.fractalforest.gui.LiveSessions;
import com.eclipsekingdom.fractalforest.sys.Permissions;
import com.eclipsekingdom.fractalforest.sys.PluginHelp;
import com.eclipsekingdom.fractalforest.worldgen.Generator;
import com.eclipsekingdom.fractalforest.worldgen.WorldData;
import com.eclipsekingdom.fractalforest.worldgen.pop.util.NameValidation;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

import static com.eclipsekingdom.fractalforest.sys.language.Message.*;

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
                    } else if (subCommand.equalsIgnoreCase("createfrom")) {
                        processCreateFrom(player, args);
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
        if (!PopCache.isPreset(name)) {
            if (!LiveSessions.isBusyPopBusy(name)) {
                if (!PopCache.hasPopulator(name)) {
                    if (status == NameValidation.Status.VALID) {
                        TreePopulator pop = TreePopulator.defaultPopulator(name);
                        LiveSessions.launchPop(player, pop, true);
                    } else {
                        player.sendMessage(ChatColor.RED + status.message);
                    }
                } else {
                    player.sendMessage(WARN_TPOP_EXISTS.coloredFromPop(name, ChatColor.RED));
                }
            } else {
                player.sendMessage(WARN_BUSY_TPOP.fromPlayer(LiveSessions.getPopEditor(name)));
            }
        } else {
            player.sendMessage(ChatColor.RED + WARN_PRESET.fromPop(name));
        }
    }

    private void processEdit(Player player, String[] args) {
        if (args.length > 1) {
            String name = args[1];
            if (!PopCache.isPreset(name)) {
                if (PopCache.hasPopulator(name)) {
                    if (!LiveSessions.isBusyPopBusy(name)) {
                        TreePopulator pop = PopCache.getPopulator(name);
                        LiveSessions.launchPop(player, pop, false);
                    } else {
                        player.sendMessage(WARN_BUSY_TPOP.fromPlayer(LiveSessions.getPopEditor(name)));
                    }
                } else {
                    player.sendMessage(WARN_TPOP_NOT_FOUND.coloredFromPop(name, ChatColor.RED));
                }
            } else {
                player.sendMessage(ChatColor.RED + WARN_PRESET.fromPop(name));
            }
        } else {
            player.sendMessage(ChatColor.RED + INFO_FORMAT.fromFormat("/tpop edit [" + ARG_TPOP + "]"));
        }
    }

    private void processCreateFrom(Player player, String[] args) {
        if (args.length > 1) {
            String name = args[1];
            if (PopCache.hasPopulator(name)) {
                TreePopulator pop = PopCache.getPopulator(name);
                LiveSessions.launchPop(player, pop.clone(), true);
            } else {
                player.sendMessage(WARN_TPOP_NOT_FOUND.coloredFromPop(name, ChatColor.RED));
            }
        } else {
            player.sendMessage(ChatColor.RED + INFO_FORMAT.fromFormat("/tpop createfrom [" + ARG_TPOP + "]"));
        }
    }

    private void processRename(Player player, String[] args) {
        if (args.length > 2) {
            String from = args[1];
            if (!PopCache.isPreset(from)) {
                TreePopulator pop = PopCache.getPopulator(from);
                if (pop != null) {
                    String to = args[2];
                    NameValidation.Status status = NameValidation.clean(to);
                    if (status == NameValidation.Status.VALID) {
                        pop.setName(to);
                        player.sendMessage(SUCCESS_TPOP_RENAME.coloredFromPop(to, ChatColor.GREEN));
                    } else {
                        player.sendMessage(ChatColor.RED + status.message);
                    }
                } else {
                    player.sendMessage(WARN_TPOP_NOT_FOUND.coloredFromPop(args[1], ChatColor.RED));
                }
            } else {
                player.sendMessage(ChatColor.RED + WARN_PRESET.fromPop(from));
            }
        } else {
            player.sendMessage(ChatColor.RED + SUGGEST_TPOP_HELP.toString());
        }
    }

    private void processRemove(Player player, String[] args) {
        if (args.length > 1) {
            String name = args[1];
            if (!PopCache.isPreset(name)) {
                TreePopulator pop = PopCache.getPopulator(name);
                if (pop != null) {
                    PopCache.removePopulator(pop);
                    for (Map.Entry<World, WorldData> entry : Generator.getWorldToData().entrySet()) {
                        WorldData worldData = entry.getValue();
                        if (pop == worldData.getTreePopulator()) {
                            worldData.setTreePopulator(entry.getKey(), null);
                        }
                    }
                    player.sendMessage(SUCCESS_TPOP_REMOVE.coloredFromPop(pop.getName(), ChatColor.GREEN));
                } else {
                    player.sendMessage(WARN_TPOP_NOT_FOUND.coloredFromPop(args[1], ChatColor.RED));
                }
            } else {
                player.sendMessage(ChatColor.RED + WARN_PRESET.fromPop(name));
            }

        } else {
            player.sendMessage(ChatColor.RED + SUGGEST_TPOP_HELP.toString());
        }
    }

    public static void processPopListRequest(Player player) {
        player.sendMessage(ChatColor.GOLD + "- - - " + LABEL_POP + " - - -");
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
        String popBase = ARG_TPOP + "_";
        int num = 1;
        String attempt = popBase + num;
        while (PopCache.hasPopulator(attempt)) {
            num++;
            attempt = popBase + num;
        }
        return attempt;
    }

}
