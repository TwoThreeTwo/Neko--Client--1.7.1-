package lgbt.vaimok.neko.nekohax.modules.client;

import lgbt.vaimok.neko.nekohax.NekoHax;
import lgbt.vaimok.neko.nekohax.guiscreen.settings.Setting;
import lgbt.vaimok.neko.nekohax.modules.Category;
import lgbt.vaimok.neko.nekohax.modules.Module;

public class ClickHUD extends Module {

	public ClickHUD() {
		super(Category.client);

		this.name        = "HUD";
		this.tag         = "HUD";
		this.description = "gui for pinnables";
	}

	Setting frame_view = create("info", "HUDStringsList", "Strings");

	Setting strings_r = create("Color R", "HUDStringsColorR", 255, 0, 255);
	Setting strings_g = create("Color G", "HUDStringsColorG", 255, 0, 255);
	Setting strings_b = create("Color B", "HUDStringsColorB", 255, 0, 255);
	Setting strings_a = create("Alpha", "HUDStringsColorA", 230, 0, 255);
	Setting compass_scale = create("Compass Scale", "HUDCompassScale", 16, 1, 60);
	Setting arraylist_mode = create("ArrayList", "HUDArrayList", "Free", combobox("Free", "Top R", "Top L", "Bottom R", "Bottom L"));
	Setting show_all_pots = create("All Potions", "HUDAllPotions", false);
	Setting max_player_list = create("Max Players", "HUDMaxPlayers", 24, 1, 64);

	@Override
	public void enable() {
		if (mc.world != null && mc.player != null) {
			NekoHax.get_hack_manager().get_module_with_tag("GUI").set_active(false);
				
			NekoHax.click_hud.back = false;

			mc.displayGuiScreen(NekoHax.click_hud);
		}
	}
}