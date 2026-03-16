package czg.objects;

import czg.scenes.BaseScene;
import czg.util.Input;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Ein Drag-Objekt, bestehend aus einer Position und einem Bild,
 * welches mit der Maus gezogen werden kann.
 */
public class DragObject extends BaseObject {

    /**
     * Ob das Objekt gerade gezogen wird
     */
    private boolean isDragged = false;

    /**
     * Einen neues Drag-Objekt erstellen und in die Mitte des Bildschirms platzieren.
     * Die Größe des Objektes entspricht der Größe des Bildes.
     * @param sprite Bild
     */
    public DragObject(Image sprite) {
        super(sprite);
    }

    /**
     * Einen neues Drag-Objekt erstellen und an die angegebene Stelle platzieren.
     * Die Größe des Objektes entspricht der Größe des Bildes.
     * @param sprite Bild
     * @param x X-Position
     * @param y Y-Position
     */
    public DragObject(Image sprite, int x, int y) {
        super(sprite, x, y);
    }

    /**
     * Einen neues Drag-Objekt erstellen.
     * @param sprite Bild
     * @param x X-Position
     * @param y Y-Position
     * @param width Breite
     * @param height Höhe
     */
    public DragObject(Image sprite, int x, int y, int width, int height) {
        super(sprite, x, y, width, height);
    }

    @Override
    public void update(BaseScene scene) {
        // Aktuelle und vorherige Maus-Position abfragen
        Point mousePos = Input.INSTANCE.getMousePosition();
        Point lastMousePos = Input.INSTANCE.getLastMousePosition();
        // Diese *können* (technisch gesehen) null sein
        if(mousePos == null || lastMousePos == null)
            return;

        if(! isDragged && isClicked(true)) {
            // Wenn das Objekt angeklickt wird, verschieben wir es an oberste Stelle (z-Achse) und beginnen, es zu ziehen
            scene.objects.remove(this);
            scene.objects.add(this);
            isDragged = true;
        } else if (! Input.INSTANCE.getMouseState(MouseEvent.BUTTON1).isDown()) {
            // Wenn die linke Maustaste losgelassen wird, wird das Objekt nicht mehr gezogen
            isDragged = false;
        }

        if (isDragged) {
            this.x += mousePos.x - lastMousePos.x;
            this.y += mousePos.y - lastMousePos.y;
        }
    }
}