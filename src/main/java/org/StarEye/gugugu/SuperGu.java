package org.StarEye.gugugu;

import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.jetbrains.annotations.NotNull;

public class SuperGu extends JavaPlugin {
	HashMap<String, String> signin = new HashMap<>();
	public static SuperGu INSTANCE = new SuperGu();

	private SuperGu() {
		super(new JvmPluginDescriptionBuilder("com.StarEye.plugin.gugugu", "1.0.0").author("me").name("template")
				.info("prototype").build());
	}

	@Override
	public void onLoad(@NotNull PluginComponentStorage $this$onLoad) {

	}

	@Override
	public void onEnable() {
		getLogger().info("日志");
		GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, g -> {
			// 监听群消�?
			if (g.getGroup().getId() == 984084494) {
				String mess = g.getMessage().serializeToMiraiCode();
				if(mess.contains("特色功能"))
				{
					g.getGroup().sendMessage("日羽毛");
				}
				if (mess.contains("[mirai:at:1353537643]")) {
					if (mess.contains("取消报名")) {
						if (signin.containsKey(g.getSender().getId() + "")) {
							signin.remove(g.getSender().getId() + "");
							g.getGroup().sendMessage("已经取消报名:" + g.getSender().getNick());
						} else {
							g.getGroup().sendMessage("用户未报名：" + g.getSender().getNick());
						}
					} else if (mess.contains("报名")) {
						if (signin.containsKey(g.getSender().getId() + "")) {
							g.getGroup().sendMessage("你都报完名了MD�?" + g.getSender().getNick());
						}
						else
						{
							signin.put(g.getSender().getId() + "", g.getSender().getNick());
							g.getGroup().sendMessage("报名成功�?" + g.getSender().getNick());
						}
					}
					if (mess.contains("分桌")) {
						if (signin.size() <= 10) {
							g.getGroup().sendMessage("�?" + signin.size() + "个人，分�?么桌分卓");
						} else {
							if (signin.size() > 20) {
								g.getGroup().sendMessage("这个人很懒，还没�?发超�?20分分�?");
							} else {
								 Iterator<Entry<String, String>> iter = signin.entrySet().iterator();
								 List<String> groupA=new ArrayList<>();
								 List<String> groupB=new ArrayList<>();
								for (int i = 0; i < signin.size(); i++) {
									if(groupA.size()==signin.size()/2)
									{
										while(iter.hasNext())
										{
											groupB.add(iter.next().getValue());
										}
										break;
									}
									if ( ((int) (Math.random() * 100))%2 == 0) {
										groupA.add(iter.next().getValue());
									}
									else
									{
										groupB.add(iter.next().getValue());
									}
								}
								g.getGroup().sendMessage("分组1成员:"+groupA.toString());
								g.getGroup().sendMessage("分组2成员:"+groupB.toString());
							}
						}
					}
					if (mess.contains("人数")) {
						g.getGroup().sendMessage("当前报名人数:" + signin.size());
					}
					if (mess.contains("清空")) {
						if(g.getSender().getId()==763236586)
						{
							signin.clear();
							g.getGroup().sendMessage("人数已清�?");
						}
					}
				}
			}
			getLogger().info(g.getMessage().contentToString());

		});
	}
}