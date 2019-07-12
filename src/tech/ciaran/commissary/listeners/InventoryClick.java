/*     */ package tech.ciaran.commissary.listeners;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import net.md_5.bungee.api.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import tech.ciaran.commissary.Main;
/*     */ 
/*     */ 
/*     */ public class InventoryClick
/*     */   implements Listener
/*     */ {
/*     */   Main plugin;
/*     */   
/*  21 */   public InventoryClick(Main plugin) { this.plugin = plugin; }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onInventoryClick(InventoryClickEvent e) {
/*  26 */     Player p = (Player)e.getWhoClicked();
/*  27 */     if (e.getView().getTitle().equalsIgnoreCase("Commissary")) {
/*  28 */       e.setCancelled(true);
/*  29 */       if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || !e.getCurrentItem().hasItemMeta()) {
/*     */         return;
/*     */       }
/*  32 */       switch (e.getCurrentItem().getType()) {
/*     */         case PAPER:
/*  34 */           if (e.getCurrentItem().hasItemMeta()) {
/*  35 */             ItemStack c1 = new ItemStack(Material.PAPER);
/*  36 */             ItemMeta c1Meta = c1.getItemMeta();
/*  37 */             c1Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  38 */                   .getConfig().getString("c1.displayname")));
/*  39 */             ArrayList<String> c1Lore = new ArrayList<String>();
/*  40 */             for (int i = 0; i < this.plugin.getConfig().getStringList("c1.lore").size(); i++) {
/*  41 */               c1Lore.add(ChatColor.translateAlternateColorCodes('&', (String)this.plugin
/*  42 */                     .getConfig().getStringList("c1.lore").get(i)));
/*     */             }
/*  44 */             c1Meta.setLore(c1Lore);
/*  45 */             c1.setItemMeta(c1Meta);
/*     */             
/*  47 */             ItemStack c2 = new ItemStack(Material.PAPER);
/*  48 */             ItemMeta c2Meta = c2.getItemMeta();
/*  49 */             c2Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  50 */                   .getConfig().getString("c2.displayname")));
/*  51 */             ArrayList<String> c2Lore = new ArrayList<String>();
/*  52 */             for (int i = 0; i < this.plugin.getConfig().getStringList("c2.lore").size(); i++) {
/*  53 */               c2Lore.add(ChatColor.translateAlternateColorCodes('&', (String)this.plugin
/*  54 */                     .getConfig().getStringList("c2.lore").get(i)));
/*     */             }
/*  56 */             c2Meta.setLore(c2Lore);
/*  57 */             c2.setItemMeta(c2Meta);
/*     */             
/*  59 */             ItemStack c3 = new ItemStack(Material.PAPER);
/*  60 */             ItemMeta c3Meta = c3.getItemMeta();
/*  61 */             c3Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  62 */                   .getConfig().getString("c3.displayname")));
/*  63 */             ArrayList<String> c3Lore = new ArrayList<String>();
/*  64 */             for (int i = 0; i < this.plugin.getConfig().getStringList("c3.lore").size(); i++) {
/*  65 */               c3Lore.add(ChatColor.translateAlternateColorCodes('&', (String)this.plugin
/*  66 */                     .getConfig().getStringList("c3.lore").get(i)));
/*     */             }
/*  68 */             c3Meta.setLore(c3Lore);
/*  69 */             c3.setItemMeta(c3Meta);
/*     */             
/*  71 */             if (e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  72 */                   .getConfig().getString("gui.c1.displayname")))) {
/*  73 */               if (Main.players.getInt(p.getUniqueId() + ".tickets") >= this.plugin.getConfig().getInt("c1.cost")) {
/*  74 */                 p.getInventory().addItem(new ItemStack[] { c1 });
/*  75 */                 Main.players.set(p.getUniqueId() + ".tickets", Integer.valueOf(Main.players.getInt(p.getUniqueId() + ".tickets") - this.plugin
/*  76 */                       .getConfig().getInt("c1.cost")));
/*  77 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  78 */                       .getConfig().getString("messages.purchased_ticket").replace("{ticket}", "C1")
/*  79 */                       .replace("{cost}", String.valueOf(this.plugin.getConfig().getInt("c1.cost")))));
/*  80 */                 saveFile();
/*     */               } else {
/*  82 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  83 */                       .getConfig().getString("messages.not_enough_tickets")));
/*     */                 break;
/*     */               } 
/*  86 */               p.closeInventory(); break;
/*     */             } 
/*  88 */             if (e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  89 */                   .getConfig().getString("gui.c2.displayname")))) {
/*  90 */               if (Main.players.getInt(p.getUniqueId() + ".tickets") >= this.plugin.getConfig().getInt("c2.cost")) {
/*  91 */                 p.getInventory().addItem(new ItemStack[] { c2 });
/*  92 */                 Main.players.set(p.getUniqueId() + ".tickets", Integer.valueOf(Main.players.getInt(p.getUniqueId() + ".tickets") - this.plugin
/*  93 */                       .getConfig().getInt("c2.cost")));
/*  94 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  95 */                       .getConfig().getString("messages.purchased_ticket").replace("{ticket}", "C2")
/*  96 */                       .replace("{cost}", String.valueOf(this.plugin.getConfig().getInt("c2.cost")))));
/*  97 */                 saveFile();
/*     */               } else {
/*  99 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 100 */                       .getConfig().getString("messages.not_enough_tickets")));
/*     */                 break;
/*     */               } 
/* 103 */               p.closeInventory(); break;
/*     */             } 
/* 105 */             if (e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 106 */                   .getConfig().getString("gui.c3.displayname")))) {
/* 107 */               if (Main.players.getInt(p.getUniqueId() + ".tickets") >= this.plugin.getConfig().getInt("c3.cost")) {
/* 108 */                 p.getInventory().addItem(new ItemStack[] { c3 });
/* 109 */                 Main.players.set(p.getUniqueId() + ".tickets", Integer.valueOf(Main.players.getInt(p.getUniqueId() + ".tickets") - this.plugin
/* 110 */                       .getConfig().getInt("c3.cost")));
/* 111 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 112 */                       .getConfig().getString("messages.purchased_ticket").replace("{ticket}", "C3")
/* 113 */                       .replace("{cost}", String.valueOf(this.plugin.getConfig().getInt("c3.cost")))));
/* 114 */                 saveFile();
/*     */               } else {
/* 116 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 117 */                       .getConfig().getString("messages.not_enough_tickets")));
/*     */                 break;
/*     */               } 
/* 120 */               p.closeInventory();
/*     */             } 
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveFile() {
/*     */     try {
/* 132 */       Main.players.save(this.plugin.playersFile);
/* 133 */     } catch (IOException e) {
/* 134 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\linco\Desktop\Commissary (6).jar!\tech\ciaran\commissary\listeners\InventoryClick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */