import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import java.util.*;

//this is the player object
public class MineObject extends DrawableObject{
   protected Color pokeBall;
   protected float t;
   protected int rStart;
   protected int rEnd;           //Declare members 
   protected float red;
   Random random=new Random();
   private double colorValue=0;
   private int way = 1;
   
      

   
	//takes in its position
   public MineObject(float x, float y)
   {
      super(x,y);
      
   }
   //draws itself at the passed in x and y.
   public void drawMe(float x, float y, GraphicsContext gc)
   {
      
      gc.setFill(Color.WHITE.interpolate(Color.RED, colorValue));          //interpolates the mines between red and white colors. Uses colorvalue.
      gc.fillOval(x-6,y-6,12,12);
   }
   
   
   public void advanceColor()
   {
      colorValue += 0.01 * way;
      
      if(colorValue > 1)
      {                                            //Method that cycles through the value of color applied to mines
         colorValue = 1;
         way = - 1;
      }
      if(colorValue < 0)
      {
         colorValue = 0;
         way = 1;
      }
   }

      

   
    //Create overridden .equals method for comparing mines
@Override
public boolean equals(Object o){  //Standard overridden .equals method, with mine variables featured. 

   if (!(o instanceof MineObject))
      {
         return false;
      }
   else 
      {
         MineObject mine=(MineObject) o;
         if(getX()==mine.getX()&&getY()==mine.getY())
         {
            return true;
         }         
         else 
         {
            return false;
         }
      }


}

    
    }//end MineObject Class

