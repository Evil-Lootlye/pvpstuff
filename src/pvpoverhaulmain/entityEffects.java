package pvpoverhaulmain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class entityEffects extends pvpmain{

	public static HashMap<Player, Entity> particleEf = new HashMap<Player, Entity>();
	public static HashMap<Entity, Player> poisonE = new HashMap<Entity, Player>();
	public static HashMap<Entity, Player> poisonT = new HashMap<Entity, Player>();
	public static HashMap<Entity, Player> witheringE = new HashMap<Entity, Player>();
	public static HashMap<Entity, Player> witheringT = new HashMap<Entity, Player>();
	public static HashMap<Entity, Player> quicksandE = new HashMap<Entity, Player>();
	public static HashMap<Entity, Player> quicksandT = new HashMap<Entity, Player>();
	public static HashMap<Entity, Player> moltenE = new HashMap<Entity, Player>();
	public static HashMap<Entity, Player> moltenT = new HashMap<Entity, Player>();
	public static HashMap<Entity, Player> confusingE = new HashMap<Entity, Player>();
	public static HashMap<Entity, Player> confusingT = new HashMap<Entity, Player>();
	public static HashMap<Entity, Player> volleyE = new HashMap<Entity, Player>();
	public static HashMap<Entity, Player> volleyT = new HashMap<Entity, Player>();
	//public static HashMap<Entity, Player> stalkerE = new HashMap<Entity, Player>();
	//public static HashMap<Entity, Player> stalkerT = new HashMap<Entity, Player>();
	public static List<Entity> stalkerE = new ArrayList<Entity>();
	public static HashMap<Player, Integer> convertE = new HashMap<Player, Integer>();
	public static HashMap<Entity, Player> watcherE = new HashMap<Entity, Player>();
	public static HashMap<Entity, Player> watcherT = new HashMap<Entity, Player>();
	public static List<Entity> shadowE = new ArrayList<Entity>();
	public static List<Entity> shadowAdd = new ArrayList<Entity>();
	public static HashMap<Player, Integer> livingAE = new HashMap<Player, Integer>();
	public static HashMap<Entity, Player> infernoE = new HashMap<Entity, Player>();
	public static HashMap<Entity, Player> infernoT = new HashMap<Entity, Player>();
	public static Entity keeperE = null;
	
	public static boolean runningTemplate = false;
	public static boolean runningParticleE = false;
	public static boolean runningPoisonE = false;
	public static boolean runningconvertE = false;
	public static boolean runningstalkerE = false;
	public static boolean runningshadowE = false;
	
	public static Random rand = new Random();
	
	//particleExample
		public static void particleE(Entity effects, Entity effectors) {
			if(particleEf.isEmpty()) {
				return;
			}
			if(runningParticleE==false) {
				runningParticleE=true;
				HashMap<Player, LivingEntity> tempParticleE = new HashMap<Player, LivingEntity>();
				for(Player p : particleEf.keySet()) {
					if(particleEf.get(p).isDead()||particleEf.get(p)==null) {
						tempParticleE.put(p, ((LivingEntity) particleEf.get(p)));
					}
					else {
						p.spawnParticle(Particle.DRAGON_BREATH, p.getLocation(), 20);
					}
				}
					doParticleE(tempParticleE);
			}
	}
		public static void doParticleE(HashMap<Player, LivingEntity> m) {
			for(Player player2 : m.keySet()) {
					particleEf.remove(player2);
					if(m.get(player2)!=null) {
						m.get(player2).remove();
					}
				}
			runningParticleE=false;
		}
		//
		
		//poisonous
		
		public static void poisonous(Entity effects, Entity effectors) {
			try {
			if(poisonE.isEmpty()) {
				return;
			}
				for(Entity p : poisonE.keySet()) {
					if(poisonE.get(p).isDead()||poisonE.get(p)==null||p.isDead()||p==null||(!(poisonE.get(p).isOnline()))) {
						poisonT.put(p, poisonE.get(p));
					}
					else {
						if(rand.nextInt(3)==1) {
							p.getWorld().spawnParticle(Particle.SLIME, p.getLocation(), 30);
						}
						if(rand.nextInt(6)==1) {
							 AreaEffectCloud area = (AreaEffectCloud) p.getWorld().spawnEntity(p.getLocation(), EntityType.AREA_EFFECT_CLOUD);
							 area.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 20, 0), true);
							 area.setColor(Color.OLIVE);
						}
					}
				}
				if(!poisonT.isEmpty()) {
				for(Entity p : poisonT.keySet()) {
					poisonE.remove(p);
				}
				poisonT.clear();
				}
			}
			catch(Exception e) {
				
			}
	}
		
		public static void withering(Entity effects, Entity effectors) {
			try {
			if(witheringE.isEmpty()) {
				return;
			}
				for(Entity p : witheringE.keySet()) {
					if(witheringE.get(p).isDead()||witheringE.get(p)==null||p.isDead()||p==null||(!(witheringE.get(p).isOnline()))) {
						witheringT.put(p, witheringE.get(p));
					}
					else {
						if(rand.nextInt(3)==1) {
							p.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, p.getLocation().add(0,1,0), 15);
						}
					}
				}
				if(!witheringT.isEmpty()) {
				for(Entity p : witheringT.keySet()) {
					witheringE.remove(p);
				}
				witheringT.clear();
				}
			}
			catch(Exception e) {
				
			}
	}
		
		public static void quicksand(Entity effects, Entity effectors) {
			try {
			if(quicksandE.isEmpty()) {
				return;
			}
				for(Entity p : quicksandE.keySet()) {
					if(quicksandE.get(p).isDead()||quicksandE.get(p)==null||p.isDead()||p==null||(!(quicksandE.get(p).isOnline()))) {
						quicksandT.put(p, quicksandE.get(p));
					}
					else {
						if(rand.nextInt(4)==1) {
							p.getLocation().subtract(0, 1, 0).getBlock().setType(Material.SAND);
						}
					}
				}
				if(!quicksandT.isEmpty()) {
				for(Entity p : quicksandT.keySet()) {
					quicksandE.remove(p);
				}
				quicksandT.clear();
				}
			}
			catch(Exception e) {
				
			}
	}
		
		public static void molten(Entity effects, Entity effectors) {
			try {
			if(moltenE.isEmpty()) {
				return;
			}
				for(Entity p : moltenE.keySet()) {
					if(moltenE.get(p).isDead()||moltenE.get(p)==null||p.isDead()||p==null||(!(moltenE.get(p).isOnline()))) {
						moltenT.put(p, moltenE.get(p));
					}
					else {
						if(rand.nextInt(2)==1) {
							p.getLocation().getBlock().setType(Material.FIRE);
						}
						if(rand.nextInt(4)==1) {
							p.getLocation().subtract(0, 1, 0).getBlock().setType(Material.MAGMA);
						}
					}
				}
				if(!moltenT.isEmpty()) {
				for(Entity p : moltenT.keySet()) {
					moltenE.remove(p);
				}
				moltenT.clear();
				}
			}
			catch(Exception e) {
				
			}
	}
		
		public static void confusing(Entity effects, Entity effectors) {
			try {
			if(confusingE.isEmpty()) {
				return;
			}
				for(Entity p : confusingE.keySet()) {
					if(confusingE.get(p).isDead()||confusingE.get(p)==null||p.isDead()||p==null||(!(confusingE.get(p).isOnline()))) {
						confusingT.put(p, confusingE.get(p));
					}
					else {
						if(rand.nextInt(6)==1) {
							((LivingEntity) p).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2, 10));
						}
						else if(rand.nextInt(6)==1) {
							((LivingEntity) p).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2, 10));
						}
					}
				}
				if(!confusingT.isEmpty()) {
				for(Entity p : confusingT.keySet()) {
					confusingE.remove(p);
				}
				confusingT.clear();
				}
			}
			catch(Exception e) {
				
			}
	}
		
		public static void removeIf(Entity e) {
			if(e!=null) {
				if(!e.isDead()) {
					e.remove();
				}
			}
		}
		
		public static void volley(Entity effects, Entity effectors) {
			try {
			if(volleyE.isEmpty()) {
				return;
			}
				for(Entity p : volleyE.keySet()) {
					if(volleyE.get(p).isDead()||volleyE.get(p)==null||p.isDead()||p==null||(!(volleyE.get(p).isOnline()))) {
						volleyT.put(p, volleyE.get(p));
					}
					else {
						if(rand.nextInt(8)==1) {
							LivingEntity e1 = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
							e1.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
							LivingEntity e2 = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
							e2.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
							Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(pvpmain.class),
									() -> removeIf(e1), 80);
							Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(pvpmain.class),
									() -> removeIf(e2), 80);
						}
					}
				}
				if(!volleyT.isEmpty()) {
				for(Entity p : volleyT.keySet()) {
					volleyE.remove(p);
				}
				volleyT.clear();
				}
			}
			catch(Exception e) {
				
			}
	}
		
		//converter
		public static void convertEffect(Entity effects, Entity effectors) {
			if(convertE.isEmpty()) {
				return;
			}
			if(runningconvertE==false) {
				runningconvertE=true;
				HashMap<Player, Integer> tempTemplate = new HashMap<Player, Integer>();
				HashMap<Player, Integer> adders = new HashMap<Player, Integer>();
				for(Player p : convertE.keySet()) {
					//p.sendMessage("hi");
					//remove checker
					if(p.isDead()||!p.isOnline()||p==null) {
						//p.sendMessage("hi2");
					tempTemplate.put(p, convertE.get(p));
					}
					else {
						//p.sendMessage("hi3");
						if(convertE.get(p)<2) {
							//p.sendMessage("hi4");
							p.spawnParticle(Particle.LAVA, p.getLocation(), 50);
							if(rand.nextInt(6)==1) {
							adders.put(p, convertE.get(p)+1);
							}
						}
						else if(convertE.get(p)<5) {
							p.spawnParticle(Particle.LAVA, p.getLocation(), 50);
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 2));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 999999, 4));
							p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 999999, 3));
							if(rand.nextInt(6)==1) {
							adders.put(p, convertE.get(p)+1);
							}
						}
						else if(convertE.get(p)<8) {
							p.spawnParticle(Particle.LAVA, p.getLocation(), 50);
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 2));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 999999, 4));
							p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 999999, 3));
							p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999, 4));
							p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 999999, 3));
							p.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
							p.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 2);
							p.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 0);
							p.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1, 1);
							if(rand.nextInt(6)==1) {
							adders.put(p, convertE.get(p)+1);
							}
						}
						else{
							meatyFurniture.meatFurn(p, (rand.nextInt(6)+1));
							tempTemplate.put(p, convertE.get(p));
						}
					}
				}
			
				//call remover
			for(Player p2 : tempTemplate.keySet()) {
				convertE.remove(p2);
			}
			tempTemplate.clear();
			for(Player p3 : adders.keySet()) {
				convertE.put(p3, adders.get(p3));
			}
			adders.clear();
			runningconvertE=false;
	}
		//	
		
}

		
		//watcher
		public static void watcher(Entity effects, Entity effectors) {
			if(watcherE.isEmpty()) {
				return;
			}
				for(Entity p : watcherE.keySet()) {
					if(watcherE.get(p).isDead()||watcherE.get(p)==null||p.isDead()||p==null||(!(watcherE.get(p).isOnline()))) {
					   watcherT.put(p, watcherE.get(p));
					}
					else {
						p.teleport(getBlockBehindPlayer(watcherE.get(p)));
						((Monster) p).setTarget(watcherE.get(p));
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_MULE_DEATH, 1, 0);
					}
				}
				if(!watcherT.isEmpty()) {
				for(Entity p : watcherT.keySet()) {
					watcherE.remove(p);
				}
				watcherT.clear();
				}
			
	}
		
		public static Location getBlockBehindPlayer(Player player) {
			Vector inverseDirectionVec = player.getLocation().getDirection().normalize().multiply(-1);
			return player.getLocation().add(inverseDirectionVec);
		}
		//
			//Living Armor
		public static void armorHit(Player p) {
			if(p.isDead()||p==null) {
				if(livingAE.containsKey(p)) {
				livingAE.remove(p);
				}
			}
			else if(!livingAE.containsKey(p)) {
				livingAE.put(p, 0);
			}
			else {
				int num = livingAE.get(p);
				if(num==0) {
					livingAE.put(p, livingAE.get(p)+1);
				}
				if(num==1) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,999999,1));
					livingAE.put(p, livingAE.get(p)+1);
				}
				else if(num==2) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,999999,1));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,999999,2));
					livingAE.put(p, livingAE.get(p)+1);
				}
				else if(num==3) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,999999,2));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,999999,3));
					p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,999999,1));
					livingAE.put(p, livingAE.get(p)+1);
				}
				else if(num>=4) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,999999,200));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,999999,200));
					p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,999999,200));
					Entity armor = p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE_VILLAGER);
					Monster armorM = ((Monster) armor);
					armorM.getEquipment().setArmorContents(p.getInventory().getArmorContents());
					armorM.getEquipment().setItemInMainHand(p.getInventory().getItemInMainHand());
					armorM.getEquipment().setItemInOffHand(p.getInventory().getItemInOffHand());
					armorM.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1));
					armorM.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 1));
					armorM.setCustomName("Living Armor");
					armorM.setSilent(true);
					livingAE.remove(p);
					p.damage(9999);
				}
			}
		}
		//
		public void setBlockRandom(List<Location> blocks, int chance, int maxRuns, int runStart){
			List<Location> blocks2 = blocks;
			//check if all blocks are placed
			if(blocks.size()<1) {
				return;
			}
			//check if run ammount is exceeding max, used to stop infinitish loops
			if(maxRuns<=runStart){
				for(Location l : blocks2) {
					l.getBlock().setType(null);
				}
				return;
			}
			else {
				for(int i = 0; i < blocks.size(); i++) {
					if(randor.nextInt(chance)==1) {
						//can add bukkit task later here for random times
						blocks2.get(i).getBlock().setType(null);
						blocks2.remove(i);
					}
				}
				//calls method with blocks left over
				setBlockRandom(blocks2, chance, maxRuns, runStart+1);
			}

		}
		//Inferno
		public static void inferno(Entity effects, Entity effectors) {
			if(infernoE.isEmpty()) {
				return;
			}
				for(Entity p : infernoE.keySet()) {
					if(infernoE.get(p).isDead()||infernoE.get(p)==null||p.isDead()||p==null||(!(infernoE.get(p).isOnline()))||((Monster) p).getTarget()==null) {
					   infernoT.put(p, infernoE.get(p));
					}
					else {
						if(getLookingAt((LivingEntity) p, (LivingEntity) infernoE.get(p))) {
							int radius = 2;
							Location loc = infernoE.get(p).getLocation();
			    			int cx = loc.getBlockX();
			    			int cy = loc.getBlockY();
			    			int cz = loc.getBlockZ();
			    			for (int x = cx - radius; x <= cx + radius; x++) {
			    				for (int z = cz - radius; z <= cz + radius; z++) {
			    					for (int y = (cy - radius); y < (cy + radius); y++) {
			    						double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + ((cy - y) * (cy - y));

			    						if (dist < radius * radius) {
			    							Location l = new Location(loc.getWorld(), x, y, z);
			    							if(l.getBlock().getType()==Material.AIR) {
			    								l.getBlock().setType(Material.FIRE, false);
			    							}
			    						}
			    					}
			    				}
			    			}
						}
					}
				}
				if(!infernoT.isEmpty()) {
				for(Entity p : infernoT.keySet()) {
					infernoE.remove(p);
				}
				infernoT.clear();
				}
			
	}
		
		private static boolean getLookingAt(LivingEntity player, LivingEntity player1) {
			Location eye = player.getEyeLocation();
			Vector toEntity = ((LivingEntity) player1).getEyeLocation().toVector().subtract(eye.toVector());
			double dot = toEntity.normalize().dot(eye.getDirection());

			return dot > 0.99D;
		}
		
		private static boolean getLookingAt2(LivingEntity player, LivingEntity player1) {
			Location eye = player.getEyeLocation();
			Vector toEntity = ((LivingEntity) player1).getEyeLocation().toVector().subtract(eye.toVector());
			double dot = toEntity.normalize().dot(eye.getDirection());

			return dot > 0.95D;
		}
		//
		//Stalker
		public static void stalkerE() {
			if(stalkerE.isEmpty()) {
				return;
			}
			if(runningstalkerE==false) {
				runningstalkerE=true;
			 List<Entity> stalkerT = new ArrayList<Entity>();
				for(Entity e : stalkerE) {
					if(e.isDead()||e==null) {
						stalkerT.add(e);
					}
					if(e instanceof Monster) {
						Monster mon = (Monster) e;
						if(mon.getTarget() == null) {
							stalkerT.add(e);
						}
						else if(mon.getTarget() instanceof Player && !mon.getTarget().isDead()) {
							if(getLookingAt2(mon.getTarget(), mon)) {
								mon.removePotionEffect(PotionEffectType.SPEED);
								mon.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
								mon.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 200));
								mon.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 200));
							}
							else {
								mon.removePotionEffect(PotionEffectType.SLOW);
								mon.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
								mon.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 10));
								mon.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 10));
								mon.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 200));
							}
						}
						else {
							stalkerT.add(e);
						}
					}
				}
				if(!stalkerT.isEmpty()) {
				for(Entity p : stalkerT) {
					stalkerE.remove(p);
				}
				stalkerT.clear();
				}
				runningstalkerE=false;
		}
		}
		//
		//living shadow
		public static void shadow(Entity effects, Entity effectors) {
			if(!shadowAdd.isEmpty()) {
				for(int i = 0; i < shadowAdd.size(); i++) {
					shadowE.add(shadowAdd.get(i));
				}
				shadowAdd.clear();
			}
			if(shadowE.isEmpty()) {
				return;
			}
			if(runningshadowE==false) {
				runningshadowE=true;
			List<Entity> shadowT = new ArrayList<Entity>();
				for(Entity p : shadowE) {
					if(p.isDead()||p==null||((Monster)p).getTarget()==null||((Monster)p).getTarget().isDead()) {
					  shadowT.add(p);
					}
					else {
						if(p.getLocation().getBlock().getLightLevel()<12) {
							List<Entity> ents = p.getNearbyEntities(3, 3, 3);
							for(Entity e : ents) {
								if(e instanceof LivingEntity) {
									((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,120,1));
									((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.WITHER,120,1));
								}
							}
							//p.getWorld().spawnParticle(Particle.SUSPENDED_DEPTH, p.getLocation(), 300, 1,2,1);
							MaterialData m = new MaterialData(Material.CONCRETE, (byte) 15);
							p.getWorld().spawnParticle(Particle.CLOUD, p.getLocation().add(0, 1, 0), 60, 1,1,1,0.00f/*, m*/);
							//p.getWorld().spawnParticle(Particle.BLOCK_CRACK, p.getLocation(), 60, 1,2,1,0.05f, m);
						}
					}
				}
				if(!shadowT.isEmpty()) {
				for(Entity p : shadowT) {
					shadowE.remove(p);
				}
				shadowT.clear();
				}
				runningshadowE=false;
			}
	}
		//
}
