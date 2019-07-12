/*    */ package tech.ciaran.commissary.listeners;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ import tech.ciaran.commissary.Main;
/*    */ 
/*    */ 
/*    */ public class PlayerJoin
/*    */   implements Listener
/*    */ {
/*    */   Main plugin;
/*    */   
/* 16 */   public PlayerJoin(Main plugin) { this.plugin = plugin; }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerJoin(PlayerJoinEvent e) {
/* 21 */     Player p = e.getPlayer();
/* 22 */     if (!Main.players.contains(p.getUniqueId().toString())) {
/* 23 */       Main.players.set(p.getUniqueId() + ".tickets", Integer.valueOf(0));
/* 24 */       Main.players.set(p.getUniqueId() + ".timer", Integer.valueOf(0));
/* 25 */       Main.players.set(p.getUniqueId() + ".location.x", null);
/* 26 */       Main.players.set(p.getUniqueId() + ".location.y", null);
/* 27 */       Main.players.set(p.getUniqueId() + ".location.z", null);
/* 28 */       Main.players.set(p.getUniqueId() + ".location.yaw", null);
/* 29 */       Main.players.set(p.getUniqueId() + ".location.pitch", null);
/* 30 */       saveFile();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void saveFile() {
/*    */     try {
/* 36 */       Main.players.save(this.plugin.playersFile);
/* 37 */     } catch (IOException e) {
/* 38 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\linco\Desktop\Commissary (6).jar!\tech\ciaran\commissary\listeners\PlayerJoin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */