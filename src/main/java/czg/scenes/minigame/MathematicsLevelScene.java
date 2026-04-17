package czg.scenes.minigame;

import czg.MainWindow;
import czg.minigame.MathematicsPuzzle;
import czg.objects.BaseObject;
import czg.objects.Department;
import czg.objects.minigame.TangramPieceObject;
import czg.util.Images;

import java.util.Arrays;

/**
 * Level des Mathematik-Minigames, in welchem ein Tangram-Puzzle
 * gelegt werden muss
 */
public class MathematicsLevelScene extends LevelScene {
    private final TangramPieceObject[] PIECES;
    private final MathematicsPuzzle PUZZLE;
    private final int PUZZLE_X;
    private final int PUZZLE_Y;
    private final int PUZZLE_WIDTH;
    private final int PUZZLE_HEIGHT;

    /**
     * Ob in der Szene ein Tangram-Stein gezogen wird
     */
    public boolean isDragging = false;

    /**
     * Neue Level-Szene erstellen
     * @param level Entweder {@code 0}, {@code 1} oder {@code 2}
     */
    public MathematicsLevelScene(int level) {
        super(Department.MATHEMATICS, level);

        // Zufällig eines der drei für dieses Level verfügbaren
        // Puzzles wählen
        this.PUZZLE = MathematicsPuzzle.getPuzzle(level);

        BaseObject puzzleObject = new BaseObject(PUZZLE.SPRITE, 0, 0);

        // Skalierungsfaktor der Steine und somit auch des Puzzles berechnen
        double scale = Images.get("/assets/minigames/mathematics/tangram_packed.png").getWidth(null) / (double) (MainWindow.HEIGHT/2);

        // Puzzle skalieren
        puzzleObject.width = (int) (puzzleObject.width * scale);
        puzzleObject.height = (int) (puzzleObject.height * scale);

        this.PUZZLE_WIDTH = puzzleObject.width;
        this.PUZZLE_HEIGHT = puzzleObject.height;
        this.PUZZLE_X = (int) (MainWindow.WIDTH - PUZZLE_WIDTH - MainWindow.WIDTH*0.1);
        this.PUZZLE_Y = (MainWindow.HEIGHT - PUZZLE_HEIGHT)/2;

        puzzleObject.x = PUZZLE_X;
        puzzleObject.y = PUZZLE_Y;

        objects.add(puzzleObject);

        // Steine erstellen
        this.PIECES = TangramPieceObject.generatePieces();

        TangramPieceObject.setLevelScene(PIECES, this);

        PUZZLE.reset(PIECES, MainWindow.HEIGHT / 4, MainWindow.HEIGHT / 4, MainWindow.HEIGHT/2, PUZZLE_X, PUZZLE_Y, PUZZLE_WIDTH, PUZZLE_HEIGHT);

        objects.addAll(Arrays.asList(PIECES));
    }

    /**
     * Diese Art von Level speichert einen komplexen Zustand,
     * weshalb es am einfachsten ist, die Szene komplett neu
     * zu erstellen, um das Level zurückzusetzen
     * @return Eine <b>neue</b> Szene
     */
    @Override
    public LevelScene reset() {
        return new MathematicsLevelScene(this.LEVEL);
    }

    /**
     * Prüft, ob das Tangram-Puzzle richtig gelöst wurde. Ist
     * dies der Fall, wird {@link #levelWon()} aufgerufen.
     */
    public void checkPuzzle() {
        if(PUZZLE.isSolutionValid(PUZZLE_X, PUZZLE_Y, PUZZLE_WIDTH)) {
            levelWon();
        }
    }
}
