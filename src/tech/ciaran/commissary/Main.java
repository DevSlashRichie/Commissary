/*    */ package tech.ciaran.commissary;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import com.mysql.jdbc.Buffer;
import org.bukkit.Bukkit;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ import tech.ciaran.commissary.commands.Commissary;
/*    */ import tech.ciaran.commissary.commands.Tickets;
/*    */ import tech.ciaran.commissary.listeners.InventoryClick;
/*    */ import tech.ciaran.commissary.listeners.PlayerJoin;
/*    */ import tech.ciaran.commissary.listeners.SignClick;
/*    */ import tech.ciaran.commissary.utils.Placeholder;
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

            if(Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                new Placeholder(this).register();
            }
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
/*    */   }
/*    */ 
/*    */   
/* 54 */   public void onDisable() { Bukkit.getLogger().info("[Commissary] Disabled Commissary v" + getDescription().getVersion()); }
/*    */ }


/* Location:              C:\Users\linco\Desktop\Commissary (6).jar!\tech\ciaran\commissary\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */