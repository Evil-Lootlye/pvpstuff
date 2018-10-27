package pvpoverhaulmain;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Spider;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

public class pvpmain extends JavaPlugin implements Listener, CommandExecutor {

	public ConsoleCommandSender console = getServer().getConsoleSender();
	Random randor = new Random();
	public World wor = null;
	public List<Arrow> arrows1 = new ArrayList<Arrow>();
	FileConfiguration config = getConfig();
	public HashMap<String, Float> velocityPower = new HashMap<String, Float>();
	public HashMap<String, Float> attackPower = new HashMap<String, Float>();
	public HashMap<String, Float> strengthPower = new HashMap<String, Float>();
	public HashMap<String, Float> magicPower = new HashMap<String, Float>();
	public HashMap<String, Float> resisPower = new HashMap<String, Float>();
	public HashMap<String, Float> reachPower = new HashMap<String, Float>();
	public static HashMap<String, Float> scopePower = new HashMap<String, Float>();
	HashMap<String,PlayerCountDown> jumpcd = new HashMap<String,PlayerCountDown>();
	public List<String> powersOn = new ArrayList<String>();
	List<Entity> beserks = new ArrayList<Entity>();
	List<Entity> effectEnts = new ArrayList<Entity>();
	HashMap<Player, Double> deHealth = new HashMap<Player, Double>();
	public Boolean runningDemon = false;
	public List<Material> flameblocks = new ArrayList<Material>();

