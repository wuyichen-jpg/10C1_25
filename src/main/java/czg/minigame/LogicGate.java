package czg.minigame;

import czg.scenes.minigame.ComputerScienceLevelScene;
import czg.util.Images;

import java.awt.*;
import java.util.Random;

/**
 * Informationen über die verschiedenen Logikgatter für {@link ComputerSciencePuzzle}
 * und {@link ComputerScienceLevelScene}
 */
public enum LogicGate {

    AND("/assets/minigames/computer_science/and_gate.png"),
    OR("/assets/minigames/computer_science/or_gate.png"),
    NOT("/assets/minigames/computer_science/not_gate.png"),
    NAND("/assets/minigames/computer_science/nand_gate.png"),
    NOR("/assets/minigames/computer_science/nor_gate.png"),
    XOR("/assets/minigames/computer_science/xor_gate.png"),
    XNOR("/assets/minigames/computer_science/xnor_gate.png");

    public final Image sprite;

    LogicGate(String path) {
        this.sprite = Images.get(path);
    }

    /**
     * Gibt eine zufällige Enum-Konstante zurück
     * @return Enum-Konstante
     */
    public static LogicGate getRandom() {
        int r = new Random().nextInt(values().length);
        return LogicGate.values()[r];
    }

    /**
     * Gibt einen Array von zufälligen Enum-Konstanten zurück.
     * Dabei kommt jeder Wert im Array nur ein Mal vor.
     * @param length Wie viele Elemente?
     * @param excludedGate Dieses Element wird nicht im Ergebnis enthalten sein
     * @return Array mit Enum-Konstanten
     */
    public static LogicGate[] getRandomArray(int length, LogicGate excludedGate) {
        LogicGate[] tmp = new LogicGate[length];
        LogicGate[] usedGates = new LogicGate[length];

        for (int i = 0; i < length; i++) {
            boolean valid = false;
            LogicGate rGate = null;
            while (!valid) {
                valid = true;

                rGate = getRandom();

                if (rGate == excludedGate) {
                    valid = false;
                    continue;
                }
                for (int j = 0; j < length; j++) {
                    if (usedGates[j] == rGate) {
                        valid = false;
                        break;
                    }
                }
            }
            tmp[i] = rGate;
            usedGates[i] = rGate;
        }

        return tmp;
    }
}
