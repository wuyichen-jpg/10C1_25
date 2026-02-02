/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package czg.scenes.minigame_scenes;

import czg.objects.BackdropObject;
import czg.objects.BaseObject;
import czg.scenes.*;
import czg.util.Images;

/**
 *
 * @author guest-kaafgt
 */
public class LevelSelector extends BaseScene {
    public LevelSelector() {
        objects.add(new BackdropObject(Images.get("/assets/background/example.png")));
        
        BaseObject buttonLevel1 = new BaseObject(Images.get("/assets/minigames/general/buttonLevel1.png"));
        BaseObject buttonLevel2 = new BaseObject(Images.get("/assets/minigames/general/buttonLevel2.png"));
        BaseObject buttonLevel3 = new BaseObject(Images.get("/assets/minigames/general/buttonLevel2.png"));
    }
}
