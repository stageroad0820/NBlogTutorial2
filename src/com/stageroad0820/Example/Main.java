package com.stageroad0820.Example;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Main plugin;

	PluginDescriptionFile pdfFile = this.getDescription();

	// 많이 쓰이는 String 값 정리
	String prefix = ChatColor.AQUA + "[TUT] " + ChatColor.WHITE + "";
	String info = ChatColor.YELLOW + "[Info]" + ChatColor.WHITE + "";
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
		console(cinfo + " (이)가 활성화 되는 중 입니다.");
	}

	@Override
	public void onDisable() {
		console(cinfo + " (이)가 비활성화 되는 중 입니다.");

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		Player player = (Player) sender;

		// "/blog" 커맨드!
		if (commandLabel.equalsIgnoreCase("blog")) {
			if (args.length == 0) {
				player.sendMessage(
						error + "인자 값이 너무 작거나 없습니다!" + yellow + " /blog help " + red + "를 입력해 더 많은 커맨드를 알아보세요!");
			}
			
			else if (args.length > 0) {
				if(args[0].equalsIgnoreCase("help")) {
					player.sendMessage(aqua + "= = = = = = = 블로그 튜토리얼 = = = = = = =");
					player.sendMessage(green + "/blog help : 도움말을 표시합니다");
					player.sendMessage(green + "/blog random : 랜덤으로 메세지 1개를 표시합니다.");
					player.sendMessage(aqua + "= = = = = = = = = = = = = = = = = = = =");
				}
				
				else if(args[0].equalsIgnoreCase("random")) {
					int random = (int)(Math.random()*3);
					
					switch(random) {
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
			}
		}

		return false;
	}

	public void console(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}

}
