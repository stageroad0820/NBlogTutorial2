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

	// 많이 쓰이는 String 값 정리
	String prefix = ChatColor.AQUA + "[TUT] " + ChatColor.WHITE + "";
	String info = ChatColor.YELLOW + "[Info] " + ChatColor.WHITE + "";
	String error = ChatColor.DARK_RED + "[Error] " + ChatColor.RED + "";
	String warning = ChatColor.DARK_RED + "[Warning] " + ChatColor.RED + "";
	String plname = ChatColor.GREEN + plugin.getDescription().getName() + "";
	String plvers = ChatColor.GREEN + plugin.getDescription().getVersion() + "";
	String pname = plname + " v" + plvers + " ";
	String cinfo = info + ChatColor.YELLOW + pname + ChatColor.WHITE + " ";

	// 많이 쓰이는 색깔 코드 정리 (ChatColor.<Color>)
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

		if (!event.getInventory().getTitle().equalsIgnoreCase("게임모드 변경"))
			return;

		switch (event.getCurrentItem().getType()) {
		case WORKBENCH:
			player.setGameMode(GameMode.SURVIVAL);
			player.closeInventory();
			player.sendMessage(prefix + "플레이어의 게임모드를 " + red + "서바이벌 모드" + white + " 로 변경하였습니다.");
			break;
		case DIAMOND_BLOCK:
			player.setGameMode(GameMode.CREATIVE);
			player.closeInventory();
			player.sendMessage(prefix + "플레이어의 게임모드를 " + red + "크리에이티브 모드" + white + " 로 변경하였습니다.");
			break;
		case LEATHER_HELMET:
			player.setGameMode(GameMode.ADVENTURE);
			player.closeInventory();
			player.sendMessage(prefix + "플레이어의 게임모드를 " + red + "어드벤처 모드" + white + " 로 변경하였습니다.");
			break;
		case GLASS:
			player.setGameMode(GameMode.SPECTATOR);
			player.closeInventory();
			player.sendMessage(prefix + "플레이어의 게임모드를 " + red + "관전자 모드" + white + " 로 변경하였습니다.");
			break;
		default:
			Bukkit.broadcastMessage(warning + "디버그: switch~case 에서 default: 가 실행되었습니다.");
			break;
		}
	}

	public void openInv(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, "게임모드 변경");

		ItemStack srv = new ItemStack(Material.WORKBENCH);
		ItemMeta srvm = srv.getItemMeta();

		srvm.setDisplayName(gold + "서바이벌 모드");
		srvm.setLore(Arrays.asList(gray + "플레이어의 게임모드를 " + gold + "서바이벌 모드" + gray + " 로 변경합니다.", "", blue + "-코드: 0"));
		srv.setItemMeta(srvm);

		ItemStack ctv = new ItemStack(Material.DIAMOND_BLOCK);
		ItemMeta ctvm = ctv.getItemMeta();

		ctvm.setDisplayName(gold + "크리에이티브 모드");
		ctvm.setLore(
				Arrays.asList(gray + "플레이어의 게임모드를 " + gold + "크리에이티브 모드" + gray + " 로 변경합니다.", "", blue + "-코드: 1"));
		ctv.setItemMeta(ctvm);

		ItemStack adv = new ItemStack(Material.LEATHER_HELMET);
		ItemMeta advm = adv.getItemMeta();

		advm.setDisplayName(gold + "어드벤처 모드");
		advm.setLore(Arrays.asList(gray + "플레이어의 게임모드를 " + gold + "어드벤처 모드" + gray + " 로 변경합니다.", "", blue + "-코드: 2"));
		adv.setItemMeta(advm);

		ItemStack spt = new ItemStack(Material.GLASS);
		ItemMeta sptm = spt.getItemMeta();

		sptm.setDisplayName(gold + "관전자 모드");
		sptm.setLore(Arrays.asList(gray + "플레이어의 게임모드를 " + gold + "관전자 모드" + gray + " 로 변경합니다.", "", blue + "-코드: 3"));
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

			// "/blog" 커맨드!
			if (commandLabel.equalsIgnoreCase("blog")) {
				if (args.length == 0) {
					player.sendMessage(
							error + "인자 값이 너무 작거나 없습니다!" + yellow + " /blog help " + red + "를 입력해 더 많은 커맨드를 알아보세요!");
				}

				else if (args.length > 0) {
					if (args[0].equalsIgnoreCase("help")) {
						player.sendMessage(aqua + "= = = = = = = 블로그 튜토리얼 = = = = = = =");
						player.sendMessage(green + "/blog help : 도움말을 표시합니다");
						player.sendMessage(green + "/blog random : 랜덤으로 메세지 1개를 표시합니다.");
						player.sendMessage(green + "/blog config <string> : config.yml 파일에 있는 String 값을 읽어 출력합니다.");
						player.sendMessage(green + "/blog tp <save/move> : 플레이어의 현재 좌표를 저장하거나 저장한 좌표로 텔레포트 합니다.");
						player.sendMessage(green + "/blog inv : 플레이어의 게임모드 변경이 가능한 인벤토리 창을 띄웁니다.");
						player.sendMessage(green + "/blog timer [bar] : 카운트 다운을 실행합니다. (10초)");
						player.sendMessage(green + "/blog cancel : 지금 이 순간 플러그인을 통해 실행되는 모든 것을 취소합니다.");
						player.sendMessage(green + "/blog time <7/12/18/24> : 플레이어가 있는 세계의 시간을 변경합니다.");
						player.sendMessage(red + "/blog bossbar <show/hide/timer> : 플레이어에게 보스바 메세지를 출력합니다.");
						player.sendMessage(green + "/blog potion <nvision, speed> : 플레이어에게 입력한 포션효과를 각각 1분씩 부여합니다.");
						player.sendMessage(green + "/blog nickname <'String'/reset> : 플레이어의 닉네임을 'String' 으로 변경하고, reset 으로 초기화 합니다.");
						player.sendMessage(aqua + "= = = = = = = = = = = = = = = = = = = =");
					}

					else if (args[0].equalsIgnoreCase("random")) {
						int random = (int) (Math.random() * 3);

						switch (random) {
						case 0:
							player.sendMessage(gold + "전체 메세지 중 첫번째 메세지가 랜덤으로 출력되었습니다!");
							break;
						case 1:
							player.sendMessage(gold + "전체 메세지 중 두번째 메세지가 랜덤으로 출력되었습니다!");
							break;
						case 2:
							player.sendMessage(gold + "전체 메세지 중 세번째 메세지가 랜덤으로 출력되었습니다!");
							break;
						default:
							player.sendMessage(warning + "예외 처리 되지 않은 상황이 발생하였습니다!");
							break;
						}
					}

					else if (args[0].equalsIgnoreCase("config")) {
						if (args.length == 1) {
							player.sendMessage(error + "입력하신 인자값이 너무 적거나 없습니다. 아래의 목록에서 config 에 등록된 이름을 찾을 수 있습니다.");
							player.sendMessage(error + "config.yml 에 있는 내용: " + yellow + "first, second");
						} else if (args.length > 1) {
							if (args[1].equalsIgnoreCase("first")) {
								player.sendMessage(prefix + "config.yml 의 first 메세지 입니다.");
								player.sendMessage(prefix + "first: " + gray + plugin.getConfig().getString("first"));
							} else if (args[1].equalsIgnoreCase("second")) {
								player.sendMessage(prefix + "config.yml 의 second 메세지 입니다.");
								player.sendMessage(prefix + "second: " + gray + plugin.getConfig().getString("second"));
							}
						} else {
							player.sendMessage(error + "예기치 못한 에러가 발생하였습니다. 개발자는 확인해 주시기 바랍니다. (ln=" + getLineNumber() + ")");
						}
					}

					else if (args[0].equalsIgnoreCase("perm")) {
						if (player.hasPermission(new Permissions().Example)) {
							player.sendMessage(prefix + "현재 " + aqua + "Example.bypass" + white + " 권한을 가지고 있습니다.");
						}

						else if (!player.hasPermission(new Permissions().Example)) {
							player.sendMessage(prefix + "현재 " + aqua + "Example.bypass" + white + " 권한을 가지고 있지 않습니다.");
						}

						else {
							player.sendMessage(error + "예기치 못한 에러가 발생했습니다. 개발자는 확인해 주세요. (ln=" + getLineNumber() +")");
						}
					}

					else if (args[0].equalsIgnoreCase("inv")) {
						openInv(player);
						player.sendMessage(prefix + "게임모드 변경이 가능한 인벤토리 창을 띄웠습니다. ESC 키를 이용해 닫을 수 있습니다.");
					}
					
					else if (args[0].equalsIgnoreCase("timer")) {
						if(args.length == 1) {
							player.sendMessage(prefix + green + "카운트 다운을 시작합니다.");
							plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
								public void run() {
									for (Player p : Bukkit.getOnlinePlayers()) {
										if (time != -1) {
											if (time != 0) {
												p.playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10F, 1F);
												
												if (time < 6) {
													p.sendMessage(prefix + gold + "카운트 다운 : " + time + " 초");
												}
												
												time--;
											}
											else {
												p.sendMessage(prefix + gold + "카운트 다운 종료!");
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
								player.sendMessage(prefix + green + "카운트 다운을 시작합니다.");
								plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
									public void run() {
										for (Player p : Bukkit.getOnlinePlayers()) {
											if (time != -1) {
												if (time != 0) {
													p.setLevel(time);
													p.playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10F, 1F);
													
													if (time < 6) {
														p.setLevel(time);
														p.sendMessage(prefix + gold + "카운트 다운 : " + time + " 초");
													}
													
													time--;
												}
												else {
													p.setLevel(0);
													p.sendMessage(prefix + gold + "카운트 다운 종료!");
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
						player.sendMessage(prefix + red + "카운트 다운이 강제로 종료되었습니다.");
						Bukkit.getScheduler().cancelAllTasks();
						time = 10;
					}
					
					else if (args[0].equalsIgnoreCase("time")) {
						if (args.length <= 1) {
							player.sendMessage(error + "인자 값이 너무 작거나 없습니다!" + yellow + " /blog help " + red
									+ "를 입력해 더 많은 커맨드를 알아보세요!");
						}
						else if (args.length > 1){
							if (args[1].equalsIgnoreCase("7")) {
								Bukkit.getWorld(world).setTime(0);
								player.sendMessage(prefix + "세계의 시간을 " + yellow + "AM 07:00" + white + " (으)로 변경하였습니다.");
							}
							
							else if (args[1].equalsIgnoreCase("12")) {
								Bukkit.getWorld(world).setTime(6000);
								player.sendMessage(prefix + "세계의 시간을 " + yellow + "PM 12:00" + white + " (으)로 변경하였습니다.");
							}
							
							else if (args[1].equalsIgnoreCase("18")) {
								Bukkit.getWorld(world).setTime(12000);
								player.sendMessage(prefix + "세계의 시간을 " + yellow + "PM 06:00" + white + " (으)로 변경하였습니다.");
							}
							
							else if (args[1].equalsIgnoreCase("24")) {
								Bukkit.getWorld(world).setTime(18000);
								player.sendMessage(prefix + "세계의 시간을 " + yellow + "AM 12:00" + white + " (으)로 변경하였습니다.");
							}
						}
					}
					
//					else if (args[0].equalsIgnoreCase("bossbar")) {
//						if (args.length == 1) {
//							player.sendMessage(error + "인자 값이 너무 작거나 없습니다!" + yellow + " /blog help " + red
//									+ "를 입력해 더 많은 커맨드를 알아보세요!");
//						}
//						else {
//							if(args[1].equalsIgnoreCase("show")) {
//								BarAPI.setMessage(player, yellow + "BarAPI 를 이용해 보스바 메세지를 출력합니다!", 100f);
//							}
//							else if(args[1].equalsIgnoreCase("hide")) {
//								BarAPI.removeBar(player);
//							}
//							else if(args[1].equalsIgnoreCase("timer")) {
//								BarAPI.setMessage(player, gold + "stageroad0820 님이 '경험치 x2' 이벤트를 1분간 실행했습니다!", 60);
//							}
//						}
//					}
					
					else if(args[0].equalsIgnoreCase("potion")) {
						if (args.length == 1) {
							player.sendMessage(
									error + "인자 값이 너무 작거나 없습니다!" + yellow + " /blog help " + red + "를 입력해 더 많은 커맨드를 알아보세요!");
						}
						else {
							if(args[1].equalsIgnoreCase("nvision")) {
								player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1200, 1, false, false));
								player.sendMessage(prefix + "포션 효과 " + yellow + "'야간 투시 (1:00)'" + white + " (이)가 적용되었습니다!");
							}
							else if(args[1].equalsIgnoreCase("speed")) {
								player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 1, false, false));
								player.sendMessage(prefix + "포션 효과 " + yellow + "'신속 (1:00)'" + white + " (이)가 적용되었습니다!");
							}
							else {
								player.sendMessage(error + "알 수 없는 포션 효과 입니다. 아래의 목록에 있는 포션 효과를 입력해 주세요.");
								player.sendMessage(error + yellow + "nvision, speed");
							}
						}
					}
					
					else if(args[0].equalsIgnoreCase("nickname")) {
						if (args.length == 1) {
							player.sendMessage(
									error + "인자 값이 너무 작거나 없습니다!" + yellow + " /blog help " + red + "를 입력해 더 많은 커맨드를 알아보세요!");
						}
						else {
							String realName = player.getName();
							
							if(args[1].equals("reset")) {
								player.setDisplayName(realName);
								player.setPlayerListName(realName);
								
								player.sendMessage(prefix + "플레이어의 이름을 원래대로 설정하였습니다.");
							}
							else if(args[1].equals(realName) || args[1].equals(player.getDisplayName())){
								player.sendMessage(error + "변경하려는 이름이 현재 설정된 이름과 동일합니다. 다시 입력해 주세요!");
							}
							else {
								player.setDisplayName(args[1]);
								player.setPlayerListName(args[1]);
								
								player.sendMessage(prefix + "플레이어의 닉네임이 " + yellow + realName + white + " 에서 " 
										+ yellow + player.getDisplayName() + white + " (으)로 변경되었습니다.");
							}
						}
					}
					
					else if(args[0].equalsIgnoreCase("enum")) {
						if (args.length == 1) {
							player.sendMessage(error + "열거 값이 입력되지 않거나 알 수 없는 열거형 값입니다. 아래의 값을 참고해 주세요.");
							player.sendMessage(error + yellow + "apple, chocolate, ramen, rice, corn, sugar_cube");
						}
						else {
							for (EnumTest e : EnumTest.getFoods()) {
								if(args[1].equalsIgnoreCase(e.foodName)) {
									String result = e.healthy ? "건강한 음식입니다." : "건강하지 않은 음식입니다.";
									String fdName = "";
									
									switch(e.foodName) {
									case "apple":
										fdName = "사과";
										break;
									case "chocolate":
										fdName = "초콜릿";
										break;
									case "ramen":
										fdName = "라면";
										break;
									case "rice":
										fdName = "밥";
										break;
									case "corn":
										fdName = "옥수수";
										break;
									case "sugar_cube":
										fdName = "각설탕";
										break;
									default:
										break;
									}
									
									player.sendMessage(prefix + fdName + " 은(는) " + result);
									break;
								}
							}
						}
					}
					
					if (player.isOp() == true) {
						if (args[0].equalsIgnoreCase("tp")) {
							if (args.length == 1) {
								player.sendMessage(error + "인자 값이 너무 작거나 없습니다!" + yellow + " /blog help " + red
										+ "를 입력해 더 많은 커맨드를 알아보세요!");
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
											prefix + "플레이어 " + yellow + player.getName() + white + " 의 좌표가 저장되었습니다.");
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

										player.sendMessage(prefix + "저장된 위치로 텔레포트 되었습니다!");
									}

									else {
										player.sendMessage(error + "저장된 좌표가 없습니다! " + yellow + "/blog tp save" + red
												+ " 를 이용해 좌표를 저장해 주세요!");
									}
								}
							}

							else {
								player.sendMessage(error + "예기치 못한 에러가 발생하였습니다. 개발자는 확인해 주시기 바랍니다. (ln=" + getLineNumber() + ")");
							}
						}
					}

					else {
						player.sendMessage(error + "이 커맨드를 사용할 충분한 권한이 부여되지 않았습니다! 관리자에게 문의해 보세요.");
					}
				}
			}
		}
		else {
			if(command.getName().equalsIgnoreCase("cs")) {
				if(args.length == 0) {
					sender.sendMessage(error + "인자 값이 너무 적거나 없습니다. 다른 커맨드를 입력해 주세요!");
				}
				else {
					if(args[0].equalsIgnoreCase("test")) {
						sender.sendMessage(prefix + "콘솔 커맨드가 정상적으로 작동합니다! (멀티 커맨드 1");
					}
					else if (args[0].equalsIgnoreCase("exam")) {
						if(args.length == 1) {
							sender.sendMessage(error + "인자 값이 너무 적거나 없습니다. 다른 커맨드를 입력해 주세요!");
						}
						else {
							if (args[1].equalsIgnoreCase("t1")) {
								sender.sendMessage(prefix + "콘솔 커맨드가 정상적으로 작동합니다! (멀티 커맨드 2)");
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
