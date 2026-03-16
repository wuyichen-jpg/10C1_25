package czg.scenes.minigame_scenes;

import czg.MainWindow;
import czg.objects.BaseObject;
import czg.objects.Department;
import czg.objects.minigame_objects.TangramPieceObject;
import czg.util.Images;

import java.util.Arrays;

public class MathematicsLevelScene extends LevelScene {
    public MathematicsLevelScene(int level) {
        super(Department.MATHEMATICS, level);

        objects.addAll(Arrays.asList(TangramPieceObject.PIECES));

        int size = MainWindow.HEIGHT / 2;
        double scale = Images.get("/assets/minigames/mathematics/tangram_packed.png").getWidth(null) / (double) size;
        TangramPieceObject.generatePacked((MainWindow.HEIGHT - size) / 2, (MainWindow.HEIGHT - size) / 2, size, size);

        BaseObject puzzle = new BaseObject(Images.get("/assets/minigames/mathematics/puzzle_1_1.png"), 0, 0);

        puzzle.width *= scale;
        puzzle.height *= scale;

        puzzle.x = (int) (MainWindow.WIDTH - puzzle.width - MainWindow.WIDTH*0.1);
        puzzle.y = (MainWindow.HEIGHT - puzzle.height)/2;

        objects.add(puzzle);
    }
}
