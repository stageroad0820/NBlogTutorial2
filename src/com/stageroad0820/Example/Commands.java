package com.stageroad0820.Example;

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
import org.bukkit.plugin.PluginManager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

//import me.confuser.barapi.BarAPI;

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
		srvm.setLore(Arrays.asList(gray + "�÷��̾��� ���Ӹ�带 " + gold + "�����̹� ���" + gray + " �� �����մϴ�.", "", blue + "-�ڵ�: 0"));
		srv.setItemMeta(srvm);

		ItemStack ctv = new ItemStack(Material.DIAMOND_BLOCK);
		ItemMeta ctvm = ctv.getItemMeta();

		ctvm.setDisplayName(gold + "ũ������Ƽ�� ���");
		ctvm.setLore(
				Arrays.asList(gray + "�÷��̾��� ���Ӹ�带 " + gold + "ũ������Ƽ�� ���" + gray + " �� �����մϴ�.", "", blue + "-�ڵ�: 1"));
		ctv.setItemMeta(ctvm);

		ItemStack adv = new ItemStack(Material.LEATHER_HELMET);
		ItemMeta advm = adv.getItemMeta();

		advm.setDisplayName(gold + "��庥ó ���");
		advm.setLore(Arrays.asList(gray + "�÷��̾��� ���Ӹ�带 " + gold + "��庥ó ���" + gray + " �� �����մϴ�.", "", blue + "-�ڵ�: 2"));
		adv.setItemMeta(advm);

		ItemStack spt = new ItemStack(Material.GLASS);
		ItemMeta sptm = spt.getItemMeta();

		sptm.setDisplayName(gold + "������ ���");
		sptm.setLore(Arrays.asList(gray + "�÷��̾��� ���Ӹ�带 " + gold + "������ ���" + gray + " �� �����մϴ�.", "", blue + "-�ڵ�: 3"));
		spt.setItemMeta(sptm);

		inv.setItem(10, srv);
		inv.setItem(12, ctv);
		inv.setItem(14, adv);
		inv.setItem(16, spt);

		player.openInventory(inv);
	}

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
						player.sendMessage(green + "/blog timer [bar] : ī��Ʈ �ٿ��� �����մϴ�. (10��)");
						player.sendMessage(green + "/blog cancel : ���� �� ���� �÷������� ���� ����Ǵ� ��� ���� ����մϴ�.");
						player.sendMessage(green + "/blog time <7/12/18/24> : �÷��̾ �ִ� ������ �ð��� �����մϴ�.");
						player.sendMessage(red + "/blog bossbar <show/hide/timer> : �÷��̾�� ������ �޼����� ����մϴ�.");
						player.sendMessage(green + "/blog potion <nvision, speed> : �÷��̾�� �Է��� ����ȿ���� ���� 1�о� �ο��մϴ�.");
						player.sendMessage(green + "/blog nickname <'String'/reset> : �÷��̾��� �г����� 'String' ���� �����ϰ�, reset ���� �ʱ�ȭ �մϴ�.");
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
							player.sendMessage(error + "����ġ ���� ������ �߻��Ͽ����ϴ�. �����ڴ� Ȯ���� �ֽñ� �ٶ��ϴ�. (ln=" + getLineNumber() + ")");
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
							player.sendMessage(error + "����ġ ���� ������ �߻��߽��ϴ�. �����ڴ� Ȯ���� �ּ���. (ln=" + getLineNumber() +")");
						}
					}

					else if (args[0].equalsIgnoreCase("inv")) {
						openInv(player);
						player.sendMessage(prefix + "���Ӹ�� ������ ������ �κ��丮 â�� ������ϴ�. ESC Ű�� �̿��� ���� �� �ֽ��ϴ�.");
					}
					
					else if (args[0].equalsIgnoreCase("timer")) {
						if(args.length == 1) {
							player.sendMessage(prefix + green + "ī��Ʈ �ٿ��� �����մϴ�.");
							plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
								public void run() {
									for (Player p : Bukkit.getOnlinePlayers()) {
										if (time != -1) {
											if (time != 0) {
												p.playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10F, 1F);
												
												if (time < 6) {
													p.sendMessage(prefix + gold + "ī��Ʈ �ٿ� : " + time + " ��");
												}
												
												time--;
											}
											else {
												p.sendMessage(prefix + gold + "ī��Ʈ �ٿ� ����!");
												p.playSound(location, Sound.ENTITY_FIREWORK_TWINKLE, 10F, 1F);
												time--;
												time = 10;
												Bukkit.getScheduler().cancelAllTasks();
											}
										}
									}
								}
							}, 0L, 20L);
						}
						else {
							if(args[1].equalsIgnoreCase("bar")) {
								player.sendMessage(prefix + green + "ī��Ʈ �ٿ��� �����մϴ�.");
								plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
									public void run() {
										for (Player p : Bukkit.getOnlinePlayers()) {
											if (time != -1) {
												if (time != 0) {
													p.setLevel(time);
													p.playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10F, 1F);
													
													if (time < 6) {
														p.setLevel(time);
														p.sendMessage(prefix + gold + "ī��Ʈ �ٿ� : " + time + " ��");
													}
													
													time--;
												}
												else {
													p.setLevel(0);
													p.sendMessage(prefix + gold + "ī��Ʈ �ٿ� ����!");
													p.playSound(location, Sound.ENTITY_FIREWORK_TWINKLE, 10F, 1F);
													time--;
													time = 10;
													Bukkit.getScheduler().cancelAllTasks();
												}
											}
										}
									}
								}, 0L, 20L);
							}
						}
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
					
