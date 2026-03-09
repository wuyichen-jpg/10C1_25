/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package czg.objects;

import czg.scenes.BaseScene;
import czg.scenes.SceneStack;

/**
 *
 * @author guest-fqz0q0
 */
public class InvisibleDoorObject extends BaseObject{
    //Initialisieren eines Ziels (Wohin soll die Tür führen?)
    public BaseScene target;
    //Initialisieren eines Ursprungs
    public BaseScene origin;
   
    public InvisibleDoorObject(int x, int y, BaseScene origin, BaseScene target){
        //Einfügen des Objektes
        //kein Bild (Objekt ist unsichtbar)
        //x und y beschreiben die Position des Objektes, diese wird in den jeweiligen Szenen festgelegt
        //Breite und Höhe entsprechen den Werten der Tür in den Bildern
        super(null, x, y, 140, 240);
        this.target = target;
        this.origin = origin;
    }
    
    @Override
    public void update(BaseScene scene) {
         if(isClicked())
            SceneStack.INSTANCE.replace(origin, target);
    }
}
