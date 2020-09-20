package com.eclipsekingdom.fractalforest.sys.language;

import org.bukkit.ChatColor;

public enum Message {

    LABEL_ENABLED("Label - enabled", "Enabled"),
    LABEL_DISABLED("Label - disabled", "Disabled"),
    LABEL_PRESET("Label - preset", "Preset"),
    LABEL_POP("Label - pop", "Tree Populators"),
    LABEL_GENERAL_COMMANDS("Label - general commands", "General Commands"),
    LABEL_POP_COMMANDS("Label - pop commands", "Populator Commands"),
    LABEL_SAPLING("Label - sapling", "Sapling"),
    LABEL_SAPLINGS("Label - saplings", "Saplings"),
    LABEL_SPECIES("Label - species", "Species"),
    LABEL_OVERVIEW("Label - overview", "Overview"),
    LABEL_BIOMES("Label - biomes", "Biomes"),
    LABEL_BIOME_SELECTION("Label - biome selection", "Biome Selection"),
    LABEL_TREES("Label - trees", "Trees"),
    LABEL_TREE_SELECTION("Label - tree selection", "Tree Selection"),
    LABEL_SPAWNER("Label - spawner", "Spawner"),
    LABEL_SPAWN_CHANCE("Label - spawn chance", "Spawn Chance"),
    LABEL_SPAWN_MIN("Label - spawn min", "Spawn Amount Min"),
    LABEL_SPAWN_MAX("Label - spawn max", "Spawn Amount Max"),
    LABEL_OVERFLOW_RAD("Label - overflow rad", "Overflow Radius"),
    LABEL_SELECTION("Label - selection", "Selection"),
    LABEL_POP_SELECTION("Label - pop selection", "Populator Selection"),
    LABEL_GENOME("Label - genome", "Tree Genome"),
    LABEL_GENERATOR("Label - generator", "Tree Generator"),
    LABEL_NO_PAGE("Label - no page", "Page Not Found"),
    LABEL_CHANCE("Label - chance", "Chance"),
    LABEL_MIN("Label - min", "Min"),
    LABEL_MAX("Label - max", "Max"),
    LABEL_OVERFLOW("Label - overflow", "Overflow"),
    LABEL_EDIT("Label - edit", "Edit"),
    LABEL_NONE("Label - none", "None"),

    UNIT_BLOCKS("Unit - blocks", "blocks"),
    UNIT_TREES("Unit - trees", "trees"),

    TEXT_OPEN_SAPLING("Text - open sapling", "select saplings"),
    TEXT_GET_SAPLINGS("Text - get saplings", "get saplings"),
    TEXT_GIFT_SAPLINGS("Text - gift saplings", "give saplings to player"),
    TEXT_TENCYCLOPEDIA("Text - tencyclopedia", "request an encyclopedia"),
    TEXT_UPDATE_TRECORDS("Text - updatetrecords", "update the records in inventory"),
    TEXT_TGENERATOR("Text - tgenerator", "edit the tree generator"),
    TEXT_POP_LIST("Text - pop list", "list all populators"),
    TEXT_POP_HELP("Text - pop help", "display populator commands"),
    TEXT_POP_CREATE("Text - pop create", "create populator"),
    TEXT_POP_CREATEFROM("Text - createfrom", "create populator from template"),
    TEXT_POP_EDIT("Text - edit", "edit populator"),
    TEXT_POP_DELETE("Text - delete", "delete populator"),
    TEXT_POP_RENAME("Text - rename", "rename populator"),

    ARG_WORLD("Arg - world", "world"),
    ARG_TPOP("Arg - tpop", "populator"),
    ARG_PLAYER("Arg - player", "player"),
    ARG_AMOUNT("Arg - amount", "amount"),
    ARG_SPECIES("Arg - species", "species"),
    ARG_OLD("Arg - old", "old"),
    ARG_NEW("Arg - new", "new"),

    CONSOLE_DETECT("Console - plugin detected", "%plugin% detected"),
    CONSOLE_FILE_ERROR("Console - file error", "Error saving %file%"),
    CONSOLE_WORLD_LOADED("Console - world loaded", "%world% loaded"),

