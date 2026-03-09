/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package czg.scenes;

import czg.MainWindow;
import czg.objects.BackdropObject;
import czg.objects.ButtonObject;
import czg.objects.InvisibleDoorObject;
import czg.util.Images;

import static czg.MainWindow.HEIGHT;

/**
 *
 * @author guest-zmpzia
 */
public class MathegangScene extends BaseScene{
    public MathegangScene(){
        //Einfügen des Hintergrunds
        objects.add(new  BackdropObject(Images.get("/assets/background/Mathegang.png")));
        
        //Einfügen des unsichtbaren Tür-Objektes auf Position der Tür
        MatheraumScene mathe = new MatheraumScene();
        objects.add(new InvisibleDoorObject(MainWindow.PIXEL_SCALE * 177, MainWindow.PIXEL_SCALE * 45,this, mathe));
        
        //Buttons zum Klicken in andere Gangszenen
        ButtonObject rechts = new ButtonObject(
                Images.get("/assets/background/PfeilRechts.png"),
                () -> {
                    TreppeRechts2Scene tr2 = new TreppeRechts2Scene();
                    /*
                    this.objects.remove(ExamplePlayerObject.INSTANCE);
                    tr2.objects.add(ExamplePlayerObject.INSTANCE);
                    */
                    SceneStack.INSTANCE.replace(this, tr2);
                });

        rechts.x = 880;
        rechts.y = (HEIGHT/2) - (rechts.height/2);
        objects.add(rechts);
        
        ButtonObject links = new ButtonObject(
                Images.get("/assets/background/PfeilLinks.png"),
                () -> {
                    ErstesOGScene erstes = new ErstesOGScene();
                    /*
                    this.objects.remove(ExamplePlayerObject.INSTANCE);
                    mathe.objects.add(ExamplePlayerObject.INSTANCE);
                    */
                    SceneStack.INSTANCE.replace(this, erstes);
                });

        links.x = 9;
        links.y = (HEIGHT/2) - (links.height/2);
        objects.add(links);
        
    }
}
