/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package czg.scenes.minigame_scenes;

import czg.scenes.*;

/**
 *
 * @author guest-kaafgt
 */
public class MinigameScene extends BaseScene {
    BaseScene levelSelector;
    BaseScene level1;
    BaseScene level2;
    BaseScene level3;
    
    //Item reward;
    
    public MinigameScene(BaseScene levelSelector, BaseScene level1, BaseScene level2, BaseScene level3) {
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        //this.reward = reward;
    }
    
    public void startMinigame() {
        SceneStack.INSTANCE.push(levelSelector);
    }
}
