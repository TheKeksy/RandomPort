package com.github.TheKeksy.RandomPort;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Port implements CommandExecutor {

	private RandomPort plugin;

	public Port(RandomPort randomport) {
		plugin = randomport;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		Player p = null;

		if (sender instanceof Player) {
			p = (Player) sender;
		}

		if (cmd.getName().equalsIgnoreCase("tpr")) {
			p = (Player) sender;
			Location loc = p.getLocation();
			Random random = new Random();
			int x;
			x = this.plugin.getConfig().getInt("Teleport.X0", -100)
					+ random.nextInt(this.plugin.getConfig().getInt(
							"Teleport.X1", 200));
			int y;
			y = this.plugin.getConfig().getInt("Teleport.Y0", 5)
					+ random.nextInt(this.plugin.getConfig().getInt(
							"Teleport.Y1", 5));
			int z;
 			z = this.plugin.getConfig().getInt("Teleport.Z0", -100)
					+ random.nextInt(this.plugin.getConfig().getInt(
							"Teleport.Z1", 200));
			loc.setX(x);
			loc.setY(y);
			loc.setZ(z);
			Block b = loc.getBlock();
			int counter = 200;

			do {
				counter = counter - 1;
				b = loc.getBlock();

				if ((b.getType() == Material.WATER)
						|| (b.getType() == Material.LAVA)) {
					x = -100 + random.nextInt(200);
					z = -100 + random.nextInt(200);
					loc.setX(x);
					loc.setZ(z);
					loc.setY(220);
					b = loc.getBlock();
					counter = 200;
				}
				if (b.getType() != Material.AIR) {
					loc.setY(loc.getY() - 10);
					if (loc.getY() <= 0) {
						loc.setY(10);
					}
				} else {
					counter = 0;
				}

			}

			while (counter != 0);

			b = loc.getBlock();
			b.getRelative(0, 0, 0).setType(Material.STONE);
			b.getRelative(-1, 0, 0).setType(Material.STONE);
			b.getRelative(-1, 0, -1).setType(Material.STONE);
			b.getRelative(0, 0, -1).setType(Material.STONE);

			b.getRelative(0, 1, 0).setType(Material.AIR);
			b.getRelative(-1, 1, 0).setType(Material.AIR);
			b.getRelative(-1, 1, -1).setType(Material.AIR);
			b.getRelative(0, 1, -1).setType(Material.AIR);

			b.getRelative(0, 2, 0).setType(Material.AIR);
			b.getRelative(-1, 2, 0).setType(Material.AIR);
			b.getRelative(-1, 2, -1).setType(Material.AIR);
			b.getRelative(0, 2, -1).setType(Material.AIR);

			loc.setWorld(Bukkit.getWorld("farm"));
			loc.setY(loc.getY() + 1);
			p.teleport(loc);

			return true;
		}
		return false;
	}
}