//					else if (args[0].equalsIgnoreCase("bossbar")) {
//						if (args.length == 1) {
//							player.sendMessage(error + "���� ���� �ʹ� �۰ų� �����ϴ�!" + yellow + " /blog help " + red
//									+ "�� �Է��� �� ���� Ŀ�ǵ带 �˾ƺ�����!");
//						}
//						else {
//							if(args[1].equalsIgnoreCase("show")) {
//								BarAPI.setMessage(player, yellow + "BarAPI �� �̿��� ������ �޼����� ����մϴ�!", 100f);
//							}
//							else if(args[1].equalsIgnoreCase("hide")) {
//								BarAPI.removeBar(player);
//							}
//							else if(args[1].equalsIgnoreCase("timer")) {
//								BarAPI.setMessage(player, gold + "stageroad0820 ���� '����ġ x2' �̺�Ʈ�� 1�а� �����߽��ϴ�!", 60);
//							}
//						}
//					}
					
					else if(args[0].equalsIgnoreCase("potion")) {
						if (args.length == 1) {
							player.sendMessage(
									error + "���� ���� �ʹ� �۰ų� �����ϴ�!" + yellow + " /blog help " + red + "�� �Է��� �� ���� Ŀ�ǵ带 �˾ƺ�����!");
						}
						else {
							if(args[1].equalsIgnoreCase("nvision")) {
								player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1200, 1, false, false));
								player.sendMessage(prefix + "���� ȿ�� " + yellow + "'�߰� ���� (1:00)'" + white + " (��)�� ����Ǿ����ϴ�!");
							}
							else if(args[1].equalsIgnoreCase("speed")) {
								player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 1, false, false));
								player.sendMessage(prefix + "���� ȿ�� " + yellow + "'�ż� (1:00)'" + white + " (��)�� ����Ǿ����ϴ�!");
							}
							else {
								player.sendMessage(error + "�� �� ���� ���� ȿ�� �Դϴ�. �Ʒ��� ��Ͽ� �ִ� ���� ȿ���� �Է��� �ּ���.");
								player.sendMessage(error + yellow + "nvision, speed");
							}
						}
					}
					
					else if(args[0].equalsIgnoreCase("nickname")) {
						if (args.length == 1) {
							player.sendMessage(
									error + "���� ���� �ʹ� �۰ų� �����ϴ�!" + yellow + " /blog help " + red + "�� �Է��� �� ���� Ŀ�ǵ带 �˾ƺ�����!");
						}
						else {
							String realName = player.getName();
							
							if(args[1].equals("reset")) {
								player.setDisplayName(realName);
								player.setPlayerListName(realName);
								
								player.sendMessage(prefix + "�÷��̾��� �̸��� ������� �����Ͽ����ϴ�.");
							}
							else if(args[1].equals(realName) || args[1].equals(player.getDisplayName())){
								player.sendMessage(error + "�����Ϸ��� �̸��� ���� ������ �̸��� �����մϴ�. �ٽ� �Է��� �ּ���!");
							}
							else {
								player.setDisplayName(args[1]);
								player.setPlayerListName(args[1]);
								
								player.sendMessage(prefix + "�÷��̾��� �г����� " + yellow + realName + white + " ���� " 
										+ yellow + player.getDisplayName() + white + " (��)�� ����Ǿ����ϴ�.");
							}
						}
					}
					
					else if(args[0].equalsIgnoreCase("enum")) {
						if (args.length == 1) {
							player.sendMessage(error + "���� ���� �Էµ��� �ʰų� �� �� ���� ������ ���Դϴ�. �Ʒ��� ���� ������ �ּ���.");
							player.sendMessage(error + yellow + "apple, chocolate, ramen, rice, corn, sugar_cube");
						}
						else {
							for (EnumTest e : EnumTest.getFoods()) {
								if(args[1].equalsIgnoreCase(e.foodName)) {
									String result = e.healthy ? "�ǰ��� �����Դϴ�." : "�ǰ����� ���� �����Դϴ�.";
									String fdName = "";
									
									switch(e.foodName) {
									case "apple":
										fdName = "���";
										break;
									case "chocolate":
										fdName = "���ݸ�";
										break;
									case "ramen":
										fdName = "���";
										break;
									case "rice":
										fdName = "��";
										break;
									case "corn":
										fdName = "������";
										break;
									case "sugar_cube":
										fdName = "������";
										break;
									default:
										break;
									}
									
									player.sendMessage(prefix + fdName + " ��(��) " + result);
									break;
								}
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
								player.sendMessage(error + "����ġ ���� ������ �߻��Ͽ����ϴ�. �����ڴ� Ȯ���� �ֽñ� �ٶ��ϴ�. (ln=" + getLineNumber() + ")");
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
	
	public static int getLineNumber() {
		return Thread.currentThread().getStackTrace()[2].getLineNumber();
	}
}
