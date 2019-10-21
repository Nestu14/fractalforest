package com.eclipsekingdom.fractalforest.util.language;

public enum Message {

    ARG_WORLD("Arg - world", "world"),
    ARG_TPOP("Arg - tpop", "tpop"),
    ARG_OLD("Arg - old", "old"),
    ARG_NEW("Arg - new", "new"),

    CONSOLE_DETECT("Console - plugin detected", "%plugin% detected"),
    CONSOLE_FILE_ERROR("Console - file error", "§cError saving %file%"),
    CONSOLE_WORLD_UNLOADED("Console -  world unloaded", "§cUnable to load world for storm %storm%"),
    CONSOLE_WORLD_LOADED("Console - world loaded", "%world% loaded"),

    WARN_NO_PERMISSION("Warn - no permission", "§cYou do not have permission for this command"),
    WARN_NOT_INT_DEVIATION("Warn - not int", "§cStorm rate deviation must be an integer"),
    WARN_NOT_INT_MEAN("Warn - not int", "§cStorm rate mean must be an integer"),
    WARN_NOT_INT_RADIUS("Warn - not int", "§cStorm radius must be an integer"),
    WARN_TPOP_NOT_FOUND("Warn - populator not found", "§cPopulator §7%pop% §cnot found"),
    WARN_WORLD_NOT_FOUND("Warn - world not found", "§cWorld §7%world% §cnot found"),
    WARN_TPOP_EXISTS("Console - populator exists", "§cA populator named §7%pop% §calready exists"),

    SUCCESS_TPOP_CREATE("Success - populator create", "§dPopulator §7%pop% §dwas created"),
    SUCCESS_TPOP_RENAME("Success - populator rename", "§dPopulator renamed to §7%pop%"),
    SUCCESS_TPOP_REMOVE("Success - populator remove", "§dPopulator §7%pop% §dwas removed"),

    SUGGEST_TPOP_HELP("Suggest - populator help", "§cInvalid format. Use /tpop help for a list of commands."),

    FORMAT_METEOR("Format - meteor", "§cFormat is /meteor [world] [x] [z]"),
    FORMAT_EDIT_TPOP("Format - populator edit", "§cFormat is /tpop edit [tpop]"),

    STATUS_VALID("Status - sucess", "success"),
    STATUS_SPECIAL_CHAR("Status - special characters", "§cStorm names must consist of only a-z, A-Z, 0-9, _, and -"),
    STATUS_TOO_LONG("Status - too long", "§cStorm names must be 20 characters or less"),
    STATUS_TPOP_ENABLED("Status - tpop enabled", "§aEnabled"),
    STATUS_TPOP_DISABLED("Status - tpop disabled", "§cDisabled"),
    STATUS_NOT_FOUND("Status - not found", "Not Found"),

    CONSOLE_TPOP_ERROR("Console - populator error", "§Unable to load Console %pop%"),

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
        return messageSetting.getMessage();
    }

    public String getFromPop(String namespace) {
        return get().replaceAll("%pop%", namespace);
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
