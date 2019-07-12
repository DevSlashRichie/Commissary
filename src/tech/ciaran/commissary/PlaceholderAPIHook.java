package tech.ciaran.commissary;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

/**
 * @author Ricardo R
 * @website socialvalidation.xyz
 * @version 1.0
 * @see me.clip.placeholderapi.expansion.PlaceholderExpansion
 *
 */
public class PlaceholderAPIHook extends PlaceholderExpansion {

    private Main plugin;

    public PlaceholderAPIHook(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean persist() {
        return  true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return StringUtils.join(plugin.getDescription().getAuthors(), ", ");
    }

    @Override
    public String getIdentifier() {
        return plugin.getDescription().getName();
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player p, String id) {
        if(plugin == null) {
            return "";
        }

        if(id.equals("tickets")) {
            return String.valueOf(Main.players.getInt(p.getUniqueId().toString() + ".tickets"));
        }

        if(id.equals("test")) {
            return "The hook is working!";
        }

        return null;
    }
}