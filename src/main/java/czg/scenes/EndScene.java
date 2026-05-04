package czg.scenes;

import czg.objects.*;
import czg.sound.BaseSound;
import czg.sound.ClipSound;
import czg.sound.EndOfFileBehaviour;
import czg.sound.StreamSound;
import czg.util.Draw;
import czg.util.Images;
import czg.util.Sounds;
import czg.util.character_creator.CharacterCreator;

import java.awt.*;

import static czg.MainWindow.*;

public class EndScene extends BaseScene {

    public int veryImportantTimer = 10 * 60 * FPS;
    private BaseSound someSound = null;
    private int otherTimer = -1;

    public EndScene() {
        objects.add(new BackdropObject(Images.get("/assets/background/Endbildschirm.png")));

        objects.add(PlayerObject.INSTANCE);
        CharacterCreator.enabled = false;
        PlayerObject.INSTANCE.allowInventory = false;
        PlayerObject.INSTANCE.x = 56 * PIXEL_SCALE;
        PlayerObject.INSTANCE.y = 72 * PIXEL_SCALE;

        Sounds.HALLWAY_MUSIC.stop();
        sounds.get().addSound(new StreamSound("/assets/sound/mensa.ogg", true, EndOfFileBehaviour.LOOP));
    }

    @Override
    public void update() {
        super.update();

        if(veryImportantTimer > 0) {
            veryImportantTimer--;
            return;
        }

        if(someSound == null) {
            sounds.get().removeAndStopAllSounds();
            someSound = new ClipSound("/assets/sound/record_scratch.ogg", true, EndOfFileBehaviour.STOP);
            return;
        }

        if(someSound.isStopped() && otherTimer == -1) {
            new StreamSound("/assets/sound/fight_intro.ogg", true, EndOfFileBehaviour.STOP);
            BaseObject someone = new BaseObject(Images.get("/assets/characters/a_certain_someone.png"), 120 * PIXEL_SCALE, 67 * PIXEL_SCALE);
            objects.add(someone);
            objects.add(new ItemObject(ItemType.PÄDAGOGIK, 67, someone.x - 10 * PIXEL_SCALE, someone.y + someone.height / 2));
            otherTimer = 4 * FPS;
        }

        if(otherTimer > 0) {
            otherTimer--;
            if(otherTimer == 0) {
                System.exit(67);
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        if(otherTimer != -1) {
            g.setColor(Color.GRAY);
            g.setFont(Draw.FONT_INFO.deriveFont(30f));
            Draw.drawTextCentered(g, "%.2fs".formatted((otherTimer * 1d) / FPS), PlayerObject.INSTANCE.x + PIXEL_SCALE * 50, HEIGHT - 50 * PIXEL_SCALE, true);
        }
    }
}
