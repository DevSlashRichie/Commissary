/*     */ package tech.ciaran.commissary.listeners;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import net.md_5.bungee.api.ChatColor;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Sign;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import tech.ciaran.commissary.Main;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SignClick
/*     */   implements Listener
/*     */ {
/*     */   Main plugin;
/*     */   
/*  28 */   public SignClick(Main plugin) { this.plugin = plugin; }
/*     */ 
/*     */   
/*  31 */   public static ArrayList<String> commissary = new ArrayList();
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerInteract(PlayerInteractEvent e) {
/*  36 */     ItemStack c1 = new ItemStack(Material.PAPER);
/*  37 */     ItemMeta c1Meta = c1.getItemMeta();
/*  38 */     c1Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  39 */           .getConfig().getString("c1.displayname")));
/*  40 */     ArrayList<String> c1Lore = new ArrayList<String>();
/*  41 */     for (int i = 0; i < this.plugin.getConfig().getStringList("c1.lore").size(); i++) {
/*  42 */       c1Lore.add(ChatColor.translateAlternateColorCodes('&', (String)this.plugin
/*  43 */             .getConfig().getStringList("c1.lore").get(i)));
/*     */     }
/*  45 */     c1Meta.setLore(c1Lore);
/*  46 */     c1.setItemMeta(c1Meta);
/*     */     
/*  48 */     ItemStack c2 = new ItemStack(Material.PAPER);
/*  49 */     ItemMeta c2Meta = c2.getItemMeta();
/*  50 */     c2Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  51 */           .getConfig().getString("c2.displayname")));
/*  52 */     ArrayList<String> c2Lore = new ArrayList<String>();
/*  53 */     for (int i = 0; i < this.plugin.getConfig().getStringList("c2.lore").size(); i++) {
/*  54 */       c2Lore.add(ChatColor.translateAlternateColorCodes('&', (String)this.plugin
/*  55 */             .getConfig().getStringList("c2.lore").get(i)));
/*     */     }
/*  57 */     c2Meta.setLore(c2Lore);
/*  58 */     c2.setItemMeta(c2Meta);
/*     */     
/*  60 */     ItemStack c3 = new ItemStack(Material.PAPER);
/*  61 */     ItemMeta c3Meta = c3.getItemMeta();
/*  62 */     c3Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  63 */           .getConfig().getString("c3.displayname")));
/*  64 */     ArrayList<String> c3Lore = new ArrayList<String>();
/*  65 */     for (int i = 0; i < this.plugin.getConfig().getStringList("c3.lore").size(); i++) {
/*  66 */       c3Lore.add(ChatColor.translateAlternateColorCodes('&', (String)this.plugin
/*  67 */             .getConfig().getStringList("c3.lore").get(i)));
/*     */     }
/*  69 */     c3Meta.setLore(c3Lore);
/*  70 */     c3.setItemMeta(c3Meta);
/*     */     
/*  72 */     ItemStack air = new ItemStack(Material.AIR);
/*     */     
/*  74 */     Player p = e.getPlayer();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     if (e.getClickedBlock().getState() instanceof Sign) {
/*  80 */       Sign sign = (Sign)e.getClickedBlock().getState();
/*     */       
/*  82 */       if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) && e.getClickedBlock().getType() == Material.OAK_WALL_SIGN && sign.getLine(0).contains(ChatColor.DARK_PURPLE + "[Commissary]"))
/*     */       {
/*  84 */         if (e.getItem() == null) {
/*     */           
/*  86 */           p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  87 */                 .getConfig().getString("messages.not_commissary_ticket")));
/*     */         }
/*  89 */         else if (e.getItem().equals(c1)) {
/*     */           
/*  91 */           if (sign.getLine(1).contains("C1"))
/*     */           {
/*  93 */             p.getInventory().setItemInHand(air);
/*     */             
/*  95 */             p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  96 */                   .getConfig().getString("messages.entered_commissary").replace("{commissary}", "1")
/*  97 */                   .replace("{time}", String.valueOf(this.plugin.getConfig().getInt("c1.time")))));
/*     */             
/*  99 */             saveLocation(p);
/*     */             
/* 101 */             teleportLocation(p, "c1");
/*     */             
/* 103 */             commissary.add(p.getName());
/*     */             
/* 105 */             timer(p, this.plugin.getConfig().getInt("c1.time"));
/*     */           }
/*     */           else
/*     */           {
/* 109 */             p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 110 */                   .getConfig().getString("messages.not_correct_commissary_ticket")));
/*     */           }
/*     */         
/*     */         }
/* 114 */         else if (e.getItem().equals(c2)) {
/*     */           
/* 116 */           if (sign.getLine(1).contains("C2"))
/*     */           {
/* 118 */             p.getInventory().setItemInHand(air);
/*     */             
/* 120 */             p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 121 */                   .getConfig().getString("messages.entered_commissary").replace("{commissary}", "2")
/* 122 */                   .replace("{time}", String.valueOf(this.plugin.getConfig().getInt("c2.time")))));
/*     */             
/* 124 */             saveLocation(p);
/*     */             
/* 126 */             teleportLocation(p, "c2");
/*     */             
/* 128 */             commissary.add(p.getName());
/*     */             
/* 130 */             timer(p, this.plugin.getConfig().getInt("c2.time"));
/*     */           }
/*     */           else
/*     */           {
/* 134 */             p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 135 */                   .getConfig().getString("messages.not_correct_commissary_ticket")));
/*     */           }
/*     */         
/*     */         }
/* 139 */         else if (e.getItem().equals(c3)) {
/*     */           
/* 141 */           if (sign.getLine(1).contains("C3"))
/*     */           {
/* 143 */             p.getInventory().setItemInHand(air);
/*     */             
/* 145 */             p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 146 */                   .getConfig().getString("messages.entered_commissary").replace("{commissary}", "2")
/* 147 */                   .replace("{time}", String.valueOf(this.plugin.getConfig().getInt("c1.time")))));
/*     */             
/* 149 */             saveLocation(p);
/*     */             
/* 151 */             teleportLocation(p, "c3");
/*     */             
/* 153 */             commissary.add(p.getName());
/*     */             
/* 155 */             timer(p, this.plugin.getConfig().getInt("c3.time"));
/*     */           }
/*     */           else
/*     */           {
/* 159 */             p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 160 */                   .getConfig().getString("messages.not_correct_commissary_ticket")));
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 166 */           p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 167 */                 .getConfig().getString("messages.not_commissary_ticket")));
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerJoin(PlayerJoinEvent e) {
/* 179 */     Player p = e.getPlayer();
/* 180 */     if (commissary.contains(p.getName())) {
/* 181 */       teleportPlayerLocation(p);
/* 182 */       p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 183 */             .getConfig().getString("messages.time_up")));
/* 184 */       commissary.remove(p.getName());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void timer(final Player p, final int length) {
/* 189 */     (new BukkitRunnable() {
/* 190 */         Integer time = Integer.valueOf(length);
/*     */         public void run() {
/* 192 */           Integer integer1, integer2 = this.time = (integer1 = this.time).valueOf(this.time.intValue() - 1);
/* 193 */           if (this.time.intValue() <= 0) {
/* 194 */             if (SignClick.commissary.contains(p.getName())) {
/* 195 */               SignClick.this.teleportPlayerLocation(p);
/* 196 */               p.sendMessage(ChatColor.translateAlternateColorCodes('&', SignClick.this.plugin
/* 197 */                     .getConfig().getString("messages.time_up")));
/* 198 */               SignClick.commissary.remove(p.getName());
/* 199 */               cancel();
/*     */             } else {
/* 201 */               cancel();
/*     */             } 
/*     */           }
/*     */         }
/* 205 */       }).runTaskTimer(this.plugin, 0L, 20L);
/*     */   }
/*     */   
/*     */   public void saveLocation(Player p) {
/* 209 */     Location l = p.getLocation();
/* 210 */     Main.players.set(p.getUniqueId() + ".location.world", l.getWorld().getName());
/* 211 */     Main.players.set(p.getUniqueId() + ".location.x", Double.valueOf(l.getX()));
/* 212 */     Main.players.set(p.getUniqueId() + ".location.y", Double.valueOf(l.getY()));
/* 213 */     Main.players.set(p.getUniqueId() + ".location.z", Double.valueOf(l.getZ()));
/* 214 */     Main.players.set(p.getUniqueId() + ".location.yaw", Float.valueOf(l.getYaw()));
/* 215 */     Main.players.set(p.getUniqueId() + ".location.pitch", Float.valueOf(l.getPitch()));
/*     */     try {
/* 217 */       Main.players.save(this.plugin.playersFile);
/* 218 */     } catch (IOException e) {
/* 219 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void teleportLocation(Player p, String commissary) {
/* 224 */     Location l = new Location(Bukkit.getWorld(this.plugin.getConfig().getString(commissary + ".world")), this.plugin.getConfig().getDouble(commissary + ".x"), this.plugin.getConfig().getDouble(commissary + ".y"), this.plugin.getConfig().getDouble(commissary + ".z"), this.plugin.getConfig().getInt(commissary + ".yaw"), this.plugin.getConfig().getInt(commissary + ".pitch"));
/* 225 */     p.teleport(l);
/*     */   }
/*     */   
/*     */   public void teleportPlayerLocation(Player p) {
/* 229 */     Location l = new Location(Bukkit.getWorld(Main.players.getString(p.getUniqueId() + ".location.world")), Main.players.getDouble(p.getUniqueId() + ".location.x"), Main.players.getDouble(p.getUniqueId() + ".location.y"), Main.players.getDouble(p.getUniqueId() + ".location.z"), Main.players.getInt(p.getUniqueId() + ".location.yaw"), Main.players.getInt(p.getUniqueId() + ".location.pitch"));
/* 230 */     p.teleport(l);
/*     */   }
/*     */   
/*     */   public void saveFile() {
/*     */     try {
/* 235 */       Main.players.save(this.plugin.playersFile);
/* 236 */     } catch (IOException e) {
/* 237 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\linco\Desktop\Commissary (6).jar!\tech\ciaran\commissary\listeners\SignClick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */