/*    */ package tech.ciaran.commissary;
/*    */ 
/*    */

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import me.clip.ezblocks.EZBlocks;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import tech.ciaran.commissary.commands.Commissary;
import tech.ciaran.commissary.commands.Tickets;
import tech.ciaran.commissary.listeners.InventoryClick;
import tech.ciaran.commissary.listeners.PlayerJoin;
import tech.ciaran.commissary.listeners.SignClick;

import java.io.File;
import java.io.IOException;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ 
/*    */ 
/*    */ public class Main
/*    */   extends JavaPlugin
/*    */ {
/*    */   public static FileConfiguration players;
/*    */   public File playersFile;
/*    */   
/*    */   public void onEnable() {
/* 24 */     Bukkit.getLogger().info("[Commissary] Enabled Commissary v" + getDescription().getVersion());
/* 25 */     Bukkit.getLogger().info("[Commissary] This plugin was developed by Ciaran Baker. Do not use/distribute without permission.");
/*    */ 
/*    */     
/* 28 */     getConfig().options().copyDefaults(true);
/* 29 */     saveConfig();
/*    */     
/* 35 */     getCommand("commissary").setExecutor(new Commissary(this));
/* 36 */     getCommand("tickets").setExecutor(new Tickets(this));
/*    */     
/* 38 */     Bukkit.getServer().getPluginManager().registerEvents(new InventoryClick(this), this);
/* 39 */     Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
/* 40 */     Bukkit.getServer().getPluginManager().registerEvents(new SignClick(this), this);
/*    */     
/* 42 */     this.playersFile = new File(getDataFolder(), "players.yml");
/* 43 */     if (!this.playersFile.exists()) {
/*    */       try {
/* 45 */         this.playersFile.createNewFile();
/* 46 */       } catch (IOException exception) {
/* 47 */         exception.printStackTrace();
/*    */       } 
/*    */     }
/* 50 */     players = YamlConfiguration.loadConfiguration(this.playersFile);

    if(Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
        new PlaceholderAPIHook(this).register();
    }

    if(Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
        PlaceholderAPI.registerPlaceholder(this, "commissary_tickets", placeholderReplaceEvent -> {
            if(placeholderReplaceEvent.isOnline()) {
                return String.valueOf(Main.players.getInt(placeholderReplaceEvent.getPlayer().getUniqueId().toString() + ".tickets"));
            }
            return "-1";
        });
    }

/*    */   }
/*    */ 
/*    */   
/* 54 */   public void onDisable() { Bukkit.getLogger().info("[Commissary] Disabled Commissary v" + getDescription().getVersion()); }
/*    */ }


/* Location:              C:\Users\linco\Desktop\Commissary (6).jar!\tech\ciaran\commissary\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */