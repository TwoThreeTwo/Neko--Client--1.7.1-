package lgbt.vaimok.neko.nekohax.modules.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import lgbt.vaimok.neko.nekohax.modules.Category;
import lgbt.vaimok.neko.nekohax.modules.Module;
import lgbt.vaimok.neko.nekohax.util.FriendUtil;
import lgbt.vaimok.neko.nekohax.util.MessageUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;


public class VisualRange extends Module {

	private List<String> people;

	public VisualRange() {
		super(Category.chat);

		this.name        = "Visual Range";
		this.tag         = "VisualRange";
		this.description = "bc using ur eyes is overrated";
	}

	@Override
	public void enable() {
		people = new ArrayList<>();
	}

	@Override
	public void update() {
		if (mc.world == null | mc.player == null) return;

		List<String> peoplenew = new ArrayList<>();
		List<EntityPlayer> playerEntities = mc.world.playerEntities;

		for (Entity e : playerEntities) {
			if (e.getName().equals(mc.player.getName())) continue;
			peoplenew.add(e.getName());
		}

		if (peoplenew.size() > 0) {
			for (String name : peoplenew) {
				if (!people.contains(name)) {
					if (FriendUtil.isFriend(name)) {
						MessageUtil.send_client_message("I see an epic dude called " + ChatFormatting.RESET + ChatFormatting.GREEN + name + ChatFormatting.RESET + " :D");
					} else {
						MessageUtil.send_client_message("I see a dude called " + ChatFormatting.RESET + ChatFormatting.RED + name + ChatFormatting.RESET + ". Yuk");
					}
					people.add(name);
				}
			}
		}

	}
}
