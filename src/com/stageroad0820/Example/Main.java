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

	// 많이 쓰이는 String 값 정리
	String prefix = ChatColor.AQUA + "[TUT] " + ChatColor.WHITE + "";
	String info = ChatColor.YELLOW + "[Info] " + ChatColor.WHITE + "";
	String error = ChatColor.DARK_RED + "[Error] " + ChatColor.RED + "";
	String warning = ChatColor.DARK_RED + "[Warning] " + ChatColor.RED + "";
	String plname = ChatColor.GREEN + pdfFile.getName() + "";
	String plvers = ChatColor.GREEN + pdfFile.getVersion() + "";
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

		console(cinfo + " (이)가 활성화 되는 중 입니다.");
	}

	@Override
	public void onDisable() {
		saveConfig();

		console(cinfo + " (이)가 비활성화 되는 중 입니다.");
	}

	@EventHandler
	public void onPlayerEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();

		ItemStack is = new ItemStack(Material.ARROW);
		ItemMeta im = is.getItemMeta();

		im.setDisplayName(aqua + "체력 회복");
		ArrayList<String> il = new ArrayList<>();
		im.setLore(Arrays.asList(gray + "마나를 3 소모하여 캐릭터의 체력을 모두 회복합니다."));
		is.setItemMeta(im);

		int mp = player.getFoodLevel();

		if (action.equals(Action.LEFT_CLICK_AIR) && player.getItemInHand().equals(is)) {
			if (mp == 0 || mp <= 3) {
				player.sendMessage(error + "스킬을 사용하기 위한 마나가 부족합니다. 배고픔을 해결하여 마나를 충전해 주세요!");
			}

			else if (mp > 4) {
				player.setHealth(20.0);
				player.sendMessage(info + yellow + player.getName() + white + " 의 체력을 모두 회복하였습니다.");
			}
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		ItemStack is = new ItemStack(Material.ARROW);
		ItemMeta im = is.getItemMeta();

		im.setDisplayName(aqua + "체력 회복");
		ArrayList<String> il = new ArrayList<>();
		im.setLore(Arrays.asList(gray + "마나를 3 소모하여 캐릭터의 체력을 모두 회복합니다."));
		is.setItemMeta(im);

		player.getInventory().addItem(is);

		event.setJoinMessage(prefix + yellow + player.getName() + white + " 님이 서버에 접속하셨습니다!");
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		ItemStack is = new ItemStack(Material.ARROW);
		ItemMeta im = is.getItemMeta();

		im.setDisplayName(aqua + "체력 회복");
		ArrayList<String> il = new ArrayList<>();
		im.setLore(Arrays.asList(gray + "마나를 3 소모하여 캐릭터의 체력을 모두 회복합니다."));
		is.setItemMeta(im);

		player.getInventory().removeItem(is);

		event.setQuitMessage(prefix + yellow + player.getName() + white + " 님이 서버에서 나가셨습니다!");
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if(!event.getInventory().getTitle().equalsIgnoreCase("게임모드 변경"))
			return;
		
		switch(event.getCurrentItem().getType()) {
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
		ArrayList<String> srvl = new ArrayList<String>();
		srvm.setLore(Arrays.asList(gray + "플레이어의 게임모드를 " + gold + "서바이벌 모드" + gray + " 로 변경합니다."
				, "", blue + "-코드: 0"));
		srv.setItemMeta(srvm);
		
		ItemStack ctv = new ItemStack(Material.DIAMOND_BLOCK);
		ItemMeta ctvm = ctv.getItemMeta();
		
		ctvm.setDisplayName(gold + "크리에이티브 모드");
		ArrayList<String> ctvl = new ArrayList<String>();
		ctvm.setLore(Arrays.asList(gray + "플레이어의 게임모드를 " + gold + "크리에이티브 모드" + gray + " 로 변경합니다."
				, "", blue + "-코드: 1"));
		ctv.setItemMeta(ctvm);
		
		ItemStack adv = new ItemStack(Material.LEATHER_HELMET);
		ItemMeta advm = adv.getItemMeta();
		
		advm.setDisplayName(gold + "어드벤처 모드");
		ArrayList<String> advl = new ArrayList<String>();
		advm.setLore(Arrays.asList(gray + "플레이어의 게임모드를 " + gold + "어드벤처 모드" + gray + " 로 변경합니다."
				, "", blue + "-코드: 2"));
		adv.setItemMeta(advm);
		
		ItemStack spt = new ItemStack(Material.GLASS);
		ItemMeta sptm = spt.getItemMeta();
		
		sptm.setDisplayName(gold + "관전자 모드");
		ArrayList<String> sptl = new ArrayList<String>();
		sptm.setLore(Arrays.asList(gray + "플레이어의 게임모드를 " + gold + "관전자 모드" + gray + " 로 변경합니다."
				, "", blue + "-코드: 3"));
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
							player.sendMessage(prefix + "first: " + gray + getConfig().getString("first"));
						} else if (args[1].equalsIgnoreCase("second")) {
							player.sendMessage(prefix + "config.yml 의 second 메세지 입니다.");
							player.sendMessage(prefix + "second: " + gray + getConfig().getString("second"));
						}
					} else {
						player.sendMessage(error + "예기치 못한 에러가 발생하였습니다. 개발자는 확인해 주시기 바랍니다. (ln=203)");
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
						player.sendMessage(error + "예기치 못한 에러가 발생했습니다. 개발자는 확인해 주세요. (ln=217)");
					}
				}
				
				else if (args[0].equalsIgnoreCase("inv")) {
					openInv(player);
					player.sendMessage(prefix + "게임모드 변경이 가능한 인벤토리 창을 띄웠습니다. ESC 키를 이용해 닫을 수 있습니다.");
				}

				if (player.isOp() == true) {
					if (args[0].equalsIgnoreCase("tp")) {
						if (args.length == 1) {
							player.sendMessage(error + "인자 값이 너무 작거나 없습니다!" + yellow + " /blog help " + red
									+ "를 입력해 더 많은 커맨드를 알아보세요!");
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
										prefix + "플레이어 " + yellow + player.getName() + white + " 의 좌표가 저장되었습니다.");
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

									player.sendMessage(prefix + "저장된 위치로 텔레포트 되었습니다!");
								}

								else {
									player.sendMessage(error + "저장된 좌표가 없습니다! " + yellow + "/blog tp save" + red
											+ " 를 이용해 좌표를 저장해 주세요!");
								}
							}
						}

						else {
							player.sendMessage(error + "예기치 못한 에러가 발생하였습니다. 개발자는 확인해 주시기 바랍니다. (ln=262)");
						}
					}
				}

				else {
					player.sendMessage(error + "이 커맨드를 사용할 충분한 권한이 부여되지 않았습니다! 관리자에게 문의해 보세요.");
				}
			}
		}

		return false;
	}

	public void console(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}

}