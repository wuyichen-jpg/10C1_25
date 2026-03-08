package czg.objects;

import czg.scenes.BaseScene;
import czg.sound.BaseSound;
import czg.util.Draw;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.function.DoubleFunction;

public class VolumeControllerObject extends BaseObject {

    public static final DoubleFunction<Double> LINEAR = x -> -x + 1;
    public static final DoubleFunction<Double> QUADRATIC = x -> -(x * x) + 1;

    private static final float MUTE_THRESHOLD_DB = -30f;

    private final BaseSound sound;
    private final double minDistance, maxDistance;
    private final boolean pauseIfMute;
    private final DoubleFunction<Double> function;
    private final float fullVolume;

    private float result;
    private float volume;

    public VolumeControllerObject(int x, int y, BaseSound sound, double minDistance, double maxDistance, boolean pauseIfMute, DoubleFunction<Double> function) {
        super(null, x, y);
        this.sound = sound;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.pauseIfMute = pauseIfMute;
        this.function = function;
        this.fullVolume = sound.getVolumeControl().getValue();
    }

    @Override
    public void update(BaseScene scene) {
        // https://www.desmos.com/calculator/jms6df9p16
        // Ergibt einen Wert zwischen 0 (Stille) und 1 (Normale Lautstärke)
        volume = function.apply(
                Math.min(1, Math.max(
                                0,
                                Point2D.distance(PlayerObject.INSTANCE.x, PlayerObject.INSTANCE.y, x, y) - minDistance
                        ) / maxDistance
                )).floatValue();

        result = MUTE_THRESHOLD_DB + (fullVolume - (MUTE_THRESHOLD_DB)) * volume;

        if(sound.isPlaying() && result <= MUTE_THRESHOLD_DB+1 && pauseIfMute) {
            sound.setPlaying(false);
        }
        if(! sound.isPlaying() && result >= MUTE_THRESHOLD_DB+1 && pauseIfMute) {
            sound.setPlaying(true);
        }

        sound.getVolumeControl().setValue(result);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.setFont(Draw.FONT_TITLE.deriveFont(16f));
        Draw.drawTextCentered(g, "Vol: %.2f".formatted(volume),x,y);
        Draw.drawTextCentered(g, "Res: %.2f".formatted(result),x, (int) (y+g.getFont().getSize()*1.3));
        Draw.drawTextCentered(g, "Mut: %s".formatted(!sound.isPlaying()), x, (int)(y+g.getFont().getSize()*1.3*2));
    }
}
