package com.github.TheKeksy.RandomPort;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomPort extends JavaPlugin {

	@Override
	public void onEnable() {
		System.out.println("[RandomPort] Plugin load complete!");
		this.getCommand("tpr").setPermissionMessage(
				ChatColor.RED + "You dont have the permisson!");
		this.getCommand("tpr").setExecutor(new Port(this));
		loadConfig();
	}

	@Override
	public void onDisable() {
		System.out.println("[RandomPort] Plugin disabled!");
	}

	public FileConfiguration config;

	public void loadConfig() {
		config = getConfig();
		config.options().copyDefaults(true);

		if (new File("plugins/RandomPort/config.yml").exists()) {
			System.out.println("[RandomPort] config.yml load.");
		} else {
			saveDefaultConfig();
			System.out.println("[RandomPort] config.yml created an loaded.");
		}
	}

}
