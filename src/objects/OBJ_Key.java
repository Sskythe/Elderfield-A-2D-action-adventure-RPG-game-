package src.objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends InteractableObject {
    public OBJ_Key(){
        name = "Key";
        collision = true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Assets/Object/key.png"));


        }catch (IOException e){
            System.out.println("error here");
            e.getMessage();

        }
    }

}