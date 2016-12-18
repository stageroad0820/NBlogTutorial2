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

	// ���� ���̴� String �� ����
	String prefix = ChatColor.AQUA + "[TUT] " + ChatColor.WHITE + "";
	String info = ChatColor.YELLOW + "[Info]" + ChatColor.WHITE + "";
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
		console(cinfo + " (��)�� Ȱ��ȭ �Ǵ� �� �Դϴ�.");
	}

	@Override
	public void onDisable() {
		console(cinfo + " (��)�� ��Ȱ��ȭ �Ǵ� �� �Դϴ�.");

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		Player player = (Player) sender;

		// "/blog" Ŀ�ǵ�!
		if (commandLabel.equalsIgnoreCase("blog")) {
			if (args.length == 0) {
				player.sendMessage(
						error + "���� ���� �ʹ� �۰ų� �����ϴ�!" + yellow + " /blog help " + red + "�� �Է��� �� ���� Ŀ�ǵ带 �˾ƺ�����!");
			}
			
			else if (args.length > 0) {
				if(args[0].equalsIgnoreCase("help")) {
					player.sendMessage(aqua + "= = = = = = = ��α� Ʃ�丮�� = = = = = = =");
					player.sendMessage(green + "/blog help : ������ ǥ���մϴ�");
					player.sendMessage(green + "/blog random : �������� �޼��� 1���� ǥ���մϴ�.");
					player.sendMessage(aqua + "= = = = = = = = = = = = = = = = = = = =");
				}
				
				else if(args[0].equalsIgnoreCase("random")) {
					int random = (int)(Math.random()*3);
					
					switch(random) {
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
			}
		}

		return false;
	}

	public void console(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}

}
