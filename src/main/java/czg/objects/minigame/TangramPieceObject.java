package czg.objects.minigame;

import czg.MainWindow;
import czg.objects.BaseObject;
import czg.scenes.BaseScene;
import czg.scenes.minigame.MathematicsLevelScene;
import czg.util.Images;
import czg.util.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Ein Tangram-Stein, welcher für das Mathematik-Minigame
 * verwendet wird
 */
public class TangramPieceObject extends BaseObject {

    /**
     * Dateipfad des ursprünglichen Bildes
     */
    private final String SPRITE_PATH;

    /**
     * Ausgangsgröße des Steines
     */
    public int originalWidth;
    public int originalHeight;

    /**
     * Drehung in Grad
     */
    public double rotation;
    /**
     * LevelScene in der sich der Stein befindet
     */
    public MathematicsLevelScene levelScene;

    private boolean isDragged = false;

    /**
     * Einen neuen Tangram-Stein erstellen
     * @param id Bestimmt die zu ladende Bilddatei
     */
    private TangramPieceObject(int id) {
       super(Images.get("/assets/minigames/mathematics/tangram_piece_%02d.png".formatted(id)));
       this.SPRITE_PATH = "/assets/minigames/mathematics/tangram_piece_%02d.png".formatted(id);
       this.originalWidth = sprite.getWidth(null);
       this.originalHeight = sprite.getHeight(null);
       this.rotation = 0;
       this.levelScene = null;
    }

    /**
     * Erstellen eines Arrays, das alle Tangram-Steine enthält
     * @return Vollständiges Array an Tangram-Steinen
     */
    public static TangramPieceObject[] generatePieces() {
        return new TangramPieceObject[] {
            new TangramPieceObject(0),
            new TangramPieceObject(1),
            new TangramPieceObject(2),
            new TangramPieceObject(3),
            new TangramPieceObject(4),
            new TangramPieceObject(5),
            new TangramPieceObject(6)
        };
    }

    /**
     * Festlegen der LevelScene aller Tangram-Steine eines Arrays
     * @param pieces Die Steine, deren LevelScene gesetzt wird
     * @param levelScene Die LevelScene
     */
    public static void setLevelScene(TangramPieceObject[] pieces, MathematicsLevelScene levelScene) {
        for (TangramPieceObject piece : pieces) {
            piece.levelScene = levelScene;
        }
    }

    /**
     * Drehung in Grad
     * @param degree Der zu drehende Winkel
     */
    public void rotate(double degree) {
        // Drehung + Normalisierung der Rotation (0.0 bis 360.0)
        rotation = (rotation + degree) % 360;
        if (rotation < 0) rotation += 360;

        // Skalierung des Bildes berechnen
        double scaleX = (double) width / sprite.getWidth(null);
        double scaleY = (double) height / sprite.getHeight(null);

        // Bild neu laden und rotieren
        Image rotatedSprite = Images.rotateImage(Images.get(SPRITE_PATH), rotation);

        // Mittelpunkt des Bildes berechnen
        Point imageCenter = new Point(x + width/2, y + height/2);

        // Größe aktualisieren
        // Falls der Tangram-Stein die ausgangs Rotation besitzt (0) wird die Größe auf die Ausgangsgröße gesetzt
        width = rotation != 0 ? (int) (rotatedSprite.getWidth(null) * scaleX) : originalWidth;
        height = rotation != 0 ? (int) (rotatedSprite.getHeight(null) * scaleY) : originalHeight;

        // Bild des Tangram-Steins aktualisieren
        sprite = rotatedSprite;

        // Tangram-Stein verschieben, sodass der Bildmittelpunkt sich nicht verändert
        x = imageCenter.x - width/2;
        y = imageCenter.y - height/2;
    }

    /**
     * Automatische Drehung, sodass der Stein eine gewünschte Rotation erhält
     * @param degree Die gewünschte Rotation in Grad
     */
    public void setRotation(double degree) {
        double currentRotation = rotation;

        rotate(degree - currentRotation);
    }

    /**
     * Verschiebung und Rotation des Steines
     * @param scene Die Szene, in welcher sich das Objekt befindet
     */
    @Override
    public void update(BaseScene scene) {
        // Aktuelle und vorherige Maus-Position abfragen
        Point mousePos = Input.INSTANCE.getMousePosition();
        Point lastMousePos = Input.INSTANCE.getLastMousePosition();
        // Diese *können* (technisch gesehen) null sein
        if(mousePos == null || lastMousePos == null)
            return;

        if (!isDragged && !levelScene.isDragging && isClicked(false)) {
            // Wenn das Objekt angeklickt wird und noch kein Stein gezogen wird,
            // verschieben wir es an oberste Stelle (z-Achse) und beginnen, es zu ziehen
            scene.objects.remove(this);
            scene.objects.add(this);

            isDragged = true;
            // Außerdem teilen wir der levelScene mit, dass jetzt ein Stein gezogen wird
            levelScene.isDragging = true;
        } else if (isDragged && !Input.INSTANCE.getMouseState(MouseEvent.BUTTON1).isDown()) {
            // Wenn die linke Maustaste losgelassen wird, wird das Objekt nicht mehr gezogen,
            isDragged = false;
            // Der levelScene mitgeteilt, dass kein Stein mehr gezogen wird
            levelScene.isDragging = false;
            // Und überprüft, ob das Puzzle gelöst ist
            levelScene.checkPuzzle();
        }

        if (isDragged) {
            // Berechnung der neuen Position
            int newPosX = x + mousePos.x - lastMousePos.x;
            int newPosY = y + mousePos.y - lastMousePos.y;
            // Überprüfung, ob die neue Position innerhalb des Bildschirms liegt
            if (
                newPosX > 0 && newPosX + width < MainWindow.WIDTH &&
                newPosY > 0 && newPosY + height < MainWindow.HEIGHT
            ) {
                // Aktualisierung der Position
                x = newPosX;
                y = newPosY;
            }

            // Rotieren des Objektes, falls 'R' gedrückt wird
            if(Input.INSTANCE.getKeyState(KeyEvent.VK_R) == Input.KeyState.PRESSED) {
                rotate(45);
            }
        }
    }
}
