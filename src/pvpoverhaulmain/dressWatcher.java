package pvpoverhaulmain;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class dressWatcher {

	public static void dressDGolem(LivingEntity s) {
		EntityEquipment ee = (s).getEquipment();

		ItemStack myAwesomeSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
		myAwesomeSkullMeta.setOwner("colin");
		myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);
		//net.minecraft.server.v1_12_R1.ItemStack nmsStack5 = CraftItemStack.asNMSCopy(myAwesomeSkull);
		ee.setHelmet(myAwesomeSkull);
	}
	
}
