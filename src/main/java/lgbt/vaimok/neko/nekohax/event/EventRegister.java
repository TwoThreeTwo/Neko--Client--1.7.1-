package lgbt.vaimok.neko.nekohax.event;

import lgbt.vaimok.neko.nekohax.manager.CommandManager;
import lgbt.vaimok.neko.nekohax.manager.EventManager;
import net.minecraftforge.common.MinecraftForge;


public class EventRegister {
	public static void register_command_manager(CommandManager manager) {
		MinecraftForge.EVENT_BUS.register(manager);
	}

	public static void register_module_manager(EventManager manager) {
		MinecraftForge.EVENT_BUS.register(manager);
	}
}