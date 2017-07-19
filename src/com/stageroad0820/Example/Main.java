package com.stageroad0820.Example;

import java.util.Arrays;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Main extends JavaPlugin implements Listener {
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Main plugin;
	
	private Scoreboard board;
	private Objective ob;
	private Score score;

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

	int time = 10;
	
	@Override
	public void onEnable() {
		Commands.setPlugin(this);
		
		getCommand("blog").setExecutor(new Commands());
		getCommand("cs").setExecutor(new Commands());
		
		pm.registerEvents(this, this);
		pm.registerEvents(new Commands(), this);

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
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player victim = event.getEntity().getPlayer();
		Entity killer = event.getEntity().getKiller();

		if (event.getDeathMessage().contains("fell from a high place")) {
			event.setDeathMessage(prefix + yellow + victim.getName() + white + " 님이 " + aqua + "앞을 보지 않고 달리다가 떨어졌습니다.");
		}

		else if (event.getDeathMessage().contains("went up in flames")) {
			event.setDeathMessage(
					prefix + yellow + victim.getName() + white + " 님이 " + aqua + "너무 추운 나머지 불속으로 뛰어 들었습니다.");
		}

		else if (killer instanceof Player) {
			if (event.getDeathMessage().contains("was slain by")) {
				event.setDeathMessage(prefix + yellow + victim.getName() + white + " 님이 " + red + killer.getName()
						+ white + " 님 에게 " + dred + "극악무도하게 살해당하였습니다.");
			}

			else if (event.getDeathMessage().contains("was shot by")) {
				event.setDeathMessage(prefix + red + killer.getName() + white + " 님이 " + yellow + victim.getName()
						+ white + " 님에게 " + aqua + "화살이라는 것이 무엇인지 몸으로 직접 알게 해 주었습니다.");
			}
		}
	}

	@EventHandler
	public void onPlayerEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();

		ItemStack is = new ItemStack(Material.ARROW);
		ItemMeta im = is.getItemMeta();

		im.setDisplayName(aqua + "체력 회복");
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
		im.setLore(Arrays.asList(gray + "마나를 3 소모하여 캐릭터의 체력을 모두 회복합니다."));
		is.setItemMeta(im);

		player.getInventory().addItem(is);

		scboard(player);
		
		event.setJoinMessage(prefix + yellow + player.getName() + white + " 님이 서버에 접속하셨습니다!");
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		ItemStack is = new ItemStack(Material.ARROW);
		ItemMeta im = is.getItemMeta();

		im.setDisplayName(aqua + "체력 회복");
		im.setLore(Arrays.asList(gray + "마나를 3 소모하여 캐릭터의 체력을 모두 회복합니다."));
		is.setItemMeta(im);

		player.getInventory().removeItem(is);

		event.setQuitMessage(prefix + yellow + player.getName() + white + " 님이 서버에서 나가셨습니다!");
	}
	
	@SuppressWarnings("deprecation")
	public void scboard (Player player) {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        
        board = sm.getNewScoreboard();
        ob = board.registerNewObjective("Test", "dummy");
        score = ob.getScore("Score");
        
        Team team = board.registerNewTeam("TestTeam");
        
        team.addPlayer(player);
        team.setPrefix(green + "");
        team.setCanSeeFriendlyInvisibles(true);
       
        ob.setDisplayName(aqua + "스코어보드 테스트!");
        ob.setDisplaySlot(DisplaySlot.SIDEBAR);
       
        score.setScore(20);
       
        player.setScoreboard(board);
    }

	public void console(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}
}