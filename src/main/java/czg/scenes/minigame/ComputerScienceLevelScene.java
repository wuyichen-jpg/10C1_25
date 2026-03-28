package czg.scenes.minigame;

import czg.MainWindow;
import czg.minigame.ComputerSciencePuzzle;
import czg.objects.BaseObject;
import czg.objects.ButtonObject;
import czg.objects.Department;

/**
 * Level des Informatik-Minigames, in welchem Logikgatter in einem
 * Schaltkreis erkannt werden müssen.
 */
public class ComputerScienceLevelScene extends LevelScene {

    /**
     * Neue Level-Szene erstellen
     * @param level Entweder {@code 0}, {@code 1} oder {@code 2}
     */
    public ComputerScienceLevelScene(int level) {
        super(Department.COMPUTER_SCIENCE, level);

        // Zufällig eines von drei für dieses Level verfügbaren
        // Rätsel auswählen
        ComputerSciencePuzzle puzzle = ComputerSciencePuzzle.getPuzzle(level);

        int availableHeight = (int) (MainWindow.HEIGHT * 0.7);
        int gateHeight = availableHeight / puzzle.answers.length;

        // ButtonObjects für die Antwortmöglichkeiten
        for (int i = 0; i < puzzle.answers.length; i++) {
            int finalI = i;
            objects.add(new ButtonObject(
                    puzzle.answers[i].sprite,
                    (int) (MainWindow.WIDTH * 0.125),
                    (MainWindow.HEIGHT - availableHeight) / 2 + i * gateHeight,
                    () -> {
                        if (puzzle.answers[finalI] == puzzle.solution)
                            levelWon();
                        else
                            levelLost();
                    })
            );
        }

        // Das eigentliche Rätsel wird durch ein Bild dargestellt
        objects.add(new BaseObject(
                puzzle.sprite,
                (int) (MainWindow.WIDTH * 0.3),
                (MainWindow.HEIGHT - availableHeight) / 2,
                (int) (MainWindow.WIDTH * 0.6),
                availableHeight
        ));
    }

    /**
     * Da die {@link ComputerScienceLevelScene} keinen komplexen
     * Zustand speichert (wie z.B. {@link MathematicsLevelScene},
     * wird keine neue Szene erstellt, sondern einfach die aktuelle
     * weiterverwendet.
     * @return {@code this}
     */
    @Override
    public LevelScene reset() {
        return this;
    }
}
