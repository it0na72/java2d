package object;
import entity.entity;
import main.panel;

public class OBJ_Chest extends entity
    {
        public OBJ_Chest(main.panel panel) {
            super(panel);

            name = "Chest";
            down1 = setup("/objects/chest");
        }
    }
