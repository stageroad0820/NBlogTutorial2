package com.stageroad0820.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class Commands implements CommandExecutor, Listener {
	public final Logger logger = Logger.getLogger("Example");
	public static Main plugin;
	
	public static void setPlugin(Main MainPlugin) {
		plugin = MainPlugin;
	}
	
	PluginManager pm = Bukkit.getPluginManager();

	// ���� ���̴� String �� ����
	String prefix = ChatColor.AQUA + "[TUT] " + ChatColor.WHITE + "";
	String info = ChatColor.YELLOW + "[Info] " + ChatColor.WHITE + "";
	String error = ChatColor.DARK_RED + "[Error] " + ChatColor.RED + "";
	String warning = ChatColor.DARK_RED + "[Warning] " + ChatColor.RED + "";
	String plname = ChatColor.GREEN + plugin.getDescription().getName() + "";
	String plvers = ChatColor.GREEN + plugin.getDescription().getVersion() + "";
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

	int time = 10;
	
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();

		if (!event.getInventory().getTitle().equalsIgnoreCase("���Ӹ�� ����"))
			return;

		switch (event.getCurrentItem().getType()) {
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
		srvm.setLore(Arrays.asList(gray + "�÷��̾��� ���Ӹ�带 " + gold + "�����̹� ���" + gray + " �� �����մϴ�.", "", blue + "-�ڵ�: 0"));
		srv.setItemMeta(srvm);

		ItemStack ctv = new ItemStack(Material.DIAMOND_BLOCK);
		ItemMeta ctvm = ctv.getItemMeta();

		ctvm.setDisplayName(gold + "ũ������Ƽ�� ���");
		ArrayList<String> ctvl = new ArrayList<String>();
		ctvm.setLore(
				Arrays.asList(gray + "�÷��̾��� ���Ӹ�带 " + gold + "ũ������Ƽ�� ���" + gray + " �� �����մϴ�.", "", blue + "-�ڵ�: 1"));
		ctv.setItemMeta(ctvm);

		ItemStack adv = new ItemStack(Material.LEATHER_HELMET);
		ItemMeta advm = adv.getItemMeta();

		advm.setDisplayName(gold + "��庥ó ���");
		ArrayList<String> advl = new ArrayList<String>();
		advm.setLore(Arrays.asList(gray + "�÷��̾��� ���Ӹ�带 " + gold + "��庥ó ���" + gray + " �� �����մϴ�.", "", blue + "-�ڵ�: 2"));
		adv.setItemMeta(advm);

		ItemStack spt = new ItemStack(Material.GLASS);
		ItemMeta sptm = spt.getItemMeta();

		sptm.setDisplayName(gold + "������ ���");
		ArrayList<String> sptl = new ArrayList<String>();
		sptm.setLore(Arrays.asList(gray + "�÷��̾��� ���Ӹ�带 " + gold + "������ ���" + gray + " �� �����մϴ�.", "", blue + "-�ڵ�: 3"));
		spt.setItemMeta(sptm);

		inv.setItem(10, srv);
		inv.setItem(12, ctv);
		inv.setItem(14, adv);
		inv.setItem(16, spt);

		player.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			Location location = player.getLocation();
			String world = player.getWorld().getName();

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
						player.sendMessage(green + "/blog timer : ī��Ʈ �ٿ��� �����մϴ�. (10��)");
						player.sendMessage(green + "/blog cancel : ���� �� ���� �÷������� ���� ����Ǵ� ��� ���� ����մϴ�.");
						player.sendMessage(green + "/blog time <7/12/18/24> : �÷��̾ �ִ� ������ �ð��� �����մϴ�.");
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
								player.sendMessage(prefix + "first: " + gray + plugin.getConfig().getString("first"));
							} else if (args[1].equalsIgnoreCase("second")) {
								player.sendMessage(prefix + "config.yml �� second �޼��� �Դϴ�.");
								player.sendMessage(prefix + "second: " + gray + plugin.getConfig().getString("second"));
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
					
					else if (args[0].equalsIgnoreCase("timer")) {
						player.sendMessage(prefix + green + "ī��Ʈ �ٿ��� �����մϴ�.");
						plugin.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {
							public void run() {
								for (Player p : Bukkit.getOnlinePlayers()) {
									if (time != -1) {
										if (time != 0) {
											player.sendMessage(prefix + gold + "ī��Ʈ �ٿ� : " + time + " ��");
											player.playSound(location, Sound.ORB_PICKUP, 10F, 1F);
											time--;
										}
										
										else {
											player.sendMessage(prefix + gold + "ī��Ʈ �ٿ� ����!");
											player.playSound(location, Sound.FIREWORK_TWINKLE, 10F, 1F);
											time--;
											time = 10;
											Bukkit.getScheduler().cancelAllTasks();
										}
									}
								}
							}
						}, 0L, 20L);
					}
					
					else if (args[0].equalsIgnoreCase("cancel")) {
						player.sendMessage(prefix + red + "ī��Ʈ �ٿ��� ������ ����Ǿ����ϴ�.");
						Bukkit.getScheduler().cancelAllTasks();
						time = 10;
					}
					
					else if (args[0].equalsIgnoreCase("time")) {
						if (args.length <= 1) {
							player.sendMessage(error + "���� ���� �ʹ� �۰ų� �����ϴ�!" + yellow + " /blog help " + red
									+ "�� �Է��� �� ���� Ŀ�ǵ带 �˾ƺ�����!");
						}
						else if (args.length > 1){
							if (args[1].equalsIgnoreCase("7")) {
								Bukkit.getWorld(world).setTime(0);
								player.sendMessage(prefix + "������ �ð��� " + yellow + "AM 07:00" + white + " (��)�� �����Ͽ����ϴ�.");
							}
							
							else if (args[1].equalsIgnoreCase("12")) {
								Bukkit.getWorld(world).setTime(6000);
								player.sendMessage(prefix + "������ �ð��� " + yellow + "PM 12:00" + white + " (��)�� �����Ͽ����ϴ�.");
							}
							
							else if (args[1].equalsIgnoreCase("18")) {
								Bukkit.getWorld(world).setTime(12000);
								player.sendMessage(prefix + "������ �ð��� " + yellow + "PM 06:00" + white + " (��)�� �����Ͽ����ϴ�.");
							}
							
							else if (args[1].equalsIgnoreCase("24")) {
								Bukkit.getWorld(world).setTime(18000);
								player.sendMessage(prefix + "������ �ð��� " + yellow + "AM 12:00" + white + " (��)�� �����Ͽ����ϴ�.");
							}
						}
					}
					
					if (player.isOp() == true) {
						if (args[0].equalsIgnoreCase("tp")) {
							if (args.length == 1) {
								player.sendMessage(error + "���� ���� �ʹ� �۰ų� �����ϴ�!" + yellow + " /blog help " + red
										+ "�� �Է��� �� ���� Ŀ�ǵ带 �˾ƺ�����!");
							}

							else if (args.length > 1) {
								if (args[1].equalsIgnoreCase("save")) {
									plugin.getConfig().set(player.getName() + ".position.x", player.getLocation().getBlockX());
									plugin.getConfig().set(player.getName() + ".position.y", player.getLocation().getBlockY());
									plugin.getConfig().set(player.getName() + ".position.z", player.getLocation().getBlockZ());
									plugin.getConfig().set(player.getName() + ".position.pitch", player.getLocation().getPitch());
									plugin.getConfig().set(player.getName() + ".position.yaw", player.getLocation().getYaw());

									plugin.saveConfig();

									player.sendMessage(
											prefix + "�÷��̾� " + yellow + player.getName() + white + " �� ��ǥ�� ����Ǿ����ϴ�.");
								}

								else if (args[1].equalsIgnoreCase("move")) {
									if (plugin.getConfig().isSet(player.getName() + ".position.x") == true) {
										double x = plugin.getConfig().getDouble(player.getName() + ".position.x");
										double y = plugin.getConfig().getDouble(player.getName() + ".position.y");
										double z = plugin.getConfig().getDouble(player.getName() + ".position.z");

										player.teleport(new Location(player.getWorld(), x, y, z));
										player.getLocation().setPitch(
												(float) plugin.getConfig().getDouble(player.getName() + ".position.pitch"));
										player.getLocation()
												.setYaw((float) plugin.getConfig().getDouble(player.getName() + ".position.yaw"));

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
		}
		else {
			if(command.getName().equalsIgnoreCase("cs")) {
				if(args.length == 0) {
					sender.sendMessage(error + "���� ���� �ʹ� ���ų� �����ϴ�. �ٸ� Ŀ�ǵ带 �Է��� �ּ���!");
				}
				else {
					if(args[0].equalsIgnoreCase("test")) {
						sender.sendMessage(prefix + "�ܼ� Ŀ�ǵ尡 ���������� �۵��մϴ�! (��Ƽ Ŀ�ǵ� 1");
					}
					else if (args[0].equalsIgnoreCase("exam")) {
						if(args.length == 1) {
							sender.sendMessage(error + "���� ���� �ʹ� ���ų� �����ϴ�. �ٸ� Ŀ�ǵ带 �Է��� �ּ���!");
						}
						else {
							if (args[1].equalsIgnoreCase("t1")) {
								sender.sendMessage(prefix + "�ܼ� Ŀ�ǵ尡 ���������� �۵��մϴ�! (��Ƽ Ŀ�ǵ� 2)");
							}
						}
					}
				}
			}
		}

		return false;
	}

	public void console(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}
}
