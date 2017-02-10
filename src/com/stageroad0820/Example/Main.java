package com.stageroad0820.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Main plugin;

	PluginDescriptionFile pdfFile = this.getDescription();
	PluginManager pm = Bukkit.getPluginManager();

	// ���� ���̴� String �� ����
	String prefix = ChatColor.AQUA + "[TUT] " + ChatColor.WHITE + "";
	String info = ChatColor.YELLOW + "[Info] " + ChatColor.WHITE + "";
	String error = ChatColor.DARK_RED + "[Error] " + ChatColor.RED + "";
	String warning = ChatColor.DARK_RED + "[Warning] " + ChatColor.RED + "";
	String plname = ChatColor.GREEN + pdfFile.getName() + "";
	String plvers = ChatColor.GREEN + pdfFile.getVersion() + "";
	String pname = plname + " v" + plvers + " ";
	String cinfo = info + ChatColor.YELLOW + pname + ChatColor.WHITE + " ";

	// ���� ���̴� ���� �ڵ� ���� (ChatColor.<Color>)
	String white = ChatColor.WHITE + "";
	String red = ChatColor.RED + "";
	String dred = ChatColor.DARK_RED + "";
	String gold = ChatColor.GOLD + "";
	String yellow = ChatColor.YELLOW + "";
	String green = ChatColor.GREEN + "";
	String dgreen = ChatColor.DARK_GREEN + "";
	String aqua = ChatColor.AQUA + "";
	String blue = ChatColor.BLUE + "";
	String dblue = ChatColor.DARK_BLUE + "";
	String gray = ChatColor.GRAY + "";

	@Override
	public void onEnable() {
		pm.registerEvents(this, this);

		ShapedRecipe saddle = new ShapedRecipe(new ItemStack(Material.SADDLE, 1)).shape("!  ", "@@@", "  !")
				.setIngredient('!', Material.STRING).setIngredient('@', Material.LEATHER);

		ShapelessRecipe chest = new ShapelessRecipe(new ItemStack(Material.WOOD, 8)).addIngredient(Material.CHEST);

		Bukkit.addRecipe(saddle);
		Bukkit.addRecipe(chest);

		ItemStack dm = new ItemStack(Material.DIAMOND);

		Bukkit.addRecipe(new FurnaceRecipe(dm, Material.COAL));

		getConfig().options().copyDefaults(true);
		saveConfig();

		console(cinfo + " (��)�� Ȱ��ȭ �Ǵ� �� �Դϴ�.");
	}

	@Override
	public void onDisable() {
		saveConfig();

		console(cinfo + " (��)�� ��Ȱ��ȭ �Ǵ� �� �Դϴ�.");
	}

	@EventHandler
	public void onPlayerEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();

		ItemStack is = new ItemStack(Material.ARROW);
		ItemMeta im = is.getItemMeta();

		im.setDisplayName(aqua + "ü�� ȸ��");
		ArrayList<String> il = new ArrayList<>();
		im.setLore(Arrays.asList(gray + "������ 3 �Ҹ��Ͽ� ĳ������ ü���� ��� ȸ���մϴ�."));
		is.setItemMeta(im);

		int mp = player.getFoodLevel();

		if (action.equals(Action.LEFT_CLICK_AIR) && player.getItemInHand().equals(is)) {
			if (mp == 0 || mp <= 3) {
				player.sendMessage(error + "��ų�� ����ϱ� ���� ������ �����մϴ�. ������� �ذ��Ͽ� ������ ������ �ּ���!");
			}

			else if (mp > 4) {
				player.setHealth(20.0);
				player.sendMessage(info + yellow + player.getName() + white + " �� ü���� ��� ȸ���Ͽ����ϴ�.");
			}
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		ItemStack is = new ItemStack(Material.ARROW);
		ItemMeta im = is.getItemMeta();

		im.setDisplayName(aqua + "ü�� ȸ��");
		ArrayList<String> il = new ArrayList<>();
		im.setLore(Arrays.asList(gray + "������ 3 �Ҹ��Ͽ� ĳ������ ü���� ��� ȸ���մϴ�."));
		is.setItemMeta(im);

		player.getInventory().addItem(is);

		event.setJoinMessage(prefix + yellow + player.getName() + white + " ���� ������ �����ϼ̽��ϴ�!");
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		ItemStack is = new ItemStack(Material.ARROW);
		ItemMeta im = is.getItemMeta();

		im.setDisplayName(aqua + "ü�� ȸ��");
		ArrayList<String> il = new ArrayList<>();
		im.setLore(Arrays.asList(gray + "������ 3 �Ҹ��Ͽ� ĳ������ ü���� ��� ȸ���մϴ�."));
		is.setItemMeta(im);

		player.getInventory().removeItem(is);

		event.setQuitMessage(prefix + yellow + player.getName() + white + " ���� �������� �����̽��ϴ�!");
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if(!event.getInventory().getTitle().equalsIgnoreCase("���Ӹ�� ����"))
			return;
		
		switch(event.getCurrentItem().getType()) {
		case WORKBENCH:
			player.setGameMode(GameMode.SURVIVAL);
			player.closeInventory();
			player.sendMessage(prefix + "�÷��̾��� ���Ӹ�带 " + red + "�����̹� ���" + white + " �� �����Ͽ����ϴ�.");
			break;
		case DIAMOND_BLOCK:
			player.setGameMode(GameMode.CREATIVE);
			player.closeInventory();
			player.sendMessage(prefix + "�÷��̾��� ���Ӹ�带 " + red + "ũ������Ƽ�� ���" + white + " �� �����Ͽ����ϴ�.");
			break;
		case LEATHER_HELMET:
			player.setGameMode(GameMode.ADVENTURE);
			player.closeInventory();
			player.sendMessage(prefix + "�÷��̾��� ���Ӹ�带 " + red + "��庥ó ���" + white + " �� �����Ͽ����ϴ�.");
			break;
		case GLASS:
			player.setGameMode(GameMode.SPECTATOR);
			player.closeInventory();
			player.sendMessage(prefix + "�÷��̾��� ���Ӹ�带 " + red + "������ ���" + white + " �� �����Ͽ����ϴ�.");
			break;
		default:
			Bukkit.broadcastMessage(warning + "�����: switch~case ���� default: �� ����Ǿ����ϴ�.");
			break;
		}
	}

	public void openInv(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, "���Ӹ�� ����");
		
		ItemStack srv = new ItemStack(Material.WORKBENCH);
		ItemMeta srvm = srv.getItemMeta();
		
		srvm.setDisplayName(gold + "�����̹� ���");
		ArrayList<String> srvl = new ArrayList<String>();
		srvm.setLore(Arrays.asList(gray + "�÷��̾��� ���Ӹ�带 " + gold + "�����̹� ���" + gray + " �� �����մϴ�."
				, "", blue + "-�ڵ�: 0"));
		srv.setItemMeta(srvm);
		
		ItemStack ctv = new ItemStack(Material.DIAMOND_BLOCK);
		ItemMeta ctvm = ctv.getItemMeta();
		
		ctvm.setDisplayName(gold + "ũ������Ƽ�� ���");
		ArrayList<String> ctvl = new ArrayList<String>();
		ctvm.setLore(Arrays.asList(gray + "�÷��̾��� ���Ӹ�带 " + gold + "ũ������Ƽ�� ���" + gray + " �� �����մϴ�."
				, "", blue + "-�ڵ�: 1"));
		ctv.setItemMeta(ctvm);
		
		ItemStack adv = new ItemStack(Material.LEATHER_HELMET);
		ItemMeta advm = adv.getItemMeta();
		
		advm.setDisplayName(gold + "��庥ó ���");
		ArrayList<String> advl = new ArrayList<String>();
		advm.setLore(Arrays.asList(gray + "�÷��̾��� ���Ӹ�带 " + gold + "��庥ó ���" + gray + " �� �����մϴ�."
				, "", blue + "-�ڵ�: 2"));
		adv.setItemMeta(advm);
		
		ItemStack spt = new ItemStack(Material.GLASS);
		ItemMeta sptm = spt.getItemMeta();
		
		sptm.setDisplayName(gold + "������ ���");
		ArrayList<String> sptl = new ArrayList<String>();
		sptm.setLore(Arrays.asList(gray + "�÷��̾��� ���Ӹ�带 " + gold + "������ ���" + gray + " �� �����մϴ�."
				, "", blue + "-�ڵ�: 3"));
		spt.setItemMeta(sptm);
		
		inv.setItem(10, srv);
		inv.setItem(12, ctv);
		inv.setItem(14, adv);
		inv.setItem(16, spt);
		
		player.openInventory(inv);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		Player player = (Player) sender;
		Location location = player.getLocation();

		// "/blog" Ŀ�ǵ�!
		if (commandLabel.equalsIgnoreCase("blog")) {
			if (args.length == 0) {
				player.sendMessage(
						error + "���� ���� �ʹ� �۰ų� �����ϴ�!" + yellow + " /blog help " + red + "�� �Է��� �� ���� Ŀ�ǵ带 �˾ƺ�����!");
			}

			else if (args.length > 0) {
				if (args[0].equalsIgnoreCase("help")) {
					player.sendMessage(aqua + "= = = = = = = ��α� Ʃ�丮�� = = = = = = =");
					player.sendMessage(green + "/blog help : ������ ǥ���մϴ�");
					player.sendMessage(green + "/blog random : �������� �޼��� 1���� ǥ���մϴ�.");
					player.sendMessage(green + "/blog config <string> : config.yml ���Ͽ� �ִ� String ���� �о� ����մϴ�.");
					player.sendMessage(green + "/blog tp <save/move> : �÷��̾��� ���� ��ǥ�� �����ϰų� ������ ��ǥ�� �ڷ���Ʈ �մϴ�.");
					player.sendMessage(green + "/blog inv : �÷��̾��� ���Ӹ�� ������ ������ �κ��丮 â�� ���ϴ�.");
					player.sendMessage(aqua + "= = = = = = = = = = = = = = = = = = = =");
				}

				else if (args[0].equalsIgnoreCase("random")) {
					int random = (int) (Math.random() * 3);

					switch (random) {
					case 0:
						player.sendMessage(gold + "��ü �޼��� �� ù��° �޼����� �������� ��µǾ����ϴ�!");
						break;
					case 1:
						player.sendMessage(gold + "��ü �޼��� �� �ι�° �޼����� �������� ��µǾ����ϴ�!");
						break;
					case 2:
						player.sendMessage(gold + "��ü �޼��� �� ����° �޼����� �������� ��µǾ����ϴ�!");
						break;
					default:
						player.sendMessage(warning + "���� ó�� ���� ���� ��Ȳ�� �߻��Ͽ����ϴ�!");
						break;
					}
				}

				else if (args[0].equalsIgnoreCase("config")) {
					if (args.length == 1) {
						player.sendMessage(error + "�Է��Ͻ� ���ڰ��� �ʹ� ���ų� �����ϴ�. �Ʒ��� ��Ͽ��� config �� ��ϵ� �̸��� ã�� �� �ֽ��ϴ�.");
						player.sendMessage(error + "config.yml �� �ִ� ����: " + yellow + "first, second");
					} else if (args.length > 1) {
						if (args[1].equalsIgnoreCase("first")) {
							player.sendMessage(prefix + "config.yml �� first �޼��� �Դϴ�.");
							player.sendMessage(prefix + "first: " + gray + getConfig().getString("first"));
						} else if (args[1].equalsIgnoreCase("second")) {
							player.sendMessage(prefix + "config.yml �� second �޼��� �Դϴ�.");
							player.sendMessage(prefix + "second: " + gray + getConfig().getString("second"));
						}
					} else {
						player.sendMessage(error + "����ġ ���� ������ �߻��Ͽ����ϴ�. �����ڴ� Ȯ���� �ֽñ� �ٶ��ϴ�. (ln=203)");
					}
				}

				else if (args[0].equalsIgnoreCase("perm")) {
					if (player.hasPermission(new Permissions().Example)) {
						player.sendMessage(prefix + "���� " + aqua + "Example.bypass" + white + " ������ ������ �ֽ��ϴ�.");
					}

					else if (!player.hasPermission(new Permissions().Example)) {
						player.sendMessage(prefix + "���� " + aqua + "Example.bypass" + white + " ������ ������ ���� �ʽ��ϴ�.");
					}

					else {
						player.sendMessage(error + "����ġ ���� ������ �߻��߽��ϴ�. �����ڴ� Ȯ���� �ּ���. (ln=217)");
					}
				}
				
				else if (args[0].equalsIgnoreCase("inv")) {
					openInv(player);
					player.sendMessage(prefix + "���Ӹ�� ������ ������ �κ��丮 â�� ������ϴ�. ESC Ű�� �̿��� ���� �� �ֽ��ϴ�.");
				}

				if (player.isOp() == true) {
					if (args[0].equalsIgnoreCase("tp")) {
						if (args.length == 1) {
							player.sendMessage(error + "���� ���� �ʹ� �۰ų� �����ϴ�!" + yellow + " /blog help " + red
									+ "�� �Է��� �� ���� Ŀ�ǵ带 �˾ƺ�����!");
						}

						else if (args.length > 1) {
							if (args[1].equalsIgnoreCase("save")) {
								getConfig().set(player.getName() + ".position.x", player.getLocation().getBlockX());
								getConfig().set(player.getName() + ".position.y", player.getLocation().getBlockY());
								getConfig().set(player.getName() + ".position.z", player.getLocation().getBlockZ());
								getConfig().set(player.getName() + ".position.pitch", player.getLocation().getPitch());
								getConfig().set(player.getName() + ".position.yaw", player.getLocation().getYaw());

								saveConfig();

								player.sendMessage(
										prefix + "�÷��̾� " + yellow + player.getName() + white + " �� ��ǥ�� ����Ǿ����ϴ�.");
							}

							else if (args[1].equalsIgnoreCase("move")) {
								if (getConfig().isSet(player.getName() + ".position.x") == true) {
									double x = getConfig().getDouble(player.getName() + ".position.x");
									double y = getConfig().getDouble(player.getName() + ".position.y");
									double z = getConfig().getDouble(player.getName() + ".position.z");

									player.teleport(new Location(player.getWorld(), x, y, z));
									player.getLocation().setPitch(
											(float) getConfig().getDouble(player.getName() + ".position.pitch"));
									player.getLocation()
											.setYaw((float) getConfig().getDouble(player.getName() + ".position.yaw"));

									player.sendMessage(prefix + "����� ��ġ�� �ڷ���Ʈ �Ǿ����ϴ�!");
								}

								else {
									player.sendMessage(error + "����� ��ǥ�� �����ϴ�! " + yellow + "/blog tp save" + red
											+ " �� �̿��� ��ǥ�� ������ �ּ���!");
								}
							}
						}

						else {
							player.sendMessage(error + "����ġ ���� ������ �߻��Ͽ����ϴ�. �����ڴ� Ȯ���� �ֽñ� �ٶ��ϴ�. (ln=262)");
						}
					}
				}

				else {
					player.sendMessage(error + "�� Ŀ�ǵ带 ����� ����� ������ �ο����� �ʾҽ��ϴ�! �����ڿ��� ������ ������.");
				}
			}
		}

		return false;
	}

	public void console(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}

}