    WARN_NO_PERMISSION("Warn - no permission", "You do not have permission for this command"),
    WARN_SAPLING_NOT_PERMITTED("Warn - sapling not permitted", "You do not have permission to plant a %species% sapling"),
    WARN_TPOP_NOT_FOUND("Warn - pop not found", "Populator %pop% not found"),
    WARN_TPOP_EXISTS("Console - pop exists", "A pop named %pop% already exists"),
    WARN_BUSY_TPOP("Warn - busy pop", "Player %player% is busy editing the requested populator"),
    WARN_BUSY_TGEN("Warn - busy tgen", "Player %player% is busy editing the requested generator"),
    WARN_PRESET("Warn - preset", "%pop% is a preset tree populator."),
    WARN_UNKNOWN_SPECIES("Warn - unknown species", "Unrecognized species"),
    WARN_NOT_ONLINE("Warn - not online", "Player %player% is not online"),

    SUCCESS_GEN_UPDATE("Success - gen update", "generator updated"),
    SUCCESS_TPOP_UPDATE("Success - pop update", "populator updated"),
    SUCCESS_TPOP_CREATE("Success - pop create", "populator %pop% was created"),
    SUCCESS_TPOP_RENAME("Success - pop rename", "populator renamed to %pop%"),
    SUCCESS_TPOP_REMOVE("Success - pop remove", "populator %pop% was removed"),
    SUCCESS_GIFT_SAPLING("Success - gift sapling", "%amount% %species% saplings gifted to %player%"),

    SUGGEST_TPOP_HELP("Suggest - pop help", "Invalid format. Use /tpop help for a list of commands."),

    INFO_FORMAT("Format - format", "Format is %format%"),

    ICON_CLOSE("Icon - close", "Close"),
    ICON_BACK("Icon - back", "Back"),

    MENU_MIN_TREE_NUMBER("Menu - min tree number", "Min Tree Number"),
    MENU_MAX_TREE_NUMBER("Menu - max tree number", "Max Tree Number"),
    MENU_SCROLL_UP("Menu - scroll up", "Scroll Up"),
    MENU_SCROLL_DOWN("Menu - scroll down", "Scroll Down"),
    MENU_SCROLL_LEFT("Menu - scroll left", "Scroll Left"),
    MENU_SCROLL_RIGHT("Menu - scroll right", "Scroll Right"),
    MENU_EDIT_BIOMES("Menu - edit biomes", "Edit Biomes"),
    MENU_CHANCE_PER_CHUNK("Menu - chance per chunk", "Chance per Chunk"),
    MENU_OVERFLOW_RADIUS("Menu - overflow radius", "Overflow Radius"),
    MENU_TREE_SPAWNER("Menu - tree spawner", "Tree Spawner"),
    MENU_EDIT_TREE_SPAWNERS("Menu - edit spawners", "Edit Tree Spawners"),
    MENU_GENERATOR("Menu - generator", "Generator"),

    STATUS_VALID("Status - success", "success"),
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

    public String fromPlugin(String namespace) {
        return get().replaceAll("%plugin%", namespace);
    }

    private static ChatColor highlight = ChatColor.GRAY;

    public String coloredFromPop(String namespace, ChatColor base) {
        return base + get().replaceAll("%pop%", highlight + namespace + base);
    }

    public String fromPlayer(String playerName) {
        return get().replaceAll("%player%", playerName);
    }

    public String fromPop(String pop) {
        return get().replaceAll("%pop%", pop);
    }

    public String fromFormat(String format) {
        return get().replaceAll("%format%", format);
    }

    public String fromFile(String fileName) {
        return get().replaceAll("%file%", fileName);
    }

    public String fromSpecies(String species) {
        return get().replaceAll("%species%", species);
    }

    public String fromAmountSpeciesPlayer(int amount, String species, String player) {
        return get().replaceAll("%amount%", String.valueOf(amount)).replaceAll("%species%", species).replaceAll("%player%", player);
    }

}
