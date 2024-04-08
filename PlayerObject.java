import javafx.scene.paint.*;
import javafx.scene.canvas.*;

//this is the player object
public class PlayerObject extends DrawableObject{

   
	//takes in its position
   public PlayerObject(float x, float y)
   {
      super(x,y);
   }
   //draws itself at the passed in x and y.
   public void drawMe(float x, float y, GraphicsContext gc)
   {
      gc.setFill(Color.BLACK);
      gc.fillOval(x-14,y-14,27,27);             //Specs for drawing
      gc.setFill(Color.LIGHTGRAY);
      gc.fillOval(x-13,y-13,25,25);
   }
    }//end PlayerObject Class

