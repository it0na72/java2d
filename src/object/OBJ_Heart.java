package object;
import entity.entity;

public class OBJ_Heart extends entity
{
    public OBJ_Heart(main.panel panel) {
        super(panel);

        name = "Heart";
        image = setup("res/objects/heart_full");
        image2 = setup("res/objects/heart_half");
        image3 = setup("res/objects/heart_blank");
    }
}
