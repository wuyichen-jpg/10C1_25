package czg.objects.minigame;

import czg.objects.BaseObject;
import czg.scenes.BaseScene;
import czg.scenes.minigame.MathematicsLevelScene;
import czg.util.Images;
import czg.util.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Ein Tangram-Teil, welches für das Mathematik-Minigame
 * verwendet wird
 */
public class TangramPieceObject extends BaseObject {

    /**
     * Dateipfad des ursprünglichen Bildes
     */
    private final String SPRITE_PATH;

    private int originalWidth;
    private int originalHeight;
    /**
     * Drehung in Grad
     */
    public double rotation;
    public MathematicsLevelScene levelScene;

    private boolean isDragged = false;

    /**
     * Ein neues Tangram-Teil erstellen
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

    public static void setLevelScene(TangramPieceObject[] pieces, MathematicsLevelScene levelScene) {
        for (TangramPieceObject piece : pieces) {
            piece.levelScene = levelScene;
        }
    }

    /**
     * Schiebt die Tangram-Teile wieder zu einem Quadrat zusammen
     * @param pieces Array von {@link TangramPieceObject}s, erhalten von {@link #generatePieces()}
     * @param x X-Koordinate des Tangram-Quadrats
     * @param y Y-Koordinate des Tangram-Quadrats
     * @param width Breite des Tangram-Quadrats
     * @param height Höhe des Tangram-Quadrats
     */
    public static void generatePacked(TangramPieceObject[] pieces, int x, int y, int width, int height) {
        pieces[0].setRotation(0);
        pieces[0].x = x;
        pieces[0].y = y;
        pieces[0].width = width;
        pieces[0].height = height/2;
        pieces[0].originalWidth = pieces[0].width;
        pieces[0].originalHeight = pieces[0].height;

        pieces[1].setRotation(0);
        pieces[1].x = x;
        pieces[1].y = y;
        pieces[1].width = width/2;
        pieces[1].height = height;
        pieces[1].originalWidth = pieces[1].width;
        pieces[1].originalHeight = pieces[1].height;

        pieces[2].setRotation(0);
        pieces[2].x = x + width/2;
        pieces[2].y = y + height/2;
        pieces[2].width = width/2;
        pieces[2].height = height/2;
        pieces[2].originalWidth = pieces[2].width;
        pieces[2].originalHeight = pieces[2].height;

        pieces[3].setRotation(0);
        pieces[3].x = x;
        pieces[3].y = y + (int) (height*0.75);
        pieces[3].width = width/2;
        pieces[3].height = height/4;
        pieces[3].originalWidth = pieces[3].width;
        pieces[3].originalHeight = pieces[3].height;

        pieces[4].setRotation(0);
        pieces[4].x = x + width/2;
        pieces[4].y = y + height/4;
        pieces[4].width = width/4;
        pieces[4].height = height/2;
        pieces[4].originalWidth = pieces[4].width;
        pieces[4].originalHeight = pieces[4].height;

        pieces[5].setRotation(0);
        pieces[5].x = x + (int) (width*0.75);
        pieces[5].y = y;
        pieces[5].width = width/4;
        pieces[5].height = (int) (height*0.75);
        pieces[5].originalWidth = pieces[5].width;
        pieces[5].originalHeight = pieces[5].height;

        pieces[6].setRotation(0);
        pieces[6].x = x + width/4;
        pieces[6].y = y + height/2;
        pieces[6].width = width/2;
        pieces[6].height = height/2;
        pieces[6].originalWidth = pieces[6].width;
        pieces[6].originalHeight = pieces[6].height;
    }

    public void rotate(double degree) {
        rotation = (rotation + degree) % 360;

        double scaleX = (double) width / sprite.getWidth(null);
        double scaleY = (double) height / sprite.getHeight(null);

        Image rotatedSprite = Images.rotateImage(Images.get(SPRITE_PATH), rotation);

        Point imageCenter = new Point(x + width/2, y + height/2);

        width = rotation != 0 ? (int) (rotatedSprite.getWidth(null) * scaleX) : originalWidth;
        height = rotation != 0 ? (int) (rotatedSprite.getHeight(null) * scaleY) : originalHeight;

        sprite = rotatedSprite;

        x = imageCenter.x - width/2;
        y = imageCenter.y - height/2;
    }

    public void setRotation(double degree) {
        double currentRotation = rotation;

        rotate(degree - currentRotation);
    }

    @Override
    public void update(BaseScene scene) {
        // Aktuelle und vorherige Maus-Position abfragen
        Point mousePos = Input.INSTANCE.getMousePosition();
        Point lastMousePos = Input.INSTANCE.getLastMousePosition();
        // Diese *können* (technisch gesehen) null sein
        if(mousePos == null || lastMousePos == null)
            return;

        if(! isDragged && isClicked(false)) {
            // Wenn das Objekt angeklickt wird, verschieben wir es an oberste Stelle (z-Achse) und beginnen, es zu ziehen
            scene.objects.remove(this);
            scene.objects.add(this);

            isDragged = true;
        } else if (isDragged && !Input.INSTANCE.getMouseState(MouseEvent.BUTTON1).isDown()) {
            // Wenn die linke Maustaste losgelassen wird, wird das Objekt nicht mehr gezogen
            isDragged = false;
            // Und überprüft, ob das Puzzle gelöst ist
            levelScene.checkPuzzle();
        }

        if (isDragged) {
            // Aktualisierung der Position
            this.x += mousePos.x - lastMousePos.x;
            this.y += mousePos.y - lastMousePos.y;

            // Rotieren des Objektes
            if(Input.INSTANCE.getKeyState(KeyEvent.VK_R) == Input.KeyState.PRESSED) {
                rotate(45);
            }
        }
    }
}
