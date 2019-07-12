/*     */ package tech.ciaran.commissary.commands;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import tech.ciaran.commissary.Main;
/*     */ 
/*     */ 
/*     */ public class Commissary
/*     */   implements CommandExecutor
/*     */ {
/*     */   Main plugin;
/*     */   
/*  23 */   public Commissary(Main plugin) { this.plugin = plugin; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
/*  28 */     if (!(sender instanceof Player)) {
/*  29 */       sender.sendMessage("You must be a player to use that command!");
/*  30 */       return true;
/*     */     } 
/*     */     
/*  33 */     Player p = (Player)sender;
/*     */     
/*  35 */     if (!p.hasPermission("commissary.commissary")) {
/*  36 */       p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  37 */             .getConfig().getString("messages.no_permission")));
/*  38 */       return true;
/*     */     } 
/*     */     
/*  41 */     if (cmd.getName().equalsIgnoreCase("commissary")) {
/*  42 */       if (args.length == 0) {
/*  43 */         openInventory(p);
/*  44 */         return true;
/*  45 */       }  if (args.length == 2) {
/*  46 */         if (p.hasPermission("commissary.commissary.admin")) {
/*  47 */           if (args[0].equalsIgnoreCase("open")) {
/*  48 */             Player t = Bukkit.getPlayerExact(args[1]);
/*  49 */             if (t == null) {
/*  50 */               p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  51 */                     .getConfig().getString("messages.player_not_online")));
/*  52 */               return true;
/*     */             } 
/*  54 */             p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  55 */                   .getConfig().getString("messages.player_opened").replace("{player}", t.getName())));
/*  56 */             openInventory(t);
/*  57 */             return true;
/*     */           } 
/*  59 */           if (args[0].equalsIgnoreCase("set") && (args[1]
/*  60 */             .equalsIgnoreCase("c1") || args[1].equalsIgnoreCase("c2") || args[1]
/*  61 */             .equalsIgnoreCase("c3"))) {
/*  62 */             Location l = p.getLocation();
/*  63 */             this.plugin.getConfig().set(args[1] + ".world", l.getWorld().getName());
/*  64 */             this.plugin.getConfig().set(args[1] + ".x", Integer.valueOf(l.getBlockX()));
/*  65 */             this.plugin.getConfig().set(args[1] + ".y", Integer.valueOf(l.getBlockY()));
/*  66 */             this.plugin.getConfig().set(args[1] + ".z", Integer.valueOf(l.getBlockZ()));
/*  67 */             this.plugin.getConfig().set(args[1] + ".pitch", Float.valueOf(l.getPitch()));
/*  68 */             this.plugin.getConfig().set(args[1] + ".yaw", Float.valueOf(l.getYaw()));
/*  69 */             this.plugin.saveConfig();
/*  70 */             p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  71 */                   .getConfig().getString("messages.set_location")));
/*     */           } 
/*     */         } else {
/*     */           
/*  75 */           p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  76 */                 .getConfig().getString("messages.no_permission")));
/*  77 */           return true;
/*     */         } 
/*     */       } else {
/*  80 */         p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  81 */               .getConfig().getString("messages.invalid_args")));
/*  82 */         return true;
/*     */       } 
/*     */     } 
/*  85 */     return true;
/*     */   }
/*     */   
/*     */   public void openInventory(Player p) {
/*  89 */     Inventory i = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', this.plugin
/*  90 */           .getConfig().getString("gui.title")));
/*     */     
              ItemStack glass = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
/*  93 */     ItemMeta glassMeta = glass.getItemMeta();
/*  94 */     glassMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  95 */           .getConfig().getString("gui.fill_displayname")));
/*  96 */     glass.setItemMeta(glassMeta);
/*     */     
/*  98 */     ItemStack c1 = new ItemStack(Material.PAPER);
/*  99 */     ItemMeta c1Meta = c1.getItemMeta();
/* 100 */     c1Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("gui.c1.displayname")));
/* 101 */     ArrayList<String> c1Lore = new ArrayList<String>();
/* 102 */     for (int i2 = 0; i2 < this.plugin.getConfig().getStringList("gui.c1.lore").size(); i2++) {
/* 103 */       c1Lore.add(ChatColor.translateAlternateColorCodes('&', (String)this.plugin.getConfig().getStringList("gui.c1.lore").get(i2)));
/*     */     }
/* 105 */     c1Meta.setLore(c1Lore);
/* 106 */     c1.setItemMeta(c1Meta);
/*     */     
/* 108 */     ItemStack c2 = new ItemStack(Material.PAPER);
/* 109 */     ItemMeta c2Meta = c2.getItemMeta();
/* 110 */     c2Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("gui.c2.displayname")));
/* 111 */     ArrayList<String> c2Lore = new ArrayList<String>();
/* 112 */     for (int i2 = 0; i2 < this.plugin.getConfig().getStringList("gui.c2.lore").size(); i2++) {
/* 113 */       c2Lore.add(ChatColor.translateAlternateColorCodes('&', (String)this.plugin.getConfig().getStringList("gui.c2.lore").get(i2)));
/*     */     }
/* 115 */     c2Meta.setLore(c2Lore);
/* 116 */     c2.setItemMeta(c2Meta);
/*     */     
/* 118 */     ItemStack c3 = new ItemStack(Material.PAPER);
/* 119 */     ItemMeta c3Meta = c3.getItemMeta();
/* 120 */     c3Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("gui.c3.displayname")));
/* 121 */     ArrayList<String> c3Lore = new ArrayList<String>();
/* 122 */     for (int i2 = 0; i2 < this.plugin.getConfig().getStringList("gui.c3.lore").size(); i2++) {
/* 123 */       c3Lore.add(ChatColor.translateAlternateColorCodes('&', (String)this.plugin.getConfig().getStringList("gui.c3.lore").get(i2)));
/*     */     }
/* 125 */     c3Meta.setLore(c3Lore);
/* 126 */     c3.setItemMeta(c3Meta);
/*     */     
/* 128 */     if (this.plugin.getConfig().getBoolean("gui.fill") == true) {
/* 129 */       i.setItem(0, glass);
/* 130 */       i.setItem(9, glass);
/* 131 */       i.setItem(18, glass);
/* 132 */       i.setItem(8, glass);
/* 133 */       i.setItem(17, glass);
/* 134 */       i.setItem(26, glass);
/*     */     } 
/*     */     
/* 137 */     i.setItem(11, c1);
/* 138 */     i.setItem(13, c2);
/* 139 */     i.setItem(15, c3);
/*     */     
/* 141 */     p.openInventory(i);
/*     */   }
/*     */ }


/* Location:              C:\Users\linco\Desktop\Commissary (6).jar!\tech\ciaran\commissary\commands\Commissary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */