/*     */ package tech.ciaran.commissary.commands;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.lang.math.NumberUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import tech.ciaran.commissary.Main;
/*     */ 
/*     */ 
/*     */ public class Tickets
/*     */   implements CommandExecutor
/*     */ {
/*     */   Main plugin;
/*     */   
/*  19 */   public Tickets(Main plugin) { this.plugin = plugin; }
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
/*  23 */     if (!(sender instanceof Player)) {
/*  24 */       if (args.length < 2) {
/*  25 */         sender.sendMessage("Invalid arguments.");
/*  26 */         return true;
/*  27 */       }  if (args.length == 2) {
/*  28 */         if (args[0].equalsIgnoreCase("reset")) {
/*  29 */           Player t = Bukkit.getPlayerExact(args[1]);
/*  30 */           if (t == null) {
/*  31 */             sender.sendMessage("The player " + args[1] + " is not online.");
/*  32 */             return true;
/*     */           } 
/*  34 */           Main.players.set(t.getUniqueId() + ".tickets", Integer.valueOf(0));
/*  35 */           sender.sendMessage("The player " + t.getName() + "'s tickets have been reset.");
/*  36 */           saveFile();
/*  37 */           return true;
/*     */         } 
/*     */         
/*  40 */         sender.sendMessage("Invalid arguments.");
/*  41 */         return true;
/*     */       } 
/*  43 */       if (args.length == 3) {
/*  44 */         if (args[0].equalsIgnoreCase("set")) {
/*  45 */           Player t = Bukkit.getPlayerExact(args[1]);
/*  46 */           if (t == null) {
/*  47 */             sender.sendMessage("The player " + args[1] + " is not online.");
/*  48 */             return true;
/*     */           } 
/*  50 */           if (NumberUtils.isNumber(args[2]) == true) {
/*  51 */             if (Integer.parseInt(args[2]) > 0) {
/*  52 */               Main.players.set(t.getUniqueId() + ".tickets", Integer.valueOf(Integer.parseInt(args[2])));
/*  53 */               sender.sendMessage("The player " + t.getName() + "'s tickets have been set to " + args[2] + ".");
/*  54 */               saveFile();
/*  55 */               return true;
/*     */             } 
/*  57 */             sender.sendMessage("Invalid arguments.");
/*  58 */             return true;
/*     */           } 
/*     */           
/*  61 */           sender.sendMessage("Invalid arguments.");
/*  62 */           return true;
/*     */         } 
/*     */         
/*  65 */         if (args[0].equalsIgnoreCase("give")) {
/*  66 */           Player t = Bukkit.getPlayerExact(args[1]);
/*  67 */           if (t == null) {
/*  68 */             sender.sendMessage("The player " + args[1] + " is not online.");
/*  69 */             return true;
/*     */           } 
/*  71 */           if (NumberUtils.isNumber(args[2]) == true) {
/*  72 */             if (Integer.parseInt(args[2]) > 0) {
/*  73 */               Main.players.set(t.getUniqueId() + ".tickets", Integer.valueOf(Main.players.getInt(t.getUniqueId() + ".tickets") + Integer.parseInt(args[2])));
/*  74 */               sender.sendMessage("The player " + t.getName() + "'s tickets have been increased by " + args[2] + ".");
/*  75 */               t.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/*  76 */                     .getConfig().getString("messages.tickets_recieved").replace("{tickets}", args[2])));
/*  77 */               saveFile();
/*  78 */               return true;
/*     */             } 
/*  80 */             sender.sendMessage("Invalid arguments.");
/*  81 */             return true;
/*     */           } 
/*     */           
/*  84 */           sender.sendMessage("Invalid arguments.");
/*  85 */           return true;
/*     */         } 
/*     */         
/*  88 */         if (args[0].equalsIgnoreCase("take")) {
/*  89 */           Player t = Bukkit.getPlayerExact(args[1]);
/*  90 */           if (t == null) {
/*  91 */             sender.sendMessage("The player " + args[1] + " is not online.");
/*  92 */             return true;
/*     */           } 
/*  94 */           if (NumberUtils.isNumber(args[2]) == true) {
/*  95 */             if (Integer.parseInt(args[2]) > 0) {
/*  96 */               Main.players.set(t.getUniqueId() + ".tickets", Integer.valueOf(Main.players.getInt(t.getUniqueId() + ".tickets") - Integer.parseInt(args[2])));
/*  97 */               sender.sendMessage("The player " + t.getName() + "'s tickets have been decreased by " + args[2] + ".");
/*  98 */               if (Main.players.getInt(t.getUniqueId() + ".tickets") > 0) {
/*  99 */                 Main.players.set(t.getUniqueId() + ".tickets", Integer.valueOf(0));
/* 100 */                 saveFile();
/* 101 */                 return true;
/*     */               } 
/* 103 */               return true;
/*     */             } 
/* 105 */             sender.sendMessage("Invalid arguments.");
/* 106 */             return true;
/*     */           } 
/*     */           
/* 109 */           sender.sendMessage("You specified invalid arguments.");
/* 110 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 116 */     Player p = (Player)sender;
/* 117 */     if (!p.hasPermission("commissary.tickets")) {
/* 118 */       p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 119 */             .getConfig().getString("messages.no_permission")));
/* 120 */       return true;
/*     */     } 
/* 122 */     if (cmd.getName().equalsIgnoreCase("tickets")) {
/* 123 */       if (args.length == 0) {
/* 124 */         p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 125 */               .getConfig().getString("messages.tickets_amount").replace("{tickets}", 
/* 126 */                 String.valueOf(Main.players.getInt(p.getUniqueId().toString() + ".tickets")))));
/* 127 */         return true;
/* 128 */       }  if (args.length == 1) {
/* 129 */         p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 130 */               .getConfig().getString("messages.invalid_args")));
/* 131 */         return true;
/* 132 */       }  if (args.length == 2) {
/* 133 */         if (args[0].equalsIgnoreCase("reset")) {
/* 134 */           if (p.hasPermission("commissary.tickets.admin")) {
/* 135 */             Player t = Bukkit.getPlayerExact(args[1]);
/* 136 */             if (t == null) {
/* 137 */               p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 138 */                     .getConfig().getString("messages.player_not_online")));
/* 139 */               return true;
/*     */             } 
/* 141 */             Main.players.set(t.getUniqueId() + ".tickets", Integer.valueOf(0));
/* 142 */             p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 143 */                   .getConfig().getString("messages.tickets_reset").replace("{player}", t.getName())));
/* 144 */             saveFile();
/* 145 */             return true;
/*     */           } 
/*     */           
/* 148 */           p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 149 */                 .getConfig().getString("messages.no_permission")));
/* 150 */           return true;
/*     */         } 
/*     */         
/* 153 */         p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 154 */               .getConfig().getString("messages.invalid_args")));
/* 155 */         return true;
/*     */       } 
/* 157 */       if (args.length == 3) {
/* 158 */         if (args[0].equalsIgnoreCase("set")) {
/* 159 */           if (p.hasPermission("commissary.tickets.admin")) {
/* 160 */             Player t = Bukkit.getPlayerExact(args[1]);
/* 161 */             if (t == null) {
/* 162 */               p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 163 */                     .getConfig().getString("messages.player_not_online")));
/* 164 */               return true;
/*     */             } 
/* 166 */             if (NumberUtils.isNumber(args[2]) == true) {
/* 167 */               if (Integer.parseInt(args[2]) > 0) {
/* 168 */                 Main.players.set(t.getUniqueId() + ".tickets", Integer.valueOf(Integer.parseInt(args[2])));
/* 169 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 170 */                       .getConfig().getString("messages.tickets_set").replace("{player}", t.getName())
/* 171 */                       .replace("{tickets}", args[2])));
/* 172 */                 saveFile();
/* 173 */                 return true;
/*     */               } 
/* 175 */               p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 176 */                     .getConfig().getString("messages.invalid_args")));
/* 177 */               return true;
/*     */             } 
/*     */             
/* 180 */             p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 181 */                   .getConfig().getString("messages.invalid_args")));
/* 182 */             return true;
/*     */           } 
/*     */ 
/*     */           
/* 186 */           p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 187 */                 .getConfig().getString("messages.no_permission")));
/* 188 */           return true;
/*     */         } 
/* 190 */         if (args[0].equalsIgnoreCase("give")) {
/* 191 */           if (p.hasPermission("commissary.tickets.admin")) {
/* 192 */             Player t = Bukkit.getPlayerExact(args[1]);
/* 193 */             if (t == null) {
/* 194 */               p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 195 */                     .getConfig().getString("messages.player_not_online")));
/* 196 */               return true;
/*     */             } 
/* 198 */             if (NumberUtils.isNumber(args[2]) == true) {
/* 199 */               if (Integer.parseInt(args[2]) > 0) {
/* 200 */                 Main.players.set(t.getUniqueId() + ".tickets", Integer.valueOf(Main.players.getInt(t.getUniqueId() + ".tickets") + Integer.parseInt(args[2])));
/* 201 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 202 */                       .getConfig().getString("messages.tickets_given").replace("{player}", t.getName())
/* 203 */                       .replace("{tickets}", args[2])));
/* 204 */                 saveFile();
/* 205 */                 return true;
/*     */               } 
/* 207 */               p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 208 */                     .getConfig().getString("messages.invalid_args")));
/* 209 */               return true;
/*     */             } 
/*     */             
/* 212 */             p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 213 */                   .getConfig().getString("messages.invalid_args")));
/* 214 */             return true;
/*     */           } 
/*     */ 
/*     */           
/* 218 */           p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 219 */                 .getConfig().getString("messages.no_permission")));
/* 220 */           return true;
/*     */         } 
/* 222 */         if (args[0].equalsIgnoreCase("take")) {
/* 223 */           if (p.hasPermission("commissary.tickets.admin")) {
/* 224 */             Player t = Bukkit.getPlayerExact(args[1]);
/* 225 */             if (t == null) {
/* 226 */               p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 227 */                     .getConfig().getString("messages.player_not_online")));
/* 228 */               return true;
/*     */             } 
/* 230 */             if (NumberUtils.isNumber(args[2]) == true) {
/* 231 */               if (Integer.parseInt(args[2]) > 0) {
/* 232 */                 Main.players.set(t.getUniqueId() + ".tickets", Integer.valueOf(Main.players.getInt(t.getUniqueId() + ".tickets") - Integer.parseInt(args[2])));
/* 233 */                 p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 234 */                       .getConfig().getString("messages.tickets_taken").replace("{player}", t.getName())
/* 235 */                       .replace("{tickets}", args[2])));
/* 236 */                 if (Main.players.getInt(t.getUniqueId() + ".tickets") > 0) {
/* 237 */                   Main.players.set(t.getUniqueId() + ".tickets", Integer.valueOf(0));
/* 238 */                   saveFile();
/* 239 */                   return true;
/*     */                 } 
/* 241 */                 return true;
/*     */               } 
/* 243 */               p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 244 */                     .getConfig().getString("messages.invalid_args")));
/* 245 */               return true;
/*     */             } 
/*     */             
/* 248 */             p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 249 */                   .getConfig().getString("messages.invalid_args")));
/* 250 */             return true;
/*     */           } 
/*     */ 
/*     */           
/* 254 */           p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 255 */                 .getConfig().getString("messages.invalid_args")));
/* 256 */           return true;
/*     */         } 
/*     */       } else {
/*     */         
/* 260 */         p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
/* 261 */               .getConfig().getString("messages.invalid_args")));
/* 262 */         return true;
/*     */       } 
/*     */     } 
/* 265 */     return true;
/*     */   }
/*     */   
/*     */   public void saveFile() {
/*     */     try {
/* 270 */       Main.players.save(this.plugin.playersFile);
/* 271 */     } catch (IOException e) {
/* 272 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\linco\Desktop\Commissary (6).jar!\tech\ciaran\commissary\commands\Tickets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */