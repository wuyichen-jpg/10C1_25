/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package czg.scenes;

import czg.objects.BackdropObject;
import czg.objects.ButtonObject;
import czg.util.Images;

import static czg.MainWindow.WIDTH;

/**
 *
 * @author guest-rwl69f
 */
public class HausmeisterraumScene extends BaseScene{
    public HausmeisterraumScene() {
        //Einfügen des Hintergrunds
        objects.add(new BackdropObject(Images.get("/assets/background/Hausmeisterraum.png")));
        

        ButtonObject unten = new ButtonObject(
                Images.get("/assets/background/PfeilUnten.png"),
                () -> {
                    GangHausmeisterScene hausm = new GangHausmeisterScene();
                    /*
                    this.objects.remove(ExamplePlayerObject.INSTANCE);
                    tr1.objects.add(ExamplePlayerObject.INSTANCE);
                    */
                    SceneStack.INSTANCE.replace(this, hausm);
                });

        unten.x = (WIDTH/2) - (unten.width/2);
        unten.y = 440;
        objects.add(unten);
        
    }
}