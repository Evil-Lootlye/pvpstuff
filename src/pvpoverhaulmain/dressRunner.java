package pvpoverhaulmain;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class dressRunner {

	public static void dressDGolem(LivingEntity s) {
		EntityEquipment ee = (s).getEquipment();

		ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		LeatherArmorMeta lch6 = (LeatherArmorMeta) lchest.getItemMeta();
		lch6.setColor(Color.fromRGB(96, 96, 96));
		lchest.setItemMeta(lch6);
		//net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(lchest);
		ItemStack lchest2 = new ItemStack(Material.LEATHER_BOOTS, 1);
		LeatherArmorMeta lch1 = (LeatherArmorMeta) lchest2.getItemMeta();
		lch1.setColor(Color.fromRGB(96, 96, 96));
		lchest2.setItemMeta(lch1);
		//net.minecraft.server.v1_12_R1.ItemStack nmsStack2 = CraftItemStack.asNMSCopy(lchest2);
		ItemStack lchest4 = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		LeatherArmorMeta lch3 = (LeatherArmorMeta) lchest4.getItemMeta();
		lch3.setColor(Color.fromRGB(96, 96, 96));
		lchest4.setItemMeta(lch3);
		//net.minecraft.server.v1_12_R1.ItemStack nmsStack4 = CraftItemStack.asNMSCopy(lchest4);
		ItemStack myAwesomeSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
		myAwesomeSkullMeta.setOwner("Varn");
		myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);
		//net.minecraft.server.v1_12_R1.ItemStack nmsStack5 = CraftItemStack.asNMSCopy(myAwesomeSkull);
		//lchest = CraftItemStack.asBukkitCopy(nmsStack);
		ee.setChestplate(lchest);
		ee.setBoots(lchest2);
		ee.setHelmet(myAwesomeSkull);
		ee.setLeggings(lchest4);
	}
	
}
