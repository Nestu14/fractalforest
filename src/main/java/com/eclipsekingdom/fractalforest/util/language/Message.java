package com.eclipsekingdom.fractalforest.util.language;

import org.bukkit.ChatColor;

public enum Message {

    LABEL_ENABLED("Label - enabled", "Enabled"),
    LABEL_DISABLED("Label - disabled", "Disabled"),

    ARG_WORLD("Arg - world", "world"),
    ARG_TPOP("Arg - tpop", "tpop"),
    ARG_OLD("Arg - old", "old"),
    ARG_NEW("Arg - new", "new"),

    CONSOLE_DETECT("Console - plugin detected", "%plugin% detected"),
    CONSOLE_FILE_ERROR("Console - file error", "Error saving %file%"),
    CONSOLE_WORLD_LOADED("Console - world loaded", "%world% loaded"),

    WARN_NO_PERMISSION("Warn - no permission", "You do not have permission for this command"),
    WARN_TPOP_NOT_FOUND("Warn - pop not found", "Populator %pop% not found"),
    WARN_TPOP_EXISTS("Console - pop exists", "A pop named %pop% already exists"),
    WARN_BUSY_TPOP("Warn - busy pop", "Player %player% is busy editing the requested populator"),
    WARN_BUSY_TGEN("Warn - busy worldgen", "Player %player% is busy editing the requested generator"),

    SUCCESS_TPOP_CREATE("Success - pop create", "Populator %pop% was created"),
    SUCCESS_TPOP_RENAME("Success - pop rename", "Populator renamed to %pop%"),
    SUCCESS_TPOP_REMOVE("Success - pop remove", "Populator %pop% was removed"),

    SUGGEST_TPOP_HELP("Suggest - pop help", "Invalid format. Use /tpop help for a list of commands."),

    FORMAT_EDIT_TPOP("Format - pop edit", "Format is /tpop edit [tpop]"),

    STATUS_VALID("Status - sucess", "success"),
    STATUS_SPECIAL_CHAR("Status - special characters", "Names must consist of only a-z, A-Z, 0-9, _, and -"),
    STATUS_TOO_LONG("Status - too long", "Names must be 20 characters or less"),
    STATUS_NOT_FOUND("Status - not found", "Not Found"),

    ;

    private MessageSetting messageSetting;

    Message(String messageSetting, String messageDefault) {
        this.messageSetting = new MessageSetting(messageSetting, messageDefault);
    }

    public MessageSetting getMessageSetting() {
        return messageSetting;
    }

    @Override
    public String toString() {
        return get();
    }

    private String get() {
        return ChatColor.translateAlternateColorCodes('&', messageSetting.getMessage());
    }

    public String getFromPlugin(String namespace) {
        return get().replaceAll("%plugin%", namespace);
    }

    private static ChatColor highlight = ChatColor.GRAY;

    public String getColoredFromPop(String namespace, ChatColor base) {
        return base + get().replaceAll("%pop%", highlight + namespace + base);
    }

    public String getFromPlayer(String playerName) {
        return get().replaceAll("%player%", playerName);
    }


    public String getFromFile(String fileName) {
        return get().replaceAll("%file%", fileName);
    }

    public String getFormatted() {
        String formatted = get().toUpperCase();
        if (formatted.length() > 1) {
            return formatted.charAt(0) + formatted.substring(1).toLowerCase();
        } else {
            return formatted;
        }
    }


}