	@Override
	public void onEnable() {
		createConfigFol();
		this.getServer().getPluginManager().registerEvents(this, this);
		BukkitScheduler scheduler = getServer().getScheduler();
		loadConfig();
		for(Player p : Bukkit.getOnlinePlayers()) {
			wor = p.getWorld();
			break;
		}
		for(Player p : Bukkit.getOnlinePlayers()) {
			PlayerCountDown cd = new PlayerCountDown(p.getName());
			jumpcd.put(p.getName(), cd);
			if(!magicPower.containsKey(p.getName())) {
				magicPower.put(p.getName(), (float) .00001);
			}
			if(!velocityPower.containsKey(p.getName())) {
				velocityPower.put(p.getName(), (float) .00001);
			}
			if(!attackPower.containsKey(p.getName())) {
				attackPower.put(p.getName(), (float) .00001);
			}
			if(!strengthPower.containsKey(p.getName())) {
				strengthPower.put(p.getName(), (float) .00001);
			}
			if(!reachPower.containsKey(p.getName())) {
				reachPower.put(p.getName(), (float) .00001);
			}
			if(!scopePower.containsKey(p.getName())) {
				scopePower.put(p.getName(), (float) .00001);
			}
			if(!resisPower.containsKey(p.getName())) {
				resisPower.put(p.getName(), (float) .00001);
			}
		}
		//
		flameblocks.add(Material.WOOD);
		flameblocks.add(Material.LEAVES);
		flameblocks.add(Material.LEAVES_2);
		flameblocks.add(Material.LOG);
		flameblocks.add(Material.LOG_2);
		flameblocks.add(Material.GRASS);
		flameblocks.add(Material.WOOL);
		flameblocks.add(Material.BOOKSHELF);
		flameblocks.add(Material.WORKBENCH);
		flameblocks.add(Material.CHEST);
		//
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				deleteArrows();
			}
		}, 0L, 24000L);
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				doArrowEffects();
			}
		}, 0L, 1L);
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				saveConfigs();
			}
		}, 0L, 15000L);
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				if(config.getBoolean("Enable Dynamic Difficulty")) {
				betterEffectLooper();
				}
			}
		}, 0L, /* 600 */6L);
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				if(config.getBoolean("Enable Dynamic Difficulty")) {
				doSpooks();
				entityEffects.convertEffect(null, null);
				
				}
			}
		}, 0L, /* 600 */100L);
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				if(config.getBoolean("Enable Dynamic Difficulty")) {
				entityEffects.inferno(null, null);
				}
			}
		}, 0L, /* 600 */18L);
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				if(config.getBoolean("Enable Dynamic Difficulty")) {
				entityEffects.shadow(null, null);
				}
			}
		}, 0L, /* 600 */4L);
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				if(config.getBoolean("Enable Dynamic Difficulty")) {
				doBeserkSounds();
				entityEffects.watcher(null, null);
				entityEffects.stalkerE();
				}
			}
		}, 0L, /* 600 */2L);
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				if(config.getBoolean("Enable Dynamic Difficulty")) {
				entityEffects.quicksand(null, null);
				entityEffects.confusing(null, null);
				entityEffects.withering(null, null);
				entityEffects.molten(null, null);
				entityEffects.volley(null, null);
				entityEffects.poisonous(null, null);
				}
			}
		}, 0L, /* 600 */20L);
	}

	@Override
	public void onDisable() {

	}

	public void createConfigFol() {
		config.addDefault("Enable Dynamic Difficulty", false);
		config.addDefault("::::::::::::::::::::::::::::::::::::::::::::::::", "");
		config.addDefault("Velocity Power", new ArrayList<String>());
		config.addDefault("Attack Power", new ArrayList<String>());
		config.addDefault("Strength Power", new ArrayList<String>());
		config.addDefault("Magic Power", new ArrayList<String>());
		config.addDefault("Resis Power", new ArrayList<String>());
		config.addDefault("Reach Power", new ArrayList<String>());
		config.options().copyDefaults(true);
		saveConfig();
	}
	
	public void deleteArrows() {
		if(wor!=null) {
			for(Entity e : wor.getEntities()) {
				if(e instanceof Arrow) {
					e.remove();
				}
			}
		}
	}
	
	public void loadConfig() {
		List<String> vel = config.getStringList("Velocity Power");
		List<String> attack = config.getStringList("Attack Power");
		List<String> stren = config.getStringList("Strength Power");
		List<String> magic = config.getStringList("Magic Power");
		List<String> resis = config.getStringList("Resis Power");
		List<String> reach = config.getStringList("Reach Power");
		List<String> scope = config.getStringList("Scope Power");
		for(String s : vel) {
			try {
			String name = s.substring(0, s.lastIndexOf("^"));
			float pow = Float.parseFloat(s.substring(s.lastIndexOf("^")+1, s.length()));
			velocityPower.put(name, pow);
			}
			catch(Exception e) {
				
			}
		}
		for(String s : attack) {
			try {
			String name = s.substring(0, s.lastIndexOf("^"));
			float pow = Float.parseFloat(s.substring(s.lastIndexOf("^")+1, s.length()));
			attackPower.put(name, pow);
			}
			catch(Exception e) {
				
			}
		}
		for(String s : stren) {
			try {
			String name = s.substring(0, s.lastIndexOf("^"));
			float pow = Float.parseFloat(s.substring(s.lastIndexOf("^")+1, s.length()));
			strengthPower.put(name, pow);
			}
			catch(Exception e) {
				
			}
		}
		for(String s : magic) {
			try {
			String name = s.substring(0, s.lastIndexOf("^"));
			float pow = Float.parseFloat(s.substring(s.lastIndexOf("^")+1, s.length()));
			magicPower.put(name, pow);
			}
			catch(Exception e) {
				
			}
		}
		for(String s : resis) {
			try {
			String name = s.substring(0, s.lastIndexOf("^"));
			float pow = Float.parseFloat(s.substring(s.lastIndexOf("^")+1, s.length()));
			resisPower.put(name, pow);
			}
			catch(Exception e) {
				
			}
		}
		for(String s : reach) {
			try {
			String name = s.substring(0, s.lastIndexOf("^"));
			float pow = Float.parseFloat(s.substring(s.lastIndexOf("^")+1, s.length()));
			reachPower.put(name, pow);
			}
			catch(Exception e) {
				
			}
		}
		for(String s : scope) {
			try {
			String name = s.substring(0, s.lastIndexOf("^"));
			float pow = Float.parseFloat(s.substring(s.lastIndexOf("^")+1, s.length()));
			scopePower.put(name, pow);
			}
			catch(Exception e) {
				
			}
		}
	}
	
	public void saveConfigs() {
		List<String> vel = new ArrayList<String>();
		List<String> attack = new ArrayList<String>();
		List<String> stren = new ArrayList<String>();
		List<String> magic = new ArrayList<String>();
		List<String> resis = new ArrayList<String>();
		List<String> reach = new ArrayList<String>();
		List<String> scope = new ArrayList<String>();
		if(!velocityPower.isEmpty()) {
		for(String name : velocityPower.keySet()) {
			String newValue = name + "^" + velocityPower.get(name);
			vel.add(newValue);
		}
		}
		if(!attackPower.isEmpty()) {
		for(String name : attackPower.keySet()) {
			String newValue = name + "^" + attackPower.get(name);
			attack.add(newValue);
		}
		}
		if(!strengthPower.isEmpty()) {
		for(String name : strengthPower.keySet()) {
			String newValue = name + "^" + strengthPower.get(name);
			stren.add(newValue);
		}
		}
		if(!magicPower.isEmpty()) {
		for(String name : magicPower.keySet()) {
			String newValue = name + "^" + magicPower.get(name);
			magic.add(newValue);
		}
		}
		if(!resisPower.isEmpty()) {
		for(String name : resisPower.keySet()) {
			String newValue = name + "^" + resisPower.get(name);
			resis.add(newValue);
		}
		}
		if(!reachPower.isEmpty()) {
			for(String name : reachPower.keySet()) {
				String newValue = name + "^" + reachPower.get(name);
				reach.add(newValue);
		}
		}
		if(!scopePower.isEmpty()) {
			for(String name : scopePower.keySet()) {
				String newValue = name + "^" + scopePower.get(name);
				scope.add(newValue);
		}
		}
		if(config.contains("Velocity Power")) {
		config.set("Velocity Power", vel);
		}
		if(config.contains("Attack Power")) {
		config.set("Attack Power", attack);
		}
		if(config.contains("Strength Power")) {
		config.set("Strength Power", stren);
		}
		if(config.contains("Magic Power")) {
		config.set("Magic Power", magic);
		}
		if(config.contains("Resis Power")) {
		config.set("Resis Power", resis);
		}
		if(config.contains("Reach Power")) {
			config.set("Reach Power", reach);
		}
		if(config.contains("Scope Power")) {
			config.set("Scope Power", scope);
		}
		saveConfig();
	}
	
	public void doArrowEffects() {
		if(!arrows1.isEmpty()) {
		List<Arrow> itr = new ArrayList<Arrow>(arrows1);
		for(Arrow a : itr) {
			if(a != null) {
			a.getWorld().spawnParticle(Particle.SWEEP_ATTACK, a.getLocation(), 1);
			a.getWorld().playSound(a.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
			a.getWorld().playSound(a.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1, 0);
			}
			else {
				arrows1.remove(a);
			}
		}
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		wor = e.getPlayer().getWorld();
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(e.getPlayer().getName());
		if(!offlinePlayer.hasPlayedBefore())
		{
			velocityPower.put(e.getPlayer().getName(), (float) .00001);
			attackPower.put(e.getPlayer().getName(), (float) .00001);
			strengthPower.put(e.getPlayer().getName(), (float) .00001);
			magicPower.put(e.getPlayer().getName(), (float) .00001);
			resisPower.put(e.getPlayer().getName(), (float) .00001);
			reachPower.put(e.getPlayer().getName(), (float) .00001);
			scopePower.put(e.getPlayer().getName(), (float) .00001);
		}
		if(!jumpcd.containsKey(e.getPlayer().getName())) {
			PlayerCountDown cd = new PlayerCountDown(e.getPlayer().getName());
			jumpcd.put(e.getPlayer().getName(), cd);
		}
		if(velocityPower.get(e.getPlayer().getName())==null) {
			velocityPower.put(e.getPlayer().getName(), (float) .00001);
		}
		if(strengthPower.get(e.getPlayer().getName())==null) {
			strengthPower.put(e.getPlayer().getName(), (float) .00001);
		}
		if(magicPower.get(e.getPlayer().getName())==null) {
			magicPower.put(e.getPlayer().getName(), (float) .00001);
		}
		if(resisPower.get(e.getPlayer().getName())==null) {
			resisPower.put(e.getPlayer().getName(), (float) .00001);
		}
		if(attackPower.get(e.getPlayer().getName())==null) {
			attackPower.put(e.getPlayer().getName(), (float) .00001);
		}
		if(reachPower.get(e.getPlayer().getName())==null) {
			reachPower.put(e.getPlayer().getName(), (float) .00001);
		}
		if(scopePower.get(e.getPlayer().getName())==null) {
			scopePower.put(e.getPlayer().getName(), (float) .00001);
		}
	}
	
	@EventHandler
	public void onPlayerInteract2(PlayerInteractEvent e) {
		if(powersOn.contains(e.getPlayer().getName())) {
		if(e.getPlayer().isSneaking()) {
			Player p = e.getPlayer();
			if(e.getAction()==Action.LEFT_CLICK_AIR) {
				attackPower.put(p.getName(), (float) (attackPower.get(p.getName()) + .0001));
			}
			if(e.getAction()==Action.LEFT_CLICK_AIR&&jumpcd.get(p.getName()).count==0&&(Math.abs(jumpcd.get(p.getName()).countS-System.currentTimeMillis())>=jumpcd.get(p.getName()).tempSec)) {
				int time = getTime(p.getInventory().getItemInMainHand(),p.getInventory());
				if(time==2) {
				jumpcd.get(p.getName()).addTime(getTime(p.getInventory().getItemInMainHand(),p.getInventory()), 0);
				countDown(p);
				if(p.getLocation().subtract(0, 1, 0).getBlock().getType()!=Material.AIR) {
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 35, 0,0,0,0.05f, p.getLocation().subtract(0, 1, 0).getBlock().getState().getData());
				}
				Vector direction = p.getLocation().getDirection().multiply(.3);
				p.setVelocity(direction.multiply(1+velocityPower.get(p.getName())));
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 65, 0));
				velocityPower.put(p.getName(), (float) (velocityPower.get(p.getName()) + .0001));
				}
				else if(time==3) {
					if(velocityPower.get(p.getName())>.1) {
					jumpcd.get(p.getName()).addTime(getTime(p.getInventory().getItemInMainHand(),p.getInventory()), 1200);
					countDown(p);
					if(p.getLocation().subtract(0, 1, 0).getBlock().getType()!=Material.AIR) {
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 45, 0,0,0,0.05f, p.getLocation().subtract(0, 1, 0).getBlock().getState().getData());
					}
					Vector direction = p.getLocation().getDirection().multiply(.3);;
					p.setVelocity(direction.multiply(getPower(p.getInventory().getItemInMainHand(),p.getInventory())+velocityPower.get(p.getName())));
					p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 120, 0));
					velocityPower.put(p.getName(), (float) (velocityPower.get(p.getName()) + .0001));
					}
				}
				else if(time==4) {
					if(velocityPower.get(p.getName())>.3) {
					jumpcd.get(p.getName()).addTime(getTime(p.getInventory().getItemInMainHand(),p.getInventory()), 1600);
					countDown(p);
					if(p.getLocation().subtract(0, 1, 0).getBlock().getType()!=Material.AIR) {
						p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 55, 0,0,0,0.05f, p.getLocation().subtract(0, 1, 0).getBlock().getState().getData());
					}
					Vector direction = p.getLocation().getDirection().multiply(.3);;
					p.setVelocity(direction.multiply(getPower(p.getInventory().getItemInMainHand(),p.getInventory())+velocityPower.get(p.getName())));
					p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 165, 0));
					velocityPower.put(p.getName(), (float) (velocityPower.get(p.getName()) + .0001));
					}
				}
			}
		}
		Player p = e.getPlayer();
		if(e.getAction()==Action.LEFT_CLICK_AIR&&(!p.isSneaking())&&p.getLocation().subtract(0,1,0).getBlock().getType()==Material.AIR){
			if(p.getInventory().contains(Material.ARROW)) {
				if(velocityPower.get(p.getName())>.5) {
				removeItemNaturallyA2(p, p.getInventory().first(Material.ARROW));
				Arrow a = p.launchProjectile(Arrow.class, p.getLocation().getDirection().multiply(2+velocityPower.get(p.getName())));
				a.setGravity(false);
				a.setMetadata("Shot1", new FixedMetadataValue(this, 0));
				a.setMetadata(p.getName(), new FixedMetadataValue(this, 0));
				a.setShooter(p);
				destroyArrow(a);
				arrows1.add(a);
				velocityPower.put(p.getName(), (float) (velocityPower.get(p.getName()) + .0001));
				}
			}
		}
		if((e.getAction()==Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK)) {
			if(blockBehindPlayer(p)==true) {
				Vector direction = (p.getLocation().getDirection().multiply(.1)).multiply(1+velocityPower.get(p.getName()));
				if(velocityPower.get(p.getName())>.05) {
				direction.setY(direction.getY()+.2);
				}
				p.setVelocity(direction);
				velocityPower.put(p.getName(), (float) (velocityPower.get(p.getName()) + .000001));
			}
		}
		if((e.getAction()==Action.LEFT_CLICK_AIR||e.getAction()==Action.LEFT_CLICK_BLOCK)&&(getSword(p.getInventory().getItemInMainHand())!=Material.AIR)) {
			List<Entity> es = p.getNearbyEntities(4+reachPower.get(p.getName()), 4+reachPower.get(p.getName()), 4+reachPower.get(p.getName()));
			for(Entity est : es) {
				if(est instanceof LivingEntity) {
					if(getLookingAt(p, (LivingEntity) est)==true) {
						Material item = getSword(p.getInventory().getItemInMainHand());
						if(item==Material.WOOD_SWORD) {
							if(est instanceof Player) {
								if(((4+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())))<=0){
									
								}
								else {
								((LivingEntity) est).damage((4+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())), p);
								}
							}
							else {
							((LivingEntity) est).damage(4+attackPower.get(p.getName()), p);
							}
						}
						else if(item==Material.STONE_SWORD) {
							if(est instanceof Player) {
								if(((5+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())))<=0){
									
								}
								else {
								((LivingEntity) est).damage((5+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())), p);
								}
							}
							else {
							((LivingEntity) est).damage(5+attackPower.get(p.getName()), p);
							}
						}
						else if(item==Material.IRON_SWORD) {
							if(est instanceof Player) {
								if(((6+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())))<=0){
									
								}
								else {
								((LivingEntity) est).damage((6+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())), p);
								}
							}
							else {
							((LivingEntity) est).damage(6+attackPower.get(p.getName()), p);
							}
						}
						else if(item==Material.GOLD_SWORD) {
							if(est instanceof Player) {
								if(((4+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())))<=0){
									
								}
								else {
								((LivingEntity) est).damage((4+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())), p);
								}
							}
							else {
							((LivingEntity) est).damage(4+attackPower.get(p.getName()), p);
							}
						}
						else if(item==Material.DIAMOND_SWORD) {
							if(est instanceof Player) {
								if(((7+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())))<=0){
									
								}
								else {
								((LivingEntity) est).damage((7+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())), p);
								}
							}
							else {
							((LivingEntity) est).damage(7+attackPower.get(p.getName()), p);
							}
						}
						else if(item==Material.WOOD_AXE) {
							if(est instanceof Player) {
								if(((2+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())))<=0){
									
								}
								else {
								((LivingEntity) est).damage((2+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())), p);
								}
							}
							else {
							((LivingEntity) est).damage(2+attackPower.get(p.getName()), p);
							}
						}
						else if(item==Material.STONE_AXE) {
							if(est instanceof Player) {
								if(((3+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())))<=0){
									
								}
								else {
								((LivingEntity) est).damage((3+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())), p);
								}
							}
							else {
							((LivingEntity) est).damage(3+attackPower.get(p.getName()), p);
							}
						}
						else if(item==Material.IRON_AXE) {
							if(est instanceof Player) {
								if(((4+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())))<=0){
									
								}
								else {
								((LivingEntity) est).damage((4+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())), p);
								}
							}
							else {
							((LivingEntity) est).damage(4+attackPower.get(p.getName()), p);
							}
						}
						else if(item==Material.GOLD_AXE) {
							if(est instanceof Player) {
								if(((2+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())))<=0){
									
								}
								else {
								((LivingEntity) est).damage((2+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())), p);
								}
							}
							else {
							((LivingEntity) est).damage(2+attackPower.get(p.getName()), p);
							}
						}
						else if(item==Material.DIAMOND_AXE) {
							if(est instanceof Player) {
								if(((6+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())))<=0){
									
								}
								else {
								((LivingEntity) est).damage((6+attackPower.get(p.getName()))-(resisPower.get(((Player) est).getName())), p);
								}
							}
							else {
							((LivingEntity) est).damage(6+attackPower.get(p.getName()), p);
							}
						}
					}
				}
				else {
					if(getLookingAt(p, est)==true) {
						Material item = getSword(p.getInventory().getItemInMainHand());
						if(item!=Material.AIR) {
							if((est instanceof Arrow)||(est instanceof Snowball)||(est instanceof Egg)||(est instanceof EnderPearl)||(est instanceof Fireball)) {
								if(est.hasMetadata(p.getName())) {
									
								}
								else {
								est.setVelocity(est.getVelocity().multiply(-1+(-1*velocityPower.get(p.getName()))));
								velocityPower.put(p.getName(), (float) (velocityPower.get(p.getName()) + .0002));
								}
							}
						}
					}
				}
			}
		}
		}
	}
	
	public void destroyArrow(Arrow a) {
		for(Player pa : Bukkit.getServer().getOnlinePlayers()) {
			try {
			Object packet = getNmsClass("PacketPlayOutEntityDestroy").getConstructor(int[].class).newInstance(new int[] {a.getEntityId()});
	        sendPacket(pa, packet);
			}
			catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	}
	
	void sendPacket(Player p, Object packet) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, ClassNotFoundException {
	    Object nmsPlayer = p.getClass().getMethod("getHandle").invoke(p);
	    Object plrConnection = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
	    plrConnection.getClass().getMethod("sendPacket", getNmsClass("Packet")).invoke(plrConnection, packet);
	}

	Class<?> getNmsClass(String nmsClassName) throws ClassNotFoundException {
	    return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + nmsClassName);
	}
	
	public boolean blockBehindPlayer(Player p) {
		Location l = p.getLocation();
		if(l.subtract(0, .08, 0).getBlock().getType()!=Material.AIR) {
			return false;
		}
		else {
			int direction = (Math.round(l.getYaw()/90))+1;
			if(direction==1) {
				if(p.getLocation().subtract(0, 0, 1).getBlock().getType()!=Material.AIR) {
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 30, 0,0,0,0.05f, p.getLocation().subtract(0, 0, 1).getBlock().getState().getData());
						return true;
				}
				else {
					return false;
				}
			}
			else if(direction==2) {
				if(p.getLocation().add(1, 0, 0).getBlock().getType()!=Material.AIR) {
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 30, 0,0,0,0.05f, p.getLocation().add(1, 0, 0).getBlock().getState().getData());
						return true;
				}
				else {
					return false;
				}
			}
			else if(direction==3||direction==-1) {
				if(p.getLocation().add(0, 0, 1).getBlock().getType()!=Material.AIR) {
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 30, 0,0,0,0.05f, p.getLocation().add(0, 0, 1).getBlock().getState().getData());
						return true;
				}
				else {
					return false;
				}
			}
			else if(direction==4||direction==0) {
				if(p.getLocation().subtract(1, 0, 0).getBlock().getType()!=Material.AIR) {
					p.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 30, 0,0,0,0.05f, p.getLocation().subtract(1, 0, 0).getBlock().getState().getData());
						return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
	}
	
	private static boolean getLookingAt(LivingEntity player, LivingEntity player1) {
		Location eye = player.getEyeLocation();
		Vector toEntity = ((LivingEntity) player1).getEyeLocation().toVector().subtract(eye.toVector());
		double dot = toEntity.normalize().dot(eye.getDirection());
		if((1D-scopePower.get(((Player) player).getName()))<=.01) {
			return dot > 0.01D;
		}
		else {
		return dot > (1D-scopePower.get(((Player) player).getName()));
		}
	}
	
	private static boolean getLookingAt(LivingEntity player, Entity player1) {
		Location eye = player.getEyeLocation();
		Vector toEntity = ((Entity) player1).getLocation().toVector().subtract(eye.toVector());
		double dot = toEntity.normalize().dot(eye.getDirection());
		if((0.85D-scopePower.get(((Player) player).getName()))<=.01) {
			return dot > 0.01D;
		}
		else {
		return dot > (0.85D-scopePower.get(((Player) player).getName()));
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("resetpowers")) {
			if (sender instanceof Player) {
				velocityPower.put(((Player) sender).getName(), (float) .00001);
				attackPower.put(((Player) sender).getName(), (float) .00001);
				strengthPower.put(((Player) sender).getName(), (float) .00001);
				magicPower.put(((Player) sender).getName(), (float) .00001);
				resisPower.put(((Player) sender).getName(), (float) .00001);
				reachPower.put(((Player) sender).getName(), (float) .00001);
				scopePower.put(((Player) sender).getName(), (float) .00001);
				return true;
			}
		}
		if (command.getName().equalsIgnoreCase("turnonpower")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if(powersOn.contains(p.getName())) {
					powersOn.remove(p.getName());
					p.sendMessage(ChatColor.RED + "Powers disabled!");
				}
				else {
					powersOn.add(p.getName());
					p.sendMessage(ChatColor.GREEN + "Powers enabled!");
				}
				return true;
			}
		}
		if (command.getName().equalsIgnoreCase("reloadconfigfol")) {
			if (sender instanceof Player) {
				loadConfig();
				return true;
			}
		}
		//
		if (command.getName().equalsIgnoreCase("saveconfigfol")) {
			if (sender instanceof Player) {
				saveConfigs();
				return true;
			}
		}
		if (command.getName().equalsIgnoreCase("getpower")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;//48 char
				p.sendMessage(ChatColor.DARK_GRAY +    "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~");
				p.sendMessage(ChatColor.GOLD +         "            -~ Your Power Levels ~-             ");
				p.sendMessage(ChatColor.GRAY +         "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~");
				p.sendMessage(ChatColor.AQUA +         "Velocity Power   : " +  "" + ChatColor.RESET + "" + velocityPower.get(p.getName()));
				p.sendMessage(ChatColor.RED +          "Attack Power     : " +  "" + ChatColor.RESET + "" + attackPower.get(p.getName()));
				p.sendMessage(ChatColor.DARK_RED +     "Strength Power   : " +  "" + ChatColor.RESET + "" + strengthPower.get(p.getName()));
				p.sendMessage(ChatColor.LIGHT_PURPLE + "Magic Power      : " +  "" + ChatColor.RESET + "" + magicPower.get(p.getName()));
				p.sendMessage(ChatColor.YELLOW +       "Resistance Power : " +  "" + ChatColor.RESET + "" + resisPower.get(p.getName()));
				p.sendMessage(ChatColor.GREEN +        "Range Power      : " +  "" + ChatColor.RESET + "" + reachPower.get(p.getName()));
				p.sendMessage(ChatColor.DARK_GREEN +   "Scope Power      : " +  "" + ChatColor.RESET + "" + scopePower.get(p.getName()));
				p.sendMessage(ChatColor.GRAY +         "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~");
				p.sendMessage(ChatColor.GOLD +         "                                                ");
				p.sendMessage(ChatColor.DARK_GRAY +    "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~");
				return true;
			}
		}
		if (command.getName().equalsIgnoreCase("alterpower")) {
		if (sender instanceof Player) {
			if (args != null) {
				if (args.length>=3) {
					String name = (String) args[0];
					Integer type = Integer.parseInt(args[1]);
					Float power = Float.parseFloat(args[2]);
					if(type==1) {
						velocityPower.put(name, power);
					}
					else if(type==2) {
						attackPower.put(name, power);
					}
					else if(type==3) {
						strengthPower.put(name, power);
					}
					else if(type==4) {
						magicPower.put(name, power);
					}
					else if(type==5) {
						resisPower.put(name, power);
					}
					else if(type==6) {
						reachPower.put(name, power);
					}
					else if(type==7) {
						scopePower.put(name, power);
					}
					return true;
				}
			}
		}
		}
		return false;
	}

	
	public double getPower(ItemStack i, PlayerInventory i2) {
		if(i!=null) {
			if(i.getType()==Material.AIR) {
				return 1.1;
			}
			else if(i.getType()==Material.WOOD_SWORD||i.getType()==Material.WOOD_AXE) {
				return 1.15;
			}
			else if(i.getType()==Material.STONE_SWORD||i.getType()==Material.STONE_AXE) {
				return 1.30;
			}
			else if(i.getType()==Material.IRON_SWORD||i.getType()==Material.IRON_AXE) {
				return 1.45;
			}
			else if(i.getType()==Material.GOLD_SWORD||i.getType()==Material.GOLD_AXE) {
				return 1.65;
			}
			else if(i.getType()==Material.DIAMOND_SWORD||i.getType()==Material.DIAMOND_AXE) {
				return 1.85;
			}
		}
		else {
			return 1.1;
		}
		return 1.1;
	}
	
	public Material getSword(ItemStack i) {
		if(i!=null) {
			if(i.getType()==Material.AIR) {
				return Material.AIR;
			}
			else if(i.getType()==Material.WOOD_SWORD) {
				return Material.WOOD_SWORD;
			}
			else if(i.getType()==Material.STONE_SWORD) {
				return Material.STONE_SWORD;
			}
			else if(i.getType()==Material.IRON_SWORD) {
				return Material.IRON_SWORD;
			}
			else if(i.getType()==Material.GOLD_SWORD) {
				return Material.GOLD_SWORD;
			}
			else if(i.getType()==Material.DIAMOND_SWORD) {
				return Material.DIAMOND_SWORD;
			}
			else if(i.getType()==Material.WOOD_AXE) {
				return Material.WOOD_AXE;
			}
			else if(i.getType()==Material.STONE_AXE) {
				return Material.STONE_AXE;
			}
			else if(i.getType()==Material.IRON_AXE) {
				return Material.IRON_AXE;
			}
			else if(i.getType()==Material.GOLD_AXE) {
				return Material.GOLD_AXE;
			}
			else if(i.getType()==Material.DIAMOND_AXE) {
				return Material.DIAMOND_AXE;
			}
		}
		else {
			return Material.AIR;
		}
		return Material.AIR;
	}
	
	public int getTime(ItemStack i, PlayerInventory i2) {
		if(i!=null) {
			if(i.getType()==Material.AIR) {
				return 2;
			}
			else if(i.getType()==Material.WOOD_SWORD) {
				return 2;
			}
			else if(i.getType()==Material.STONE_SWORD) {
				return 3;
			}
			else if(i.getType()==Material.IRON_SWORD) {
				return 3;
			}
			else if(i.getType()==Material.GOLD_SWORD) {
				return 4;
			}
			else if(i.getType()==Material.DIAMOND_SWORD) {
				return 4;
			}
		}
		else {
			return 2;
		}
		return 2;
	}
	
	@EventHandler
	public void onPlayerDeath(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			resisPower.put(((Player) e.getEntity()).getName(), (float) (resisPower.get(((Player) e.getEntity()).getName()) + .0001));
			if(powersOn.contains(((Player) e.getEntity()).getName())) {
			double damage = e.getFinalDamage()-resisPower.get(((Player) e.getEntity()).getName());
			e.setDamage(damage);
			if(e.getCause()==DamageCause.FALL) {
				velocityPower.put(((Player) e.getEntity()).getName(), (float) (velocityPower.get(((Player) e.getEntity()).getName()) + .0001));
				e.setCancelled(true);
				return;
			}
			if(e.getCause()==DamageCause.BLOCK_EXPLOSION||e.getCause()==DamageCause.ENTITY_EXPLOSION) {
				e.setDamage(e.getFinalDamage()/7);
			}
			Player p = (Player) e.getEntity();
			if(p.getHealth()-e.getFinalDamage()<=0) {
				ItemStack[] items = p.getInventory().getContents();
				if(p.getInventory().getItemInMainHand()!=null) {
				if(p.getInventory().getItemInMainHand().getType()==Material.TOTEM) {
					return;
				}
				}
				else if(p.getInventory().getItemInOffHand()!=null) {
					if(p.getInventory().getItemInOffHand().getType()==Material.TOTEM) {
						return;
					}
				}
				e.setCancelled(true);
				for(ItemStack i : items) {
					if(i!=null) {
					if(i == p.getInventory().getItemInMainHand()) {
						
					}
					else {
					p.getWorld().dropItemNaturally(p.getLocation(), i);
					i.setAmount(0);
					}
					}
				}
				for(PotionEffect pe : p.getActivePotionEffects()) {
					p.removePotionEffect(pe.getType());
				}
				p.addPotionEffect(new PotionEffect((PotionEffectType.INVISIBILITY), 15, 1));
				if(p.getBedSpawnLocation()!=null) {
					Bukkit.getScheduler().runTaskLater(this, () -> p.teleport(p.getBedSpawnLocation()), 1);
				}
				else {
					Bukkit.getScheduler().runTaskLater(this, () -> p.teleport(wor.getSpawnLocation()), 1);
				}
				p.setFoodLevel(20);
				p.setHealth(p.getHealthScale());
				p.setExp(0);
				p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_FIREBALL_EXPLODE, 100, 1);
				p.sendTitle(ChatColor.DARK_RED + "YOU DIED!", ChatColor.RED + "Better luck next time.", 10, 40, 10);
			}
		}
		}
	}
	
	public double getRemain(double t1, double t2) {
		if(t1==2) {
			double tn = t2-.5;
			if(tn<0||tn>1) {
				return 0;
			}
			else {
			return t2-.5;
			}
		}
		else if(t1==3) {
			double tn = t2-.3;
			if(tn<0||tn>1) {
				return 0;
			}
			else {
			return t2-.5;
			}
		}
		else{
			double tn = t2-.2;
			if(tn<0||tn>1) {
				return 0;
			}
			else {
			return t2-.5;
			}
		}
	}
	
	public void countDown(Player p){
		if(jumpcd.get(p.getName()).getBossBar()==null) {
		BossBar b = Bukkit.createBossBar(ChatColor.RED + "Jump Cooldown", BarColor.RED, BarStyle.SEGMENTED_10);
		b.addPlayer(p);
		jumpcd.get(p.getName()).replaceBossBar(b);
		}
		if(jumpcd.get(p.getName()).count==0) {
				jumpcd.get(p.getName()).getBossBar().removePlayer(p);
				jumpcd.get(p.getName()).replaceBossBar(null);
				return;
		}
		else {
			jumpcd.get(p.getName()).removeTime(0);
			jumpcd.get(p.getName()).getBossBar().setProgress(getRemain(((jumpcd.get(p.getName()).tempSec2/1000)+1.25), jumpcd.get(p.getName()).getBossBar().getProgress()));
		Bukkit.getScheduler()
		.runTaskLater(this,
				() -> countDown(p),
				8);
		}
	}
	
	public static void removeItemNaturallyA2(Player p, int i) {
		if (p.getInventory().getItem(i).getAmount() <= 1) {
			p.getInventory().getItem(i).setAmount(0);
		} else {
			p.getInventory().getItem(i).setAmount(p.getInventory().getItem(i).getAmount() - 1);
		}
	}
	
	@EventHandler
	public void onArrowHit2(ProjectileHitEvent event) {
		if(event.getEntity() instanceof Arrow) {
			Arrow a = (Arrow) event.getEntity();
			if(a.hasMetadata("Shot1")) {
				arrows1.remove(a);
				a.getWorld().createExplosion(a.getLocation(), (float) .1+magicPower.get(((Player) a.getShooter()).getName()));
				if(a!=null) {
					a.remove();
				}
			}
		}
	}
	
	@EventHandler
	public void onArtifactShoot2(EntityShootBowEvent event) {
		if(event.getEntity() instanceof Player) {
					Player p = ((Player) event.getEntity());
					if(powersOn.contains(p.getName())) {
						if(velocityPower.get(p.getName())>1) {
					velocityPower.put(p.getName(), (float) (velocityPower.get(p.getName()) + .0001));
					if(p.isSneaking()) {
						event.getProjectile().setGravity(false);
					}
					else {
					Vector v = event.getProjectile().getVelocity().clone();
					Bukkit.getScheduler().runTaskLater(this, () -> p.launchProjectile(Arrow.class, v), 2);	
					Bukkit.getScheduler().runTaskLater(this, () -> p.launchProjectile(Arrow.class, v), 5);	
					}
						}
					}
				}
			}
	
	@EventHandler
	public void onPlayerAttack2(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			attackPower.put(p.getName(), (float) (attackPower.get(p.getName()) + .00003));
			strengthPower.put(p.getName(), (float) (strengthPower.get(p.getName()) + .00003));
			reachPower.put(p.getName(), (float) (reachPower.get(p.getName()) + .000000001));
			scopePower.put(p.getName(), (float) (scopePower.get(p.getName()) + .0000001));
			if(powersOn.contains(p.getName())) {
			ItemStack weapon = p.getInventory().getItemInMainHand();
			if(weapon!=null) {
				Material m = weapon.getType();
				if(m==Material.DIAMOND_SWORD||m==Material.STONE_SWORD||m==Material.IRON_SWORD||m==Material.WOOD_SWORD||m==Material.GOLD_SWORD) {
					e.setDamage(e.getFinalDamage()+attackPower.get(p.getName()));
				}
				else {
					e.setDamage(e.getFinalDamage()+strengthPower.get(p.getName()));
				}
			}
			else {
				e.setDamage(e.getFinalDamage()+strengthPower.get(p.getName()));
			}
			}
		}
	}
	
	@EventHandler
	public void onPlayerKill(EntityDeathEvent e) {
		if(e.getEntity().getKiller()!=null) {
			Player p = e.getEntity().getKiller();
			attackPower.put(p.getName(), (float) (attackPower.get(p.getName()) + .0001));
			strengthPower.put(p.getName(), (float) (strengthPower.get(p.getName()) + .0001));
		}
	}
	
	@EventHandler
	public void onPlayerMine(BlockBreakEvent e) {
		Player p = e.getPlayer();
		strengthPower.put(p.getName(), (float) (strengthPower.get(p.getName()) + .000001));
	}
	
	@EventHandler
	public void onPlayerEnchant(EnchantItemEvent e) {
		Player p = e.getEnchanter();
		magicPower.put(p.getName(), (float) (magicPower.get(p.getName()) + .002));
	}
	
	@EventHandler
	public void onPlayerDrink(PlayerItemConsumeEvent e) {
		Player p = e.getPlayer();
		if(e.getItem().getType()==Material.POTION) {
		magicPower.put(p.getName(), (float) (magicPower.get(p.getName()) + .001));
		}
	}
	
	//difficulty - Enable Dynamic Difficulty
	
	@EventHandler
	public void onMobDeath(EntityDeathEvent e) {
		try {
		if(config.getBoolean("Enable Dynamic Difficulty")) {
			Entity ent = e.getEntity();
			if(ent instanceof Monster) {
				LivingEntity m = (LivingEntity) ent;
				Location l = m.getLocation();
				int level = Math.abs(Math.max((int) (Math.abs(l.getX() - l.getWorld().getSpawnLocation().getX())/640),(int) (Math.abs(l.getZ() - l.getWorld().getSpawnLocation().getZ())/640)));
				e.setDroppedExp(e.getDroppedExp() + level*5/5);
				int ammount = level/5;
				if(level>200 || ammount > 60) {
					ammount = 40;
				}
				for(ItemStack i : e.getDrops()) {
					l.getWorld().dropItemNaturally(l, new ItemStack(i.getType(), randor.nextInt(ammount)+1));
				}
				if(ent instanceof Spider && (level>=25 && level<40)) {
					if(randor.nextBoolean()!=false) {
						l.getWorld().spawnEntity(l, EntityType.CAVE_SPIDER);
					}
				}
			}
		}
		}
		catch(Exception exec) {
			
		}
	}
	
	@EventHandler
	public void onEntityHit(EntityDamageByEntityEvent e) {
		if(config.getBoolean("Enable Dynamic Difficulty")) {
			Entity ent = e.getDamager();
			if(ent instanceof Monster && e.getEntity() instanceof Player) {
				LivingEntity m = (LivingEntity) ent;
				Location l = m.getLocation();
				int level = Math.abs(Math.max((int) (Math.abs(l.getX() - l.getWorld().getSpawnLocation().getX())/640),(int) (Math.abs(l.getZ() - l.getWorld().getSpawnLocation().getZ())/640)));
				if(m instanceof Zombie) {
					if(level >= 10 && level<40) {
						if(randor.nextBoolean()) {
							((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 119, randor.nextInt(2), false, false));
						}
					}
				}
				else if(m instanceof Spider || m instanceof CaveSpider) {
					if(level >= 10 && level<40) {
						if(randor.nextBoolean()) {
							((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 99, randor.nextInt(2), false, false));
						}
					}
					if(level >= 15 && level<40) {
						if(randor.nextInt(3)==0) {
							e.getEntity().getLocation().getBlock().setType(Material.WEB);
						}
					}
				}
			}
			else if(ent instanceof Player && e.getEntity() instanceof Monster) {
				
			}
			runEffectors(e);
		}
	}
	
	public String hasEffectName(Entity e) {
		if(e.hasMetadata("Withered")) {
			return "Withered";
		}
		else if(e.hasMetadata("Plagued")) {
			return "Plagued";
		}
		else if(e.hasMetadata("Burnt")) {
			return "Burnt";
		}
		else if(e.hasMetadata("Hungering")) {
			return "Hungering";
		}
		else if(e.hasMetadata("Slowing")) {
			return "Slowing";
		}
		return "none";
	}
	
	public void runEffectors(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Monster && event.getEntity() instanceof LivingEntity) {
			LivingEntity lv = (LivingEntity) event.getEntity();
				if(event.getDamager().getCustomName()!=null||(!hasEffectName(event.getDamager()).equals("none"))) {
					String name1 = hasEffectName(event.getDamager());
					String name2 = "none";
					if(event.getDamager().getCustomName()!=null) {
						name2 = event.getDamager().getCustomName();
					}
					if(name2.equals("Withered")||name1.equals("Withered")) {
						lv.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 135, 0));
					}
					else if(name2.equals("Plagued")||name1.equals("Plagued")) {
						lv.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 135, 0));
					}
					else if(name2.equals("Burnt")||name1.equals("Burnt")) {
						lv.setFireTicks(130);
					}
					else if(name2.equals("Hungering")||name1.equals("Hungering")) {
						lv.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 120, 1));
					}
					else if(name2.equals("Slowing")||name1.equals("Slowing")) {
						lv.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 160, 0));
					}
				}
			}
			if(event.getDamager() instanceof Arrow) {
				ProjectileSource damagerR = ((Arrow) event.getDamager()).getShooter();
				if(damagerR instanceof Skeleton) {
					Entity enemy = (Entity) ((Arrow) event.getDamager()).getShooter();
					LivingEntity lv = (LivingEntity) event.getEntity();
					if(enemy.getCustomName()!=null||(!hasEffectName(enemy).equals("none"))) {
						String name1 = hasEffectName(enemy);
						String name2 = "none";
						if(enemy.getCustomName()!=null) {
							name2 = enemy.getCustomName();
						}
						if(name2.equals("Withered")||name1.equals("Withered")) {
							lv.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 135, 0));
						}
						else if(name2.equals("Plagued")||name1.equals("Plagued")) {
							lv.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 135, 0));
						}
						else if(name2.equals("Burnt")||name1.equals("Burnt")) {
							lv.setFireTicks(130);
						}
						else if(name2.equals("Hungering")||name1.equals("Hungering")) {
							lv.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 120, 1));
					}
				else if(name2.equals("Slowing")||name1.equals("Slowing")) {
					lv.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 160, 0));
				}
			}
		}
		}
	}

	public String cusNName() {
		int randt = randor.nextInt(5);
		if(randt == 0) {
			return "Withered";
		}
		else if(randt == 1) {
			return "Plagued";
		}
		else if(randt == 2) {
			return "Burnt";
		}
		else if(randt == 3) {
			return "Hungering";
		}
		else if(randt == 4) {
			return "Slowing";
		}
		return "";
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if(config.getBoolean("Enable Dynamic Difficulty")) {
			checkMemetic(e.getMessage(), e.getPlayer());
			Entity ent = e.getPlayer();
			Location l = ent.getLocation();
			int level = Math.abs(Math.max((int) (Math.abs(l.getX() - l.getWorld().getSpawnLocation().getX())/640),(int) (Math.abs(l.getZ() - l.getWorld().getSpawnLocation().getZ())/640)));
			if(level > 100 && level < 103) {
				e.setCancelled(true);
			}
			if(level >= 120 && level < 124) {
				e.setMessage(e.getMessage() + " ankle?");
			}
		}
	}
	
	public void checkMemetic(String c, Player p) {
		if (c.toLowerCase().contains("edemy") || c.toLowerCase().contains("3d3my") || c.toLowerCase().contains("3demy")
				|| c.toLowerCase().contains("ed3my")) {
			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_HORSE_AMBIENT, 1, 1);
			Bukkit.getScheduler().runTaskLater(this,
					() -> p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 999999, 200)), 90);
		} else if (c.toLowerCase().contains("2834712840")) {
			List<Player> allP = p.getWorld().getPlayers();
			for (Player all : allP) {
				if (all == p) {
					all.damage(9999);
				} else {
					all.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 20));
					all.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 130, 20));
				}
			}
		} else if (c.toLowerCase().contains("finend") || c.toLowerCase().contains("fin3nd")
				|| c.toLowerCase().contains("f1nend") || c.toLowerCase().contains("f1n3nd")) {
			List<LivingEntity> ents = p.getWorld().getLivingEntities();
			for (LivingEntity m : ents) {
				if (m.getCustomName() != null) {
					if (m.getCustomName().equals("finend")) {
						List<Player> allP = p.getWorld().getPlayers();
						for (Player all : allP) {
							all.damage(9999);
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onMobSpawn2(EntitySpawnEvent e) {
		if(config.getBoolean("Enable Dynamic Difficulty")) {
			Entity ent = e.getEntity();
			Location l = ent.getLocation();
			int level = Math.abs(Math.max((int) (Math.abs(l.getX() - l.getWorld().getSpawnLocation().getX())/640),(int) (Math.abs(l.getZ() - l.getWorld().getSpawnLocation().getZ())/640)));
			if(level > 100 && level < 103) {
				ent.remove();
			}
			else if(level >= 124 && level < 130 && (!(e.getEntity() instanceof Item))) {
				EntityType et = EntityType.values()[randor.nextInt(EntityType.values().length)];
				if(et == EntityType.COMPLEX_PART || et == EntityType.PLAYER || et == EntityType.ENDER_DRAGON || et == EntityType.WITHER || et == EntityType.PAINTING || et == EntityType.ITEM_FRAME || et == EntityType.ARMOR_STAND) {
					return;
				}
				if(et == EntityType.DROPPED_ITEM || et == EntityType.FISHING_HOOK ||et == EntityType.WEATHER ||et == EntityType.BOAT || et == EntityType.LEASH_HITCH || et == EntityType.UNKNOWN || et == EntityType.MINECART_MOB_SPAWNER || et == EntityType.MINECART_HOPPER || et == EntityType.MINECART_TNT || et == EntityType.MINECART_FURNACE || et == EntityType.MINECART_COMMAND || et == EntityType.MINECART_CHEST || et == EntityType.MINECART) {
					return;
				}
				try {
				e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), et);
				e.getEntity().remove();
				}
				catch(Exception exe) {
					
				}
			}
		}
	}
	
	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent e) {
		if(config.getBoolean("Enable Dynamic Difficulty")) {
			Entity ent = e.getEntity();
			if(ent instanceof Monster && (e.getSpawnReason() == SpawnReason.NATURAL || e.getSpawnReason() == SpawnReason.SPAWNER)) {
				LivingEntity m = (LivingEntity) ent;
				Location l = m.getLocation();
				int level = Math.abs(Math.max((int) (Math.abs(l.getX() - l.getWorld().getSpawnLocation().getX())/640),(int) (Math.abs(l.getZ() - l.getWorld().getSpawnLocation().getZ())/640)));
				int health = level/2;
				double speed = .01*level;
				double dmg = .5*level/2;
				if(level>40) {
					speed = .3;
				}
				if(m instanceof Creeper) {
					int power = level/5;
					if(power > 10) {
						power = 10;
					}
					((Creeper) m).setExplosionRadius(((Creeper) m).getExplosionRadius() + power);
				}
				AttributeInstance healthAttribute = m.getAttribute(Attribute.GENERIC_MAX_HEALTH);
				healthAttribute.setBaseValue(healthAttribute.getBaseValue()+health);
				m.setHealth(m.getHealth()+health);
				AttributeInstance speedAttribute = m.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
				speedAttribute.setBaseValue(speedAttribute.getBaseValue()+speed);
				AttributeInstance attackAttribute = m.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
				attackAttribute.setBaseValue(attackAttribute.getBaseValue()+dmg);
				if((level>=15 && level<40)||(level > 103)) {
					if(level >= 999998 && level < 999999) {
					}
					else {
					String name = cusNName();
					m.setCustomName(name);
					m.setMetadata(name, new FixedMetadataValue(this, 0));
					}
				}
				else if(level >= 40 && level <= 100){
					String name = setRandomScp(ent, level);
					m.setCustomName(name);
					m.setMetadata(name, new FixedMetadataValue(this, 0));
					onSpawnEffects(name, (LivingEntity) ent);
				}
			}
		}
	}
	
	public void onSpawnEffects(String n, Entity e) {
		if (e != null && e instanceof Monster && !e.isDead()) {
			if (e.getCustomName() == null) {
				return;
			} 
			else if (e.getCustomName().equals("Old Shadow")) {
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
				e.setSilent(true);
			}
			else if (e.getCustomName().equals("Sherogath")) {
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
				e.setSilent(true);
			}
			else if (e.getCustomName().equals("Sadness")) {
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 200));
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999, 2));
				e.setSilent(true);
			}
			else if (e.getCustomName().equals("Armored")) {
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999, 2));
				wor = e.getWorld();
			} else if (e.getCustomName().equals("Cloaked")) {
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
			} else if (e.getCustomName().equals("Sprint")) {
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 1));
				wor = e.getWorld();
			} else if (e.getCustomName().equals("Screamer")) {
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 3));
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 8));
				((Monster) e).setSilent(true);
				wor = e.getWorld();
			} else if (e.getCustomName().equals("Converter")) {
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 2));
				((Monster) e).setSilent(true);
			} else if (e.getCustomName().equals("Watcher")) {
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 2));
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 2));
				((Monster) e).setSilent(true);
			} else if (e.getCustomName().equals("Living Armor")) {
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 2));
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 2));
				((Monster) e).setSilent(true);
			}else if (e.getCustomName().equals("Inferno")) {
				Entity scream = e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), EntityType.ZOMBIE_VILLAGER);
				((ZombieVillager) scream).setBaby(true);
				EntityEquipment ee = ((Monster) scream).getEquipment();
				ItemStack myAwesomeSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
				myAwesomeSkullMeta.setOwner("crolin");
				myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);
				//net.minecraft.server.v1_12_R1.ItemStack nmsStack5 = CraftItemStack.asNMSCopy(myAwesomeSkull);
				ee.setHelmet(myAwesomeSkull);
				Entity scream2 = e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), EntityType.BAT);
				((LivingEntity) scream).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
				((LivingEntity) scream).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 200));
				((LivingEntity) scream2).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
				scream.setCustomName("Inferno");
				scream.setMetadata("Inferno", new FixedMetadataValue(this, 0));
				scream.setSilent(true);
				scream2.setSilent(true);
				scream2.setPassenger(scream);
				e.remove();
			} else if (e.getCustomName().equals("Runner")) {
				Entity mob1 = e.getWorld().spawnEntity(e.getLocation(), EntityType.ZOMBIE);
				mob1.setCustomName("Runren");
				((Monster) mob1).getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(32);
				mob1.setSilent(true);
				((LivingEntity) mob1).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
				dressRunner.dressDGolem((LivingEntity) mob1);
				e.remove();
			} else if (e.getCustomName().equals("Cursed Armor")) {
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
				DressCursed.dressDGolem((LivingEntity) e);
				e.setSilent(true);
			} else if (e.getCustomName().equals("Shadow")) {
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
				e.setSilent(true);
			}
			else {
				dressMob(n, (LivingEntity) e);
			}
		}
	}

	public boolean customNameM(String s) {
		if(s.equals("Beamer")) {
			return true;
		}
		if(s.equals("Webber")) {
			return true;
		}
		if(s.equals("Ender")) {
			return true;
		}
		if(s.equals("Vengeance")) {
			return true;
		}
		if(s.equals("Beserk")) {
			return true;
		}
		if(s.equals("Spawner 2")) {
			return true;
		}
		if(s.equals("Launcher")) {
			return true;
		}
		if(s.equals("Poisonous")) {
			return true;
		}
		if(s.equals("Blinding")) {
			return true;
		}
		if(s.equals("Withering")) {
			return true;
		}
		if(s.equals("Theif")) {
			return true;
		}
		if(s.equals("Quicksand")) {
			return true;
		}
		if(s.equals("Sapper")) {
			return true;
		}
		if(s.equals("Lifesteal")) {
			return true;
		}
		if(s.equals("Lightning")) {
			return true;
		}
		if(s.equals("Weakness")) {
			return true;
		}
		if(s.equals("Spawner 1")) {
			return true;
		}
		if(s.equals("Molten")) {
			return true;
		}
		if(s.equals("Gravity")) {
			return true;
		}
		if(s.equals("Confusing")) {
			return true;
		}
		if(s.equals("Fireball")) {
			return true;
		}
		if(s.equals("Volley")) {
			return true;
		}
		if(s.equals("Necromancer")) {
			return true;
		}
		if(s.equals("Armored")) {
			return true;
		}
		if(s.equals("Cloaked")) {
			return true;
		}
		if(s.equals("Sprint")) {
			return true;
		}
		if(s.equals("Demon")) {
			return true;
		}
		if(s.equals("Beserker")) {
			return true;
		}
		if(s.equals("Beserkerr")) {
			return true;
		}
		if(s.equals("Screamer")) {
			return true;
		}
		if(s.equals("E Book")) {
			return true;
		}
		if(s.equals("Converter")) {
			return true;
		}
		if(s.equals("Watcher")) {
			return true;
		}
		if(s.equals("Ghastly")) {
			return true;
		}
		if(s.equals("Living Armor")) {
			return true;
		}
		if(s.equals("Keeper")) {
			return true;
		}
		if(s.equals("repeeK")) {
			return true;
		}
		if(s.equals("Inferno")) {
			return true;
		}
		if(s.equals("Runner")) {
			return true;
		}
		if(s.equals("Cursed Armor")) {
			return true;
		}
		if(s.equals("Shadow")) {
			return true;
		}
		if(s.equals("Infinity")) {
			return true;
		}
		if(s.equals("Mother")) {
			return true;
		}
		if(s.equals("Speedster")) {
			return true;
		}
		if(s.equals("Linear")) {
			return true;
		}
		
		return false;
	}
	
	public static void removeItemNaturally(Player p) {
		if (p.getInventory().getItemInMainHand().getAmount() <= 1) {
			//p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
			p.getInventory().getItemInMainHand().setAmount(0);
		} else {
			p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
		}
	}
	
	@EventHandler
	public void onMobName(PlayerInteractEntityEvent event) {
		if(!(event.getRightClicked() instanceof Player)) {
			ItemStack i = event.getPlayer().getInventory().getItemInMainHand();
			if(i.getType()==Material.NAME_TAG) {
				if(i.hasItemMeta()) {
					if(i.getItemMeta().hasDisplayName()) {
						String s = i.getItemMeta().getDisplayName();
						if(customNameM(s)||s.equals(event.getPlayer().getName())) {
							event.getPlayer().sendMessage(ChatColor.DARK_RED + "Custom Mobs can Not be Made!");
							event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_SKELETON_HORSE_DEATH, 1, 2);
							removeItemNaturally(event.getPlayer());
							if(event.getRightClicked() instanceof LivingEntity) {
							((LivingEntity) event.getRightClicked()).damage(9999);
							}
							else {
								event.getRightClicked().remove();
							}
						}
					}
				}
			}
		}
	}
	
	public String setRandomScp(Entity e, int level) {
		int randomNum = randor.nextInt(4);
		if(level>=40 && level<45) {
			if(randomNum == 0) {
			return "Beamer";
			}
			else if(randomNum == 1) {
			return "Webber";
			}
			else if(randomNum == 2) {
			return "Ender";
			}
			else if(randomNum == 3) {
			return "Vengeance";
			}
		}
		else if(level>=45 && level<50) {
			if(randomNum == 0) {
				return "Beserk";
			}
			else if(randomNum == 1) {
				return "Spawner 2";
			}
			else if(randomNum == 2) {
				return "Launcher";
			}
			else if(randomNum == 3) {
				return "Poisonous";
			}
		}
		else if(level>=50 && level<55) {
			if(randomNum == 0) {
				return "Blinding";
			}
			else if(randomNum == 1) {
				return "Withering";
			}
			else if(randomNum == 2) {
				return "Theif";
			}
			else if(randomNum == 3) {
				return "Quicksand";
			}
		}
		else if(level>=55 && level<60) {
			if(randomNum == 0) {
				return "Ender";
			}
			else if(randomNum == 1) {
				return "Lifesteal";
			}
			else if(randomNum == 2) {
				return "Weakness";
			}
			else if(randomNum == 3) {
				return "Lightning";
			}
		}
		else if(level>=60 && level<65) {
			if(randomNum == 0) {
				return "Gravity";
			}
			else if(randomNum == 1) {
				return "Confusing";
			}
			else if(randomNum == 2) {
				return "Fired";
			}
			else if(randomNum == 3) {
				return "Ghastly";
			}
		}
		else if(level>=65 && level<70) {
			if(randomNum == 0) {
				return "Necromancer";
			}
			else if(randomNum == 1) {
				return "Armored";
			}
			else if(randomNum == 2) {
				return "Cloaked";
			}
			else if(randomNum == 3) {
				return "Sprint";
			}
		}
		else if(level>=70 && level<75) {
			if(randomNum == 0) {
				return "Beserker";
			}
			else if(randomNum == 1) {
				return "Screamer";
			}
			else if(randomNum == 2) {
				return "Converter";
			}
			else if(randomNum == 3) {
				return "Watcher";
			}
		}
		else if(level>=75 && level<80) {
			if(randomNum == 0) {
				return "Living Armor";
			}
			else if(randomNum == 1) {
				return "Runner";
			}
			else if(randomNum == 2) {
				return "Cursed Armor";
			}
			else if(randomNum == 3) {
				return "Shadow";
			}
		}
		else if(level>=80 && level<85) {
			if(randomNum == 0) {
				return "2834712840";
			}
			else if(randomNum == 1) {
				return "Edemy";
			}
			else if(randomNum == 2) {
				return "Infinity";
			}
			else if(randomNum == 3) {
				return "Mother";
			}
		}
		else if(level>=85 && level<90) {
			if(randomNum == 0) {
				return "Speedster";
			}
			else if(randomNum == 1) {
				return "Linear";
			}
			else if(randomNum == 2) {
				return "Molten";
			}
			else if(randomNum == 3) {
				return "Fireball";
			}
		}
		else if(level>=90 && level<95) {
			if(randomNum == 0) {
				return "Demon";
			}
			else if(randomNum == 1) {
				return "Inferno";
			}
			else if(randomNum == 2) {
				return "Old Shadow";
			}
			else if(randomNum == 3) {
				return "Sherogath";
			}
		}
		else if(level>=95 && level<100) {
			return "Sadness";
		}
		return "";
	}
	
	public void dressMob(String n, LivingEntity e) {
		if (n.equals("Beamer")) {
			LivingEntity ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), EntityType.HUSK);
			EntityEquipment ee = (ent).getEquipment();
			ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			LeatherArmorMeta lch6 = (LeatherArmorMeta) lchest.getItemMeta();
			lch6.setColor(Color.fromRGB(255, 255, 255));
			lchest.setItemMeta(lch6);
			//net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(lchest);
			ItemStack lboot = new ItemStack(Material.LEATHER_BOOTS, 1);
			LeatherArmorMeta lb = (LeatherArmorMeta) lboot.getItemMeta();
			lb.setColor(Color.fromRGB(255, 255, 255));
			ItemStack myAwesomeSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
			myAwesomeSkullMeta.setOwner("hamburger");
			myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);
			//net.minecraft.server.v1_12_R1.ItemStack nmsStack5 = CraftItemStack.asNMSCopy(myAwesomeSkull);
			//lchest = CraftItemStack.asBukkitCopy(nmsStack);
			lboot.setItemMeta(lch6);
			ee.setChestplate(lchest);
			ee.setBoots(lboot);
			ee.setHelmet(myAwesomeSkull);
			ent.setCustomName("Beamer");
			ent.setMetadata("Beamer", new FixedMetadataValue(this, 0));
			e.remove();
		} else if (n.equals("Webber")) {
			LivingEntity ent = null;
			if (randor.nextBoolean() == true) {
				ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), EntityType.SPIDER);
			} else {
				ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), EntityType.CAVE_SPIDER);
			}
			ent.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
			ent.setCustomName("Webber");
			ent.setMetadata("Webber", new FixedMetadataValue(this, 0));
			e.remove();
		} else if (n.equals("Ender")) {
			LivingEntity ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), e.getType());
			EntityEquipment ee = (ent).getEquipment();
			ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			LeatherArmorMeta lch6 = (LeatherArmorMeta) lchest.getItemMeta();
			lch6.setColor(Color.fromRGB(0, 0, 0));
			lchest.setItemMeta(lch6);
			//net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(lchest);
			ItemStack lboot = new ItemStack(Material.LEATHER_BOOTS, 1);
			LeatherArmorMeta lb = (LeatherArmorMeta) lboot.getItemMeta();
			lb.setColor(Color.fromRGB(0, 0, 0));
			ItemStack lboot3 = new ItemStack(Material.LEATHER_LEGGINGS, 1);
			LeatherArmorMeta lb3 = (LeatherArmorMeta) lboot3.getItemMeta();
			lb3.setColor(Color.fromRGB(0, 0, 0));
			lboot3.setItemMeta(lb3);
			ItemStack myAwesomeSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
			myAwesomeSkullMeta.setOwner("CartoonZ12");
			myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);
			//net.minecraft.server.v1_12_R1.ItemStack nmsStack5 = CraftItemStack.asNMSCopy(myAwesomeSkull);
			//lchest = CraftItemStack.asBukkitCopy(nmsStack);
			lboot.setItemMeta(lch6);
			ee.setChestplate(lchest);
			ee.setBoots(lboot);
			ee.setLeggings(lboot3);
			ee.setHelmet(myAwesomeSkull);
			ent.setCustomName("Ender");
			ent.setMetadata("Ender", new FixedMetadataValue(this, 0));
			e.remove();
		} else if (n.equals("Vengeance")) {
			LivingEntity ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), EntityType.ZOMBIE);
			ent.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
			EntityEquipment ee = (ent).getEquipment();
			ItemStack myAwesomeSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
			myAwesomeSkullMeta.setOwner("WalkinEggs");
			myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);
			//net.minecraft.server.v1_12_R1.ItemStack nmsStack5 = CraftItemStack.asNMSCopy(myAwesomeSkull);
			ee.setHelmet(myAwesomeSkull);
			ent.setCustomName("Vengeance");
			ent.setMetadata("Vengeance", new FixedMetadataValue(this, 0));
			e.remove();
		} else if (n.equals("Beserk")) {
			LivingEntity ent = null;
			if (randor.nextBoolean() == true) {
				ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), EntityType.ZOMBIE);
			} else {
				ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), EntityType.SKELETON);
			}
			EntityEquipment ee = (ent).getEquipment();
			ent.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
			ent.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 1));
			ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			LeatherArmorMeta lch6 = (LeatherArmorMeta) lchest.getItemMeta();
			lch6.setColor(Color.fromRGB(162, 23, 57));
			lchest.setItemMeta(lch6);
			ItemStack myAwesomeSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
			myAwesomeSkullMeta.setOwner("Mannequin");
			myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);
			//net.minecraft.server.v1_12_R1.ItemStack nmsStack5 = CraftItemStack.asNMSCopy(myAwesomeSkull);
			ee.setChestplate(lchest);
			ee.setHelmet(myAwesomeSkull);
			ent.setCustomName("Beserk");
			ent.setMetadata("Beserk", new FixedMetadataValue(this, 0));
			e.remove();
		} else if (n.equals("Spawner 2") || n.equals("Spawner 1")) {
			LivingEntity ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), e.getType());
			EntityEquipment ee = (ent).getEquipment();
			ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			LeatherArmorMeta lch6 = (LeatherArmorMeta) lchest.getItemMeta();
			lch6.setColor(Color.fromRGB(162, 23, 57));
			lchest.setItemMeta(lch6);
			ItemStack lchest1 = new ItemStack(Material.LEATHER_LEGGINGS, 1);
			LeatherArmorMeta lch61 = (LeatherArmorMeta) lchest1.getItemMeta();
			lch61.setColor(Color.fromRGB(162, 23, 57));
			lchest1.setItemMeta(lch61);
			ItemStack lchest2 = new ItemStack(Material.LEATHER_BOOTS, 1);
			LeatherArmorMeta lch62 = (LeatherArmorMeta) lchest2.getItemMeta();
			lch62.setColor(Color.fromRGB(162, 23, 57));
			lchest2.setItemMeta(lch62);
			ee.setLeggings(lchest1);
			ee.setChestplate(lchest);
			ee.setBoots(lchest2);
			ent.setCustomName("Spawner 2");
			ent.setMetadata("Spawner 2", new FixedMetadataValue(this, 0));
			e.remove();
		} else if (n.equals("Quicksand")) {
			LivingEntity ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), e.getType());
			EntityEquipment ee = (ent).getEquipment();
			ItemStack lchest2 = new ItemStack(Material.LEATHER_BOOTS, 1);
			LeatherArmorMeta lch62 = (LeatherArmorMeta) lchest2.getItemMeta();
			lch62.setColor(Color.fromRGB(230, 208, 149));
			lchest2.setItemMeta(lch62);
			ee.setBoots(lchest2);
			ent.setCustomName("Quicksand");
			ent.setMetadata("Quicksand", new FixedMetadataValue(this, 0));
			e.remove();
		} else if (n.equals("Sapper")) {
			LivingEntity ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), e.getType());
			EntityEquipment ee = (ent).getEquipment();
			ItemStack myAwesomeSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
			myAwesomeSkullMeta.setOwner("wallabee");
			myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);
			net.minecraft.server.v1_12_R1.ItemStack nmsStack5 = CraftItemStack.asNMSCopy(myAwesomeSkull);
			ee.setHelmet(myAwesomeSkull);
			ent.setCustomName("Sapper");
			ent.setMetadata("Sapper", new FixedMetadataValue(this, 0));
			e.remove();
		} else if (n.equals("Blinding")) {
			LivingEntity ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0),
					EntityType.WITHER_SKELETON);
			EntityEquipment ee = (ent).getEquipment();
			ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			LeatherArmorMeta lch6 = (LeatherArmorMeta) lchest.getItemMeta();
			lch6.setColor(Color.fromRGB(0, 0, 0));
			lchest.setItemMeta(lch6);
			//net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(lchest);
			ItemStack lboot = new ItemStack(Material.LEATHER_BOOTS, 1);
			LeatherArmorMeta lb = (LeatherArmorMeta) lboot.getItemMeta();
			lb.setColor(Color.fromRGB(0, 0, 0));
			ItemStack lboot3 = new ItemStack(Material.LEATHER_LEGGINGS, 1);
			LeatherArmorMeta lb3 = (LeatherArmorMeta) lboot3.getItemMeta();
			lb3.setColor(Color.fromRGB(0, 0, 0));
			lboot3.setItemMeta(lb3);
			ItemStack myAwesomeSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
			myAwesomeSkullMeta.setOwner("SpencePent");
			myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);
			//net.minecraft.server.v1_12_R1.ItemStack nmsStack5 = CraftItemStack.asNMSCopy(myAwesomeSkull);
			//lchest = CraftItemStack.asBukkitCopy(nmsStack);
			lboot.setItemMeta(lch6);
			ee.setChestplate(lchest);
			ee.setBoots(lboot);
			ee.setLeggings(lboot3);
			ee.setHelmet(myAwesomeSkull);
			ent.setCustomName("Blinding");
			ent.setMetadata("Blinding", new FixedMetadataValue(this, 0));
			e.remove();
		} else if (n.equals("Withering")) {
			LivingEntity ent = null;
			if (randor.nextBoolean() == true) {
				ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), EntityType.ZOMBIE);
			} else {
				ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), EntityType.SKELETON);
			}
			EntityEquipment ee = (ent).getEquipment();
			ent.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
			ItemStack myAwesomeSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
			myAwesomeSkullMeta.setOwner("dmdsldjaak");
			myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);
			//net.minecraft.server.v1_12_R1.ItemStack nmsStack5 = CraftItemStack.asNMSCopy(myAwesomeSkull);
			ee.setHelmet(myAwesomeSkull);
			ent.setCustomName("Withering");
			ent.setSilent(true);
			ent.setMetadata("Withering", new FixedMetadataValue(this, 0));
			e.remove();
		} else if (n.equals("Poisonous")) {
			LivingEntity ent = null;
			if (randor.nextBoolean() == true) {
				ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), EntityType.ZOMBIE);
			} else {
				ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), EntityType.SKELETON);
			}
			ent.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
			ent.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 999999, 1));
			EntityEquipment ee = (ent).getEquipment();
			ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			LeatherArmorMeta lch6 = (LeatherArmorMeta) lchest.getItemMeta();
			lch6.setColor(Color.fromRGB(54, 94, 46));
			lchest.setItemMeta(lch6);
			//net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(lchest);
			ItemStack lboot = new ItemStack(Material.LEATHER_BOOTS, 1);
			LeatherArmorMeta lb = (LeatherArmorMeta) lboot.getItemMeta();
			lb.setColor(Color.fromRGB(54, 94, 46));
			lboot.setItemMeta(lch6);
			ee.setChestplate(lchest);
			ee.setBoots(lboot);
			ee.setHelmet(new ItemStack(Material.SLIME_BLOCK, 1));
			ent.setCustomName("Poisonous");
			ent.setMetadata("Poisonous", new FixedMetadataValue(this, 0));
			e.remove();
		} else if (n.equals("Lightning")) {
			LivingEntity ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), e.getType());
			ent.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 1));
			EntityEquipment ee = (ent).getEquipment();
			ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			LeatherArmorMeta lch6 = (LeatherArmorMeta) lchest.getItemMeta();
			lch6.setColor(Color.fromRGB(255, 255, 255));
			lch6.addEnchant(Enchantment.LURE, 1, true);
			lch6.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			lchest.setItemMeta(lch6);
			//net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(lchest);
			ItemStack lboot = new ItemStack(Material.LEATHER_BOOTS, 1);
			LeatherArmorMeta lb = (LeatherArmorMeta) lboot.getItemMeta();
			lb.setColor(Color.fromRGB(255, 255, 255));
			lb.addEnchant(Enchantment.LURE, 1, true);
			lb.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			lboot.setItemMeta(lch6);
			ee.setChestplate(lchest);
			ee.setBoots(lboot);
			ent.setCustomName("Lightning");
			ent.setMetadata("Lightning", new FixedMetadataValue(this, 0));
			e.remove();
		} else if (n.equals("Weakness")) {
			LivingEntity ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), e.getType());
			EntityEquipment ee = (ent).getEquipment();
			ee.setItemInMainHand(new ItemStack(Material.STICK, 1));
			ee.setItemInOffHand(new ItemStack(Material.STICK, 1));
			ent.setCustomName("Weakness");
			ent.setMetadata("Weakness", new FixedMetadataValue(this, 0));
			e.remove();
		} else if (n.equals("Molten")) {
			LivingEntity ent = null;
			if (randor.nextBoolean() == true) {
				ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), EntityType.ZOMBIE);
			} else {
				ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), EntityType.SKELETON);
			}
			EntityEquipment ee = (ent).getEquipment();
			ent.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 100));
			ent.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 100));
			ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			LeatherArmorMeta lch6 = (LeatherArmorMeta) lchest.getItemMeta();
			lch6.setColor(Color.fromRGB(252, 115, 69));
			lchest.setItemMeta(lch6);
			ItemStack lchest2 = new ItemStack(Material.LEATHER_BOOTS, 1);
			LeatherArmorMeta lch61 = (LeatherArmorMeta) lchest2.getItemMeta();
			lch61.setColor(Color.fromRGB(252, 115, 69));
			lchest2.setItemMeta(lch61);
			ItemStack lchest3 = new ItemStack(Material.LEATHER_LEGGINGS, 1);
			LeatherArmorMeta lch62 = (LeatherArmorMeta) lchest3.getItemMeta();
			lch62.setColor(Color.fromRGB(252, 115, 69));
			lchest3.setItemMeta(lch62);
			ee.setItemInMainHand(new ItemStack(Material.FIRE, 1));
			ee.setItemInOffHand(new ItemStack(Material.FIRE, 1));
			ee.setChestplate(lchest);
			ee.setLeggings(lchest3);
			ee.setBoots(lchest2);
			ent.setFireTicks(999999);
			ent.setCustomName("Molten");
			ent.setMetadata("Molten", new FixedMetadataValue(this, 0));
			e.remove();
		} else if (n.equals("Linear")) {
			LivingEntity ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0), EntityType.HUSK);
			EntityEquipment ee = (ent).getEquipment();
			ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			LeatherArmorMeta lch6 = (LeatherArmorMeta) lchest.getItemMeta();
			lch6.setColor(Color.fromRGB(58, 34, 7));
			lchest.setItemMeta(lch6);
			ItemStack lchest2 = new ItemStack(Material.LEATHER_BOOTS, 1);
			LeatherArmorMeta lch61 = (LeatherArmorMeta) lchest2.getItemMeta();
			lch61.setColor(Color.fromRGB(58, 34, 7));
			lchest2.setItemMeta(lch61);
			ItemStack lchest3 = new ItemStack(Material.LEATHER_LEGGINGS, 1);
			LeatherArmorMeta lch62 = (LeatherArmorMeta) lchest3.getItemMeta();
			lch62.setColor(Color.fromRGB(58, 34, 7));
			lchest3.setItemMeta(lch62);
			ee.setItemInMainHand(new ItemStack(Material.GOLD_AXE, 1));
			ItemStack myAwesomeSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
			myAwesomeSkullMeta.setOwner("Minautor");
			myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);
			//net.minecraft.server.v1_12_R1.ItemStack nmsStack5 = CraftItemStack.asNMSCopy(myAwesomeSkull);
			ee.setHelmet(myAwesomeSkull);
			ee.setChestplate(lchest);
			ee.setLeggings(lchest3);
			ee.setBoots(lchest2);
			ent.setCustomName("Linear");
			ent.setMetadata("Linear", new FixedMetadataValue(this, 0));
			e.remove();
		} else if (n.equals("Necromancer")) {
			LivingEntity ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0),
					EntityType.SKELETON);
			EntityEquipment ee = (ent).getEquipment();
			ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			LeatherArmorMeta lch6 = (LeatherArmorMeta) lchest.getItemMeta();
			lch6.setColor(Color.fromRGB(38, 33, 36));
			lchest.setItemMeta(lch6);
			ItemStack lchest3 = new ItemStack(Material.LEATHER_LEGGINGS, 1);
			LeatherArmorMeta lch62 = (LeatherArmorMeta) lchest3.getItemMeta();
			lch62.setColor(Color.fromRGB(38, 33, 36));
			lchest3.setItemMeta(lch62);
			ItemStack myAwesomeSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
			myAwesomeSkullMeta.setOwner("TheSlander");
			myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);
			//net.minecraft.server.v1_12_R1.ItemStack nmsStack5 = CraftItemStack.asNMSCopy(myAwesomeSkull);
			ee.setHelmet(myAwesomeSkull);
			ee.setChestplate(lchest);
			ee.setLeggings(lchest3);
			ent.setCustomName("Necromancer");
			ent.setMetadata("Necromancer", new FixedMetadataValue(this, 0));
			e.remove();
		} else if (n.equals("Fireball")) {
			LivingEntity ent = (LivingEntity) e.getWorld().spawnEntity(e.getLocation().add(0, 1, 0),
					EntityType.SKELETON);
			EntityEquipment ee = (ent).getEquipment();
			ItemStack myAwesomeSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
			myAwesomeSkullMeta.setOwner("MrMatchbox");
			myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);
			//net.minecraft.server.v1_12_R1.ItemStack nmsStack5 = CraftItemStack.asNMSCopy(myAwesomeSkull);
			ee.setHelmet(myAwesomeSkull);
			ent.setCustomName("Fireball");
			ent.setMetadata("Fireball", new FixedMetadataValue(this, 0));
			e.remove();
		}
	}
	
	@EventHandler
	public void onEntityFire(EntityCombustEvent e) {
		if(config.getBoolean("Enable Dynamic Difficulty")) {
			if(e.getEntity() instanceof Monster) {
				Entity ent = e.getEntity();
				LivingEntity m = (LivingEntity) ent;
				Location l = m.getLocation();
				int level = Math.max((int) (Math.abs(l.getX() - l.getWorld().getSpawnLocation().getX())/640),(int) (Math.abs(l.getZ() - l.getWorld().getSpawnLocation().getZ())/640));
				if(level>=25 && level<40) {
					if(l.getBlock().getLightFromSky()>0 && l.getBlock().getType() != Material.FIRE && l.getBlock().getType() != Material.LAVA && l.getBlock().getType() != Material.STATIONARY_LAVA) {
						e.setCancelled(true);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent e) {
		if(config.getBoolean("Enable Dynamic Difficulty")) {
			if(e.getEntity() instanceof Creeper) {
				if(e.isCancelled()==false) {
					Entity ent = e.getEntity();
					LivingEntity m = (LivingEntity) ent;
					Location l = m.getLocation();
					int level = Math.max((int) (Math.abs(l.getX() - l.getWorld().getSpawnLocation().getX())/640),(int) (Math.abs(l.getZ() - l.getWorld().getSpawnLocation().getZ())/640));
					if(level>=20 && level<25) {
						for(Block b : e.blockList()) {
							if(randor.nextInt(4)==0) {
								b.setType(Material.FIRE);
							}
						}
					}
					else if(level>=25 && level<40) {
						for(Entity e2 : ent.getNearbyEntities(12, 12, 12)) {
							e2.setVelocity(l.toVector()
									.subtract(e2.getLocation().toVector()).normalize().multiply(3));
						}
					}
					if(level>=30 && level<40) {
						l.getWorld().spawnEntity(l, EntityType.CREEPER);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onArrowHit(ProjectileHitEvent event) {
		if(config.getBoolean("Enable Dynamic Difficulty")) {
		if(event.getEntity() instanceof Arrow) {
			Arrow a = (Arrow) event.getEntity();
			if(a.getShooter() instanceof Skeleton) {
					Entity ent = (Entity) a.getShooter();
					LivingEntity m = (LivingEntity) ent;
					Location l = m.getLocation();
					int level = Math.max((int) (Math.abs(l.getX() - l.getWorld().getSpawnLocation().getX())/640),(int) (Math.abs(l.getZ() - l.getWorld().getSpawnLocation().getZ())/640));
					if(level>=20 && level<40) {
					a.getWorld().createExplosion(a.getLocation(), (float) .1);
					if(a!=null) {
						a.remove();
					}
				}
			}	
		}
	}}
	
	public void betterEffectLooper() {
		if(config.getBoolean("Enable Dynamic Difficulty")) {
		if(!effectEnts.isEmpty()) {
		List<Entity> tempEntss = new ArrayList<Entity>(effectEnts);
		for(Entity e : tempEntss) {
			LivingEntity e2 = (LivingEntity) e;

			if(e != null) {
				if(!e.isDead()) {
					if(e instanceof Monster) {
						if(((Monster) e).getTarget()!=null) {
							String name = e2.getCustomName();
							if(name!=null) {
								if(name.equals("Inferno")||e.hasMetadata("Inferno")) {
									e2.getWorld().playSound(e2.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 1, 2);
								}
								else if(name.equals("Old Shadow")||e.hasMetadata("Old Shadow")) {
									if(e2.getLocation().getBlock().getLightLevel()>=12) {
										int radius = 4;
										Location loc = e2.getLocation();
						    			int cx = loc.getBlockX();
						    			int cy = loc.getBlockY();
						    			int cz = loc.getBlockZ();
						    			for (int x = cx - radius; x <= cx + radius; x++) {
						    				for (int z = cz - radius; z <= cz + radius; z++) {
						    					for (int y = (cy - radius); y < (cy + radius); y++) {
						    						double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + ((cy - y) * (cy - y));
						    							org.bukkit.block.Block b = new Location(loc.getWorld(), x, y, z).getBlock();
						    							if(b.getType()==Material.TORCH||b.getType()==Material.FIRE||b.getType()==Material.GLOWSTONE||b.getType()==Material.JACK_O_LANTERN||b.getType()==Material.REDSTONE_LAMP_ON||b.getType()==Material.SEA_LANTERN) {
						    								b.setType(Material.AIR);
						    							}
						    					}
						    				}
						    			}
									}
									else {
										List<Entity> ents = e2.getNearbyEntities(3, 3, 3);
										for(Entity ez : ents) {
											if(ez instanceof LivingEntity) {
												((LivingEntity) ez).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,120,1));
												((LivingEntity) ez).addPotionEffect(new PotionEffect(PotionEffectType.WITHER,120,2));
											}
										}
										e2.getWorld().spawnParticle(Particle.SUSPENDED, e2.getLocation().add(0, 1, 0), 220, 1,1,1,0.00f/*, m*/);
									}
								}
								else if(name.equals("Sadness")||e.hasMetadata("Sadness")) {
									if(e2.hasPotionEffect(PotionEffectType.SLOW)) {
										Entity e3 = ((Monster) e2).getTarget();
										if(e3 != null) {
											double blocksAway = Math.sqrt((Math.pow(Math.abs(e3.getLocation().getX()-e2.getLocation().getX()), 2) + (Math.pow(Math.abs(e3.getLocation().getZ()-e2.getLocation().getZ()), 2))));
											if(blocksAway <= 6) {
												if(randor.nextInt(8)==1) {
													e2.getWorld().playSound(e2.getLocation(), Sound.ENTITY_WOLF_GROWL, 1, 0);
												}
											}
											else {
												if(randor.nextInt(14)==1) {
													e2.getWorld().playSound(e2.getLocation(), Sound.ENTITY_WOLF_HURT, 1, 0);
												}
											}
										}
									}
								}
								else if(name.equals("Sherogath")||e.hasMetadata("Sherogath")) {
									e2.getWorld().spawnParticle(Particle.BARRIER, e2.getLocation() ,1);
									if(randor.nextInt(14)==1) {
										((Player) ((Monster) e2).getTarget()).playSound(((Player) ((Monster) e2).getTarget()).getLocation(), Sound.ENCHANT_THORNS_HIT, (float) .04, (float) .2);
									}
								}
							}
						}
						else {
							effectEnts.remove(e);
						}
					} 
					else if(e instanceof Player) {
						LivingEntity m = (LivingEntity) e;
						Location l = m.getLocation();
						int level = Math.max((int) (Math.abs(l.getX() - l.getWorld().getSpawnLocation().getX())/640),(int) (Math.abs(l.getZ() - l.getWorld().getSpawnLocation().getZ())/640));
						if(level>=130 && level<140) {
							if((l.getWorld().getTime()>12566&&l.getWorld().getTime()<22916) || l.getWorld().isThundering()) {
								
							}
							else if(l.getBlock().getLightFromSky()>0) {
								if(m.getFireTicks()<=20) {
								m.setFireTicks(999999);
								}
								m.getWorld().spawnParticle(Particle.SMOKE_LARGE, l, 2);
							}
						}
						else {
							effectEnts.remove(e);
						}
					}
					else {
						effectEnts.remove(e);
					}
				}
				else {
					effectEnts.remove(e);
				}
			}
			else {
				effectEnts.remove(e);
			}
		}
		}
		}
	}
	

	public static Location getBlockInFrontOfPlayer(LivingEntity entsa) {
		Vector inverseDirectionVec = entsa.getLocation().getDirection().normalize().multiply(1);
		return entsa.getLocation().add(inverseDirectionVec);
	}

	public void transferDimension(HashMap<Player, LivingEntity> m) {
		for (Player player2 : m.keySet()) {
			if (m.get(player2).getHealth() <= 1) {
				deHealth.remove(player2);
				if (m.get(player2) != null) {
					m.get(player2).remove();
				}
			} else {
				deHealth.put(player2, m.get(player2).getHealth());
				if (m.get(player2) != null) {
					m.get(player2).remove();
				}
			}
		}
		runningDemon = false;
	}

	
	public void doSpooks() {
		if (runningDemon == false) {
			runningDemon = true;
			HashMap<Player, LivingEntity> tempdeHealth = new HashMap<Player, LivingEntity>();
			for (Player p : deHealth.keySet()) {
				if (randor.nextInt(5) == 1) {
					Location loc = getBlockInFrontOfPlayer(p);
					// loc.setY(p.getLocation().getY());
					// loc.setDirection((p.getLocation().toVector().multiply(-1)));
					double newYaw = 0;
					double newPitch = 0;
					// pitch *-1
					// yaw - if negative add 180, if positive subtract 180
					if (p.getLocation().getYaw() < 0) {
						newYaw = p.getLocation().getYaw() + 180;
					} else {
						newYaw = p.getLocation().getYaw() - 180;
					}
					newPitch = p.getLocation().getPitch() * -1;
					Location jumpLoc = new Location(p.getWorld(), loc.getX(), p.getLocation().getY(), loc.getZ(),
							((float) newYaw), ((float) newPitch));
					p.setVelocity(new Vector(0, 0, 0));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 200));
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GHAST_HURT, 1, 2);
					p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 80, 2));
					Entity demon = p.getWorld().spawnEntity(jumpLoc, EntityType.HUSK);
					demon.setSilent(true);
					((LivingEntity) demon).setRemoveWhenFarAway(false);
					((LivingEntity) demon)
							.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 200));
					((LivingEntity) demon).setHealth(deHealth.get(p));
					EntityEquipment ee = ((LivingEntity) demon).getEquipment();
					ItemStack myAwesomeSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
					SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
					myAwesomeSkullMeta.setOwner("Creegn");
					myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);
					net.minecraft.server.v1_12_R1.ItemStack nmsStack5 = CraftItemStack.asNMSCopy(myAwesomeSkull);
					ee.setHelmet(myAwesomeSkull);

					ee.setItemInMainHand(new ItemStack(Material.CROPS));
					ee.setItemInOffHand(new ItemStack(Material.CROPS));
					((Monster) demon).setTarget(p);
					tempdeHealth.put(p, ((LivingEntity) demon));
				}
			}
			Bukkit.getScheduler().runTaskLater(this, () -> transferDimension(tempdeHealth), 90);
		}
	}
	
	public void doBeserkSounds() {
		List<Entity> deadBeserks = new ArrayList<Entity>();
		if (beserks.isEmpty()) {
			return;
		} else {
			for (Entity e : beserks) {
				if (e.isDead() || e == null || ((Monster) e).getTarget() == null) {
					deadBeserks.add(e);
				} else {
					e.getWorld().playSound(e.getLocation(), Sound.ENTITY_GHAST_HURT, 1, 1);
				}
			}
		}
		for (Entity e2 : deadBeserks) {
			beserks.remove(e2);
		}
	}

	public double[] randomSpherePoint(double x0, double y0, double z0, int radius) {
		double u = Math.random();
		double v = Math.random();
		double theta = 2 * Math.PI * u;
		double phi = Math.acos(2 * v - 1);
		double x = x0 + (radius * Math.sin(phi) * Math.cos(theta));
		double y = y0 + (radius * Math.sin(phi) * Math.sin(theta));
		double z = z0 + (radius * Math.cos(phi));
		double[] points = { x, y, z };
		return points;
	}

	public void runColor(double r, double g, double b, Location l) {
		//List<PacketPlayOutWorldParticles> packets = new ArrayList<PacketPlayOutWorldParticles>();
		for (int zz = 0; zz < 3; zz++) {
			for (int i = 0; i < 420; i++) {
				double x = l.getX() + (randor.nextInt(3) - 1);
				double y = l.getY() + (randor.nextInt(3) - 1);
				double z = l.getZ() + (randor.nextInt(3) - 1);
				double[] points = randomSpherePoint(x, y, z, 2);
				x = points[0];
				y = points[1];
				z = points[2];
				l.getWorld().spawnParticle(Particle.REDSTONE, x, y, z, 0, r, g, b, 1);
			}
		}
	}
	
	public void runColor2(double r, double g, double b, Location l) {
		//List<PacketPlayOutWorldParticles> packets = new ArrayList<PacketPlayOutWorldParticles>();
		for (int zz = 0; zz < 3; zz++) {
			for (int i = 0; i < 300; i++) {
				double x = l.getX() + ((randor.nextInt(2)*getNegOrPos())/10.0);
				double y = l.getY() + ((randor.nextInt(2)*getNegOrPos())/10.0);
				double z = l.getZ() + ((randor.nextInt(2)*getNegOrPos())/10.0);
				double[] points = randomSpherePoint(x, y, z, 2);
				x = points[0];
				y = points[1];
				z = points[2];
				l.getWorld().spawnParticle(Particle.REDSTONE, x, y, z, 0, r, g, b, 1);
			}
		}
	}
	
	public void runColor3(double r, double g, double b, Location l) {
		//List<PacketPlayOutWorldParticles> packets = new ArrayList<PacketPlayOutWorldParticles>();
		for (int zz = 0; zz < 3; zz++) {
			for (int i = 0; i < 30; i++) {
				double x = l.getX() + (randor.nextInt(12)*getNegOrPos());
				double y = l.getY() + (randor.nextInt(12)*getNegOrPos());
				double z = l.getZ() + (randor.nextInt(12)*getNegOrPos());
				double[] points = randomSpherePoint(x, y, z, 2);
				x = points[0];
				y = points[1];
				z = points[2];
				l.getWorld().spawnParticle(Particle.REDSTONE, x, y, z, 0, r, g, b, 1);
			}
		}
	}
	
	public double getNegOrPos() {
		if(randor.nextInt(2)==1) {
			return 1;
		}
		else {
			return -1;
		}
	}

	@EventHandler
	public void testShoot(EntityShootBowEvent v) {
		if(config.getBoolean("Enable Dynamic Difficulty")) {
		if (v.getEntity().getName().equals("Fireball") || getMMeta(v.getEntity()).equals("Fireball")) {
			Vector v2 = v.getProjectile().getVelocity();
			v.getProjectile().remove();
			v.getEntity().launchProjectile(SmallFireball.class, v2);
		} else if (v.getEntity().getName().equals("Ghastly") || getMMeta(v.getEntity()).equals("Ghastly")) {
			Vector v2 = v.getProjectile().getVelocity();
			v.getProjectile().remove();
			v.getEntity().launchProjectile(Fireball.class, v2);
		} else if (v.getEntity().getName().equals("Volley") || getMMeta(v.getEntity()).equals("Volley")) {
			Vector v2 = v.getProjectile().getVelocity();
			v.getProjectile().remove();
			v.getEntity().launchProjectile(Arrow.class, v2);
			v.getEntity().launchProjectile(Arrow.class, v2);
			v.getEntity().launchProjectile(Arrow.class, v2);
			v.getEntity().launchProjectile(Arrow.class, v2);
			v.getEntity().launchProjectile(Arrow.class, v2);
		} else if (v.getEntity().getName().equals("Necromancer") || getMMeta(v.getEntity()).equals("Necromancer")) {
			Vector v2 = v.getProjectile().getVelocity();
			v.getProjectile().remove();
			v.getEntity().launchProjectile(WitherSkull.class, v2);
		}
		}
	}
	
	@EventHandler
	public void wasHit(EntityDamageByEntityEvent event) {
		if(config.getBoolean("Enable Dynamic Difficulty")) {
		boolean meh = false;
		if (event.getEntity() instanceof Player
				&& (event.getDamager().getCustomName() != null || (!getMMeta(event.getDamager()).equals("null")))) {
			if (event.getDamager().getCustomName().equals("Screamer")
					|| getMMeta(event.getDamager()).equals("Screamer")) {
				event.getDamager().getWorld().playSound(event.getDamager().getLocation(), Sound.ENTITY_ENDERMEN_SCREAM,
						((float) 1), ((float) .1));
			}
			if (randor.nextInt(5) == 1) {
				if (event.getDamager().getCustomName().equals("Converter")
						|| getMMeta(event.getDamager()).equals("Converter")) {
					if (!entityEffects.convertE.containsKey((Player) event.getEntity())) {
						// console.sendMessage("Hey");
						entityEffects.convertE.put((Player) event.getEntity(), 0);
					}
				}
			}
			if ((event.getDamager().getCustomName().equals("Keeper") || getMMeta(event.getDamager()).equals("Keeper"))||event.getDamager().getCustomName().equals(".") || getMMeta(event.getDamager()).equals(".")) {
				Player p = (Player) event.getEntity();
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 160, 200, false, false));
				Location el = p.getEyeLocation();
				runColor2(0.001, 0.001, 0.001, el);
			}
			if (event.getDamager().getCustomName().equals("Linear") || getMMeta(event.getDamager()).equals("Linear")) {
				LivingEntity e = (LivingEntity) event.getDamager();
				int dra = 0;
				int dmga = 0;
				if (e.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
					dra = e.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier() + 1;
					e.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
					e.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999, dra));
				} else {
					e.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999, 1));
				}
				if (e.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
					dmga = e.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier() + 1;
					e.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
					e.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, dmga));
				} else {
					e.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 1));
				}
			}
			if (event.getDamager().getCustomName().equals("Living Armor")
					|| getMMeta(event.getDamager()).equals("Living Armor")) {
				int choosed = randor.nextInt(5);
				if (choosed == 0
						&& ((Monster) event.getDamager()).getEquipment().getBoots().getType() == Material.AIR) {
					((Monster) event.getDamager()).getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
				} else if (choosed == 1
						&& ((Monster) event.getDamager()).getEquipment().getLeggings().getType() == Material.AIR) {
					((Monster) event.getDamager()).getEquipment()
							.setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
				} else if (choosed == 2
						&& ((Monster) event.getDamager()).getEquipment().getHelmet().getType() == Material.AIR) {
					((Monster) event.getDamager()).getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
				} else if (choosed == 3
						&& ((Monster) event.getDamager()).getEquipment().getChestplate().getType() == Material.AIR) {
					((Monster) event.getDamager()).getEquipment()
							.setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
				} else if (choosed == 4 && ((Monster) event.getDamager()).getEquipment().getItemInMainHand()
						.getType() == Material.AIR) {
					((Monster) event.getDamager()).getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
				}
				if (randor.nextInt(2) == 1) {
					entityEffects.armorHit((Player) event.getEntity());
				}
			}
			if (event.getDamager().getCustomName().equals("Cursed Armor")
					|| getMMeta(event.getDamager()).equals("Cursed Armor")) {
				Monster m = (Monster) event.getDamager();
				Player p = (Player) event.getEntity();
				ItemStack[] armor = p.getInventory().getArmorContents();
				for (ItemStack i2 : armor) {
					if (i2 != null) {
						if (i2.getType() != null) {
							if (i2.getType() != Material.AIR) {
								p.getWorld().dropItemNaturally(m.getLocation(), i2);
							}
						}
					}
				}
				p.getInventory().setArmorContents(m.getEquipment().getArmorContents());
				m.getEquipment().clear();
				m.remove();

			}

			if (event.getEntity() instanceof Monster && event.getDamager() instanceof Player) {
				if (event.getEntity().getCustomName() == null || getMMeta(event.getEntity()).equals("null")) {

				} else {

					if (event.getEntity().getCustomName().equals("Beamer")
							|| getMMeta(event.getEntity()).equals("Beamer")) {
						event.getEntity().setVelocity(event.getDamager().getLocation().toVector()
								.subtract(event.getEntity().getLocation().toVector()).normalize().multiply(2));
					} else if (event.getEntity().getCustomName().equals("Ender")
							|| getMMeta(event.getEntity()).equals("Ender")) {
						if (randor.nextInt(3) == 1) {
							if (event.getDamager() instanceof Player) {
								event.getEntity().teleport(getBlockBehindPlayer(((Player) event.getDamager())));
								event.getEntity().getWorld().playSound(event.getEntity().getLocation(),
										Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1);
							}
						}
					} else if (event.getEntity().getCustomName().equals("Vengeance")
							|| getMMeta(event.getEntity()).equals("Vengeance")) {
						if (randor.nextInt(3) == 1) {
							((LivingEntity) event.getDamager()).damage(event.getDamage());
						}
					} else if (event.getEntity().getCustomName().equals("Beserk")
							|| getMMeta(event.getEntity()).equals("Beserk")) {
						if (randor.nextInt(2) == 1) {
							((LivingEntity) event.getEntity()).damage(event.getDamage() / 2);
						}
					} else if (event.getEntity().getCustomName().equals("Spawner 2")
							|| getMMeta(event.getEntity()).equals("Spawner 2")) {
						if (randor.nextInt(4) == 1) {
							event.getEntity().getWorld().spawnEntity(event.getDamager().getLocation(),
									EntityType.SILVERFISH);
							event.getEntity().getWorld().spawnEntity(event.getDamager().getLocation(),
									EntityType.SILVERFISH);
						}
					}
				}
			}

			else if (event.getDamager() instanceof Monster && event.getEntity() instanceof Player) {
				if (event.getDamager().getCustomName() == null || getMMeta(event.getDamager()).equals("null")) {
				} else {
					if (event.getDamager().getCustomName().equals("Webber")
							|| getMMeta(event.getDamager()).equals("Webber")) {
						if (randor.nextInt(2) == 1) {
							event.getEntity().getLocation().getBlock().setType(Material.WEB);
							event.getEntity().getLocation().add(0, 1, 0).getBlock().setType(Material.WEB);
							Location loc = event.getEntity().getLocation();
							if (randor.nextInt(15) == 1) {
								event.getDamager().getWorld().spawnEntity(event.getDamager().getLocation(),
										EntityType.CAVE_SPIDER);
							}
							int radius = 7;
							int cx = loc.getBlockX();
							int cy = loc.getBlockY();
							int cz = loc.getBlockZ();
							for (int x = cx - radius; x <= cx + radius; x++) {
								for (int z = cz - radius; z <= cz + radius; z++) {
									for (int y = (cy - radius); y < (cy + radius); y++) {
										double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + ((cy - y) * (cy - y));

										if (dist < radius * radius) {
											Location l = new Location(loc.getWorld(), x, y + 2, z);
											if (randor.nextInt(7) == 1) {
												if (l.getBlock().getType() == Material.AIR) {
													l.getBlock().setType(Material.WEB);
												}
											}
										}
									}

								}
							}
						}
					} else if (event.getDamager().getCustomName().equals("Launcher")
							|| getMMeta(event.getDamager()).equals("Launcher")) {
						event.getEntity().setVelocity(new Vector(event.getEntity().getVelocity().getX(), 1.2,
								event.getEntity().getVelocity().getZ()));
					} else if (event.getDamager().getCustomName().equals("Poisonous")
							|| getMMeta(event.getDamager()).equals("Poisonous")) {
						if (randor.nextInt(2) == 1) {
							((LivingEntity) event.getEntity())
									.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));
						}
					} else if (event.getDamager().getCustomName().equals("Blinding")
							|| getMMeta(event.getDamager()).equals("Blinding")) {
						if (randor.nextInt(2) == 1) {
							((LivingEntity) event.getEntity())
									.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1));
						}
					} else if (event.getDamager().getCustomName().equals("Withering")
							|| getMMeta(event.getDamager()).equals("Withering")) {
						if (randor.nextInt(2) == 1) {
							((LivingEntity) event.getEntity())
									.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
						}
					} else if (event.getDamager().getCustomName().equals("Theif")
							|| getMMeta(event.getDamager()).equals("Theif")) {
						if (randor.nextInt(6) == 1) {
							if (event.getEntity() instanceof Player) {
								Player p = (Player) event.getEntity();
								ItemStack items = p.getInventory().getItemInMainHand();
								event.getDamager().getWorld().playSound(event.getDamager().getLocation(),
										Sound.ENTITY_EGG_THROW, 1, 0);
								event.getDamager().getWorld().dropItemNaturally(event.getDamager().getLocation(),
										items);
								p.getInventory().getItemInMainHand().setType(Material.AIR);
							}
						}
					} else if (event.getDamager().getCustomName().equals("Quicksand")
							|| getMMeta(event.getDamager()).equals("Quicksand")) {
						if (randor.nextInt(2) == 1) {
							((LivingEntity) event.getEntity())
									.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 2));
						}
					} else if (event.getDamager().getCustomName().equals("Sapper")
							|| getMMeta(event.getDamager()).equals("Sapper")) {
						if (randor.nextInt(2) == 1) {
							((LivingEntity) event.getEntity())
									.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 120, 2));
						}
					} else if (event.getDamager().getCustomName().equals("Ender")
							|| getMMeta(event.getDamager()).equals("Ender")) {
						if (randor.nextInt(3) == 1) {
							if (event.getEntity() instanceof Player) {
								event.getDamager().getWorld().playSound(event.getDamager().getLocation(),
										Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1);
								event.getDamager().teleport(getBlockBehindPlayer(((Player) event.getEntity())));
							}
						}
					} else if (event.getDamager().getCustomName().equals("Lifesteal")
							|| getMMeta(event.getDamager()).equals("Lifesteal")) {
						if (randor.nextInt(2) == 1) {
							if (event.getEntity() instanceof Player) {
								event.getDamager().getWorld().spawnParticle(Particle.DAMAGE_INDICATOR,
										event.getDamager().getLocation().getX() - .5,
										event.getDamager().getLocation().getY(),
										event.getDamager().getLocation().getZ() - .5, 20,
										event.getDamager().getLocation().getX() + .5,
										event.getDamager().getLocation().getY() + 2,
										event.getDamager().getLocation().getZ() + .5);
								if (((LivingEntity) event.getDamager()).getHealth()
										+ event.getDamage() > ((LivingEntity) event.getDamager()).getMaxHealth()) {
									((LivingEntity) event.getDamager())
											.setHealth(((LivingEntity) event.getDamager()).getMaxHealth());
								} else {
									((LivingEntity) event.getDamager()).setHealth(
											((LivingEntity) event.getDamager()).getHealth() + event.getDamage());
								}
							}
						}
					} else if (event.getDamager().getCustomName().equals("Lightning")
							|| getMMeta(event.getDamager()).equals("Lightning")) {
						if (randor.nextInt(4) == 1) {
							((LivingEntity) event.getEntity()).getWorld()
									.strikeLightning(event.getEntity().getLocation());
						}
					} else if (event.getDamager().getCustomName().equals("Weakness")
							|| getMMeta(event.getDamager()).equals("Weakness")) {
						if (randor.nextInt(2) == 1) {
							((LivingEntity) event.getEntity())
									.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 60, 1));
						}
					} else if (event.getDamager().getCustomName().equals("Spawner 1")
							|| getMMeta(event.getDamager()).equals("Spawner 1")) {
						if (randor.nextInt(5) == 1) {
							event.getDamager().getWorld().spawnEntity(event.getDamager().getLocation(),
									EntityType.SILVERFISH);
							event.getDamager().getWorld().spawnEntity(event.getDamager().getLocation(),
									EntityType.SILVERFISH);
						}
					} else if (event.getDamager().getCustomName().equals("Molten")
							|| getMMeta(event.getDamager()).equals("Molten")) {
						if (randor.nextInt(2) == 1) {
							((LivingEntity) event.getEntity()).setFireTicks(60);
						}
					} else if (event.getDamager().getCustomName().equals("Gravity")
							|| getMMeta(event.getDamager()).equals("Gravity")) {
						if (randor.nextInt(3) == 1) {
							((LivingEntity) event.getEntity())
									.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 60, 2));
						}
					} else if (event.getDamager().getCustomName().equals("Confusing")
							|| getMMeta(event.getDamager()).equals("Confusing")) {
						if (randor.nextInt(2) == 1) {
							((LivingEntity) event.getEntity())
									.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60, 2));
						}
					}
				}
			}
		}
		if (event.getDamager() instanceof Player
				&& (event.getEntity().getCustomName() != null || (!getMMeta(event.getEntity()).equals("null")))
				&& event.getEntity() instanceof Monster) {
			if ((event.getDamager() instanceof Player) && (event.getEntity().getCustomName().equals("Watcher")
					|| getMMeta(event.getEntity()).equals("Watcher"))) {
				entityEffects.watcherE.remove(event.getEntity());
			}
			if ((event.getDamager() instanceof Player) && (event.getEntity().getCustomName().equals("Sadness")
					|| getMMeta(event.getEntity()).equals("Sadness"))) {
				if(randor.nextInt(3)==1) {
				event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_WOLF_DEATH, 1, 0);
				}
			}
		}
		}
	}
	
	public static Location getBlockBehindPlayer(Player player) {
		Vector inverseDirectionVec = player.getLocation().getDirection().normalize().multiply(-1);
		return player.getLocation().add(inverseDirectionVec);
	}
	
	public String getMMeta(Entity e) {
		if (e.hasMetadata(".")) {
			return ".";
		}
		if (e.hasMetadata("Beamer")) {
			return "Beamer";
		}
		if (e.hasMetadata("Webber")) {
			return "Webber";
		}
		if (e.hasMetadata("Ender")) {
			return "Ender";
		}
		if (e.hasMetadata("Old Shadow")) {
			return "Old Shadow";
		}
		if (e.hasMetadata("Sadness")) {
			return "Sadness";
		}
		if (e.hasMetadata("Sherogath")) {
			return "Sherogath";
		}
		if (e.hasMetadata("Vengeance")) {
			return "Vengeance";
		}
		if (e.hasMetadata("Beserk")) {
			return "Beserk";
		}
		if (e.hasMetadata("Spawner 2")) {
			return "Spawner 2";
		}
		if (e.hasMetadata("Launcher")) {
			return "Launcher";
		}
		if (e.hasMetadata("Poisonous")) {
			return "Poisonous";
		}
		if (e.hasMetadata("Blinding")) {
			return "Blinidng";
		}
		if (e.hasMetadata("Withering")) {
			return "Withering";
		}
		if (e.hasMetadata("Theif")) {
			return "Theif";
		}
		if (e.hasMetadata("Quicksand")) {
			return "Quicksand";
		}
		if (e.hasMetadata("Sapper")) {
			return "Sapper";
		}
		if (e.hasMetadata("Lifesteal")) {
			return "Lifesteal";
		}
		if (e.hasMetadata("Lightning")) {
			return "Lightning";
		}
		if (e.hasMetadata("Weakness")) {
			return "Weakness";
		}
		if (e.hasMetadata("Spawner 1")) {
			return "Spawner 1";
		}
		if (e.hasMetadata("Molten")) {
			return "Molten";
		}
		if (e.hasMetadata("Gravity")) {
			return "Gravity";
		}
		if (e.hasMetadata("Confusing")) {
			return "Confusing";
		}
		if (e.hasMetadata("Fireball")) {
			return "Fireball";
		}
		if (e.hasMetadata("Volley")) {
			return "Volley";
		}
		if (e.hasMetadata("Necromancer")) {
			return "Necromancer";
		}
		if (e.hasMetadata("Armored")) {
			return "Armored";
		}
		if (e.hasMetadata("Cloaked")) {
			return "Cloaked";
		}
		if (e.hasMetadata("Sprint")) {
			return "Sprint";
		}
		if (e.hasMetadata("Demon")) {
			return "Demon";
		}
		if (e.hasMetadata("Beserker")) {
			return "Beserker";
		}
		if (e.hasMetadata("Screamer")) {
			return "Screamer";
		}
		if (e.hasMetadata("Converter")) {
			return "Converter";
		}
		if (e.hasMetadata("Watcher")) {
			return "Watcher";
		}
		if (e.hasMetadata("Ghastly")) {
			return "Ghastly";
		}
		if (e.hasMetadata("Living Armor")) {
			return "Living Armor";
		}
		if (e.hasMetadata("Keeper")) {
			return "Keeper";
		}
		if (e.hasMetadata("repeeK")) {
			return "repeeK";
		}
		if (e.hasMetadata("Inferno")) {
			return "Inferno";
		}
		if (e.hasMetadata("Runner")) {
			return "Runner";
		}
		if (e.hasMetadata("Cursed Armor")) {
			return "Cursed Armor";
		}
		if (e.hasMetadata("Shadow")) {
			return "Shadow";
		}
		if (e.hasMetadata("Infinity")) {
			return "Infinity";
		}
		if (e.hasMetadata("Mother")) {
			return "Mother";
		}
		if (e.hasMetadata("Speedster")) {
			return "Speedster";
		}
		if (e.hasMetadata("Linear")) {
			return "Linear";
		}
		return "null";
	}
	
	@EventHandler
	public void doSpook(EntityTargetEvent z) {
		if (z.getEntity().getCustomName() != null || (!getMMeta(z.getEntity()).equals("null"))) {
			if ((z.getEntity().getCustomName().equals("Demon") || getMMeta(z.getEntity()).equals("Demon"))
					&& z.getTarget() instanceof Player) {
				deHealth.put(((Player) z.getTarget()), ((LivingEntity) z.getEntity()).getMaxHealth());
				z.getEntity().remove();
			} else if ((z.getEntity().getCustomName().equals("Beserker")
					|| getMMeta(z.getEntity()).equals("Beserker"))) {
				Entity mob1 = z.getEntity().getWorld().spawnEntity(z.getEntity().getLocation(), EntityType.PIG_ZOMBIE);
				mob1.setCustomName("Beserkerr");
				((Monster) mob1).setTarget((LivingEntity) z.getTarget());
				beserks.add((Entity) mob1);
				((LivingEntity) mob1).removePotionEffect(PotionEffectType.INVISIBILITY);
				z.getEntity().remove();
			} else if ((z.getEntity().getCustomName().equals("Beserkerr")
					|| getMMeta(z.getEntity()).equals("Beserkerr"))) {
				beserks.add((Entity) z.getEntity());
			} else if ((z.getEntity().getCustomName().equals("Screamer")
					|| getMMeta(z.getEntity()).equals("Screamer"))) {
				dressScreamer.dressDGolem(((LivingEntity) z.getEntity()));
			} else if ((z.getEntity().getCustomName().equals("Watcher") || getMMeta(z.getEntity()).equals("Watcher"))
					&& z.getTarget() instanceof Player) {
				entityEffects.watcherE.put(z.getEntity(), (Player) z.getTarget());
				((LivingEntity) z.getEntity())
						.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 999999, 200));
				dressWatcher.dressDGolem((LivingEntity) z.getEntity());
			}
			else if ((z.getEntity().getCustomName().equals("Sherogath") || getMMeta(z.getEntity()).equals("Sherogath"))
					&& z.getTarget() instanceof Player) {
				effectEnts.add(z.getEntity());
			}
			else if ((z.getEntity().getCustomName().equals("Sadness") || getMMeta(z.getEntity()).equals("Sadness"))
					&& z.getTarget() instanceof Player) {
				((Player) z.getTarget()).playSound(z.getTarget().getLocation(), Sound.ENTITY_SKELETON_HORSE_DEATH, 100, 0);
				effectEnts.add(z.getEntity());
			}
			else if ((z.getEntity().getCustomName().equals("Old Shadow") || getMMeta(z.getEntity()).equals("Old Shadow"))
					&& z.getTarget() instanceof Player) {
				effectEnts.add(z.getEntity());
			}else if ((z.getEntity().getCustomName().equals("Inferno") || getMMeta(z.getEntity()).equals("Inferno"))
					&& z.getTarget() instanceof Player) {
				entityEffects.infernoE.put(z.getEntity(), (Player) z.getTarget());
			} else if ((z.getEntity().getCustomName().equals("Runner") || getMMeta(z.getEntity()).equals("Runner"))
					&& z.getTarget() instanceof Player) {
				Entity e = z.getEntity();
				Entity mob1 = e.getWorld().spawnEntity(e.getLocation(), EntityType.ZOMBIE);
				mob1.setCustomName("Runren");
				((Monster) mob1).setMetadata("Runren", new FixedMetadataValue(this, 0));
				///////
				((Monster) mob1).getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(32);
				mob1.setSilent(true);
				((LivingEntity) mob1).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
				dressRunner.dressDGolem((LivingEntity) mob1);
				e.remove();
			} else if ((z.getEntity().getCustomName().equals("Runren") || getMMeta(z.getEntity()).equals("Runren"))
					&& z.getTarget() instanceof Player) {
				entityEffects.stalkerE.add(z.getEntity());
			} else if ((z.getEntity().getCustomName().equals("Shadow") || getMMeta(z.getEntity()).equals("Shadow"))
					&& z.getTarget() instanceof Player) {
				if (!entityEffects.shadowAdd.contains(z.getEntity())) {
					entityEffects.shadowAdd.add(z.getEntity());
				}
			} else if ((z.getEntity().getCustomName().equals("Quicksand")
					|| getMMeta(z.getEntity()).equals("Quicksand")) && z.getTarget() instanceof Player) {
				try {
					if (!entityEffects.quicksandE.containsKey(z.getEntity())) {
						entityEffects.quicksandE.put(z.getEntity(), (Player) z.getTarget());
					}
				} catch (Exception e) {

				}
			} else if ((z.getEntity().getCustomName().equals("Poisonous")
					|| getMMeta(z.getEntity()).equals("Poisonous")) && z.getTarget() instanceof Player) {
				try {
					if (!entityEffects.poisonE.containsKey(z.getEntity())) {
						entityEffects.poisonE.put(z.getEntity(), (Player) z.getTarget());
					}
				} catch (Exception e) {

				}
			} else if ((z.getEntity().getCustomName().equals("Withering")
					|| getMMeta(z.getEntity()).equals("Withering")) && z.getTarget() instanceof Player) {
				try {
					if (!entityEffects.witheringE.containsKey(z.getEntity())) {
						entityEffects.witheringE.put(z.getEntity(), (Player) z.getTarget());
					}
				} catch (Exception e) {

				}
			} else if ((z.getEntity().getCustomName().equals("Molten") || getMMeta(z.getEntity()).equals("Molten"))
					&& z.getTarget() instanceof Player) {
				try {
					if (!entityEffects.moltenE.containsKey(z.getEntity())) {
						entityEffects.moltenE.put(z.getEntity(), (Player) z.getTarget());
					}
				} catch (Exception e) {

				}
			} else if ((z.getEntity().getCustomName().equals("Confusing")
					|| getMMeta(z.getEntity()).equals("Confusing")) && z.getTarget() instanceof Player) {
				try {
					if (!entityEffects.confusingE.containsKey(z.getEntity())) {
						entityEffects.confusingE.put(z.getEntity(), (Player) z.getTarget());
					}
				} catch (Exception e) {

				}
			} else if ((z.getEntity().getCustomName().equals("Volley") || getMMeta(z.getEntity()).equals("Volley"))
					&& z.getTarget() instanceof Player) {
				try {
					if (!entityEffects.volleyE.containsKey(z.getEntity())) {
						entityEffects.volleyE.put(z.getEntity(), (Player) z.getTarget());
					}
				} catch (Exception e) {

				}
			}
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		if(config.getBoolean("Enable Dynamic Difficulty")) {
		Entity ent = event.getPlayer();
		Location l = ent.getLocation();
		int level = Math.abs(Math.max((int) (Math.abs(l.getX() - l.getWorld().getSpawnLocation().getX())/640),(int) (Math.abs(l.getZ() - l.getWorld().getSpawnLocation().getZ())/640)));
		boolean meh = false;
		if(event.getPlayer().getWorld().getTime()>12566&&event.getPlayer().getWorld().getTime()<22916) {
			if(level >= 103 && level < 110) {
		if(event.getTo().getBlock().getBiome()==Biome.ROOFED_FOREST||event.getTo().getBlock().getBiome()==Biome.MUTATED_ROOFED_FOREST) {
			Material main = event.getPlayer().getInventory().getItemInMainHand().getType();
			Material off = event.getPlayer().getInventory().getItemInOffHand().getType();
			if(!(off==Material.TORCH||main==Material.TORCH||main==Material.JACK_O_LANTERN||off==Material.JACK_O_LANTERN||off==Material.GLOWSTONE||main==Material.GLOWSTONE||main==Material.LAVA_BUCKET||off==Material.LAVA_BUCKET||main==Material.GLOWSTONE_DUST||off==Material.GLOWSTONE_DUST)) {
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999, 127));
			}
			else {
				event.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
			}
		}
		else if((!(event.getTo().getBlock().getBiome()==Biome.ROOFED_FOREST||event.getTo().getBlock().getBiome()==Biome.MUTATED_ROOFED_FOREST))&&((event.getFrom().getBlock().getBiome()==Biome.ROOFED_FOREST||event.getFrom().getBlock().getBiome()==Biome.MUTATED_ROOFED_FOREST))) {
			event.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
		}
		
		else if(event.getPlayer().hasPotionEffect(PotionEffectType.BLINDNESS)) {
		if(event.getPlayer().getPotionEffect(PotionEffectType.BLINDNESS).getAmplifier()==127) {
			event.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
		}
		}
			}
			else if(level >= 110 && level < 120) {
				Material main = event.getPlayer().getInventory().getItemInMainHand().getType();
				Material off = event.getPlayer().getInventory().getItemInOffHand().getType();
				if((!(off==Material.TORCH||main==Material.TORCH||main==Material.JACK_O_LANTERN||off==Material.JACK_O_LANTERN||off==Material.GLOWSTONE||main==Material.GLOWSTONE||main==Material.LAVA_BUCKET||off==Material.LAVA_BUCKET||main==Material.GLOWSTONE_DUST||off==Material.GLOWSTONE_DUST))&&(event.getPlayer().getLocation().getBlock().getLightFromBlocks()<=0)) {
					event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999, 127));
				}
				else {
					event.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
				}
			}
		
		}
		else if(event.getPlayer().hasPotionEffect(PotionEffectType.BLINDNESS)) {
			if(event.getPlayer().getPotionEffect(PotionEffectType.BLINDNESS).getAmplifier()==127) {
				event.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
			}
		}
		if(level >= 130 && level < 140) {
			if(!effectEnts.contains(event.getPlayer())) {
			effectEnts.add(event.getPlayer());
			}
			if(flameblocks.contains(event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType()) && (!event.getPlayer().getWorld().isThundering())) {
				event.getPlayer().getLocation().getBlock().setType(Material.FIRE);
			}
		}
	}
	}
	
	public void talk() {
		if(config.getBoolean("Enable Dynamic Difficulty")) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			Location l = p.getLocation();
			int level = Math.abs(Math.max((int) (Math.abs(l.getX() - l.getWorld().getSpawnLocation().getX())/640),(int) (Math.abs(l.getZ() - l.getWorld().getSpawnLocation().getZ())/640)));
			if(level >= 150 && level < 160) {
			if(randor.nextInt(3)==0) {
				p.sendMessage("" + words.generateSentence());
			}
			}
		}
		}
	}
	
}