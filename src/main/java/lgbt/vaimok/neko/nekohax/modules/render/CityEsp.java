package lgbt.vaimok.neko.nekohax.modules.render;

import lgbt.vaimok.neko.nekohax.event.events.EventRender;
import lgbt.vaimok.neko.nekohax.guiscreen.settings.Setting;
import lgbt.vaimok.neko.nekohax.modules.Category;
import lgbt.vaimok.neko.nekohax.modules.Module;
import lgbt.vaimok.neko.nekohax.turok.draw.RenderHelp;
import lgbt.vaimok.neko.nekohax.util.EntityUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class CityEsp extends Module {

    public CityEsp() {
        super(Category.render);

        this.name = "CityESP";
        this.tag = "City ESP";
        this.description = "jumpy isnt gonna be happy about this";

    }

    Setting endcrystal_mode = create("EndCrystal", "CityEndCrystal", true);
    Setting mode = create("Mode", "CityMode", "Pretty", combobox("Pretty", "Solid", "Outline"));
    Setting off_set = create("Height", "CityOffSetSide", 0.2, 0.0, 1.0);
    Setting range = create("Range", "CityRange", 6, 1, 12);
    Setting r = create("R", "CityR", 0, 0, 255);
    Setting g = create("G", "CityG", 255, 0, 255);
    Setting b = create("B", "CityB", 0, 0, 255);
    Setting a = create("A", "CityA", 50, 0, 255);

    List<BlockPos> blocks = new ArrayList<>();

    boolean outline = false;
    boolean solid   = false;

    @Override
    public void update() {
        blocks.clear();
        for (EntityPlayer player : mc.world.playerEntities) {
            if (mc.player.getDistance(player) > range.get_value(1) || mc.player == player) continue;

            BlockPos p = EntityUtil.is_cityable(player, endcrystal_mode.get_value(true));

            if (p != null) {
                blocks.add(p);
            }
        }
    }

    @Override
    public void render(EventRender event) {

        float off_set_h = (float) off_set.get_value(1.0);

        for (BlockPos pos : blocks) {

            if (mode.in("Pretty")) {
                outline = true;
                solid   = true;
            }

            if (mode.in("Solid")) {
                outline = false;
                solid   = true;
            }

            if (mode.in("Outline")) {
                outline = true;
                solid   = false;
            }

            if (solid) {
                RenderHelp.prepare("quads");
                RenderHelp.draw_cube(RenderHelp.get_buffer_build(),
                        pos.getX(), pos.getY(), pos.getZ(),
                1, off_set_h, 1,
                r.get_value(1), g.get_value(1), b.get_value(1), a.get_value(1),
                "all"
                );

                RenderHelp.release();
            }


            if (outline) {
                RenderHelp.prepare("lines");
                RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(),
                    pos.getX(), pos.getY(), pos.getZ(),
                    1, off_set_h, 1,
                    r.get_value(1), g.get_value(1), b.get_value(1), a.get_value(1),
                    "all"
                );

                RenderHelp.release();
            }
        }
    }
}


