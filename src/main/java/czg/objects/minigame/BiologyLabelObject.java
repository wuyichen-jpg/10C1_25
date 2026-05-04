package czg.objects.minigame;

import czg.objects.BaseObject;
import czg.objects.Department;
import czg.util.Draw;

import java.awt.*;

//Ein Label als BaseObject
public class BiologyLabelObject extends BaseObject {

    public String name;
    public boolean selected;

    //name des Labels und by default immer laber nicht selected bei Levelstart
    public BiologyLabelObject(String name, int x, int y, int width, int height) {
        super(null, x, y, width, height);
        this.name = name;
        this.selected = false;
    }

    //Zeichnen des Labels, Füllfarbe abhängig von Status selected (falls true, dann grau, bei false weiß)
    @Override
    public void draw(Graphics2D g) {
        if (selected) {
            g.setColor(Color.LIGHT_GRAY);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.setFont(Draw.FONT_INFO.deriveFont(13f));
        g.drawString(name, x + 5, y + (height / 2)+3 );
    }
}
