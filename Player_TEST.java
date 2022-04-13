import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Player_TEST extends GameObject
{//Player_TEST Class
   public Player_TEST(int x,int y,Color c)
   {//Player_TEST()
      super();
   }//Player_TEST()
   
   public boolean isOnGround(ArrayList<ArrayList<GameObject>> goaY)  //Backup Parameter phrase--> ArrayList<ArrayList<GameObject>> goa
   {//isOnGround           //(ArrayList<? extends ArrayList<?>> table)
      boolean temp=false;
      
      for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
         /*for(int i=0; i<goaX.size(); i++)
         {
            if(this.getY()+13==goaY.get(j).get(i).getY+1)
            {
               temp=true;
            }
         }*/
         int index=goaY.get(j).size();
      }//Outer J loop
      
      return temp;
      
   }//isOnGround
   
   public boolean move(int deltaX, int deltaY, ArrayList<ArrayList<GameObject>> goaY)  //Backup Parameter phrase--> ArrayList<ArrayList<GameObject>> goa
   {//move
      boolean temp=true;
      for(int j=0; j<goaY.size(); j++);
      {//Outer J loop
         /*for(int i=0; i<goaX.size(); i++)
         {
            if(this.collides(goaY.get(j).get(i)))
            {
               temp=false;
            }
         }*/
      }//Outer J loop
      
      return temp;
      
   }//move
   //System.out.println("SIZE OF ARRAYLIST "+goaY.size());

   public boolean collides(ArrayList<ArrayList<GameObject>> goaY) //Backup Parameter phrase-->    //ArrayList<? extends ArrayList<?>>goaX
   {//collides
      boolean temp=false;
      for(int j=0; j<goaY.size(); j++);
      {//Outer J loop
         int index=goaY.get(j).size();
         
         //*
         for(int i=0; i<index; i++)
         {
            if(this.collides(goaY.get(j).get(i)))
            {
               temp=true;
            }
         }
         //*/
         
         
      }//Outer J loop
      
      return temp;
      
   }//collides
   
   
}//Player_TEST Class 



           


