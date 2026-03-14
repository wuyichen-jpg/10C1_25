package czg.sound;

import java.util.function.Consumer;

public class EndOfFileBehaviour {

    /**
     * Von Anfang an abspielen
     */
    public static final EndOfFileBehaviour LOOP = new EndOfFileBehaviour(sound -> sound.seek(0));

    /**
     * Zurück zum Anfang spulen und pausieren
     */
    public static final EndOfFileBehaviour RESTART_AND_PAUSE = new EndOfFileBehaviour(sound -> {
        LOOP.function.accept(sound);
        sound.setPlaying(false);
    });

    /**
     * Stoppen
     */
    public static final EndOfFileBehaviour STOP = new EndOfFileBehaviour(BaseSound::stop);

    /**
     * Auszuführende Funktion
     */
    public final Consumer<BaseSound> function;

    /**
     * Erstellt ein neues {@link EndOfFileBehaviour}-Objekt, welches nach
     * der {@link #function} dieses Objekts zusätzlich die in dieser Funktion
     * angegebene ausführt
     * @param followUpFunction Zusätzlich auszuführende Funktion
     * @return Ein neues {@link EndOfFileBehaviour}-Objekt
     */
    public EndOfFileBehaviour andThen(Consumer<BaseSound> followUpFunction) {
        return new EndOfFileBehaviour(this.function.andThen(followUpFunction));
    }

    private EndOfFileBehaviour(Consumer<BaseSound> function) {
        this.function = function;
    }

}
